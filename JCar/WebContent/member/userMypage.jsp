<%@page import="java.util.ArrayList"%>
<%@page import="com.jyeob.dto.CarlistDto"%>
<%@page import="com.jyeob.dao.CarDao"%>
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

if (cookie != null){
	Cookie[] cookies = request.getCookies();
	for (int i=0; i<cookies.length; i++){
		if (cookies[i].getName().equals("id")){
			if (cookies[i].getValue().equals("admin")){
				request.getRequestDispatcher("/adminPage.do").forward(request, response);
			} 
			break; 
		} 
	}
}
String[] arr = new CarDao().showMyCart(request.getParameter("id"));
if (arr!=null){ArrayList<CarlistDto> list = new CarDao().showMyList(arr); pageContext.setAttribute("list", list);}
%>
	<div class="container">
	<div>
	<h3 style="color:black"><strong>반갑습니다. <%=dto.getName()%>님</strong></h3>
	</div>

	<table class="table table-bordered" style="margin-top:2%; height:50px;">
	<tr><th style="background:#fdf5f4; "><a href="<%=request.getContextPath()%>/myPageView.do?id=<%=dto.getId()%>" style="color:#DC232D;">내차사기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>">내차팔기 주문관리</a></th><th><a href="<%=request.getContextPath()%>/editInfo.do?id=<%=dto.getId()%>">회원정보 수정</a></th></tr>
	
	</table>
	<div class="container" style="height:500px">
	<c:if test="${list==null}"><img src="<%=request.getContextPath()%>/images/no_directing.jpg" alt="img" style="margin-left:15%;"></c:if>
	<c:if test="${list!=null}">
	<table class="table table-striped">
	<thead>
	<tr><th scope="col">판매자</th><th scope="col">모델명</th><th scope="col">연식</th><th scope="col">옵션</th><th scope="col">사고유무</th><th>직영점</th><th>후기작성</th></tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${list}" varStatus="status">
	<tr><td>${item.writer}</td><td style="color:black; font-weight:bold;">${item.model}</td><td>${item.birth}</td><td>${item.options}</td><td>${item.accident}</td><td>${item.city}</td><td><a href="${pageContext.request.contextPath}/review_write.do?id=<%=dto.getId()%>&carno=${item.no}&model=${item.model}" class="btn btn-danger">후기작성</a></td></tr>
	</c:forEach>
	</tbody>
	</table>
	</c:if>
	</div>
	</div>
	
<%@include file="../inc/footer.jsp"%>