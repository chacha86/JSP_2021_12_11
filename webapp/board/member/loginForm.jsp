<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/member">
		<div>
			<input type="text" name="loginId" placeholder="아이디" />
		</div>
		<div>
			<input type="password" name="loginPw" placeholder="비밀번호" />
		</div>
		<input type="hidden" name="action" value="doLogin" /> <input
			type="submit" value="등록" />
	</form>
</body>
</html>