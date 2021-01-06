<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/carList.css">
<%
String[] arr = {"경차", "소형차", "준중형차", "중형차", "대형차", "스포츠카", "SUV", "승합차", "버스"};
String[] colors = {"흰색", "진주색", "검정색", "검정투톤", "쥐색", "은색", "청색", "은색투톤", "빨간색"};
String[] fuel = {"가솔린", "디젤", "LPG", "전기"};
String[] mission = {"오토", "수동"};
String[] options = {"선루프", "네비게이션", "스마트키", "열선시트", "후방카메라", "가죽시트", "후방감지센서", "사이드에어백", "하이패스룸미러"};
pageContext.setAttribute("category", arr);
pageContext.setAttribute("colors", colors);
pageContext.setAttribute("fuel", fuel);
pageContext.setAttribute("mission", mission);
pageContext.setAttribute("options", options);
%>

<script>
$(function(){

	//////////////////////////////////////////////////
	var current_list = "";
	var country = "전체";
	var chk_category = "";
	var chk_maker = "";
	var min_year = "";
	var max_year = "";
	var min_km = "";
	var max_km = "";
	//////////////////////////////////////////////////
	// 페이지 오픈시 최초 이벤트
	current_list = listUpAll("전체");
	countMakers("전체");
	//mark
	if ($("#input_model").val() != ''){ 	
		$("#searc").click();
	}

	if (document.getElementById("sch_search").value == '' && document.getElementById("search_maker2").value != null){
		var sch_maker = document.getElementById("search_maker2").value;
		var minp = document.getElementById("search_min_price").value.substring(0,4);
		var maxp = document.getElementById("search_max_price").value.substring(0,4);
	if (document.getElementById("search_max_price").value=="10000만원이상"){
		maxp = 100000;
		}
		listUpPriceMaker(country, sch_maker, minp, maxp, current_list);
	}
	$("#input_model").focus();
	$("#search_category_btn_down").hide();
	$("#search_maker_model_btn_down").hide();
	$("#search_birth_btn_down").hide();
	$("#search_km_btn_down").hide();
	$("#search_price_btn_down").hide();
	$("#search_color_btn_up").hide();
	$("#search_fuel_btn_up").hide();
	$("#search_mission_btn_up").hide();
	$("#search_options_btn_up").hide();
	$("#search_category_color").slideUp();
	$("#search_category_fuel").slideUp();
	$("#search_category_mission").slideUp();
	$("#search_category_options").slideUp();
	// 탭 버튼 클릭 이벤트
	$("#search_category_btn_up").click(function(){
		$("#search_category_content").slideUp();
		$("#search_category_btn_up").hide();
		$("#search_category_btn_down").show();
	});
	$("#search_category_btn_down").click(function(){
		$("#search_category_content").slideDown();
		$("#search_category_btn_down").hide();
		$("#search_category_btn_up").show();
	});
	
	$("#search_maker_model_btn_up").click(function(){
		$("#search_category_maker_model").slideUp();
		$("#search_maker_model_btn_up").hide();
		$("#search_maker_model_btn_down").show();
	});
	$("#search_maker_model_btn_down").click(function(){
		$("#search_category_maker_model").slideDown();
		$("#search_maker_model_btn_down").hide();
		$("#search_maker_model_btn_up").show();
	});
	$("#search_birth_btn_up").click(function(){
		$("#search_category_birth").slideUp();
		$("#search_birth_btn_up").hide();
		$("#search_birth_btn_down").show();
	});
	$("#search_birth_btn_down").click(function(){
		$("#search_category_birth").slideDown();
		$("#search_birth_btn_down").hide();
		$("#search_birth_btn_up").show();
	});
	$("#search_km_btn_up").click(function(){
		$("#search_category_km").slideUp();
		$("#search_km_btn_up").hide();
		$("#search_km_btn_down").show();
	});
	$("#search_km_btn_down").click(function(){
		$("#search_category_km").slideDown();
		$("#search_km_btn_down").hide();
		$("#search_km_btn_up").show();
	});
	$("#search_price_btn_up").click(function(){
		$("#search_category_price").slideUp();
		$("#search_price_btn_up").hide();
		$("#search_price_btn_down").show();
	});
	$("#search_price_btn_down").click(function(){
		$("#search_category_price").slideDown();
		$("#search_price_btn_down").hide();
		$("#search_price_btn_up").show();
	});
	$("#search_color_btn_up").click(function(){
		$("#search_category_color").slideUp();
		$("#search_color_btn_up").hide();
		$("#search_color_btn_down").show();
	});
	$("#search_color_btn_down").click(function(){
		$("#search_category_color").slideDown();
		$("#search_color_btn_down").hide();
		$("#search_color_btn_up").show();
	});
	$("#search_fuel_btn_up").click(function(){
		$("#search_category_fuel").slideUp();
		$("#search_fuel_btn_up").hide();
		$("#search_fuel_btn_down").show();
	});
	$("#search_fuel_btn_down").click(function(){
		$("#search_category_fuel").slideDown();
		$("#search_fuel_btn_down").hide();
		$("#search_fuel_btn_up").show();
	});
	$("#search_mission_btn_up").click(function(){
		$("#search_category_mission").slideUp();
		$("#search_mission_btn_up").hide();
		$("#search_mission_btn_down").show();
	});
	$("#search_mission_btn_down").click(function(){
		$("#search_category_mission").slideDown();
		$("#search_mission_btn_down").hide();
		$("#search_mission_btn_up").show();
	});
	$("#search_options_btn_up").click(function(){
		$("#search_category_options").slideUp();
		$("#search_options_btn_up").hide();
		$("#search_options_btn_down").show();
	});
	$("#search_options_btn_down").click(function(){
		$("#search_category_options").slideDown();
		$("#search_options_btn_down").hide();
		$("#search_options_btn_up").show();
	});
	// 탭 버튼 클릭시 색상변경 css 이벤트
	$("#category_all").click(function() {
		country="전체";
		chk_category = "";
		countMakers("전체");
		current_list = listUpAll(country);
		reColor();
		$(this).css("color", "#dc232d");
		$("#category_home").css("color", "#BBBBBB");
		$("#category_import").css("color", "#BBBBBB");
		$(".chkbox_options").prop("checked", false);

		
	});
	$("#category_home").click(function() {
		country="국산";
		chk_category = "";
		countMakers("국산");
		current_list = listUpAll(country);
		reColor();
		$(this).css("color", "#dc232d");
		$("#category_all").css("color", "#BBBBBB");
		$("#category_import").css("color", "#BBBBBB");
		$(".chkbox_options").prop("checked", false);
	});
	$("#category_import").click(function() {
		country="수입";
		chk_category = "";
		countMakers("수입");
		current_list = listUpAll(country);
		reColor();
		$(this).css("color", "#dc232d");
		$("#category_home").css("color", "#BBBBBB");
		$("#category_all").css("color", "#BBBBBB");
		$(".chkbox_options").prop("checked", false);
	});
	// ajax
	
	$(".chkbox_category").on("click", function(){
		   var check_count = document.getElementsByName("carlist_category").length;
		   
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_category")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_category")[i].value+"/";
	            }
	        
	        }
	        chk_category=arr;
	        if (arr != ''){
	        	countMakersCategory(country, chk_category);
	        } else {
	        	countMakers(country);
	        }
	       if (arr != '' ){ 
	    	   $.ajax({
		        	url:"${pageContext.request.contextPath}/CarListCategory",
		        	type:"get",
		        	dataType:"json",
		        	data:{"country":country, "category":arr},
		        	success:function(data){
		        		var arr = data.menu;
						var stmp="";
						var tmp="";
						for (var i=0; i<arr.length; i++){
							tmp += arr[i].no+"/";
							stmp+="<div class='row' style='margin-top:1%;'>";
							stmp+="<div class='col-sm-3'>";
						//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
							stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
							stmp+="<div class='col-sm-6' style='margin-top:5%'>";
							stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
							stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
							stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
							stmp+="</div>";
							stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
							stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
							stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
							stmp+="</div>";
							stmp+="</div>";
						}
						$(".showCarList").html(stmp);
						$("#cntAll").html(arr.length);
						current_list = tmp;
						listUpAll(country, current_list);
		        	},error:function(xhr, textStatus, errorThrown){
		        		console.log(errorThrown);
		        	}
		        });	    	
	       } else {
	    	 listUpAll(country);
	       }
	});
	$("#search_seller").keyup(function(){
		var search_seller = $("#search_seller").val();
		if (search_seller != ''){
		$.ajax({
			url:"${pageContext.request.contextPath}/SearchSeller",
			type:"get",
			dataType:"json",
			data:{"detail":search_seller},
			success:function(data){
				var arr = data.menu;
				var stmp="";
				var tmp = "";
				for (var i=0; i<arr.length; i++){
					tmp += arr[i].no+"/";
					stmp+="<div class='row' style='margin-top:1%;'>";
					stmp+="<div class='col-sm-3'>";
				//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
					stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
					stmp+="<div class='col-sm-6' style='margin-top:5%'>";
					stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
					stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
					stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
					stmp+="</div>";
					stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
					stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
					stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
					stmp+="</div>";
					stmp+="</div>";
				}
				$(".showCarList").html(stmp);
				$("#cntAll").html(arr.length);
				current_list = tmp;
				listUpAll(country, current_list);
			},error:function(xhr, textStatus, errorThrown){
				console.log(errorThrown);
			}
			
		});
		} else {
			listUpAll(country);
		}
	});
	
	$("#search_category_maker_model").on("click", function(){
		 var check_count = document.getElementsByName("carlist_maker").length;
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_maker")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_maker")[i].value+"/";
	            }
	        }
	     chk_maker = arr;
	      if (chk_category==""){
	    	  $.ajax({
		    		url:"${pageContext.request.contextPath}/CarListMakers",
		    		type:"get",
		    		dataType:"json",
		    		data:{"country":country, "category":chk_category, "maker":arr},
		    		success:function(data){
		    			var arr = data.menu;
		    			var stmp="";
		    			var tmp = "";
		    			for (var i=0; i<arr.length; i++){
		    				tmp += arr[i].no+"/";
		    				stmp+="<div class='row' style='margin-top:1%;'>";
		    				stmp+="<div class='col-sm-3'>";
		    			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
		    				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
		    				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
		    				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
		    				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
		    				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
		    				stmp+="</div>";
		    				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
		    				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
		    				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
		    				stmp+="</div>";
		    				stmp+="</div>";
		    			}
		    			$(".showCarList").html(stmp);
		    			$("#cntAll").html(arr.length);
		    			current_list = tmp;
		    			listUpAll(country, current_list);
		    		},error:function(xhr, textStatus, errorThrown){
		    			console.log(errorThrown);
		    		}
		    	});
	      } else {
	    	  $.ajax({
		    		url:"${pageContext.request.contextPath}/CarListMakers",
		    		type:"post",
		    		dataType:"json",
		    		data:{"country":country, "category":chk_category, "maker":arr},
		    		success:function(data){
		    			var arr = data.menu;
		    			var stmp="";
		    			var tmp  = "";
		    			for (var i=0; i<arr.length; i++){
		    				tmp += arr[i].no+"/";
		    				stmp+="<div class='row' style='margin-top:1%;'>";
		    				stmp+="<div class='col-sm-3'>";
		    			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
		    				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
		    				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
		    				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
		    				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
		    				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
		    				stmp+="</div>";
		    				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
		    				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
		    				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
		    				stmp+="</div>";
		    				stmp+="</div>";
		    			}
		    			$(".showCarList").html(stmp);
		    			$("#cntAll").html(arr.length);
		    			current_list = tmp;
		    			listUpAll(country, current_list);
		    		},error:function(xhr, textStatus, errorThrown){
		    			console.log(errorThrown);
		    		}
		    	});
	      }
	});
	
	
	
	
	
	$(".chkbox_color").on("click", function(){
		   var check_count = document.getElementsByName("carlist_color").length;
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_color")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_color")[i].value+"/";
	            }
	        
	        }
	       if (arr != '' ){ 
	    	   listUpColor(country, current_list, arr);
	    	   //111
	       } else {
	    	 	listUpAll(country, current_list);
	       }
	});
	$(".chkbox_fuel").on("click", function(){
		   var check_count = document.getElementsByName("carlist_fuel").length;
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_fuel")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_fuel")[i].value+"/";
	            }
	        
	        }
	       if (arr != '' ){ 
	    	  listUpFuel(country, current_list, arr);
	       } else {
	    	  listUpAll(country, current_list);
	       }
	});
	$(".chkbox_mission").on("click", function(){
		   var check_count = document.getElementsByName("carlist_mission").length;
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_mission")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_mission")[i].value+"/";
	            }
	        
	        }
	       if (arr != '' ){ 
	    	  listUpMission(country, current_list, arr);
	       } else {
	    	  listUpAll(country, current_list);
	       }
	});
	$(".chkbox_options").on("click", function(){
		   var check_count = document.getElementsByName("carlist_options").length;
		   var arr = "";
	        for (var i=0; i<check_count; i++) {
	            if (document.getElementsByName("carlist_options")[i].checked == true) {
	            	arr+=document.getElementsByName("carlist_options")[i].value+"/";
	            }
	        
	        }
	       if (arr != '' ){ 
	    	  listUpOption(country, current_list, arr);
	       } else {
	    	  listUpAll(country, current_list);
	       }
	});
	// 검색조건_연식
	$("#birth_min").on("click", function(){
		min_year = $("#birth_min > option:selected").val();
		max_year = $("#birth_max > option:selected").val();
	
		listUpYear(country, current_list, min_year, max_year);
	});
	$("#birth_max").on("click", function(){
		min_year = $("#birth_min > option:selected").val();
		max_year = $("#birth_max > option:selected").val();
	
		listUpYear(country, current_list, min_year, max_year);
	});
	// 검색조건_키로수
		$("#km_min").on("click", function(){
		min_km = $("#km_min > option:selected").val();
		max_km = $("#km_max > option:selected").val();
	
		listUpKm(country, current_list, min_km, max_km);
	});
	$("#km_max").on("click", function(){
		min_km  = $("#km_min > option:selected").val();
		max_km = $("#km_max > option:selected").val();
	
		listUpKm(country, current_list, min_km, max_km);
	});
	// 검색조건_가격
	$("#price_min").on("click", function(){
		min_price = $("#price_min > option:selected").val();
		max_price= $("#price_max > option:selected").val();
	
		listUpPrice(country, current_list, min_price, max_price);
	});
	$("#price_max").on("click", function(){
		min_price  = $("#price_min > option:selected").val();
		max_price = $("#price_max > option:selected").val();
	
		listUpPrice(country, current_list, min_price, max_price);
	});
	
	
	// 검색창 keyup script
	$("#input_model").keyup(function(){
	var search_model = $("#input_model").val();
	if (search_model != ''){
		$.ajax({
			url:"${pageContext.request.contextPath}/SearchModel",
			type:"get",
			dataType:"json",
			data:{"detail":search_model},
			success:function(data){
				var arr = data.menu;
				var stmp="";
				var tmp = "";
				for (var i=0; i<arr.length; i++){
					tmp += arr[i].no+"/";
					stmp+="<div class='row' style='margin-top:1%;'>";
					stmp+="<div class='col-sm-3'>";
				//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
					stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
					stmp+="<div class='col-sm-6' style='margin-top:5%'>";
					stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
					stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
					stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
					stmp+="</div>";
					stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
					stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
					stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
					stmp+="</div>";
					stmp+="</div>";
				}
				$(".showCarList").html(stmp);
				$("#cntAll").html(arr.length);
				current_list = tmp;
				listUpAll(country, current_list);
			},error:function(xhr, textStatus, errorThrown){
				console.log(errorThrown);
			}
		});
	} else {
		listUpAll(country);
	}
	});
	
})
// 차종 클릭 스크립트
function keyUp(){
		var search_model = $("#input_model").val();
		if (search_model != ''){
			$.ajax({
				url:"${pageContext.request.contextPath}/SearchModel",
				type:"get",
				dataType:"json",
				data:{"detail":search_model},
				success:function(data){
					var arr = data.menu;
					var stmp="";
					var tmp = "";
					for (var i=0; i<arr.length; i++){
						tmp += arr[i].no+"/";
						stmp+="<div class='row' style='margin-top:1%;'>";
						stmp+="<div class='col-sm-3'>";
					//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
						stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
						stmp+="<div class='col-sm-6' style='margin-top:5%'>";
						stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
						stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
						stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
						stmp+="</div>";
						stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
						stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
						stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
						stmp+="</div>";
						stmp+="</div>";
					}
					$(".showCarList").html(stmp);
					$("#cntAll").html(arr.length);
					current_list = tmp;
					listUpAll(country, current_list);
				},error:function(xhr, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
		} else {
			listUpAll(country);
		}
}

function color(name){	
	var test = document.getElementById(name);
	var arr = name.split("_");
	$("#"+name).css("background-color", "#fdf5f4");
	$("#"+name+" label").css("background-color", "#fdf5f4");
	$("#"+name+" label").css("color", "#dc232d");
	$("#"+name+" label").css("font-weight", "bold");
	$("#"+name).css("border-left-color", "#dc232d");
	$("#"+name).css("border", "1px solid #dc232d");
	$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#dc232d");
	$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#dc232d");
	
	if ($("#category_"+arr[1]).prop("checked")){
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
	if ($("#cbox_fuel_"+arr[1]).prop("checked")){
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
	if ($("#cbox_mission_"+arr[1]).prop("checked")){
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
}
function color2(name){
	$("#"+name).css("color", "#dc232d");
	$("#"+name).css("font-weight", "bold");
	
	var test = document.getElementById(name);
	var arr = name.split("_");
	if ($("#cbox_color_"+arr[1]).prop("checked")){
		$("#"+name).css("color", "#555");
		$("#"+name).css("font-weight", "normal");
	}
	
}
function listUpAll(country, current_list, no){
	var tmp = "";
	$("#pagingList").empty();
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListAjax",
		async:false,
		type:"get",
		dataType:"json",
		data:{"country":country, "no":no, "list":current_list},
		success:function(data){
			var stmp="";
			var arr = data.menu;
			var arr2 = data.menu2;	
			var list = arr[arr.length-1];
			
			for (var i=0; i<arr2.length; i++){
				tmp += arr2[i].no+"/";
			}
			
			for (var i=0; i<arr.length; i++){
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			
			
			var pag = data.pag;
			var bottomList = pag.bottomList;
			var bottom_current = pag.bottom_current;
			var bottom_end = pag.bottom_end;
			var bottom_start = pag.bottom_start;
			var onepageLimit = pag.onepageLimit;
			var pageAll = pag.pageAll;
			var pageTotal = pag.pageTotal;
			var pstartno = pag.pstartno;
		 	
			if (bottom_start > bottomList){}
		
			var stmp2 = "<ul class='pagination'>";	
			if (bottom_start > bottomList){ stmp2+="<input type='button' class='btn btn-danger' onclick='window.scrollTo(0,0)' id='prev_btn' value='<'><input type='hidden' value='"+(bottom_start-onepageLimit)*onepageLimit+"'>"; }
			for (var i=bottom_start; i <= bottom_end; i++){
				stmp2+="<li <input type='button' class='btn btn-danger next_page' onclick='window.scrollTo(0,0)'>"+i+"<input type='hidden' value='"+(i-1)*onepageLimit+"'></li>";
			}   
			if (pageAll>bottom_end){ stmp2+="<input type='button' class='btn btn-danger' onclick='window.scrollTo(0,0)' id='next_btn' value='>'><input type='hidden' value='"+bottom_end*onepageLimit+"'>"; }
			stmp2+="</ul>";
			$(".showCarList").html(stmp);
			$("#pagingList").html(stmp2);
			if (data.menu.length != 0) {
				$("#cntAll").html(pag.pageTotal);
			} else {
				$("#cntAll").html(0);
			}
			current_list = tmp;
			$(".next_page").on("click", function(){
				var no = ($(this).text()-1)*onepageLimit;
				listUpAll(country,current_list,no);
			});
			$("#prev_btn").on("click", function(){
				var no = $(this).next().val();
				listUpAll(country,current_list, no);
			});
			$("#next_btn").on("click", function(){
				var no = $(this).next().val();
				listUpAll(country,current_list, no);
			});
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
	return tmp;
}
function countMakers(country){
	$.ajax({
		url:"${pageContext.request.contextPath}/CountMakers",
		async:false,
		type:"get",
		dataType:"json",
		data:{"country":country},
		success:function(data){
			var arr = data.menu;
			var stmp = "";
			for (var i=0; i<arr.length; i++){					
				stmp += "<input type=\"checkbox\" name=\"carlist_maker\" value=\""+arr[i].maker+"\" id='"+arr[i].maker+"'>";
				stmp += "<label for='"+arr[i].maker+"' class='lb_check'>"+arr[i].maker+"</label><span style='margin-left:2%; font-weight:bold;'>"+arr[i].cnt+"</span><br/>";					
			}
			$("#search_category_maker_model").html(stmp);
			
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function countMakersCategory(country, chk_category){
	$.ajax({
		url:"${pageContext.request.contextPath}/CountMakers",
		type:"post",
		dataType:"json",
		data:{"country":country, "category":chk_category},
		success:function(data){
			var arr = data.menu;
			var stmp = "";
			for (var i=0; i<arr.length; i++){					
				stmp += "<input type=\"checkbox\" name=\"carlist_maker\" value=\""+arr[i].maker+"\" id='"+arr[i].maker+"'>";
				stmp += "<label for='"+arr[i].maker+"' class='lb_check'>"+arr[i].maker+"</label><span style='margin-left:2%; font-weight:bold;'>"+arr[i].cnt+"</span><br/>";					
			}
			$("#search_category_maker_model").html(stmp);
			
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function reColor(){
	for (var i=1; i<=9; i++){
		$("input:checkbox[id='category_"+i+"']").prop("checked", false);
		var name = "color_"+i;
		var arr = name.split("_");
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
	for (var i=1; i<=9; i++){
		$("input:checkbox[id='fuel"+i+"']").prop("checked", false);
		var name = "fuel_"+i;
		var arr = name.split("_");
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
	for (var i=1; i<=9; i++){
		$("input:checkbox[id='mission"+i+"']").prop("checked", false);
		var name = "mission_"+i;
		var arr = name.split("_");
		$("#"+name).css("background-color", "#FFFFFF");
		$("#"+name+" label").css("background-color", "#FFFFFF");
		$("#"+name+" label").css("color", "#555");
		$("#"+name+" label").css("font-weight", "normal");
		$("#"+name).css("border-left-color", "#ddd");
		$("#"+name).css("border", "1px solid #ddd");
		$("#"+arr[0]+"_"+(arr[1]-1)).css("border-right-color","#ddd");
		$("#"+arr[0]+"_"+(arr[1]-3)).css("border-bottom-color","#ddd");
	}
	$("#search_category_color label").css("color", "#555");
	$("#search_category_color label").css("font-weight", "normal");
	document.getElementById("birth_min").value = "2000";
	document.getElementById("birth_max").value = "2020";
	document.getElementById("km_min").value = "10000";
	document.getElementById("km_max").value = "200000";
}
function listUpYear(country, current_list, min_year, max_year){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListAjax",
		type:"post",
		dataType:"json",
		data:{"list":current_list, "minyear":min_year, "maxyear":max_year},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function listUpKm(country, current_list, min_km, max_km){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListKm",
		type:"get",
		dataType:"json",
		data:{"list":current_list, "minkm":min_km, "maxkm":max_km},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function listUpPrice(country, current_list, min_price, max_price){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListPrice",
		type:"get",
		dataType:"json",
		data:{"list":current_list, "minprice":min_price, "maxprice":max_price},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function listUpPriceMaker(country, sch_maker, minp, maxp, current_list){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListPrice",
		type:"post",
		dataType:"json",
		data:{"maker":sch_maker, "minprice":minp, "maxprice":maxp},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
		}
	});
}
function listUpColor(country, current_list, colors){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListColor",
		async:false,
		type:"get",
		dataType:"json",
		data:{"list":current_list, "colors":colors},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list=tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function listUpFuel(country, current_list, fuel){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListFuel",
		type:"get",
		dataType:"json",
		data:{"list":current_list, "fuel":fuel},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}
function listUpMission(country, current_list, mission){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListMission",
		type:"get",
		dataType:"json",
		data:{"list":current_list, "mission":mission},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}

function listUpOption(country, current_list, option){
	$.ajax({
		url:"${pageContext.request.contextPath}/CarListOption",
		type:"get",
		dataType:"json",
		data:{"list":current_list, "option":option},
		success:function(data){
			var arr = data.menu;	
			var stmp="";
			var tmp = "";
			for (var i=0; i<arr.length; i++){
				tmp += arr[i].no+"/";
				stmp+="<div class='row' style='margin-top:1%;'>";
				stmp+="<div class='col-sm-3'>";
			//	stmp+="<img src='/upload/"+arr[i].image+"' alt='"+arr[i].image+"'> </div>";
				stmp+="<a href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'><img src='${pageContext.request.contextPath}/upload/"+arr[i].image+"' alt='"+arr[i].image+"'></a> </div>";
				stmp+="<div class='col-sm-6' style='margin-top:5%'>";
				stmp+="<div id='car_title'><a id='carName' href='${pageContext.request.contextPath}/detailCar.do?no="+arr[i].no+"'>"+arr[i].maker+"&nbsp;"+arr[i].detail+"</a></div>";
				stmp+="<div id='car_desc'><span>"+arr[i].birth.toString().substring(0,2)+"년&nbsp;"+arr[i].birth.toString().substring(2)+"월식</span></div>";
				stmp+="<span id='car_km'>"+arr[i].km+"km</span><span id='car_fuel'>"+arr[i].fuel+"</span><span id='car_city'>"+arr[i].city+"</span><span id='car_accident'>"+arr[i].accident+"</span>";
				stmp+="</div>";
				stmp+="<div class='col-sm-3' style='margin-top:5%;'>";
				stmp+="<span style='font-size:20px; color:#da2432;'><strong style='color:#da2432; font-size:30px;'>"+arr[i].price+"</strong>만원</span>";
				stmp+="<p style='margin-left:20px'>할부 월<span style='color:#dc232d'>"+Math.ceil(arr[i].price/48)+"</span>만원</p>";
				stmp+="</div>";
				stmp+="</div>";
			}
			$(".showCarList").html(stmp);
			$("#cntAll").html(arr.length);
			current_list = tmp;
			listUpAll(country, current_list);
		},error:function(xhr, textStatus, errorThrown){
			console.log(errorThrown);
		}
	});
}

</script>
<input type="hidden" value="${param.maker_2}" id="search_maker2">
<input type="hidden" value="${param.min_price}" id="search_min_price"> 
<input type="hidden" value="${param.max_price}" id="search_max_price"> 
<input type="hidden" value="${param.search}" id="sch_search">
<input type="hidden" id="cur_list">
<div id="search_condition">
	<div class="row">
	<div class="container">
	<div class="col-sm-3">
	<h2>차량 검색<span><a href="${pageContext.request.contextPath}/carList.do"><img src="${pageContext.request.contextPath}/images/reset_icon.png" alt="reset_icon" style="margin-left:30px;"></a></span></h2>
	<input type="text" class="form-control" id="input_model" style="display: inline; width: 30%; height: 45px; margin-left: -2%; float: left; background-color: #F4F4F4; border-radius: 5px; padding: 0 50px 0 20px; box-sizing: border-box; border: 0; line-height: 55px; width: 270px; height: 55px; color: #555; font-size: 14px; cursor: text;" placeholder="제조사명 또는 모델명 입력" <c:if test="${param.search != null}">value="${param.search}"</c:if> >
	<button type="button" id="searc" class="glyphicon glyphicon-search" onclick="keyUp();"></button><br/>
	<div id="search_import_tab">	
	<button class="btn btn-default" id="category_all">전체</button>
	<button class="btn btn-default" id="category_home">국산차</button>
	<button class="btn btn-default" id="category_import">수입차</button>
	</div>
	<div id="search_category_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_category_tab.png" alt="차량 카테고리 선택 이미지" id="search_category_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_category_tab2.png" alt="차량 카테고리 선택 이미지" id="search_category_btn_down"/>
	<div id="search_category_content">
	<c:set var="item" value="${category}"/>
	<table class="table-bordered">
	<tbody>
	<tr>
	<c:forEach var="i" begin="1" end="3" varStatus="status">
	<td id="color_${i}"><input type="checkbox" name="carlist_category" value="${item[i-1]}" id="category_${i}" class="chkbox_category"><label for="category_${i}" class="lb_check" onclick="color('color_${i}')">${item[i-1]}</label></td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="4" end="6" varStatus="status">
	<td id="color_${i}"><input type="checkbox" name="carlist_category" value="${item[i-1]}" id="category_${i}" class="chkbox_category"><label for="category_${i}" class="lb_check" onclick="color('color_${i}')">${item[i-1]}</label></td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="7" end="9" varStatus="status">
	<td id="color_${i}"><input type="checkbox" name="carlist_category" value="${item[i-1]}" id="category_${i}" class="chkbox_category"><label for="category_${i}" class="lb_check" onclick="color('color_${i}')">${item[i-1]}</label></td>
	</c:forEach>
	</tr>
	</tbody>
	</table>
	</div>
	<div id="search_maker_model_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_maker_model_tab.png" alt="차량 메이커/모델 선택 이미지" id="search_maker_model_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_maker_model_tab2.png" alt="차량 메이커/모델 선택 이미지" id="search_maker_model_btn_down"/>
	<div id="search_category_maker_model">
	</div>
	</div>	
	</div>
	
	
	<div id="search_birth_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_birth_tab.png" alt="차량 연식 선택 이미지" id="search_birth_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_birth_tab2.png" alt="차량 연식 선택 이미지" id="search_birth_btn_down"/>
	
	
	<div id="search_category_birth">
	<div class="row">
	<div class="col-sm-5">
	<select name="birth_min" class="form-control birth_min" id="birth_min">
	<c:forEach var="cnt" begin="0" end="20" step="1" varStatus="status">
	<c:if test="${cnt==0}"><option value="2000" selected>최소</option></c:if>
	<option value="${2020-status.index}">${2020-status.index}년</option>
	</c:forEach>
	</select>
	</div>
	<div class="col-sm-1 birth_center">~</div>
	<div class="col-sm-6">
	<select name="birth_max" class="form-control birth_max" id="birth_max">
	<c:forEach var="cnt" begin="0" end="20" step="1" varStatus="status">
	<c:if test="${cnt==0}"><option value="2020" selected>최대</option></c:if>
	<option value="${2020-status.index}">${2020-status.index}년</option>
	</c:forEach>
	</select>
	</div>
	</div>
	</div>
	</div> 
		
		
	<div id="search_km_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_km_tab.png" alt="차량 주행거리 선택 이미지" id="search_km_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_km_tab2.png" alt="차량 주행거리 선택 이미지" id="search_km_btn_down"/>
	<div id="search_category_km">
	<div class="row">
	<div class="col-sm-5">
	<select name="km_min" class="form-control km_min" id="km_min">
	<c:forEach var="cnt" begin="10000" end="200000" step="10000" varStatus="status">
	<c:if test="${cnt==10000}"><option value="10000" selected>최소</option></c:if>
	<c:if test="${cnt>=10000}"></c:if>
	<c:choose>
	<c:when test="${cnt<=100000}"><option value="${cnt}">${cnt.toString().substring(0,2)},${cnt.toString().substring(2)}km</option></c:when>
	<c:when test="${cnt>100000}"><option value="${cnt}">${cnt.toString().substring(0,3)},${cnt.toString().substring(3)}km</option></c:when>
	</c:choose>
	</c:forEach>
	</select>
	</div>
	<div class="col-sm-1 km_center">~</div>
	<div class="col-sm-6">
	<select name="km_max" class="form-control km_max" id="km_max">
	<c:forEach var="cnt" begin="10000" end="200000" step="10000" varStatus="status">
	<c:if test="${cnt==10000}"><option value="200000" selected>최대</option></c:if>
	<c:choose>
	<c:when test="${cnt<=100000}"><option value="${cnt}">${cnt.toString().substring(0,2)},${cnt.toString().substring(2)}km</option></c:when>
	<c:when test="${cnt>100000}"><option value="${cnt}">${cnt.toString().substring(0,3)},${cnt.toString().substring(3)}km</option></c:when>
	</c:choose>
	</c:forEach>
	</select>
	</div>
	</div>
	</div>
	</div>
	
	<div id="search_price_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_price_tab.png" alt="차량 가격 선택 이미지" id="search_price_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_price_tab2.png" alt="차량 가격 선택 이미지" id="search_price_btn_down"/>
	<div id="search_category_price">
	<div class="row">
	<div class="col-sm-5">
	<select name="price_min" class="form-control price_min" id="price_min">
	<c:forEach var="cnt" begin="100" end="999" step="100" varStatus="status">
	<c:if test="${cnt==100}"><option value="100">최소</option></c:if>
	<option value="${cnt}">${cnt}만원</option>
	</c:forEach>
	<c:forEach var="cnt" begin="1000" end="10000" step="1000" varStatus="status">
	<c:if test="${cnt==10000}"><option value="10000">10,000만원 이상</option></c:if>
	<c:choose>
	<c:when test="${cnt<10000}"><option value="${cnt}">${cnt.toString().substring(0,1)},${cnt.toString().substring(1)}만원</option></c:when>
	</c:choose>
	</c:forEach>
	</select>
	</div>
	<div class="col-sm-1 price_center">~</div>
	<div class="col-sm-6">
	<select name="price_max" class="form-control price_max" id="price_max">
	<c:forEach var="cnt" begin="100" end="1000" step="100" varStatus="status">
	<c:if test="${cnt==100}"><option value="100000">최대</option></c:if>
	<option value="${cnt}">${cnt}만원</option>
	</c:forEach>
	<c:forEach var="cnt" begin="2000" end="10000" step="1000" varStatus="status">
		<c:choose>
	<c:when test="${cnt<10000}"><option value="${cnt}">${cnt.toString().substring(0,1)},${cnt.toString().substring(1)}만원</option></c:when>
	</c:choose>
	<c:if test="${cnt==10000}"><option value="100000">10,000만원 이상</option></c:if>
	</c:forEach>
	</select>
	</div>
	</div>
	</div>
	</div>
	
	<div id="search_color_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_color_tab.png" alt="차량 색상 선택 이미지" id="search_color_btn_up"/>
	<img src="${pageContext.request.contextPath}/images/search_color_tab2.png" alt="차량 색상 선택 이미지" id="search_color_btn_down"/>

	<div id="search_category_color">
	<c:forEach var="color" items="${colors}" varStatus="status">
	<input type="checkbox" name="carlist_color" value="${color}" id="cbox_color_${status.count}" class="chkbox_color"><label for="cbox_color_${status.count}" class="lb_check" id="lbcheck_${status.count}" onclick="color2('lbcheck_${status.count}')"><img src="${pageContext.request.contextPath}/color/${color}.png" alt="${color}">${color}</label>
	</c:forEach>
	</div>
	</div>
	
	
	<div id="search_fuel_tab" style="display:block">
	<img src="${pageContext.request.contextPath}/images/search_fuel_tab.png" alt="차량 연료 선택 이미지" id="search_fuel_btn_down"/>
	<img src="${pageContext.request.contextPath}/images/search_fuel_tab2.png" alt="차량 연료 선택 이미지" id="search_fuel_btn_up"/>
	<div id="search_category_fuel">
	<table class="table-bordered">
	<tbody>

	<tr>
	<c:forEach var="i" begin="1" end="2">
	<td id="fuel_${i}"><input type="checkbox" name="carlist_fuel" value="${fuel[i-1]}" id="cbox_fuel_${i}" class="chkbox_fuel"><label for="cbox_fuel_${i}" class="lb_check" onclick="color('fuel_${i}')">${fuel[i-1]}</label></td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="3" end="4">
	<td id="fuel_${i}"><input type="checkbox" name="carlist_fuel" value="${fuel[i-1]}" id="cbox_fuel_${i}" class="chkbox_fuel"><label for="cbox_fuel_${i}" class="lb_check" onclick="color('fuel_${i}')">${fuel[i-1]}</label></td>
	</c:forEach>
	</tr>
	</tbody>
	</table>
	</div>
	</div>
	
	<div id="search_mission_tab" style="display:block;">
	<img src="${pageContext.request.contextPath}/images/search_mission_tab.png" alt="차량 변속기 선택 이미지" id="search_mission_btn_down"/>
	<img src="${pageContext.request.contextPath}/images/search_mission_tab2.png" alt="차량 변속기 선택 이미지" id="search_mission_btn_up"/>
	<div id="search_category_mission">
	<table class="table-bordered">
	<tbody>
	<tr>
	<c:forEach var="item" items="${mission}" varStatus="status">
	<td id="mission_${status.count}"><input type="checkbox" name="carlist_mission" value="${item}" id="cbox_mission_${status.count}" class="chkbox_mission"><label for="cbox_mission_${status.count}" class="lb_check" onclick="color('mission_${status.count}')">${item}</label></td>
	</c:forEach>
	</tr>
	</tbody>
	</table>
	</div>
	</div>
	
	<div id="search_options_tab" style="display:block;">
	<img src="${pageContext.request.contextPath}/images/search_options_tab.png" alt="차량 옵션 선택 이미지" id="search_options_btn_down"/>
	<img src="${pageContext.request.contextPath}/images/search_options_tab2.png" alt="차량 옵션 선택 이미지" id="search_options_btn_up"/>
	<div id="search_category_options">
	<c:forEach var="item" items="${options}" varStatus="status">
	<input type="checkbox" name="carlist_options" value="${item}" id="cbox_options_${status.count}" class="chkbox_options"><label for="cbox_options_${status.count}" class="lb_check">${item}</label><br/>
	</c:forEach>
	</div>
	</div>
	
	
	</div> <!-- end -->
	<div class="col-sm-9">
	
	<h4>총 <span style="color:#dc232d; font-weight:bold;" id="cntAll"></span>대<span><input type="text" class="form-control text-center" id="search_seller" placeholder="판매담당자 이름 입력"></span></h4>
	<hr>
		<div class="showCarList">

	</div>
	
	</div>
	
	</div>
	<div id="pagingList" style="text-align:center">
	
	</div>
	</div>
	
</div>
<%@include file="../inc/footer.jsp"%>