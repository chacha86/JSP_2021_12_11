<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.SqlMapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="board.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 목록</h1>
<c:forEach items="${ articles }" var="a">
	<div>
		번호 : ${ a.idx }
		제목 : ${ a.title }
		작성자 : ${ a.nickname }
		작성일 : ${ a.regDate }
	</div>
	<hr>
</c:forEach>
</body>
</html>