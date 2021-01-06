<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> 
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소를 찾을 수 없습니다.</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container panel panel-danger">
	<h3 class="panel-heading">경로를 찾을 수 없습니다. 관리자에게 문의해주세요.</h3>
	<p><a href="<%=request.getContextPath()%>/car.do" class="btn btn-danger">돌아가기</a></p>
	</div>
</body>
</html>