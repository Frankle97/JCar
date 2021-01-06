<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/joinClient.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
#post_code {text-align:left; margin-left:35%; width:30%;} 
#address {text-align:left; margin-left:35%; width:30%;} 
#address2 {text-align:left; margin-left:35%; width:30%;} 
</style>
</head>
<body>
<div class="container join text-center">
<a href="<%=request.getContextPath()%>/car.do"><img src="<%=request.getContextPath()%>/images/logo.png" alt="홈으로 가기"></a>

<p><img src="<%=request.getContextPath()%>/images/join002.png" alt="이미지를 찾을 수 없습니다."></p>

<h4><strong>회원정보입력</strong></h4>
<form action="<%=request.getContextPath()%>/joinAct.do" method="post" id="act" name="frm">
<fieldset>
<div class="form-group">
<label for="name">이름<span style="color:red">*</span></label>
<%
Boolean flag = (boolean)session.getAttribute("kakao");
if (!flag){pageContext.setAttribute("kakao_name", "실명입력"); pageContext.setAttribute("email", "example@gmail.com");}
else {pageContext.setAttribute("kakao_name", session.getAttribute("name")); pageContext.setAttribute("email", session.getAttribute("email"));}

%>
<input type="text" name="name" id="name" class="form-control" placeholder="실명입력" <c:if test="${!kakao_name.equals('실명입력')}">value="${kakao_name}" disabled</c:if>>


</div>
<div class="form-group">
<label for="birth">생년월일<span style="color:red">*</span></label>
<input type="text" name="birth" id="birth" placeholder="YYYYMMDD" class="form-control"> 
</div>
<div class="form-group">
<label>성별<span style="color:red">*</span></label> <br/>
<input type="hidden" name="gender">
<input type="button" class="btn btn-default btn-lg" id="ma" value="남" onclick="{document.frm.gender.value='남';}" style="width:170px;">
<input type="button" class="btn btn-default btn-lg" id="wo" value="여" onclick="{document.frm.gender.value='여';}" style="width:170px;">

</div>
<div class="form-group form-inline text-left">
<label for="id" style="margin-left:47%;">아이디<span style="color:red">*</span></label><br/>
<input type="text" name="id" id="id" class="form-control" placeholder="영문 소문자와 숫자 조합 4~12자리">
</div>

<p id="r1"></p>

<div class="form-group">
<label for="password">비밀번호<span style="color:red">*</span></label>
<input type="password" name="password" id="password" class="form-control">
</div>
<div class="form-group">
<label for="password">비밀번호 확인<span style="color:red">*</span></label>
<input type="password" name="passwordchk" id="passwordchk" class="form-control">
</div>
<p >비밀번호는 6~16자로 입력해주세요.</p>
<div class="form-group">
<label for="contact">휴대전화번호<span style="color:red">*</span></label>
<input type="number" name="contact" id="contact" class="form-control" placeholder="'-' 제외하고 숫자만 입력">
</div>	
<div class="form-group">
<label for="email">이메일<span style="color:red">*</span></label>
<input type="text" name="email" id="email" class="form-control" placeholder="emample@gmail.com"<c:if test="${!email.equals('example@gmail.com')}">value="${email}" disabled</c:if>>
</div>
<p id="r2"></p>
<div class="form-group">
<label for="post_code">우편번호<span style="color:red">*</span></label>
			<div class="form-group form-inline text-left">
			 <input type="text" id="post_code" name="postcode" class="form-control" placeholder="우편번호"> 
			 <input type="button" id="post" value="우편번호" class="btn btn-info">
			</div>
			
			<input type="text" id="address" name="address" class="form-control">
			<input type="text" id="address2" name="address2" class="form-control" placeholder="상세주소를 입력해주세요.">
	
</div>
<div class="form-group">
<input type="hidden" name="category" value="${param.category}">
<input type="submit" class="btn btn-danger btn-lg" style="width:30%;" value="완료">
</div>

</fieldset>
</form>
</div>	
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	var overlap = false;
	var overlap2 = false;
	if ($("#email").val() != ""){
		overlap = true;
		overlap2 = true;
	}
	$("#post").on("click", function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            $("#post_code").val(data.zonecode);
	            $("#address").val(data.address);
	            $("#address2").focus();
	            $("#post_code").css("pointer-events", "none");
				$("#post_code").css("background-color", "#eee");
				$("#post_code").css("color", "#555");
				$("#post_code").css("opacity", "1");
				$("#address").css("pointer-events", "none");
				$("#address").css("background-color", "#eee");
				$("#address").css("color", "#555");
				$("#address").css("opacity", "1");
	        }
	    }).open();
	});
	
	$("#id").keyup(function(){
		var id = $("#id").val();
		if (id != ''){
			$.ajax({
				url:"${pageContext.request.contextPath}/CheckOverlapId",
				type:"get",
				dataType:"text",
				data:{"id":id},
				success:function(data){
				if (data==0){
					$("#r1").html("현재 사용중인 아이디입니다.");
					$("#r1").css("color", "#dc232d");
					$("#id").css("border", "solid 1px #dc232d");
					overlap = false;
				} else if (data==1) {
					$("#r1").css("color", "black");
					$("#r1").empty();
					$("#id").css("border", "solid 1px #ccc");
					overlap = true;
				} 
				},
				error:function(xhr, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
			
		}
		
	});
	
	$("#email").keyup(function(){
		var email = $("#email").val();
		if (email != ''){
			$.ajax({
				url:"${pageContext.request.contextPath}/CheckOverlapEmail",
				type:"get",
				dataType:"text",
				data:{"email":email},
				success:function(data){
				if (data==0){
					$("#r2").html("현재 사용중인 이메일입니다.");
					$("#r2").css("color", "#dc232d");
					$("#email").css("border", "solid 1px #dc232d");
					overlap2 = false;
				} else if (data==1) {
					$("#r2").css("color", "black");
					$("#r2").empty();
					$("#email").css("border", "solid 1px #ccc");
					overlap2 = true;
				} 
				},
				error:function(xhr, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
			
		}
		
	});
	$("#act").submit(function(){
		
		if ($("#name").val() == ""){
			alert("이름을 입력해주세요.");
			$("#name").focus(); 
			return false;
		} else if ($("#birth").val() == ""){
			alert("생년월일을 입력해주세요.");
			$("#birth").focus(); 
			return false;
		} else if (isNaN($("#birth").val())){
			alert("연-월-일 형식의 숫자만 입력이 가능합니다.");
			$("#birth").focus();
			return false
		} else if ($("#id").val().length<4 || $("#id").val().length>12 ){
			alert("4~12자리의 아이디만 사용이 가능합니다.");
			$("#id").focus();
			return false;
		} else if ($("#password").val().length<6 || $("#password").val().length>16){
			alert("6~16자리의 비밀번호만 사용이 가능합니다.");
			$("#password").focus();
			return false;
		} else if ($("#password").val() != $("#passwordchk").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#passwordchk").focus();
			return false;
		} else if ($("#contact").val() == ""){
			alert("연락처를 입력해주세요.");
			$("#contact").focus();
			return false;
		} else if ($("#contact").val().length<10 || $("#contact").val().length>14){
			alert("연락처 형식을 확인해주세요.");
			$("#contact").focus();
			return false;
		} else if (!($("#email").val().indexOf("@") != -1)){
			alert("이메일 형식을 확인해주세요.");
			$("#email").focus();
			return false;
		} else if (overlap == false){
			alert("현재 사용중인 아이디입니다.");
			$("#id").focus();
			return false;
		} else if ($("#address2").val() == ""){
			alert("주소입력을 마무리해주세요.");
			$("#address2").focus();
			return false;
		} else if (overlap2 == false){
			alert("현재 사용중인 이메일입니다.");
			$("#email").focus();
			return false;
		} 
	
		
	});

	$("#ma").click(function(){
		$(this).css("color", "white");
		$("#wo").css("color", "#dc232d");
		request.setAttribute("gender", "남");	
	});
	$("#wo").click(function(){
		$(this).css("color", "white");
		$("#ma").css("color", "#dc232d");
		request.setAttribute("gender", "여");
	});
});
</script>
<%@include file="../inc/footer.jsp"%>

