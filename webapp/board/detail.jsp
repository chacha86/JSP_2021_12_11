<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
수정 <a href="/article?action=doDelete&idx=${ article.idx }">삭제</a>
</body>
</html>