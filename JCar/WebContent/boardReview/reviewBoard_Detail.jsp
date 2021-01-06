<%@page import="com.jyeob.dto.ReviewDto"%>
<%@page import="com.jyeob.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<div style="background-color:#f2f2f2">
<div class="container">
<h3 style="text-align:center; color:#111111;"><strong>내차사기 후기</strong></h3>
<%
ReviewDto dto = (ReviewDto)request.getAttribute("dto");
pageContext.setAttribute("dto", dto);
%>
<div class="container" style="height:600px; background-color:#FFFFFF;">
<hr>
<table class="table table-bordered">
<colgroup>
<col width="8%">
<col width="50%">
<col width="20%">
</colgroup>
<thead>
<tr>
<th scope="col" style="color:#dc232d">${dto.model}</th>
<th scope="col" style="text-align:center; color:#111111">${dto.title}</th>
<th scope="col" style="color:#111111">${dto.writer} | <%=dto.getDate().substring(0,10)%> | 조회수 ${dto.hits}</th>
</tr>
</thead>
</table>
<br/>
<p>
<%=dto.getContent().replaceAll("\n","<br/>") %>
</p>
<div style="margin-top:10%">
<p>
<img src="${pageContext.request.contextPath}/upload/${dto.image}" alt="이미지를 찾을 수 없습니다." style="width:30%;">
</p>
</div>
</div>
</div>
<div style="text-align:center;">
<a href="<%=request.getContextPath()%>/editReviewForm.do?no=${param.no}&id=<%=id%>" class="btn btn-danger btn-lg" id="modify">수정</a>
<input type="hidden" id="id_chk" value="<%=id%>">
<input type="hidden" id="id_p" value="${dto.writer}">
<script>
$(function(){
	$("#modify").on("click", function(){
		if ($("#id_chk").val() != $("#id_p").val()){
			alert("수정 권한이 없습니다.");
			return false;
		}
	});
	$("#remove").on("click", function(){
		if ($("#id_chk").val() != $("#id_p").val()){
			alert("삭제 권한이 없습니다.");
			return false;
		}
	});
});
</script>
<a href="<%=request.getContextPath()%>/removeReview.do?no=${param.no}&id=<%=id%>" class="btn btn-danger btn-lg" id="remove">삭제</a>
<a href="<%=request.getContextPath()%>/showReview.do" class="btn btn-danger btn-lg">목록</a>
</div>
</div>
<%@include file="../inc/footer.jsp"%>