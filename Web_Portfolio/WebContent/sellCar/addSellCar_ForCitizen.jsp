<%@page import="com.jyeob.dto.SellerDto"%>
<%@page import="com.jyeob.dao.SellerDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@page import="com.jyeob.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sellCar.css">
<script>
$(function(){
	$("#chk_one").on("click", function(){
		if($("input:checkbox[id=chk_one]").is(":checked") == true) {
			$("#lb_chk").css("font-weight", "bold");
			$("#lb_chk").css("color", "black");	
		} else{
			$("#lb_chk").css("font-weight", "normal");
			$("#lb_chk").css("color", "#888");
		}
	});
	$("#chk_modal").on("click", function(){
		 $("#myModal").modal();
	});
	$("#act").submit(function(){
		var chk = $("#id").val();
	
		if (chk == ''){
			alert("로그인이 필요한 컨텐츠입니다.");
			location.href="<%=request.getContextPath()%>/loginView.do";
			return false;
		}
		if (isNaN($("#birth").val())){
			alert("연식은 숫자로만 입력이 가능합니다. 예) 1901");
			return false;
		}
		if ($("input:checkbox[id='chk_one']").is(":checked") == false){
			alert("개인정보 수집에 동의하지 않으셨습니다.");
			return false;
		}
		if ($("input[type=text]").val() == ""){
			alert("입력하지 않은 항목이 있습니다.");
			return false;
		}
		if ($("#pass").val() != $("#pw").val()){
			alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
			return false;
		}
		
	});
	$("#chk_myInfo").on("click", function(){
		var chk = $("#id").val();
		if (chk == ''){
			alert("로그인이 필요한 컨텐츠입니다.");
			location.href="<%=request.getContextPath()%>/loginView.do";
			return false;
		}
	});
});
</script>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
pageContext.setAttribute("pw", dto.getPassword());
pageContext.setAttribute("aa", dto.getId());
SellerDto info = new SellerDao().findMyOrder(dto.getId());
%>

<img src="${pageContext.request.contextPath}/images/sellCarBanner.png" alt="차량 판매 배너 이미지" style="width:100%;">
<div class="container text-center">
<form action="${pageContext.request.contextPath}/requestSellCarAdd.do" method="post" id="act">
<fieldset>
<h1 id="sell_title">내차팔기 <span style="font-weight:bold">간편신청</span></h1>
<p id="sell_tit">단 1분이면 신청 끝! 쉽고 빠르게 내 차 가격을 알아보세요.</p>
<div class="row">
<p id="sell_cs">아래 정보를 입력해주시면 검토 뒤 결과를 연락처로 발송드리겠습니다. </p>
<div class="col-sm-6">
<p style="text-align:left;">개인정보 입력</p>
<div class="form group text-left">
<label for="name">이름<span>*</span></label>
<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하여주세요">
</div><br/><br/>
<div class="form group text-left">
<label for="phone">휴대전화<span>*</span></label>
<input type="text" id="phone" name="phone" class="form-control" placeholder="전화번호 예시)010-1234-5678">
</div><br/><br/>
<div class="form group text-left">
<label for="pass">비밀번호 입력<span>*</span></label>
<input type="password" id="pass" name="pass" class="form-control" placeholder="비밀번호를 입력해주세요.">
</div>
</div>

<div class="col-sm-6">
<p style="text-align:left;">차량정보 입력</p>
<div class="form group text-left">
<label for="country">국산/수입<span>*</span></label>
<input type="text" id="country" name="country" class="form-control" placeholder="국산/수입">
</div><br/><br/>
<div class="form group text-left">
<label for="model">차량명<span>*</span></label>
<input type="text" id="model" name="model" class="form-control" placeholder="예시)소나타">
</div><br/><br/>
<div class="form group text-left">
<label for="birth">연식<span>*</span></label>
<input type="text" id="birth" name="birth" class="form-control" placeholder="연식 예시)1901">
</div>
</div>
</div>
<br/>
<hr/>
<div class="text-left" style="width:35%;">
<input type="checkbox" id="chk_one">
 <label for="chk_one" id="lb_chk">개인정보수집 및 이용에 동의합니다</label><span id="chk_modal">내용보기</span>

</div>
<div class="form-group">
<input type="hidden" id="pw" value="${pw}">
<input type="hidden" id="id" name="id" value="${aa}">
<input type="submit" class="btn btn-default" id="smit" style="visibility: hidden;"><label for="smit" style="cursor:pointer;"><img src="${pageContext.request.contextPath}/images/신청하기.png" alt=""></label>
<a href="<%=request.getContextPath()%>/myPageOrderSell.do?id=<%=dto.getId()%>" id="chk_myInfo"><img src="${pageContext.request.contextPath}/images/신청내역확인.png" alt=""></a>

</div>
</fieldset>
</form>

</div>
<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-left">개인정보 취급동의</h4>
        </div>
        <div class="modal-body">
         <img src="${pageContext.request.contextPath}/images/modal.png" alt="" id="modal_img">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
        </div>
      </div>
      
    </div>
  </div>
   


<%@include file="../inc/footer.jsp"%>