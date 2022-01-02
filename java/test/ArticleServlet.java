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
	
	SqlMapper mapper = new SqlMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 공통 코드 처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 
		
		String action = request.getParameter("action");
		
		// 게시물 목록 기능
		if(action.equals("list")) {
					
			ArrayList<Article> articles = mapper.getArticleList();
			request.setAttribute("articles", articles);
			
			// JSP에 데이터 보내기
			RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
			rd.forward(request, response);		
			
		} else if(action.equals("add")) {
			
			// 게시물 등록 기능
			RequestDispatcher rd = request.getRequestDispatcher("board/addForm.jsp");
			rd.forward(request, response);
			
		} else if(action.equals("doAdd")) {
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			Article a = new Article(title, body, 1, "20211226100000");
			
			mapper.insertArticle(a);
			
			// 재요청 -> 특정 url 작성시 해당 url 페이지로 재요청
			// 리다이렉팅 => 데이터 못보냄.
			response.sendRedirect("/article?action=list");
			
		} else if(action.equals("detail")) {
			
			// 상세보기 페이지 보여준다.
			// 포워딩 => 상세보기 페이지로..
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			
			Article article = mapper.getArticleById(idx);

			request.setAttribute("article", article);
			
			RequestDispatcher rd = request.getRequestDispatcher("board/detail.jsp");
			rd.forward(request, response);		
		} else if(action.equals("doDelete")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			mapper.deleteArticle(idx);
			
			response.sendRedirect("/article?action=list");
		} else if(action.equals("update")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));	
			
			Article article = mapper.getArticleById(idx);
			request.setAttribute("article", article);
			
			RequestDispatcher rd = request.getRequestDispatcher("board/updateForm.jsp");
			rd.forward(request, response);
			
		} else if(action.equals("doUpdate")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));	
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			Article a = new Article(idx, title, body, 1, "20211226100000");
			mapper.updateArticle(a);
			
			response.sendRedirect("article?action=detail&idx=" + idx);
			
		}

	}

}
