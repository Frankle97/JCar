<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	
	<div class="container">
	<h4>아이디 조회결과, 입력하신 정보와 <br/>일치하는 아이디는 아래와 같습니다.</h4>
	<table class="table table-bordered" style="width:30%;">
	<tbody>
	<tr><th>아이디</th><th>가입일</th></tr>
	<tr><td><%=request.getParameter("id")%></td><td><%=request.getParameter("date")%></td></tr>
	</tbody>
	</table>
	<a href="<%=request.getContextPath()%>/loginView.do" class="btn btn-default">돌아가기</a>
	</div>
</body>
</html>