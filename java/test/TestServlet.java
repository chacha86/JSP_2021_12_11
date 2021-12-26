package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 이 서블릿을 실행시키고 싶을 때 요청할 URL
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		
		if(action.equals("gugu")) {
			gugu(request, response);		
			
		} else if(action.equals("login")) {
			login(request, response);
			
		} else if(action.equals("test")) {
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body>");
			for(int i = 0; i < 10; i++) {
				out.println("	hello~");				
			}
			out.println("</body>");
			out.println("</html>");
		}
		
	}

	
	
	
	
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		
		if(loginId.equals("hong123") && loginPw.equals("h1234")) {
			out.println("홍길동님 안녕하세요!");
		} else if(loginId.equals("lim123") && loginPw.equals("l1234")) {
			out.println("임꺽정님 안녕하세요!");
		} else {
			out.println("잘못된 회원정보입니다.");
		}
		
	}

	private void gugu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		String param = request.getParameter("dan");
		
		int dan = Integer.parseInt(param);
		
		for(int i = 1; i < 10; i++) {
			out.println(dan + " X " + i + " = " + dan * i + "<br>");
		}	
		
	}

}
