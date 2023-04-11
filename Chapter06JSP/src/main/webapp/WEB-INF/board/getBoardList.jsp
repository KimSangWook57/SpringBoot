<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
									"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
<h1>게시글 목록</h1>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<table class="table table-striped" border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th width="100">번호</th>
	<th width="200">제목</th>
	<th width="150">작성자</th>
	<th width="150">등록일</th>
	<th width="100">조회수</th>
</tr>
<c:forEach var="board" items="${ boardList }">
<tr>
	<td>${ board.seq }</td>
	<td align="left"><a href="getBoard?seq=${ board.seq }">${ board.title }</a></td>
	<td>${ board.writer }</td>
	<td>
		<fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd"></fmt:formatDate>
	</td>
	<td>${ board.cnt }</td>
</tr>
</c:forEach>
</center>
</table>
<br>
<a href="insertBoard">새글 등록</a>

</body>
</html>