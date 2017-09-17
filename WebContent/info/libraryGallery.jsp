<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.breadcrumb:before {
	content: '\E5CC';
	color: rgba(0, 0, 0, 0.7);
	vertical-align: top;
	display: inline-block;
	font-family: 'Material Icons';
	font-weight: normal;
	font-style: normal;
	font-size: 25px;
	margin: 0 10px 0 8px;
	-webkit-font-smoothing: antialiased;
}

</style>
<script>
	$(document).ready(function() {
		$('.carousel').carousel();
	});
</script>
</head>
<body>

	<div class="container">
	<Br>
		<div>
			<h4 class="">
				<b>도서관 갤러리</b>
			</h4>
		</div>
	</div>

	<nav class="white z-depth-0">
	<div class="col s1 nav-wrapper left-align container ">
		<a href="#!" class="breadcrumb black-text ">도서관 정보</a> <a href="#!"
			class="breadcrumb black-text">도서관 갤러리</a>
	</div>
	</nav>



	<div class="container">
		<div class="section">
			<div class="row">
				<div class="carousel ">
					<a class="carousel-item" href="#one!">
					<img src="img/1.jpg"></a> 
						<a class="carousel-item" href="#two!">
						<img src="img/2.jpg"></a> 
						<a class="carousel-item" href="#three!">
						<img src="img/3.jpg"></a> 
						<a class="carousel-item" href="#four!">
						<img src="img/4.jpg"></a> 
						<a class="carousel-item" href="#five!">
						<img src="img/5.jpg"></a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>