<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
									"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="EUC-KR">
<title>글 상세</title>
</head>
<body>
<center>
<h1>게시글 상세</h1>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<hr>
<form action="updateBoard" method="post">
<input name="seq" type="hidden" value="${ board.seq }" />
<table class="table table-striped" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="70">제목</td>
		<td align="left"><input name="title" type="text" value="${ board.title }" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td align="left">${ board.writer }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td align="left"><textarea name="content" rows="10" cols="40">${ board.content }</textarea></td>
	</tr>
	<tr>
		<td>등록일</td>
		<td align="left">
			<fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd"></fmt:formatDate>
		</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td align="left">${ board.cnt }</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="글 수정" /></td>
	</tr>
</table>	
</form>
<hr>
<a href="insertBoardView">글등록</a>&nbsp;&nbsp;&nbsp;
<a href="deleteBoard?seq=${ board.seq }">글삭제</a>&nbsp;&nbsp;&nbsp;
<a href="getBoardList">글목록</a>
</center>
</body>
</html>