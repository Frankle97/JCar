<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
  <div id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <!-- <ol class="carousel-indicators">
      <li class="item1 active"></li>
      <li class="item2"></li>
      <li class="item3"></li>
      <li class="item4"></li>
    </ol> -->

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <a href="<%=request.getContextPath()%>/carList.do"><img src="<%=request.getContextPath()%>/images/banner1.png" alt="banner1.png" style="width:100%;"></a>
      </div>

      <div class="item">
        <a href="<%=request.getContextPath()%>/carList.do"><img src="<%=request.getContextPath()%>/images/banner2.png" alt="banner2.png" style="width:100%;"></a>
      </div>
    
      <div class="item">
        <a href="<%=request.getContextPath()%>/carList.do"><img src="<%=request.getContextPath()%>/images/banner3.png" alt="banner3.png" style="width:100%;"></a>
      </div>

      <div class="item">
        <a href="<%=request.getContextPath()%>/carList.do"><img src="<%=request.getContextPath()%>/images/banner4.png" alt="banner4.png" style="width:100%;"></a>
      </div>
    </div>

    <a class="left carousel-control" role="button">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" role="button">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

<script>
$(document).ready(function(){
  // Activate Carousel
  $("#myCarousel").carousel({interval: 5000});
    
  // Enable Carousel Indicators
  $(".item1").click(function(){
    $("#myCarousel").carousel(0);
  });
  $(".item2").click(function(){
    $("#myCarousel").carousel(1);
  });
  $(".item3").click(function(){
    $("#myCarousel").carousel(2);
  });
  $(".item4").click(function(){
    $("#myCarousel").carousel(3);
  });
    
  // Enable Carousel Controls
  $(".left").click(function(){
    $("#myCarousel").carousel("prev");
  });
  $(".right").click(function(){
    $("#myCarousel").carousel("next");
  });
});
</script>