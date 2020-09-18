<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<% MemberDto dto = new MemberDao().findAccount(request.getParameter("id")); %>
<div class="container">
	<div>
	<h3 style="color:black"><strong>반갑습니다. <%=dto.getName()%>고객님</strong></h3>
	</div>

	<table class="table table-bordered" style="margin-top:1%; height:50px;">
	<tr><th><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>">내차사기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>">내차팔기 주문관리</a></th><th style="background:#fdf5f4;"><a href="<%=request.getContextPath()%>/editInfo.do?id=<%=dto.getId()%>" style="color:#DC232D;">회원정보 수정</a></th></tr>
	</table>
	</div>
	<div class="container panel" style="height:500px">
	<h3 style="margin-left:30%; color:black;">회원탈퇴</h3>
	<p style="line-height: 1.4em; margin-left:30%; color:#555; font-family: 'Roboto', 'notokr','NanumGothic','나눔고딕','Malgun Gothic','Myriad Pro',Arial,'돋움','굴림','Gulim'; font-weight:normal;">고객님의 개인정보 보호등을 위해 본인확인후 회원탈퇴를 하실수 있습니다.
<br/>앞으로 더 나은 서비스를 위해 노력하겠습니다.</p>
	<hr/>
	<p style="margin-left:30%; color:black; font-size:20px;">실명확인</p>
	<form action="<%=request.getContextPath()%>/removeAccount.do" method="post" id="act">
	<fieldset>
	<div class="form-group">
	<label for="name" style="margin-left:30%;">아이디</label>
	<input type="button" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getId()%>" disabled>
	</div>
	<div class="form-group">
	<label for="password" style="margin-left:30%;">비밀번호</label>
	<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력하세요." style="width:35%; margin-left:30%; height:40px; text-align:left;">
	</div>
	<div class="form-group">
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<input type="submit" value="회원탈퇴" class="btn btn-danger btn-lg" style="margin-left:30%"><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>" class="btn btn-default btn-lg" style="margin-left:1%">취소</a>
	</div>
	
	</fieldset>
	</form>
	</div>
<%@include file="../inc/footer.jsp"%>