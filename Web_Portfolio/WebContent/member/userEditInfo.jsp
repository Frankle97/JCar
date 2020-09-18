<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
%>
	<div class="container">
	<div>
	<h3 style="color:black"><strong>반갑습니다. <%=dto.getName()%>님</strong></h3>
	</div>

	<table class="table table-bordered" style="margin-top:2%; height:50px;">
	<tr><th><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>">내차사기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>">내차팔기 주문관리</a></th><th style="background:#fdf5f4;"><a href="#edit_myInfo" style="color:#DC232D;">회원정보 수정</a></th></tr>
	</table>
	</div>
	
	<div class="container" style="height:500px">
	<form action="<%=request.getContextPath()%>/CheckAcs.do" method="post" id="act">
	<fieldset>
	<div class="form-group" style="margin-top:3%">
	<label for="id" style="margin-left:30%;">아이디</label>
	<input type="button" class="form-control" style="width:35%; margin-left:30%; height:50px; text-align:left;" value="<%=dto.getId()%>" disabled>
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	</div>
	<div class="form-group" style="margin-top:3%">
	<label for="password" style="margin-left:30%;">비밀번호</label>
	<input type="password" name="password" id="password" class="form-control" style="width:35%; margin-left:30%; height:50px;">
	</div>
	<div class="form-group" style="margin-top:3%">
	<input type="submit" value="확인" class="btn btn-danger btn-lg" style="margin-left:30%">
	</div>
	</fieldset>
	</form>
	</div>
	
	
	<script>
	$(function(){
		$("#act").submit(function(){
			if ($("#password").val() == ""){
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return false;
			}
		});
	});
	
	</script>
	
<%@include file="../inc/footer.jsp"%>