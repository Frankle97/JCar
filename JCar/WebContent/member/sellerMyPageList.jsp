<%@page import="com.jyeob.dao.SellerDao"%>
<%@page import="com.jyeob.dto.SellerDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
ArrayList<SellerDto> list = new SellerDao().allSellList("승인대기");
pageContext.setAttribute("list", list);
%>
	<div class="container">
	<div>
	<h3 style="color:black"><strong>반갑습니다. <%=dto.getName()%>님</strong></h3>
	</div>
	<div class="container" style="height:500px">
	<c:if test="${list==null}"><img src="<%=request.getContextPath()%>/images/no_directing.jpg" alt="img" style="margin-left:15%;"></c:if>
	<c:if test="${list!=null}">
	<table class="table table-bordered" style="margin-top:2%; height:50px;">
	<tr><th><a href="${pageContext.request.contextPath}/sellerMyPage.do?id=${param.id}">보유 매물</a></th><th style="background:#fdf5f4;"><a href="${pageContext.request.contextPath}/sellerMyPageList.do?id=${param.id}" style="color:#DC232D;">매물 현황</a></th><th><a href="<%=request.getContextPath()%>/editInfo.do?id=<%=dto.getId()%>">회원정보 수정</a></th></tr>
	
	</table>
	
	
	<table class="table table-striped">
	<thead>
	<tr><th scope="col">국산/수입</th><th scope="col">모델명</th><th scope="col">연식</th><th scope="col">고객성함</th><th scope="col">연락처</th><th>현재상태</th><th scope="col">가져오기</th></tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${list}" varStatus="status">
	<tr><td>${item.country}</td><td>${item.model}</td><td>${item.birth}</td><td>${item.name}</td><td>${item.phone}</td><td style="color:#dc232d; font-weight:bold;">${item.status}</td><td><a href="${pageContext.request.contextPath}/mypageBringSell.do?no=${item.no}&id=<%=dto.getId()%>" class="btn btn-danger">가져오기</a></td></tr>
	</c:forEach>
	</tbody>
	</table>
	</c:if>
	</div>
	</div>
</body>
</html>