<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>
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

#map {
	height: 400px;
	width: 100%;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		$('.modal').modal();
	});
</script>
</head>
<body>
	<!--  header 시작하기.. -->

	<div class="container">
		<Br>
		<div>
			<h4 class="">
				<b>도서관 정보</b>
			</h4>
		</div>
	</div>
	<nav class="white z-depth-0">
	<div class="col s1 nav-wrapper left-align container ">
		<a href="#!" class="breadcrumb black-text ">도서관 정보</a> <a href="#!"
			class="breadcrumb black-text">찾아오시는 길</a>
	</div>
	</nav>
	<div class="container">
		<div class="section">
			<a class="waves-effect waves-light btn right teal darken-4"
				href="#modal1">찾아오시는길</a>
			<div class="row"></div>
			<div id="map"></div>
			<script>
				function initMap() {
					var uluru = {
						lat : 37.402196,
						lng : 127.106975
					};
					var map = new google.maps.Map(document
							.getElementById('map'), {
						zoom : 16,
						center : uluru,
					});
					var marker = new google.maps.Marker({
						position : uluru,
						map : map
					});
				}
			</script>
			<script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPMLp6eDquNfldzY01HVwIDMcOqrbrfDc&callback=initMap">
				
			</script>
		</div>
	</div>
	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<div class="modal-content">
			<h4>찾아오시는길</h4>
			<p>경기도 성남시 분당구 삼평동 대왕판교로 670길 유스페이스2 B동 8층</p>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class="modal-action modal-close waves-effect waves-green btn-flat">확인</a>
		</div>
	</div>

</body>
</html>