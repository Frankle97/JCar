<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../inc/header.jsp"%>
<style>
	.notice{width:300px; position:fixed; top:20%; left:20%; box-shadow:0 0 5px #f0ad4e; padding:10px; border-radius:20px;}
	.notice img{width:200px; }
	
</style>
</head>
<body>
	<div class="notice" style="margin-left:22%;">
	<h3>포트폴리오 공지사항</h3>
	<p>
		본 사이트는 상업적 목적이 아닌 개인 포트폴리오 용도로 제작되었으며
		홈페이지의 일부 내용과 기타이미지 등은 그 출처가 따로 있음을 밝힙니다.
	</p>
	<p><a href='http://m.site.naver.com/0Dzio'><img src='${pageContext.request.contextPath}/images/qrcode.jpg' alt="포트폴리오 바로가기"/></a></p>
	<p>
	<input type="checkbox" id="subpop" name="subpop">
	<label for="subpop">오늘 하루동안 이 창 열지 않음.</label>
	<input type="button" value="[close]" class="btn btn-danger" id="close">
	</p>
	</div>
	<script>
	$(function(){
		$("#close").on("click", function(){
			location.href="${pageContext.request.contextPath}/car.do";
		});
	});
	
	</script>
</body>
</html>