<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시글 삭제</h2>
<hr>
<form action="deleteBoard" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${b.no }">
비밀번호 입력 : <input type="password" name="pwd"><br>
<input type="hidden" name="fname" value="${b.fname }">
<input type="submit" value="삭제">
</form>
</body>
</html>