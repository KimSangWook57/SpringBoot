<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
									"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>새글등록</title>
</head>
<body>
<center>
<h3>새글 등록하기</h3>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<hr>
<form action="insertBoard" method="post">
<table class="table table-striped" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="70">제목</td>
		<td align="left"><input type="text" name="title" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td align="left"><input type="text" name="writer" size="10" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value= " 새글 등록 " /></td>
	</tr>
</table>
</form>
<hr>
</center>
</body>
</html>