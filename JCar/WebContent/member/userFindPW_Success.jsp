<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container">
	<h3>비밀번호 변경</h3>
	<form action="<%=request.getContextPath()%>/changePW.do" method="post" id="act" style="margin-top:3%;">
	<div class="form-group">
<label for="password">변경할 비밀번호</label>
<input type="password" name="password" id="password">
</div>
<div class="form-group">
<label for="password">변경할 비밀번호 확인</label>
<input type="password" name="passwordchk" id="passwordchk" >
</div>
<div class="form-group">
<input type="hidden" name="no" value="<%=request.getParameter("no")%>">
<input type="submit" class="btn btn-default" value="확인"><a href="<%=request.getContextPath()%>/loginView.do" class="btn btn-default" >돌아가기</a>
</div>
	</form>
	</div>
	<script>
	$(document).ready(function(){
		$("#act").submit(function(){
			if ($("#password").val().length<6 || $("#password").val().length>16){
				alert("6~16자리의 비밀번호만 사용이 가능합니다.");
				$("#password").focus();
				return false;
			} else if ($("#password").val() != $("#passwordchk").val()){
				alert("비밀번호가 일치하지 않습니다.");
				$("#passwordchk").focus();
				return false;
			}
		});
	});
	
	</script>
</body>
</html>