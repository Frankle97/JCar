<%@page import="com.jyeob.dto.ReviewDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../inc/header.jsp"%>
<div style="background-color:#f2f2f2; height:750px;">
<div class="container" style="text-align:center;">

<h3 style="color:#111111;"><strong>내차사기 후기</strong></h3>
<p style="color:#888888; font-family:나눔고딕;">고객님의 리얼한 후기를 통해 차량 판매 정보를 얻어가세요.</p>

<div style="text-align:left; font-size:18px; color:#555555; font-family:나눔고딕;">
<span style="color:#dc232d; font-weight:bold">${cnt}</span>건의 후기가 등록되어 있습니다.
</div>

<div class="container" style="margin-top:2%; height:550px; background-color:#FFFFFF">
<table class="table table-condensed">
<colgroup>
<col style="width:5%">
<col style="width:8%">
<col style="width:45%">
<col style="width:15%">
</colgroup>
<thead>
<tr>
<th scope="col" style="color:black">작성자</th>
<th scope="col" style="color:black">모델명</th>
<th scope="col" style="color:black">제목</th>
<th scope="col" style="color:black">등록일</th>
</tr>
</thead>
<tbody>
<c:forEach var="nr" items="${list.list}" varStatus="status">
<tr><td>${nr.writer}</td><td style="color:#dc232d"><strong>${nr.model}</strong></td><td style="color:#555; font-weight:bold;"><a href="detailReview.do?no=${nr.no}">${nr.title}</a></td><td>${nr.date.substring(0,11)}</td></tr>
</c:forEach>
</tbody>
</table>
<div style="text-align:center">
<ul class="pagination">
	<c:if test="${list.bottom_start > list.bottomList}"><li><a href="<%=request.getContextPath()%>/showReview.do?no=${(list.bottom_start-list.onepageLimit)*list.onepageLimit}">이전</a></li></c:if>
	<c:forEach var="idx" begin="${list.bottom_start}" end="${list.bottom_end}" step="1" varStatus="status">
	<li <c:if test="${idx == list.bottom_current}">class="active"</c:if>>
	<a href="<%=request.getContextPath()%>/showReview.do?no=${(idx-1)*list.onepageLimit}">${idx}</a></li>
	</c:forEach>
	<c:if test="${list.pageAll>list.bottom_end}"><li><a href="<%=request.getContextPath()%>/showReview.do?no=${(list.bottom_end)*list.onepageLimit}">다음</a></li></c:if>
	</ul>
</div>
</div>
</div>
<div>

</div>
</div>
<%@include file="../inc/footer.jsp"%>