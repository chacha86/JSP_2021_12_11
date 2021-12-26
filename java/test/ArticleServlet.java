package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Article;
import board.model.SqlMapper;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 공통 코드 처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 
		
		SqlMapper mapper = new SqlMapper();
		ArrayList<Article> articles = mapper.getArticleList();
		request.setAttribute("articles", articles);
		
		// JSP에 데이터 보내기
		RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
		rd.forward(request, response);
		
		
		
	}

}
