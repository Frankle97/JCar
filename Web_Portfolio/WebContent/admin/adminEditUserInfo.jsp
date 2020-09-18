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
	<div class="container" style="height:500px">
	<form action="<%=request.getContextPath()%>/changeInfo.do" method="post" id="act">
	<fieldset>
	<div class="form-group">
	<label for="name" style="margin-left:30%;">이름</label>
	<input type="button" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getName()%>">
	</div>
	<div class="form-group">
	<label for="contact" style="margin-left:30%;">연락처</label>
	<input type="text" name="contact" id="contact" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getContact()%>">
	</div>
	<div class="form-group">
	<label for="text" style="margin-left:30%;">생년월일</label>
	<input type="text" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getBirth()%>">
	</div>
	<div class="form-group form-inline">
	<label for="email" style="margin-left:30%;">이메일</label><br/>
	<input type="text" name="email" id="email" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getEmail()%>">
	<a href="${pageContext.request.contextPath}/sendEmailView.do?id=<%=dto.getId()%>" class="btn btn-default btn-lg">이메일 보내기</a>
	</div>
	<div class="form-group">
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<input type="submit" value="수정" class="btn btn-danger btn-lg" style="margin-left:30%"><a href="<%=request.getContextPath()%>/myPageView.do?id=admin" class="btn btn-default btn-lg" style="margin-left:1%">돌아가기</a><a href="<%=request.getContextPath()%>/userRemoveForAdmin.do?id=<%=dto.getId()%>" class="btn btn-default btn-lg" style="margin-left:11%">회원탈퇴</a>
	</div>
	
	</fieldset>
	</form>
	</div>
	
<%@include file="../inc/footer.jsp"%>