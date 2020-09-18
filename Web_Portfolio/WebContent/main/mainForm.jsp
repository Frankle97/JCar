<%@page import="com.jyeob.dao.CarDao"%>
<%@page import="com.jyeob.dto.CarlistDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header2.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script>
	$(function() {
		// 현재 기준 강남구 날씨상황
		$.ajax({
			url:"${pageContext.request.contextPath}/Current_Weather",
			type:"post",
			dataType:"json",
			success:function(data){
				var dec = "현재 서울 강남구의 날씨는 ";
				var des2 = " ";
				if (data.weather == "비"){
					des2 = " 빗길 운전 조심하세요!";
				}
				$("#current_weather").html(dec + "<strong style='color:mediumblue'>" + data.weather + "</strong>입니다." + des2 + " <strong style='font-size:60%'>출처 : 기상청 rss</strong>");
			}, error:function(xhr, textStatus, errorThrown){
				console.log(errorThrown);
			}
		});
		$("#sel_pricemin").hide();
		$("#sel_pricemax").hide();
		$("#price_search").hide();
		$("#topic_list").hide();
		// 클릭 시 색상 변경하는 스크립트
		$("#val_price").click(function() {
			$("#sel_maker").text("제조사");
			$(this).css("color", "#dc232d");
			$(this).css("text-decoration", "underline");
			$("#val_model").css("color", "#BBBBBB");
			$("#val_model").css("text-decoration", "none");
			$("#maker_maker button").css("background-color", "#f2f2f2");
			$("#maker_maker button").css("color", "#555555");
			$("#maker_maker button").css("border-color", "#ccc");
			$("#min_min button").css("background-color", "#f2f2f2");
			$("#min_min button").css("color", "#555555");
			$("#min_min button").css("border-color", "#ccc");
			$("#max_max button").css("background-color", "#f2f2f2");
			$("#max_max button").css("color", "#555555");
			$("#max_max button").css("border-color", "#ccc");
			$("#search").hide();
			$("#searc").hide();
			$("#sel_model").hide();
			$("#sel_detail_model").hide();
			$("#sel_search").hide();
			$("#sel_pricemin").show();
			$("#sel_pricemax").show();
			$("#price_search").show();
			return false;
		});
		$("#val_model").click(function() {
			$("#sel_maker").text("제조사");
			$("#sel_model").text("모델");
			$("#sel_detail_model").text("세부모델");
			$("#maker_maker button").css("background-color", "#f2f2f2");
			$("#maker_maker button").css("color", "#555555");
			$("#maker_maker button").css("border-color", "#ccc");
			$("#model_model button").css("background-color", "#f2f2f2");
			$("#model_model button").css("color", "#555555");
			$("#model_model button").css("border-color", "#ccc");
			$("#detail_detail button").css("background-color", "#f2f2f2");
			$("#detail_detail button").css("color", "#555555");
			$("#detail_detail button").css("border-color", "#ccc");
			$(this).css("color", "#dc232d");
			$(this).css("text-decoration", "underline");
			$("#val_price").css("color", "#BBBBBB");
			$("#val_price").css("text-decoration", "none");
			
			$("#search").show();
			$("#searc").show();
			$("#sel_model").show();
			$("#sel_detail_model").show();
			$("#sel_search").show();
			$("#sel_pricemin").hide();
			$("#sel_pricemax").hide();
			$("#price_search").hide();
			return false;
		});
		// 드롭다운 객체 생성 스크립트
		$.ajax({
			url:"${pageContext.request.contextPath}/BringStore",
			type:"get",
			dataType:"json",
			data:{"loc":"강남직영점"},
			success:function(data){
				$("#location").html(data.location);
				$("#address").html(data.address);
				$("#number").html(data.number);
				$("#cntcar").html(data.cnt+"대");
			},error:function(xhr, textStatus, errorThrown){
				console.log(errorThrown);
			}
		});
	$
				.ajax({
					url : "${pageContext.request.contextPath}/CountMakers",
					type : "get",
					dataType : "json",
					data : {
						"country" : "국산"
					},
					success : function(data) {
						var arr = data.menu;
						var stmp = "";
						for (var i = 0; i < arr.length; i++) {
							stmp += "<li role=\"presentation\"><a role=\"menuitem\" id='"
									+ arr[i].maker
									+ "' onclick=\"maker('"
									+ arr[i].maker
									+ "'); return false;\" tabindex=\"-1\" >"
									+ arr[i].maker
									+ "("
									+ arr[i].cnt
									+ ")</a></li>";
						}
						$("#maker").html(stmp);
					},
					error : function(xhr, textStatus, errorThrown) {
						console.log("country 에러발생")
					}
				});

		var maker = "";
		$("#country_kor")
				.click(
						function() {
							maker = $("#country_kor").text();
							$
									.ajax({
										url : "${pageContext.request.contextPath}/CountMakers",
										type : "get",
										dataType : "json",
										data : {
											"country" : "국산"
										},
										success : function(data) {
											var arr = data.menu;
											var stmp = "";
											for (var i = 0; i < arr.length; i++) {
												stmp += "<li role=\"presentation\"><a role=\"menuitem\" onclick=\"maker('"
														+ arr[i].maker
														+ "'); return false;\" tabindex=\"-1\" >"
														+ arr[i].maker
														+ "("
														+ arr[i].cnt
														+ ")</a></li>";
											}
											$("#maker").html(stmp);
										},
										error : function(xhr, textStatus,
												errorThrown) {
											console.log("country_kor -에러발생");
										}
									});
						});
		$("#country_import")
				.click(
						function() {
							maker = $("#country_import").text();
							$
									.ajax({
										url : "${pageContext.request.contextPath}/CountMakers",
										type : "get",
										dataType : "json",
										data : {
											"country" : "수입"
										},
										success : function(data) {
											var arr = data.menu;
											var stmp = "";
											for (var i = 0; i < arr.length; i++) {
												stmp += "<li role=\"presentation\"><a role=\"menuitem\" onclick=\"maker('"
														+ arr[i].maker
														+ "'); return false;\" tabindex=\"-1\" >"
														+ arr[i].maker
														+ "("
														+ arr[i].cnt
														+ ")</a></li>";
											}
											$("#maker").html(stmp);
										},
										error : function(xhr, textStatus,
												errorThrown) {
											console
													.log("country_import - 에러발생");
										}
									});
						});

		// a태그 클릭 이벤트 스크립트
		$('#country li > a').on('click', function() {
			$('#sel_country').text($(this).text());
		});

		$('#min li > a').on('click', function() {
			$('#sel_pricemin').text($(this).text());
			$("#sel_pricemin").css("background-color", "#fdf5f4");
			$("#sel_pricemin").css("border", "1px solid #dc232d");
			$("#sel_pricemin").css("color", "#dc232d");
			$("#sel_pricemin").css("border-radius", "5px");
		});
		$('#max li > a').on('click', function() {
			$('#sel_pricemax').text($(this).text());
			$("#sel_pricemax").css("background-color", "#fdf5f4");
			$("#sel_pricemax").css("border", "1px solid #dc232d");
			$("#sel_pricemax").css("color", "#dc232d");
			$("#sel_pricemax").css("border-radius", "5px")
		});
		
		$("#search_carList").submit(function(){
			var search = document.getElementById("sel_detail_model").innerText;
			var pricemin = document.getElementById("sel_pricemin").innerText;
			var pricemax = document.getElementById("sel_pricemax").innerText;
			var sel_maker = document.getElementById("sel_maker").innerText;
			var text = '세부모델';
			if (text == search.substring(0,4)){
				search=null;
			}
			if (sel_maker.substring(0,3) == '제조사'){
				sel_maker = '';
			}
			$("#search").val(search);
			$("#maker_2").val(sel_maker);
			$("#min_price").val(pricemin);
			$("#max_price").val(pricemax);
		});
		
		$("#search").keyup(function(){
			var search_data = $("#search").val();
			
			if (search_data != ''){
				$("#topic_list").show();
				$.ajax({
					url:"${pageContext.request.contextPath}/SearchMainList",
					type:"get",
					dataType:"json",
					data:{"detail":search_data},
					success:function(data){
						var arr = data.menu;
						var stmp = "";
						if (arr.length > 0){
							for (var i=0; i<arr.length; i++){
								stmp+="<p><a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'; id='itemc'>"+arr[i].detail+" "+arr[i].birth.toString().substring(0,2)+"년 "+arr[i].birth.toString().substring(2,4)+"월식</a></p>";
							}
						} else {
							stmp+="<p>검색하신 모델과 일치하는 데이터가 존재하지 않습니다.</p>";
						}
						$("#sh_list").html(stmp);
					},error:function(xhr, textStatus, errorThrown){
						console.log(errorThrown);
					}
				});
			} else {
				$("#topic_list").hide();
			}
			
		});
		
	
	$("#exit_topic").on("click", function(){
		$("#topic_list").hide();
	});
		
});		
	function maker(name) {
		$('#sel_maker').text(name);
		$("#sel_maker").css("background-color", "#fdf5f4");
		$("#sel_maker").css("border", "1px solid #dc232d");
		$("#sel_maker").css("color", "#dc232d");
		$("#sel_maker").css("border-radius", "5px");

		$
				.ajax({
					url : "${pageContext.request.contextPath}/CountModels",
					type : "get",
					dataType : "json",
					data : {
						"maker" : name
					},
					success : function(data) {
						var arr = data.menu;
						var stmp = "";
						for (var i = 0; i < arr.length; i++) {
							stmp += "<li role=\"presentation\"><a role=\"menuitem\" id='"
									+ arr[i].model
									+ "' onclick=\"model('"
									+ arr[i].model
									+ "'); return false;\" tabindex=\"-1\" >"
									+ arr[i].model
									+ "("
									+ arr[i].cnt
									+ ")</a></li>";
						}
						$("#model").html(stmp);
					},
					error : function(xhr, textStatus, errorThrown) {
						console.log("maker - error")
					}
				});
	}
	function model(name) {
		$('#sel_model').text(name);
		$("#sel_model").css("background-color", "#fdf5f4");
		$("#sel_model").css("border", "1px solid #dc232d");
		$("#sel_model").css("color", "#dc232d");
		$("#sel_model").css("border-radius", "5px");

		$
				.ajax({
					url : "${pageContext.request.contextPath}/CountDetailModels",
					type : "get",
					dataType : "json",
					data : {
						"model" : name
					},
					success : function(data) {
						var arr = data.menu;
						var stmp = "";
						for (var i = 0; i < arr.length; i++) {
							stmp += "<li role=\"presentation\"><a role=\"menuitem\" id='"
									+ arr[i].detail
									+ "' onclick=\"detail('"
									+ arr[i].detail
									+ "'); return false;\" tabindex=\"-1\" >"
									+ arr[i].detail
									+ "("
									+ arr[i].cnt
									+ ")</a></li>";
						}
						$("#detail").html(stmp);
					},
					error : function(xhr, textStatus, errorThrown) {
						console.log("detail - error")
					}
				});
	}
	function detail(name) {
		$('#sel_detail_model').text(name);
		$("#sel_detail_model").css("background-color", "#fdf5f4");
		$("#sel_detail_model").css("border", "1px solid #dc232d");
		$("#sel_detail_model").css("color", "#dc232d");
		$("#sel_detail_model").css("border-radius", "5px");
	}
	 $(function(){
		 $("#portfolio").on("click", function(){
		            var url = "https://www.youtube.com/embed/ngGF8RufEHg";
		            var name = "자바포트폴리오 동영상";
		            var option = "width = 600, height = 400, top = 100, left = 200, location = no"
		            window.open(url, name, option);
		        
		 }); 
		 $("#webportfolio").on("click", function(){
	            var url = "https://www.youtube.com/embed/PxTwLjiz0oc";
	            var name = "웹 포트폴리오 동영상";
	            var option = "width = 600, height = 400, top = 100, left = 200, location = no"
	            window.open(url, name, option);
	        
	 }); 
		 $("#sboard1").on("click", function(){
			 location.href="http://tieotdsf1324.cafe24.com/sboard1";
	        
	 }); 
	  });
	 
	 function printClock() {
		    
		    var clock = document.getElementById("clock");           
		    var currentDate = new Date();                               
		    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate()
		    var amPm = 'AM'; 
		    var currentHours = addZeros(currentDate.getHours(),2); 
		    var currentMinute = addZeros(currentDate.getMinutes() ,2);
		    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
		    
		    if(currentHours >= 12){ 
		    	amPm = 'PM';
		    	currentHours = addZeros(currentHours - 12,2);
		    }

		    if(currentSeconds >= 50){
		       currentSeconds = '<span style="color:#de1951;">'+currentSeconds+'</span>'
		    }
		    clock.innerHTML = currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:50px;'>"+ amPm+"</span>"; 
		    
		    setTimeout("printClock()",1000);         
		}

		function addZeros(num, digit) { 
			  var zero = '';
			  num = num.toString();
			  if (num.length < digit) {
			    for (i = 0; i < digit - num.length; i++) {
			      zero += '0';
			    }
			  }
			  return zero + num;
		}

</script>
<%@include file="../inc/banner.jsp"%>
	<div style="line-height:120px; color:#666;font-size:60px; text-align:center;" id="clock">
	</div>
	<p style="color:#333;font-size:30px; text-align:center;" id="current_weather">
	</p>
<% 
CarDao dao = new CarDao();
ArrayList<CarlistDto> hotlist = dao.hotList7();
pageContext.setAttribute("hotlist", hotlist);
%>


<div class="container text-left" style="margin-top:3%;">
<form action="${pageContext.request.contextPath}/carListSearch.do" method="get" id="search_carList">
<fieldset>
<div class="search_condition">
<button class="btn btn-default" id="val_model" style="background-color: #FFFFFF;
	color: #dc232d;
	font-size: 22px;
	font-weight: bold;
	border: 0;
	outline: 0;
	text-decoration: underline;">원하는 모델이 있어요</button><button class="btn btn-default" id="val_price">예산이 정해져 있어요</button>
<p class="text-right">전체 <span style="color:#dc232d; font-weight:bold;">${cntall}대</span></p>
<div id="search_form">
 <div class="dropdown text-left" style="float:left;">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_country" data-toggle="dropdown">국산
    <span class="caret" style="margin-left:40%;"></span></button>
    <ul id="country" class="dropdown-menu" role="menu" aria-labelledby="sel_country">
      <li role="presentation"><a role="menuitem" tabindex="-1" id="country_kor">국산</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" id="country_import">수입</a></li>
      
    </ul>
  </div>
   <div class="dropdown text-left" style="float:left;" id="maker_maker">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_maker" data-toggle="dropdown">제조사
    <span class="caret" style="margin-left:55%;"></span></button>
    <ul id="maker" class="dropdown-menu" role="menu" aria-labelledby="sel_maker">
    </ul>
  </div>
  <div class="dropdown text-left" style="float:left;" id="model_model">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_model" data-toggle="dropdown">모델
    <span class="caret" style="margin-left:65%;"></span></button>
    <ul id="model" class="dropdown-menu" role="menu" aria-labelledby="sel_model">
    </ul>
  </div>
   <div class="dropdown text-left" style="float:left;" id="detail_detail">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_detail_model" data-toggle="dropdown">세부모델
    <span class="caret" style="margin-left:70%;"></span></button>
    <ul id="detail" class="dropdown-menu" role="menu" aria-labelledby="sel_detail_model">
    </ul>
  </div>
  <!-- 최저금액 -->
    <div class="dropdown text-left" style="float:left;" id="min_min">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_pricemin" data-toggle="dropdown">최저금액
    <span class="caret" style="margin-left:65%;"></span></button>
    <ul id="min" class="dropdown-menu" role="menu" aria-labelledby="sel_pricemin">
    <c:forEach var="loop" begin="1000" end="10000" step="1000">
     <li role="presentation"><a role="menuitem" tabindex="-1" id="min_${loop}">${loop}만원</a></li>
    </c:forEach>
    </ul>
  </div><!-- 최고금액 -->
    <div class="dropdown text-left" style="float:left;" id="max_max">
    <button class="btn btn-default dropdown-toggle" type="button" id="sel_pricemax" data-toggle="dropdown">최고금액
    <span class="caret" style="margin-left:65%;"></span></button>
    <ul id="max" class="dropdown-menu" role="menu" aria-labelledby="sel_pricemax">
     <c:forEach var="loop" begin="1000" end="10000" step="1000">
    <c:if test="${loop<10000}"> <li role="presentation"><a role="menuitem" tabindex="-1" id="max_${loop}">${loop}만원</a></li></c:if>
     <c:if test="${loop==10000}"> <li role="presentation"><a role="menuitem" tabindex="-1" id="max_${loop}">${loop}만원이상</a></li></c:if>
    </c:forEach>
    </ul>
  </div>
  <input type="hidden" name="maker_2" id="maker_2"> 
  <input type="hidden" name="min_price" id="min_price">
  <input type="hidden" name="max_price" id="max_price">
  <button type="submit" id="sel_search">검색하기</button>
   <button type="submit" id="price_search">검색하기</button>
   <input type="text" name="search" id="search" class="form-control" placeholder="모델명을 입력 예)아반떼">
	<button type="button" id="searc" class="glyphicon glyphicon-search"></button>
	
</div>
</div>
</fieldset>
</form>
<!-- 메인 검색 ajax div group -->
<div class="container panel" style="width:1140px; height:300px; border: 1px solid #e2e2e2;" id="topic_list">
<div class="row">
<div class="col-sm-6">
<h4>인기모델명</h4>
<hr/>
<div id="list">
<c:forEach var="topic" items="${hotlist}" begin="0" end="7" step="1" varStatus="status">
<p><a href="${pageContext.request.contextPath}/detailCar.do?no=${topic.no}" class="topic">${status.count}.${topic.maker} ${topic.model}</a></p>
</c:forEach>
</div>

</div>
<div class="col-sm-6">
<h4>연관검색어<span class="glyphicon glyphicon-remove" style="margin-left:78%; cursor:pointer;" id="exit_topic"></span></h4>
<hr/>
<div id="sh_list">

</div>
</div>
</div>
</div>
</div>

<div style="margin-top:3%">
<img src="${pageContext.request.contextPath}/images/banner5.png" alt="banner5.png">
<a href="${pageContext.request.contextPath}/carList.do" class="btn btn-default service_btn" id="buy_myCar_btn" style="margin-top: -9%; margin-left: 314px;">내차사기</a>
<a class="btn btn-default service_btn" id="sell_myCar_btn" style="margin-top: -9%; margin-left: 220px; font-size: 16px; color: #111111; font-weight: bold; background-color: #FFFFFF; border-radius: 50px; text-align: center;" onclick="location.href='${pageContext.request.contextPath}/requestSellCar.do?id=<%=id%>';">내차팔기</a>
</div>



<div class="text-center" style="margin-top:3%">
<h2 style="font-size:30px; color:black; font-weight:bold;">오늘의 추천차량</h2>

<div class="container">
<div class="row">
<c:forEach var="item" items="${hotlist}" begin="0" end="14" step="1" varStatus="status">
  <div class="col-sm-4">
  <div class="mySlides">
    <a href="${pageContext.request.contextPath}/detailCar.do?no=${item.no}">
    <img src="${pageContext.request.contextPath}/upload/${item.image}" style="width:100%; height:250px;" alt="car images"><br/><br/>
  	<span style="font-size:24px; margin-top:1%; color:black;"><strong>${item.maker} ${item.model}</strong></span><br/><br/>
  	<span style="color:#dc232d; font-size:24px;"><strong>${item.price}</strong></span><span style="color:black; font-size:24px; font-weight:bold;">만원</span>
    </a>
  </div>
  </div>
</c:forEach>
</div>
<a id="prev1" class="prev" onclick="plusSlides(-3)" style="background-color:black; margin-left:-55%;">❮</a>
<a id="next1" class="next" onclick="plusSlides(3)" style="background-color:black; margin-right:-5%;">❯</a>
</div>

<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}


function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  slides[slideIndex+1].style.display = "block";
  slides[slideIndex].style.display = "block";
}
</script>
</div>
<div class="find_map" style="background-color:#f5f6f9; height:500px; margin-top:3%;">
<div class="container text-center">
<img src="${pageContext.request.contextPath}/images/find_map.png" alt="이미지를 찾을 수 없습니다." style="text-align:center;">
 <ul class="nav nav-tabs" style="margin-top:3%; background-color:#f5f6f9;">
    <li class="active"><a data-toggle="tab" href="#seoul">서울(8)</a></li>
    <li><a data-toggle="tab" href="#menu1">경기/인천(6)</a></li>
    <li><a data-toggle="tab" href="#menu2">경남(5)</a></li>
    <li><a data-toggle="tab" href="#menu3">경북(4)</a></li>
    <li><a data-toggle="tab" href="#menu4">전남(1)</a></li>
    <li><a data-toggle="tab" href="#menu5">전북(1)</a></li>
    <li><a data-toggle="tab" href="#menu6">충남(3)</a></li>
  </ul>
<script>
$(function(){
	$(".tab-content button").on("click", function(){
		var loc = $(this).text();
		$(".tab-content button").css("color", "#333");
		
		$(this).css("color", "#da2432");
		
		$.ajax({
			url:"${pageContext.request.contextPath}/BringStore",
			type:"get",
			dataType:"json",
			data:{"loc":loc},
			success:function(data){
				$("#location").html(data.location);
				$("#address").html(data.address);
				$("#number").html(data.number);
				$("#cntcar").html(data.cnt+"대");
			},error:function(xhr, textStatus, errorThrown){
				console.log(errorThrown);
			}
		});
	});
});
</script>
  <div class="tab-content" style="margin-top:1%;">
    <div id="seoul" class="tab-pane fade in active">
      <p style="text-align:left;"><button>강남직영점</button> <button >구로직영점</button><button >목동직영점</button><button >서서울직영점 </button><button >서초직영점 </button><button >성동직영점 </button><button >영등포직영점 </button><button >장한평직영점</button></p>
    </div>
    <div id="menu1" class="tab-pane fade">
      <p style="text-align:left;"><button>강서직영점</button><button >경기서부직영점</button><button >경인직영점</button><button >분당용인직영점</button><button >수원신갈직영점</button><button >수원직영점</button></p>
    </div>
    <div id="menu2" class="tab-pane fade"> 
      <p style="text-align:left;"><button>부산직영점</button><button >서부산직영점</button><button >양산직영점</button><button >울산직영점</button><button >창원마산직영점</button></p>
    </div>
    <div id="menu3" class="tab-pane fade">
      <p style="text-align:left;"><button >구미직영점</button><button >대구직영점</button><button >대구반야월직영점</button><button >서대구직영점</button></p>
    </div>
    <div id="menu4" class="tab-pane fade">
      <p style="text-align:left;"><button >광주송암직영점</button></p>
    </div>
    <div id="menu5" class="tab-pane fade">
      <p style="text-align:left;"><button >전주직영점</button></p>
    </div>
    <div id="menu6" class="tab-pane fade">
      <p style="text-align:left;"><button >대전유성직영점</button><button >대전직영점</button><button >천안직영점</button></p>
    </div>
  </div>
  
  <div class="container text-left" style="background-color:white; margin-top:1%;">
  <div class="row">
 <div class="col-sm-2">
  <h3 id="location" style="color:black;"><strong>강남직영점</strong></h3>
  <p><strong>주소</strong></p>
  <p><strong>대표전화</strong></p>
  <p><strong>영업시간</strong></p>
  <p><strong>휴무일</strong></p>
  <p><strong>보유차량</strong></p>
 </div>	 
 <div class="col-sm-5" style="margin-top:5%;">
 <p id="address">서울시 강남구 헌릉로 745길 25 강남자동차매매단지 A동 124호,125호</p>
 <p id="number">02-6203-4226</p>
 <p id="opentime">월~토요일 09:00~18:00 공휴일/일요일 휴무, 점심시간 12:00~13:00</p>
 <p id="holiday">2020년 08월 15일, 2020년 08월 17일</p>
 <p id="cntcar">2대</p>
 </div>
  <div class="col-sm-5">
  <p style="text-align:right;"><img src="${pageContext.request.contextPath}/images/carcar.png" alt=""></p>
  </div>
  </div>
  
  </div>

</div>

</div>

 <h4 style="text-align: center;
    margin-bottom: -1%;
    margin-top: 3%;
    position: fixed;
    right: 0;
    top: 50%;"><span id="portfolio" style="cursor:pointer;" class="btn btn-danger">자바포트폴리오 동영상 보기</span></h4>
    <br/>
     <h4 style="text-align: center;
    margin-bottom: -1%;
    margin-top: 5%;
    position: fixed;
    right: 0;
    top: 50%;"><span id="webportfolio" style="cursor:pointer;" class="btn btn-danger">웹포트폴리오 동영상 보기</span></h4>
    <br/>
     <h4 style="text-align: center;
    margin-bottom: -1%;
    margin-top: 7%;
    position: fixed;
    right: 0;
    top: 50%;"><span id="sboard1" style="cursor:pointer;" class="btn btn-danger">스프링보드 이동하기</span></h4>
<%@include file="../inc/footer.jsp"%>