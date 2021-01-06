<%@page import="com.jyeob.dto.SellerDto"%>
<%@page import="com.jyeob.dao.SellerDao"%>
<%@page import="com.jyeob.dao.MemberDao"%>
<%@page import="com.jyeob.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../inc/header.jsp"%>
<%
MemberDto dto = new MemberDao().findAccount(request.getParameter("id"));
SellerDto info = new SellerDao().findMyOrder2(dto.getId());
pageContext.setAttribute("item", info);
%>
<div class="container">
<div class="container" style="background-color:#FFFFFF; margin-top:2%;">
<form action="<%=request.getContextPath()%>/addListFinal.do" method="post" id="act" enctype="multipart/form-data">
<fieldset>
<legend><strong>차량 등록</strong></legend>
<div class="form-group">
<label for="writer">판매자</label>
<input type="text" id="writer" class="form-control" value="${param.id}" disabled>
<input type="hidden" name="writer" value="${param.id}">
<input type="hidden" name="nom" value="${param.no}">
</div>
<div class="form-group">
<label for="country">지역</label>
<input type="text" name="country" id="country" class="form-control" placeholder="국산/수입" value="${item.country}">
</div>
<div class="form-group">
<strong>차종</strong><br/>
<input type="radio" id="category1" name="category" value="경차"><label for="category1">경차</label>
<input type="radio" id="category2" name="category" value="소형차"><label for="category2">소형차</label>
<input type="radio" id="category3" name="category" value="준중형차"><label for="category3">준중형차</label>
<input type="radio" id="category4" name="category" value="중형차"><label for="category4">중형차</label>
<input type="radio" id="category5" name="category" value="대형차"><label for="category5">대형차</label>
<input type="radio" id="category6" name="category" value="스포츠카"><label for="category6">스포츠카</label>
<input type="radio" id="category7" name="category" value="SUV"><label for="category7">SUV</label>
<input type="radio" id="category8" name="category" value="승합차"><label for="category8">승합차</label>
<input type="radio" id="category9" name="category" value="버스"><label for="category9">버스</label>
</div>
<div class="form-group">
<label for="maker">제조사</label>
<select name="maker" id="maker" class="form-control">
<option value="" selected>제조사 선택</option>
<option>------------------국산------------------</option>
<option value="현대">현대</option>
<option value="제네시스">제네시스</option>
<option value="기아">기아</option>
<option value="쉐보레(GM대우)">쉐보레(GM대우)</option>
<option value="르노삼성">르노삼성</option>
<option value="쌍용">쌍용</option>
<option value="">------------------수입------------------</option>
<option value="벤츠">벤츠</option>
<option value="BMW">BMW</option>
<option value="아우디">아우디</option>
<option value="폭스바겐">폭스바겐</option>
<option value="도요타">도요타</option>
<option value="렉서스">렉서스</option>
<option value="혼다">혼다</option>
<option value="미니">미니</option>
<option value="포르쉐">포르쉐</option>
<option value="랜드로버">랜드로버</option>
</select>
</div>
<div class="form-group">
<label for="model">모델</label>
<input type="text" name="model" id="model" class="form-control" value="${item.model}">
</div>
<div class="form-group">
<label for="birth">연식</label>
<input type="text" name="birth" id="birth" class="form-control" value="${item.birth}" placeholder="숫자 네자리로 입력해주세요 예시)1904 -> 19년04월식">
</div>
<div class="form-group">
<label for="city">지점선택</label>
<select name="city" id="city" class="form-control">
<option value="" selected>지점 선택</option>
<option value="강남직영점">강남직영점</option>
<option value="구로직영점">구로직영점</option>
<option value="목동직영점">목동직영점</option>
<option value="서서울직영점">서서울직영점</option>
<option value="서초직영점">서초직영점</option>
<option value="성동직영점">성동직영점</option>
<option value="영등포직영점">영등포직영점</option>
<option value="장한평직영점">장한평직영점</option>
<option value="강서직영점">강서직영점</option>
<option value="경기서부직영점">경기서부직영점</option>
<option value="경인직영점">경인직영점</option>
<option value="분당용인직영점">분당용인직영점</option>
<option value="수원신갈직영점">수원신갈직영점</option>
<option value="수원직영점">수원직영점</option>
<option value="부산직영점">부산직영점</option>
<option value="서부산직영점">서부산직영점</option>
<option value="양산직영점">양산직영점</option>
<option value="울산직영점">울산직영점</option>
<option value="창원마산직영점">창원마산직영점</option>
<option value="구미직영점">구미직영점</option>
<option value="대구직영점">대구직영점</option>
<option value="대구반야월직영점">대구반야월직영점</option>
<option value="서대구직영점">서대구직영점</option>
<option value="광주송암직영점">광주송암직영점</option>
<option value="전주송암직영점">전주송암직영점</option>
<option value="대전유성직영점">대전유성직영점</option>
<option value="대전직영점">대전직영점</option>
<option value="천안직영점">천안직영점</option>
</select>
</div>
<div class="form-group">
<label for="detail">세부모델</label>
<input type="text" name="detail" id="detail" class="form-control">
</div>
<div class="form-group">
<label for="km">키로수</label>
<input type="text" name="km" id="km" class="form-control" placeholder="숫자로만 입력이 가능합니다.">
</div>
<div class="form-group">
<label for="price">차량가격</label>
<input type="text" name="price" id="price" class="form-control" placeholder="만원단위로 입력해주세요. 예) 2400 - 2400만원">
</div>
<div class="form-group">
<label for="color">색상</label>
<select name="color" id="color" class="form-control">
<option value="" selected>색상 선택</option>
<option value="흰색">흰색</option>
<option value="진주색">진주색</option>
<option value="검정색">검정색</option>
<option value="검정투톤">검정투톤</option>
<option value="쥐색">쥐색</option>
<option value="은색">은색</option>
<option value="청색">청색</option>
<option value="은색투톤">은색투톤</option>
<option value="빨간색">빨간색</option>
</select>
</div>
<div class="form-group">
<strong>연료</strong><br/>
<input type="radio" id="fuel1" name="fuel" value="가솔린"><label for="fuel1">가솔린</label>
<input type="radio" id="fuel2" name="fuel" value="디젤"><label for="fuel2">디젤</label>
<input type="radio" id="fuel3" name="fuel" value="LPG"><label for="fuel3">LPG</label>
<input type="radio" id="fuel4" name="fuel" value="전기"><label for="fuel4">전기</label>
</div>
<div class="form-group">
<strong>변속기</strong><br/>
<input type="radio" id="mission1" name="mission" value="오토"><label for="mission1">오토</label>
<input type="radio" id="mission2" name="mission" value="수동"><label for="mission2">수동</label>
</div>
<div class="form-group">
<label>옵션</label><br/>
<input type="checkbox" id="선루프" name="options" value="선루프"><label for="선루프">선루프</label>
<input type="checkbox" id="네비게이션" name="options" value="네비게이션"><label for="네비게이션">네비게이션</label>
<input type="checkbox" id="스마트키" name="options" value="스마트키"><label for="스마트키">스마트키</label>
<input type="checkbox" id="열선시트" name="options" value="열선시트"><label for="열선시트">열선시트</label>
<input type="checkbox" id="후방카메라" name="options" value="후방카메라"><label for="후방카메라">후방카메라</label>
<input type="checkbox" id="가죽시트" name="options" value="가죽시트"><label for="가죽시트">가죽시트</label>
<input type="checkbox" id="후방감지센서" name="options" value="후방감지센서"><label for="후방감지센서">후방감지센서</label>
<input type="checkbox" id="사이드에어백" name="options" value="사이드에어백"><label for="사이드에어백">사이드에어백</label>
<input type="checkbox" id="하이패스룸미러" name="options" value="하이패스룸미러"><label for="하이패스룸미러">하이패스룸미러</label>
</div>
<div class="form-group">
<label for="accident">사고유무</label><br/>
<input type="radio" id="accident1" name="accident" value="무사고"><label for="accident1">무사고</label>
<input type="radio" id="accident2" name="accident" value="단순교환"><label for="accident2">단순교환</label>
<input type="radio" id="accident3" name="accident" value="단순사고(접촉)"><label for="accident3">단순사고(접촉)</label>
<input type="radio" id="accident4" name="accident" value="사고"><label for="accident4">사고</label>
</div>
<div class="form-group">
<strong>인승</strong><br/>
<input type="radio" id="seater1" name="seater" value="4인승"><label for="seater1">4인승</label>
<input type="radio" id="seater2" name="seater" value="5인승"><label for="seater2">5인승</label>
<input type="radio" id="seater3" name="seater" value="9인승"><label for="seater3">9인승</label>
<input type="radio" id="seater4" name="seater" value="11인승"><label for="seater4">11인승</label>
</div>
<div class="form-group">
<label for="content">내용</label>
<textarea id="content" name="content" rows="10" cols="100" class="form-control"></textarea>
</div>
<div class="form-group">
<input type="file" name="image" id="image" class="form-control">
</div>
<div class="form-group" style="text-align:center">
<input type="submit" class="btn btn-danger btn-lg" value="등록">
<a href="javascript:history.go(-1);" class="btn btn-danger btn-lg">목록</a>
</div>
</fieldset>
</form>
</div>
</div>
<script src="//cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
		<script>
		CKEDITOR.replace('content');
		</script>
<%@include file="../inc/footer.jsp"%>