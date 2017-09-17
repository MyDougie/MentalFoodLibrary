<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.carousel .indicators {
  position: absolute;
  text-align: center;
  left: 0;
  right: 0;
  bottom: 0;
  margin: 0;
  background-color: transparent;
}

.carousel .indicators .indicator-item {
  display: inline-block;
  position: relative;
  cursor: pointer;
  height: 8px;
  width: 8px;
  margin: 24px 4px;
 /* background-color: rgba(255, 255, 255, 0.5); */ 
  background-color:rgba(0, 0, 0, 0.5);
  transition: background-color .3s;
  border-radius: 50%;
}

.carousel .indicators .indicator-item.active {
 /*  background-color: #fff; */
 background-color:#08ab8f;
}
</style>
<script>
	$(document).ready(function() {
		$('.carousel').carousel();
		
		$('.carousel.carousel-slider').carousel({
			fullWidth : true
		});

		$("#search").blur(function() {
			$("#search").val("");
			$("#search").focus();
		})
		
		
	});
</script>
</head>
<body>

	<!-- 이미지 부분 -->
	<div class="container">
		<div class="section">
			<div class="carousel carousel-slider" data-indicators="true" >
				<div class="carousel-fixed-item centered" >
					<div class="container white">
						<form action="mflp?command=bookList" method="post">
							<div class="input-field" >
								<label class="label-icon">
									<i class="material-icons">search</i>
								</label>
								<input type="hidden" name="opt" value="4"> 
								<input id="search" type="search" required style="border:3px gray solid" name="condition">
								 <label for="search" style="width: 100%" id="searchLabel">&nbsp;</label>
							</div>
						</form>
					</div>
				</div>
				<img class="carousel-item" src="img/library2.jpg" >
				<img class="carousel-item" src="img/main1.jpg" >
				<img class="carousel-item" src="img/main2.jpg" >
				<img class="carousel-item" src="img/main3.jpg">	
			</div>
		</div>
	
</div>
	<!-- 게시판 -->
	<div id="board">
		<jsp:include page="board/boardCard.jsp" />
	</div>

</body>
</html>