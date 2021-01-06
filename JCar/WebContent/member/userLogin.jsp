<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
#id {margin-left:35%; width:33%; height:50px;}
#password {margin-left:35%; width:33%; height:50px;}  
#checkbox {margin-left:35%;}  
 a:link { color: #999999; text-decoration: none;}
 a:visited { color: #999999; text-decoration: none;}
 a:hover { color: #999999; text-decoration: none;}
 #kakao_login{
margin-left: 55%; 
margin-top: -4%;
cursor:pointer;
 }
</style>
</head>
<body>
  <%	
    String clientId = "sUEFL28oA9odjIYGEQkL";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://tieotdsf1324.cafe24.com/port/NaverLogin", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
	<div class="container">
	<br/><br/><br/>
	<a href="<%=request.getContextPath()%>/car.do" style="margin-left:45%;"><img src="<%=request.getContextPath()%>/images/logo.png" alt="홈으로 가기"></a>
	<form action="<%=request.getContextPath()%>/login.do" method="post">
	<fieldset>
	<br/>
	<div class="form-group">
	<%
	if (session.getAttribute("id") != null){
		out.println("<input type='text' class='form-control' name='id' id='id' value="+session.getAttribute("id")+" >");
	} else {
		out.println("<input type='text' class='form-control' name='id' id='id' placeholder='아이디를 입력해주세요.'>");
	}
	%>
	</div> 
	<div class="form-group">
	<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호">
	</div> 
	<div class="form-group">					
	<input type="checkbox" name="remember_id" id="remember_id" style="margin-left:35%;"><label for="remember_id" style="font-weight:normal;">아이디 기억하기</label>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=276c3f616086bbc0130bdbbb701b41a0&redirect_uri=http://tieotdsf1324.cafe24.com/port/kakao&response_type=code"><img src="${pageContext.request.contextPath}/images/kakao_login.png" alt="카카오톡 로그인하기" id="kakao_login"></a>
	<a href="<%=apiURL%>"><img height="30" src="http://static.nid.naver.com/oauth/small_g_in.PNG" id="naver_login_icon" alt="naver_login_ic" style="margin-top:-4%;"/></a>
	</div>
	<div class="form-group">
	<input type="submit" style="margin-left:35%; width:33%; margin-top:-2%;" class="btn btn-danger btn-lg" value="로그인">
	</div>
	<div style="text-align:center">
	<a href="<%=request.getContextPath()%>/findIDView.do">아이디 찾기 | </a><a href="<%=request.getContextPath()%>/findPWView.do">비밀번호 찾기 | </a><a href="<%=request.getContextPath()%>/joinSelect.do">회원가입 </a>
	
	</div>
	
	</fieldset>
	</form>
	</div>
	<script>
	$(document).ready(function(){
		$("#id").focus();
		if ($("#id").val() != ""){
			$("input:checkbox[id='remember_id']").prop("checked", true);
		}
	
		
	});
	</script>
<%@include file="../inc/footer.jsp"%>