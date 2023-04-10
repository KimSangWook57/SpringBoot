<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<meta charset="EUC-KR">
<title>글 목록</title>
</head>
<body>
<center>
<h1>게시글 목록</h1>
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">번호</th>
	<th bgcolor="orange" width="100">조회수</th>
	<th bgcolor="orange" width="100">제목</th>
	<th bgcolor="orange" width="100">작성일</th>
	<th bgcolor="orange" width="100">제목</th>
	<th bgcolor="orange" width="100">아이디</th>
</tr>
<c:forEach var="board" items="${ boardList }">
<tr>
	<td>${ board.seq }</td>
	<td>${ board.cnt }</td>
	<td>${ board.content }</td>
	<td>${ board.createDate }</td>
	<td>${ board.title }</td>
	<td>${ board.member_id }</td>
</tr>
</c:forEach>

</table>
<br>
<a href="insertBoard">새글 등록</a>
</center>
</body>
</html>