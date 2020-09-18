<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../inc/header.jsp"%>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
%>
	<div class="container">
		<form action="${pageContext.request.contextPath}/sendEmail.do" method="post">
			<fieldset>
			<div class="row form-group">
			<div class="col-sm-3"><label for="sender">받는이</label></div>
			<div class="col-sm-9"><input type="text" value="<%=dto.getName()%>" class="form-control" disabled></div>
			</div>
			<div class="row form-group">
				<div class="col-sm-3"><label for="email">이메일</label></div>
				<div class="col-sm-9"><input type="text" id="email" name="email" class="form-control" value="<%=dto.getEmail()%>"/></div>
			</div>
			<div class="row form-group">
				<div class="col-sm-3"><label for="subject">제목</label></div>
				<div class="col-sm-9"><input type="text" id="subject" name="subject" class="form-control"></div>
			</div>
			<div class="row form-group">
				<div class="col-sm-3"><label for="content">내용</label></div>
				<div class="col-sm-9"><textarea id="content" name="content" rows="10" cols="40" class="form-control"></textarea></div>
			</div>
			
			<div class="row form-group">
			<div class="col-sm-3"></div>
			<div class="col-sm-9"><input type="submit" value="메일발송하기" class="form-control btn btn-danger"></div>
			</div>
			<div class="row form-group">
			<div class="col-sm-3"></div>
			<div class="col-sm-9"><input type="button" onclick="location.href='<%=request.getContextPath()%>/adminPage.do?id=admin';" value="돌아가기" class="form-control btn btn-danger"></div>
			</div>
			</fieldset>
		</form>
		<script src="//cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
		<script>
		CKEDITOR.replace( 'content' );
		</script>
 
		
	</div>
<%@include file="../inc/footer.jsp"%>
