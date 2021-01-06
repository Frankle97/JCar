<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>J CAR</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<%=request.getContextPath()%>/images/로고T.png" rel="shortcut icon" type="image/x-icon">
  <link href="<%=request.getContextPath()%>/images/로고T.png" rel="icon" type="image/x-icon"> 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <style>
 td {
    text-align: center;
    font-size: 16px;
 }
  .btn {
    padding: 10px 20px;
    background-color: #dc232d;
    color: #f1f1f1;
    border-radius: 0;
    transition: .2s;	
}
.drop .btn {
	background-color:#BBBBBB;
}
.nav>li>a>img {
    max-width: none;
    margin-left:20%;
}
.detail_bg {
    padding: 10px 0;
    border-top: 10px solid #efefef;
}
 </style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60" >
<div class="header_bg" style="padding-bottom:30px;
border-bottom:1px solid #ddd;">
<nav class="navbar navbar-default navbar-top" style="margin-top:15px;">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      
      <!-- href="#myPage" -->
    </div>
    <div class="collapse navbar-collapse" id="myNavbar" style="width:103%;">
      <ul class="nav navbar-nav navbar-left" style="margin-left:3%">
   	
        <li><a href="${pageContext.request.contextPath}/carList.do" style="color:black">내차사기</a></li>
        <%
        String id = "";
        String cookie = request.getHeader("Cookie");
        if (cookie != null){
        	Cookie[] cookies = request.getCookies();
        	for (int i=0; i<cookies.length; i++){
        		if (cookies[i].getName().equals("id")){
        			id = cookies[i].getValue();
        			break; 
        		}
        	}
          }
        out.println("<li><a href='"+request.getContextPath()+"/requestSellCar.do?id="+id+"' style='color:black'>내차팔기</a></li>");
        %>
        <li><a href="<%=request.getContextPath()%>/AdminLogin" style="color:#de1515" id="adLogin">관리자로그인</a></li>
    	<li><a href="<%=request.getContextPath()%>/car.do" style="margin-top:-15px; margin-left:15%;"><img src="<%=request.getContextPath()%>/images/logo.png" alt="메인페이지로 이동"></a>
      </ul>
       <ul class="nav navbar-nav navbar-right">
       <li><a href="<%=request.getContextPath()%>/showNotice.do" style="color:black">공지사항</a></li>
       <li><a href="<%=request.getContextPath()%>/showReview.do" style="color:black">고객후기</a></li>
      <%
      session.setAttribute("kakao", false);
     
      if (cookie != null){
    	Cookie[] cookies = request.getCookies();
    	for (int i=0; i<cookies.length; i++){
    		if (cookies[i].getName().equals("id")){
    			out.println("<li><a href='"+request.getContextPath()+"/myPageView.do?id="+cookies[i].getValue()+"' style='color:black'>마이페이지</a></li>");
    			out.println("<li><a href='"+request.getContextPath()+"/logout.do' style='color:black'>로그아웃</a></li>");
    			id=cookies[i].getValue();
    			break; 
    		} else if (i==cookies.length-1){
    			out.println("<li><a href='"+request.getContextPath()+"/loginView.do' style='color:black'>로그인</a></li>");
    			out.println("<li><a href='"+request.getContextPath()+"/joinSelect.do' style='color:black'>회원가입</a></li>");
    		}
    	}
      }
      
      %>
   
        
      </ul>
    </div>
  </div>

     
</nav>

</div>
