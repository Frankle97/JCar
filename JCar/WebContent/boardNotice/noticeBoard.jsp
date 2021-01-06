<%@page import="com.jyeob.dto.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<div style="background-color:#f2f2f2; height:750px;">
<div class="container" style="text-align:center;">

<h3 style="color:#111111;"><strong>공지사항</strong></h3>
<p style="color:#888888; font-family:나눔고딕;">새로운 소식을 전달받을 수 있습니다.</p>

<div style="text-align:left; font-size:18px; color:#555555; font-family:나눔고딕;">
총 <span style="color:#dc232d; font-weight:bold">${cnt}</span>건의 글이 있습니다.

</div>
<br/>       
<div class="drop">        
<form action="<%=request.getContextPath()%>/noticeSearchS.do" method="post" id="act" name="frm">
	<fieldset>
	<input type="hidden" name="category" value="${category}">
	<input type="hidden" name="tab" value="전체">
	<input type="hidden" name="no" value="0">
  <div class="dropdown text-left" style="float:left;">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">${category}
    <span class="caret"></span></button>
    <ul id="mytype" class="dropdown-menu" role="menu" aria-labelledby="menu1">

      <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/showNotice.do?category=전체공지">전체공지</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/noticeSearch.do?category=긴급공지">긴급공지</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/noticeSearch.do?category=이벤트">이벤트</a></li>
    </ul>
  </div>
  <div class="dropdown" style="margin-left:1%; float:left;">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu2" data-toggle="dropdown">${tab}
<span class="caret"></span></button>
    <ul id="mytype2" class="dropdown-menu" role="menu" aria-labelledby="menu2">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="{document.frm.tab.value='전체';}">전체</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="{document.frm.tab.value='제목';}">제목</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="{document.frm.tab.value='내용';}">내용</a></li>
    </ul>
  </div>
  <input type="text" name="search" id="search" class="form-control" style="display:inline; width:30%; height:45px; margin-left:1%; float:left;">
	<button type="submit" id="searc" class="glyphicon glyphicon-search" style="    background-color: #FFFFFF;
    border-color: #FFFFFF;
    margin-left: -57%;
    margin-top: 5px;
    height: 34px;
    width: 38px;
    border:0;
    outline:0;"></button>
  </fieldset>
  </form>    
 </div>
 </div>
                         

<div class="container" style="margin-top:2%; height:500px; background-color:#FFFFFF">
<table class="table table-condensed">
<colgroup>
<col style="width:5%">
<col style="width:8%">
<col style="width:45%">
<col style="width:15%">
</colgroup>
<thead>
<tr>
<th scope="col" style="color:black">번호</th>
<th scope="col" style="color:black">구분</th>
<th scope="col" style="color:black">제목</th>
<th scope="col" style="color:black">등록일</th>
</tr>
</thead>
<tbody>
<c:if test="${list==null}"><tr><td></td><td></td><th style="color:black">공지사항이 없습니다.</th><td></td></tr></c:if>
<c:forEach var="nr" items="${list.list}" varStatus="status">
<tr><td>${nr.no}</td><td style="color:#dc232d"><strong>${nr.category}</strong></td><td style="color:#555; font-weight:bold;"><a href="detailNotice.do?no=${nr.no}">${nr.title}</a></td><td>${nr.date.substring(0,11)}</td></tr>
</c:forEach>
</tbody>
</table>
<%
if (cookie != null){
	Cookie[] cookies = request.getCookies();
	for (int i=0; i<cookies.length; i++){
		if (cookies[i].getName().equals("id")){
			if (cookies[i].getValue().equals("admin")){
				out.println("<a href='"+request.getContextPath()+"/writeNoticeView.do' style='margin-left:45%' class='btn btn-danger btn-lg'>글 등록</a>");
			}
			break; 
		}
	}
}
%>
<c:if test="${chk==1}">
<div style="text-align:center">
<ul class="pagination">
	<c:if test="${list.bottom_start > list.bottomList}"><li><a href="<%=request.getContextPath()%>/showNotice.do?no=${(list.bottom_start-list.onepageLimit)*list.onepageLimit}">이전</a></li></c:if>
	<c:forEach var="idx" begin="${list.bottom_start}" end="${list.bottom_end}" step="1" varStatus="status">
	<li <c:if test="${idx == list.bottom_current}">class="active"</c:if>>
	<a href="<%=request.getContextPath()%>/showNotice.do?no=${(idx-1)*list.onepageLimit}">${idx}</a></li>
	</c:forEach>
	<c:if test="${list.pageAll>list.bottom_end}"><li><a href="<%=request.getContextPath()%>/showNotice.do?no=${(list.bottom_end)*list.onepageLimit}">다음</a></li></c:if>
	</ul>
</div>
</c:if>
<c:if test="${chk==0}">
<div style="text-align:center">
<ul class="pagination">
	<c:if test="${list.bottom_start > list.bottomList}"><li><a href="<%=request.getContextPath()%>/noticeSearch.do?no=${(list.bottom_start-list.onepageLimit)*list.onepageLimit}">이전</a></li></c:if>
	<c:forEach var="idx" begin="${list.bottom_start}" end="${list.bottom_end}" step="1" varStatus="status">
	<li <c:if test="${idx == list.bottom_current}">class="active"</c:if>>
	<a href="<%=request.getContextPath()%>/noticeSearch.do?no=${(idx-1)*list.onepageLimit}&category=${category}">${idx}</a></li>
	</c:forEach>
	<c:if test="${list.pageAll>list.bottom_end}"><li><a href="<%=request.getContextPath()%>/noticeSearch.do?no=${(list.bottom_end)*list.onepageLimit}">다음</a></li></c:if>
	</ul>
</div>
</c:if>
<c:if test="${chk==2}">
<div style="text-align:center">
<ul class="pagination">
	<c:if test="${list.bottom_start > list.bottomList}"><li><a href="<%=request.getContextPath()%>/noticeSearchS.do?no=${(list.bottom_start-list.onepageLimit)*list.onepageLimit}">이전</a></li></c:if>
	<c:forEach var="idx" begin="${list.bottom_start}" end="${list.bottom_end}" step="1" varStatus="status">
	<li <c:if test="${idx == list.bottom_current}">class="active"</c:if>>
	<a href="<%=request.getContextPath()%>/noticeSearchS.do?no=${(idx-1)*list.onepageLimit}&category=${category}&tab=${tab}&search=${search}">${idx}</a></li>
	</c:forEach>
	<c:if test="${list.pageAll>list.bottom_end}"><li><a href="<%=request.getContextPath()%>/noticeSearchS.do?no=${(list.bottom_end)*list.onepageLimit}">다음</a></li></c:if>
	</ul>
</div>
</c:if>
</div>
</div>
<div>
<script>
$(function(){
	$('#mytype li > a').on('click', function() {
	    // 버튼에 선택된 항목 텍스트 넣기 
	    $('#menu1').text($(this).text());
	});
	$('#mytype2 li > a').on('click', function() {
	    // 버튼에 선택된 항목 텍스트 넣기 
	    $('#menu2').text($(this).text());
	});
	
	
});
</script>

</div>

<%@include file="../inc/footer.jsp"%>