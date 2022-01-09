<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 상세</h1>
<hr>
<div>
	<div>
		제목 : ${ article.title }
	</div>
	<hr>
	<div>
		내용 : ${ article.body }
	</div>
	<hr>
	<div>
		작성자 : ${ article.nickname }
	</div>
</div>
<hr>
<a href="/article?action=update&idx=${ article.idx }">수정</a> <a href="/article?action=doDelete&idx=${ article.idx }">삭제</a>
<hr>
<h3>댓글</h3>
<hr>
<div>
	<c:forEach items="${replies}" var="r">
		<div>
			${ r.nickname }<br>
			${ r.body }<br>
			${ r.regDate }<br>
		</div>
		<hr>		
	</c:forEach>
</div>
<hr>
<form action="article">
	<div>
		${ loginedMemberName }
	</div>
	<div>
		<input type="text" name="rbody" placeholder="댓글을 남겨보세요" />
		<input type="hidden" name="action" value="addReply" />
		<input type="hidden" name="parentNo" value="${ article.idx }" />
		<input type="submit" value="등록" />
	</div>
</form>
</body>
</html>