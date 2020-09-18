<%@page import="com.jyeob.dto.MemberDto"%>
<%@page import="com.jyeob.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>가입완료</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/joinClient.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
%>
	<div class="container text-center">
	<a href="${pageContext.request.contextPath}/car.do"><img src="<%=request.getContextPath()%>/images/logo.png" alt="홈으로 가기"></a>

	<p><img src="<%=request.getContextPath()%>/images/join003.png" alt="이미지를 찾을 수 없습니다."></p>
	<h4><strong>회원가입이 정상적으로 완료되었습니다.</strong></h4>
	<p>지금부터 아래의 정보로 저희의 다양한 서비스를 이용하실 수 있습니다.</p>
	<br/>
	<p>아이디 : <%=dto.getId()%></p>
	<p>이메일 : <%=dto.getEmail() %></p>
	<a href="<%=request.getContextPath()%>/car.do"><img src="<%=request.getContextPath()%>/images/act_goHome.png" alt="이미지를 찾을 수 없습니다."></a>
	</div>
	<%
	session.removeAttribute("kakao_name");
	session.removeAttribute("email");
	session.removeAttribute("kakao");
	%>
<%@include file="../inc/footer.jsp"%>
