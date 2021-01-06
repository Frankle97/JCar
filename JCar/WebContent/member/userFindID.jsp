<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container">
	<h3>아이디 찾기</h3>
	<p>이름과 회원가입시 등록한 이메일 입력 후 확인 버튼을 클릭해주세요.</p>
	<form action="<%=request.getContextPath()%>/findID.do" method="post" id="act">
	<div class="form-group"> 
	이름<input type="text" name="name" id="name" style="margin-left:3%; width:20%;"><br/><br/>
	이메일<input type="text" name="email" id="email" style="margin-left:2%; width:20%;">
	</div>
	<div class="form-group">
	<input type="submit" value="확인" class="btn btn-default"><a href="<%=request.getContextPath()%>/loginView.do" class="btn btn-default" style="margin-left:1%;">돌아가기</a>
	</div>
	<script>
	$(document).ready(function(){
		$("#act").submit(function(){
			if ($("#id").val() == ""){
				alert("아이디를 입력해주세요.");
				$("#id").focus();
				return false;
			} else if ($("#email").val() == ""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false;
			}
		});
	});
	</script>
	</form>
	</div>
	
</body>
</html>
