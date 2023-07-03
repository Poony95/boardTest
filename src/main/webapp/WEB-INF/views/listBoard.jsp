<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
<h2>게시글 목록</h2>
<hr>
<table border="1" width=80% >
	<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>작성자</th>
	</tr>
	<c:forEach var="b" items="${list }">
		<tr>
			<td>${b.no }</td>
			<td>
				<c:if test="${b.b_level > 0 }">
					<c:forEach begin="1" end="${b.b_level }">
						&nbsp;&nbsp;
					</c:forEach>
					==>
				</c:if>
			<a href="detailBoard?no=${b.no }">${b.title }</a></td>
			<td>${b.writer }</td>
		</tr>
	</c:forEach>
</table>
<a href="insertBoard">게시글 작성</a>
</body>
</html>