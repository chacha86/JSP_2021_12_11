package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.member.Member;
import board.model.SqlMapper;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {

	final int FORWARD = 1;
	final int REDIRECT = 2;
	
	SqlMapper sqlMapper = new SqlMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공통 코드 처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");

		if (action.equals("addForm")) {
			addForm(request, response);

		} else if(action.equals("doAdd")) {
			doAdd(request, response);
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");
		
		Member member = new Member(loginId, loginPw, nickname);
		sqlMapper.insertMember(member);
		
		sendView(request, response, "article?action=list", REDIRECT);
		
	}

	private void addForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		sendView(request, response, "/board/member/addForm.jsp", FORWARD);
		
	}

	
	private void sendView(HttpServletRequest request, HttpServletResponse response, String path, int sendFlag) throws ServletException, IOException {
		
		if(sendFlag == 1) { // forwarding
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} else { // redirecting
			response.sendRedirect(path);
		}
		
	}

}
