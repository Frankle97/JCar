<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="background-color:#f2f2f2">
<div class="container">
<div class="container" style="background-color:#FFFFFF;">
<form action="<%=request.getContextPath()%>/writeReview.do?no=${param.carno}" method="post" id="act" enctype="multipart/form-data">
<fieldset>
<legend><strong>후기 등록</strong></legend>
<div class="form-group">
<label for="writer">작성자</label>
<input type="text" name="writer" id="writer" class="form-control" value="${param.id}" readonly>
</div>
<div>
<label for="model">모델명</label>
<input type="text" name="model" id="model" class="form-control" value="${param.model}" readonly>
</div>
<div class="form-group">
<label for="title">제목</label>
<input type="text" name="title" id="title" class="form-control">
</div>
<div class="form-group">
<label for="content">내용</label>
<textarea class="form-control" name="content" id="content" rows="15" cols="20"></textarea>
</div>
<div class="form-group">
<label for="image">이미지 등록</label>
<input type="file" name="image" id="image" class="form-control">
</div>
<div class="form-group" style="text-align:center">
<input type="submit" class="btn btn-danger btn-lg" value="등록">
<a href="<%=request.getContextPath()%>/showReview.do" class="btn btn-danger btn-lg">목록</a>
</div>
</fieldset>
</form>
</div>
</div>
</div>
<script>
$(function(){
	$("#act").submit(function(){
		if ($("#title").val() == ""){
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		} else if ($("#content").val() == ""){
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	});
});
</script>
<%@include file="../inc/footer.jsp"%>