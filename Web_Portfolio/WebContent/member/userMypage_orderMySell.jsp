<%@page import="com.jyeob.dto.SellerDto"%>
<%@page import="com.jyeob.dao.SellerDao"%>
<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
if (dto.getCategory().equals("매매회원")){
	request.getRequestDispatcher("/sellerMyPage.do").forward(request, response);
}

SellerDto info = new SellerDao().findMyOrder(dto.getId());
%>
	<div class="container">
	<div>
	<h3 style="color:black"><strong>반갑습니다. <%=dto.getName()%>님</strong></h3>
	</div>
	<table class="table table-bordered" style="margin-top:2%; height:50px;">
	<tr><th><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>">내차사기 주문관리</a></th><th style="background:#fdf5f4; "><a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>" style="color:#DC232D;">내차팔기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/editInfo.do?id=<%=dto.getId()%>">회원정보 수정</a></th></tr>
	</table>

	<div class="container" style="height:500px">
	<c:if test="<%=info.getModel()==null%>"><img src="<%=request.getContextPath()%>/images/no_directing.jpg" alt="img" style="margin-left:15%;"></c:if>
	<c:if test="<%=info.getModel()!=null%>">
	<table class="table table-striped">
	<thead>
	<tr><th scope="col">현재상황</th><th scope="col">모델</th><th scope="col">가입날짜</th><th scope="col">판매취소</th></tr>
	</thead>
	<tbody>
	<tr><td style="color:#dc232d; font-weight:bold;"><%=info.getStatus()%></td><td><%=info.getModel()%></td><td><%=info.getDate()%></td><td><a href="<%=request.getContextPath()%>/myPageOrderSellCancel.do?id=<%=dto.getId()%>" class="btn btn-danger">취소</a></td></tr>
	</tbody>
	</table>
	</c:if>
	
	</div>
	</div>
	
<%@include file="../inc/footer.jsp"%>