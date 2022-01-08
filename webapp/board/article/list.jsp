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
<hr>
<c:choose>
	<c:when test="${ loginedMemberName == null }">
		<div>
			<a href="/member?action=loginForm">로그인 </a>
			<a href="/member?action=addForm">회원가입</a> 
			<a href="#">아이디 </a>
			<a href="#">비밀번호 찾기</a>
		</div>
	</c:when>
	<c:otherwise>
		<div>
			${ loginedMemberName }님 안녕하세요!! 
			<a href="#">로그아웃</a>
		</div>
	</c:otherwise>
</c:choose>
<hr>
<h1>게시물 목록</h1>

<c:forEach items="${ articles }" var="a">
	<div>
		번호 : ${ a.idx }
		<a href="/article?action=detail&idx=${ a.idx }">제목 : ${ a.title }</a>
		작성자 : ${ a.nickname }
		작성일 : ${ a.regDate }
	</div>
	<hr>
</c:forEach>
<a href="/article?action=add">글쓰기</a>

</body>
</html>