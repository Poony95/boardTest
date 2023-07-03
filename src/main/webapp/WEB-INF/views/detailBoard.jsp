<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
</head>
<body>
<h2>상세조회</h2>
<hr>
	글번호 : ${b.no } <br>
	글제목 : ${b.title }<br>
	작성자 : ${b.writer }<br>
	글암호 : ${b.pwd }<br>
	글내용 : <br>
	<textarea rows="10" cols="60" readonly="readonly">${b.content }</textarea> <br>
	작성일 : ${b.regdate }<br>
	조회수 : ${b.hit }<br>
	<img src="/upload/${b.fname }"><br>
	<a href="listBoard">게시글 목록</a>
	<a href="insertBoard?no=${b.no }">답글 작성</a>
	<a href="updateBoard?no=${b.no }">수정</a>
	<a href="deleteBoard?no=${b.no }">삭제</a>
	
</body>
</html>