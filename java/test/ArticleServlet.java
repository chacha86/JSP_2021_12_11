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
import board.model.SqlMapper;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {

	final int FORWARD = 1;
	final int REDIRECT = 2;
	
	SqlMapper mapper = new SqlMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 공통 코드 처리
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");

		// 게시물 목록 기능
		if (action.equals("list")) {
			list(request, response);
			
		} else if (action.equals("add")) {
			// 로그인 했을 때만 사용 가능하도록
			add(request, response);

		} else if (action.equals("doAdd")) {
			doAdd(request, response);

		} else if (action.equals("detail")) {
			detail(request, response);
			
		} else if (action.equals("doDelete")) {
			delete(request, response);

		} else if (action.equals("update")) {
			update(request, response);

		} else if (action.equals("doUpdate")) {
			doUpdate(request, response);
			
		} else if (action.equals("search")) {
			search(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");		
		ArrayList<Article> articles =  mapper.getSearchedList(keyword);
		
		request.setAttribute("articles", articles);
		sendView(request, response, "board/article/list.jsp", FORWARD);
		
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		Article a = new Article(idx, title, body, 1, "20211226100000");
		mapper.updateArticle(a);

		String path = "article?action=detail&idx=" + idx;
		sendView(request, response, path, REDIRECT);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));

		Article article = mapper.getArticleById(idx);
		request.setAttribute("article", article);
		sendView(request, response, "board/article/updateForm.jsp", FORWARD);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.deleteArticle(idx);

		sendView(request, response, "/article?action=list", REDIRECT);
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상세보기 페이지 보여준다.
		// 포워딩 => 상세보기 페이지로..

		int idx = Integer.parseInt(request.getParameter("idx"));

		Article article = mapper.getArticleById(idx);

		request.setAttribute("article", article);		
		sendView(request, response, "board/article/detail.jsp", FORWARD);
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		Article a = new Article(title, body, 1, "20211226100000");

		mapper.insertArticle(a);
		sendView(request, response, "/article?action=list", REDIRECT);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 검증
		HttpSession session = request.getSession();
		
		String loginedMemberName = (String)session.getAttribute("loginedMemberName");
		
		if (loginedMemberName == null) {
			sendView(request, response, "board/error/doNeedLogin.jsp", FORWARD);
			return ;
		}
		
		sendView(request, response, "board/article/addForm.jsp", FORWARD);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Article> articles = mapper.getArticleList();
		request.setAttribute("articles", articles);

		sendView(request, response, "board/article/list.jsp", FORWARD);
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
