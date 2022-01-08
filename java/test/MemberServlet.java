package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Article;
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
		} else if(action.equals("loginForm")) {
			loginForm(request, response);
		} else if(action.equals("doLogin")) {
			doLogin(request, response);
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		
		Member loginedMember = sqlMapper.getMemberLoginIdAndLoginPw(loginId, loginPw);
		
		if(loginedMember == null) {
			sendView(request, response, "board/error/failedLogin.jsp", FORWARD);
		} else {
			// 로그인 성공 처리
			
			ArrayList<Article> articles = sqlMapper.getArticleList();
			
			// request에 저장
			//request.setAttribute("loginedMemberName", loginedMember.getNickname());
			
			// session에 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberName", loginedMember.getNickname());
			
			request.setAttribute("articles", articles);
			sendView(request, response, "board/article/list.jsp", FORWARD);
		}		
	}

	private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendView(request, response, "board/member/loginForm.jsp", FORWARD);
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
