<%@page import="com.jyeob.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<div style="background-color:#f2f2f2">
<div class="container">
<h3 style="text-align:center; color:#111111;"><strong>공지사항</strong></h3>
<p style="text-align:center; color:#888888; font-family:나눔고딕;">새로운 소식을 전달받을 수 있습니다.</p>
<%
BoardDto dto = (BoardDto)request.getAttribute("dto");
%>
<div class="container" style="height:600px; background-color:#FFFFFF">
<hr>
<table class="table table-condensed">
<colgroup>
<col style="width:8%">
<col style="width:50%">
<col style="width:20%">
</colgroup>
<thead>
<tr>
<th scope="col" style="color:#dc232d">${dto.category}</th>
<th scope="col" style="text-align:center; color:#111111">${dto.title}</th>
<th scope="col" style="color:#111111">관리자 | <%=dto.getDate().substring(0,10)%> | 조회수 <%=dto.getHits()%></th>
</tr>
</thead>
</table>
<br/>
<div style="font-size:14px; color:#888888; font-family:'Roboto', 'notokr','NanumGothic','나눔고딕','Malgun Gothic','Myriad Pro',Arial,'돋움','굴림','Gulim'; font-weight:normal; letter-spacing: -0.5px;">
<p>
<%=dto.getContent().replaceAll("\n","<br/>") %>
</p>
</div>
</div>
</div>
<div style="text-align:center; margin-top:2%;">
<%
if (cookie != null){
	Cookie[] cookies = request.getCookies();
	for (int i=0; i<cookies.length; i++){
		if (cookies[i].getName().equals("id")){
			if (cookies[i].getValue().equals("admin")){
				out.println("<a href='"+request.getContextPath()+"/editNoticeView.do?no="+request.getParameter("no")+"' class='btn btn-danger btn-lg'>수정</a>");
				out.println("<a href='"+request.getContextPath()+"/deleteNotice.do?no="+request.getParameter("no")+"' class='btn btn-danger btn-lg'>글삭제</a>");
			}
			break; 
		}
	}
}
%>
<a href="<%=request.getContextPath()%>/showNotice.do" class="btn btn-danger btn-lg">목록</a>
</div>
</div>
<%@include file="../inc/footer.jsp"%>