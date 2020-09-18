<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@page import="com.jyeob.dto.StoreDto"%>
<%@page import="com.jyeob.dao.CarDao"%>
<%@page import="com.jyeob.dto.CarlistDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<%
CarlistDto dto = (CarlistDto)request.getAttribute("dto");
MemberDto udto = new MemberDao().findAccount(id);
pageContext.setAttribute("dto", dto);
pageContext.setAttribute("cate", udto.getCategory());
String x = (String)request.getAttribute("x");
String y = (String)request.getAttribute("y");
%>
<div class="container" id="dtContainer" style="width:50%;">
<div class="row">
<div class="col-sm-9">
<h3><strong style="color:black">${dto.maker} ${dto.detail}</strong></h3>
<p>${dto.accident} ${dto.birth.toString().substring(0,2)}년${dto.birth.toString().substring(2,4)}월식 ${dto.km}km ${dto.fuel} ${dto.color} ${dto.mission} ${dto.city}</p>
</div>
<div class="col-sm-3">
<h4 style="color:black;"><strong>할부시 월 <span style="color:#dc232d; text-decoration:underline;"><%=(int)Math.ceil(dto.getPrice()/48)%>만원</span></strong><br/>
<strong>차량가 <span style="color:#dc232d;">${dto.price}만원</span></strong></h4>
</div>
</div>
<div id="car_image">
<img src="${pageContext.request.contextPath}/upload/${dto.image}" alt="차량 이미지를 찾을 수 없습니다." style="width:70%;">
</div>
<div id="car_options" style="margin-top:3%;" class="detail_bg">
<p style="color:black; font-family:bold; font-size:22px;">주요옵션</p>
<p>
<c:if test="${dto.options.contains('선루프')}"><img src="${pageContext.request.contextPath}/images/선루프.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('가죽시트')}"><img src="${pageContext.request.contextPath}/images/가죽시트.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('네비게이션')}"><img src="${pageContext.request.contextPath}/images/네비게이션.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('사이드에어백')}"><img src="${pageContext.request.contextPath}/images/사이드에어백.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('스마트키')}"><img src="${pageContext.request.contextPath}/images/스마트키.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('열선시트')}"><img src="${pageContext.request.contextPath}/images/열선시트.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('하이패스룸미러')}"><img src="${pageContext.request.contextPath}/images/하이패스룸미러.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('후방감지센서')}"><img src="${pageContext.request.contextPath}/images/후방감지센서.png" alt="옵션 이미지입니다."></c:if>
<c:if test="${dto.options.contains('후방카메라')}"><img src="${pageContext.request.contextPath}/images/후방카메라.png" alt="옵션 이미지입니다."></c:if>
</p>
</div>
<div class="row detail_bg" style="margin-top:3%;">
<div class="col-sm-3">
<p>차량평가사 : ${dto.writer}</p>
<p>매매사원증 GC18-00${dto.no}</p>
</div>
<div class="col-sm-5">
<p></p>
<p>
${dto.content}
</p>
</div>
<div class="col-sm-4">
</div>
</div>

<div class="detail_bg">
<h4>이 차량은<span style="color:#dc232d; font-weight:bold;"> ${dto.city}</span>에 있습니다.</h4>
<input type="hidden" id="status" value="${dto.status}">
<input type="hidden" id="cate" value="${cate}">
<input type="hidden" id="deoc" value="${dto.city}">
<input type="hidden" id="id" value="<%=id%>">
<input type="hidden" id="x" value="<%=x%>">
<input type="hidden" id="y" value="<%=y%>">
<div id="map" style="width:100%;height:350px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35f6e2e3f10fe0f979c3cde1a6c2dd44"></script>
<script>
var loc = $("#deoc").val();
var x = $("#x").val();
var tmpy = $("#y").val();
var y = tmpy.toString().substring(0, tmpy.indexOf(","));
var mapContainer = document.getElementById('map'), 
mapOption = { 
    center: new kakao.maps.LatLng(x, y), 
    level: 3 
};

var map = new kakao.maps.Map(mapContainer, mapOption);

var markerPosition  = new kakao.maps.LatLng(x, y); 

var marker = new kakao.maps.Marker({
position: markerPosition
});
map.setDraggable(false); 
marker.setMap(map);
</script>
</div>
<div style="margin-top:3%;">
<a href="${pageContext.request.contextPath}/buyCar.do?id=<%=id%>&no=${dto.no}" class="btn btn-danger" id="buy">구매신청</a> <a href="${pageContext.request.contextPath}/carList.do" class="btn btn-danger">돌아가기</a>
<%
if (id.equals("admin")){
	out.println("<a href='"+request.getContextPath()+"/removeCarlist.do?no="+request.getParameter("no")+"' class='btn btn-danger'>삭제하기</a>");
}
%>
</div>
<script>
$(function(){
	$("#buy").on("click", function(){
		if ($("#id").val() == ''){
			alert("로그인이 필요한 항목입니다.");
			return false;
		} else {
			if (confirm('정말 구매하시겠습니까?') == false){
				alert("취소되었습니다.");
				return false;
			}
		}
		if ($("#status").val()=='판매완료'){
			alert("해당 차량은 예약된 차량이라 구매가 불가합니다. 이용에 불편을 드려 죄송합니다.");
			return false;
		}
		if ($("#cate").val()=='매매회원'){
			alert("매매회원은 차량 구매가 불가합니다. 관리자에게 문의해주세요.");
			return false;
		}
		
	});
});
</script>
</div>
<%@include file="../inc/footer.jsp"%>