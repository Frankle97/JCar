<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
p {text-align:center;}
label {
font-size: 16px;
line-height: 1.5;
font-family: Malgun Gothic,맑은 고딕,sans-serif;
color: #333333;
font-weight:normal;
cursor:pointer;

}
  .btn {
    padding: 10px 20px;
    background-color: #dc232d;
    color: #f1f1f1;
    border-radius: 0;
    transition: .2s;	
}
</style>
</head>
<body>    

<div class="container">
<p>
<a href="<%=request.getContextPath()%>/car.do"><img src="<%=request.getContextPath()%>/images/logo.png" alt="홈으로 가기"></a>
</p>
<p><img src="<%=request.getContextPath()%>/images/join001.png" alt="이미지를 찾을 수 없습니다."></p>

<div style="margin-left:30%">
<input type="checkbox" id="check_all"> <label for="check_all">전체 동의하기(필수항목)</label>
<hr/>
<input type="checkbox" id="check_one" name="check">
  <label data-toggle="collapse" data-target="#demo1" for="check_one">이용약관 안내</label>
  <div id="demo1" class="collapse in">
<textarea cols="65" rows="5" disabled>
제1조 (목적)
이 약관은 정재엽이 시연하는 카 웹사이트 (http://tieotdsf1324.cafe24.com/port, 이하 "카"라 한다)에서 제공하는 인터넷 관련 서비스 및 기타 부대서비스를 이용함에 있어 카닷컴(주)와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
-- 해당 사항들은 시연을 위한 예시임을 알립니다. --
</textarea> 
  </div>

<br/><br/>
<input type="checkbox" id="check_two" name="check">
  <label data-toggle="collapse" data-target="#demo2" for="check_two">개인정보 수집/이용에 대한 안내</label>
  <div id="demo2" class="collapse in">
<textarea cols="65" rows="5" disabled>
제2조 (목적)
이 약관은 정재엽이 시연하는 카 웹사이트 (http://tieotdsf1324.cafe24.com/port, 이하 "카"라 한다)에서 제공하는 인터넷 관련 서비스 및 기타 부대서비스를 이용함에 있어 카닷컴(주)와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
-- 해당 사항들은 시연을 위한 예시임을 알립니다. --
</textarea> 
  </div>

<br/><br/>
<input type="checkbox" id="check_three" name="check">
  <label data-toggle="collapse" data-target="#demo3" for="check_three">규정에 대한 안내</label>
  <div id="demo3" class="collapse in">
<textarea cols="65" rows="5" disabled>
제3조 (목적)
이 약관은 정재엽이 시연하는 카 웹사이트 (http://tieotdsf1324.cafe24.com/port, 이하 "카"라 한다)에서 제공하는 인터넷 관련 서비스 및 기타 부대서비스를 이용함에 있어 카닷컴(주)와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
-- 해당 사항들은 시연을 위한 예시임을 알립니다. --
</textarea> 
  </div>
  <br/><br/>
  <a href="<%=request.getContextPath()%>/joinClient.do?category=${param.category}" id="click"><img src="<%=request.getContextPath()%>/images/icon_next.jpg" alt="다음페이지로 이동"></a>
</div>
<script>
$(document).ready(function(){
	$("#check_all").click(function(){
		if (!($("#check_all").is(":checked"))){
			$("input:checkbox").prop("checked", false);
		} else {
			$("input:checkbox").prop("checked", true);
		}
		
		
	});
	
	$("#click").click(function(){
		if (!($("#check_one").is(":checked")) || !($("#check_two").is(":checked")) || !($("#check_three").is(":checked"))){
			alert("약관에 전부 동의하지 않았습니다.");
			return false;
		}  
	});
});
</script>




</div>
<div>	
</div>
<%@include file="../inc/footer.jsp"%>