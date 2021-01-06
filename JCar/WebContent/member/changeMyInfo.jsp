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

	<table class="table table-bordered" style="margin-top:1%; height:50px;">
	<tr><th><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>">내차사기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>">내차팔기 주문관리</a></th><th style="background:#fdf5f4;"><a href="<%=request.getContextPath()%>/editInfo.do?id=<%=dto.getId()%>" style="color:#DC232D;">회원정보 수정</a></th></tr>
	</table>
	</div>
	
	<div class="container" style="height:500px">
	<form action="<%=request.getContextPath()%>/changeInfo.do" method="post" id="act">
	<fieldset>
	<div class="form-group">
	<label for="name" style="margin-left:30%;">이름</label>
	<input type="button" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getName()%>" disabled>
	</div>
	<div class="form-group">
	<label for="contact" style="margin-left:30%;">연락처</label>
	<input type="text" name="contact" id="contact" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getContact()%>">
	</div>
	<div class="form-group">
	<label for="password" style="margin-left:30%;">변경할 비밀번호 입력</label>
	<input type="password" name="password" id="password" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;">
	</div>
	<div class="form-group">
	<label for="passwordchk" style="margin-left:30%;">변경할 비밀번호 확인</label>
	<input type="password" name="psaswordchk" id="passwordchk" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;">
	</div>
	<div class="form-group">
	<label for="email" style="margin-left:30%;">이메일</label>
	<input type="text" name="email" id="email" class="form-control" style="width:35%; margin-left:30%; height:40px; text-align:left;" value="<%=dto.getEmail()%>">
	</div>
	<div class="form-group">
			<div class="form-group form-inline" style="margin-left:30%">
			<label for="post_code">주소</label><br/>
			<input type="text" id="address" name="address" style="width:50%; height:40px; text-align:left;" class="form-control" value="<%=dto.getAddress()%>">
			 <input type="button" id="post" value="우편번호" class="btn btn-info">
			</div>
			
			<input type="text" id="address2" name="address2" style="width:35%; margin-left:30%; height:40px; text-align:left;" class="form-control" placeholder="변경하실 상세주소를 입력해주세요.">
</div>
	<div class="form-group">
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<input type="submit" value="수정" class="btn btn-danger btn-lg" style="margin-left:30%"><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>" class="btn btn-default btn-lg" style="margin-left:1%">취소</a><a href="<%=request.getContextPath()%>/userRemoveForm.do?id=<%=dto.getId()%>" class="btn btn-default btn-lg" style="margin-left:8%">회원탈퇴</a>
	</div>
	
	</fieldset>
	</form>
	</div>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	$(function(){
		$("#act").submit(function(){
			if ($("#password").val() == ""){
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return false;
			} else if ($("#password").val() != $("#passwordchk").val()){
				alert("비밀번호가 일치하지 않습니다.\n다시 확인해주세요.");
				$("#passwordchk").focus();
				return false;
			}
		});
		$("#post").on("click", function(){
			new daum.Postcode({
		        oncomplete: function(data) {
		            console.log(data);
		            $("#address").val(data.address);
		            $("#address2").focus();
		        }
		    }).open();
		});
	});
	
	</script>
	
	
<div style="margin-top:5%;">
<%@include file="../inc/footer.jsp"%>
</div>
