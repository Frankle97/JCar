<%@page import="com.jyeob.dao.ReviewDao"%>
<%@page import="com.jyeob.dto.ReviewDto"%>
<%@page import="com.jyeob.dto.BoardDto"%>
<%@page import="com.jyeob.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<div style="background-color:#f2f2f2">
<div class="container" style="background-color:#FFFFFF">
<%ReviewDto dto = new ReviewDao().DetailReview(Integer.parseInt(request.getParameter("no")));%>
<form action="<%=request.getContextPath()%>/editReview.do" method="post" enctype="multipart/form-data">
<fieldset>
<legend><strong>후기 수정</strong></legend>
<div class="form-group">
<label for="writer">작성자</label>
<input type="text" name="writer" id="writer" class="form-control" value="<%=dto.getWriter()%>" readonly>
</div>
<div class="form-group">
<strong>모델</strong><br/>
<input type="text" name="model" id="model" class="form-control" value="<%=dto.getModel()%>" readonly>
</div>
<div class="form-group">
<label for="title">제목</label>
<input type="text" name="title" id="title" value="<%=dto.getTitle()%>" class="form-control">
</div>
<div class="form-group">
<label for="content">내용</label>
<textarea class="form-control" name="content" id="content" rows="15" cols="20"><%=dto.getContent()%></textarea>
</div>
<div class="form-group">
<label for="image">이미지 업로드</label>
<input type="file" name="image" id="image">
</div>
<div class="form-group" style="text-align:center">
<input type="hidden" name="no" value="<%=dto.getNo()%>">
<input type="hidden" name="id" value="${param.id}">
<input type="submit" class="btn btn-danger btn-lg" value="수정">
<a href="<%=request.getContextPath()%>/showReview.do" class="btn btn-danger btn-lg">목록</a>
</div>
</fieldset>
</form>
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