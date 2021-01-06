<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원선택</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

	<div class="container text-center">
	<a href="${pageContext.request.contextPath}/car.do"><img src="<%=request.getContextPath()%>/images/logo.png" alt="홈으로 가기"></a>
	<p style="margin-top:5%;"><img src="${pageContext.request.contextPath}/images/회원선택.png" alt="회원선택 이미지"></p>
	<a href="${pageContext.request.contextPath}/joinTerms.do?category=개인회원"><img src="${pageContext.request.contextPath}/images/개인회원.png" alt="개인회원 이미지"></a><br/>
	<a href="${pageContext.request.contextPath}/joinTerms.do?category=매매회원"><img src="${pageContext.request.contextPath}/images/매매회원.png" alt="매매회원 이미지"></a>
	</div>
<%@include file="../inc/footer.jsp"%>