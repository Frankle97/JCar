<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<% MemberDto dto = new MemberDao().findAccount(request.getParameter("id")); %>
	<div class="container">
	<div>
	<h3 style="color:black"><strong>관리자전용 페이지입니다.</strong></h3>
	</div>
	<table class="table table-bordered" style="margin-top:1%; height:50px;">
	<tr><th style="background:#fdf5f4;"><a href="${pageContext.request.contextPath}/adminPage.do?id=${param.id}" style="color:#DC232D;">회원 관리</a></th><th><a href="${pageContext.request.contextPath}/adminSellerList.do?id=${param.id}">매물 현황</a></th></tr>
	</table>
	</div>
	<div class="container userlist" style="height:500px">
	<table class="table table-striped">
	<caption>총 회원 수 <span style="color:#dc232d; font-weight:bold;">${cnt}명</span></caption>
	<thead>
	<tr><th scope="col">번호</th><th scope="col">회원분류</th><th scope="col">이름</th><th scope="col">아이디</th><th scope="col">연락처</th><th scope="col">이메일</th><th scope="col">아이피</th><th scope="col">가입날짜</th></tr>
	</thead>
	<tbody>
	<c:forEach var="users" items="${list}" varStatus="status">
	<tr><td>${users.no}</td><td>${users.category}</td><td>${users.name}</td><td><a href="${pageContext.request.contextPath}/adminEditUser.do?id=${users.id}" style="font-weight:bold; color:#dc232d;">${users.id}</a></td><td>${users.contact}</td><td>${users.email}</td><td>${users.ip}</td><td>${users.date.substring(0,10)}</td></tr>
	</c:forEach>
	</tbody>
	</table>
	</div>
	
</body>
</html>