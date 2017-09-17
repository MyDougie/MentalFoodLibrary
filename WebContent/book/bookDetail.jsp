<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>


<script>
	$(function(){
		$("#reserve").click(function(){
			
				location.href="mflp?command=reserve&bookNo="+$("#bookNo").text();
			
		});
		
		if(${requestScope.errorMsg != null}){
	  		Materialize.toast('${requestScope.errorMsg}', 3000);
		};
		if(${requestScope.successMsg != null}){
			Materialize.toast('${requestScope.successMsg}', 3000);
		};
	});
	$(document).ready(function(){
	    $('.modal').modal();
	  });
</script>
<style>
.headForm {
	border-width: 1px;
	border-style: solid;
	border-color: lightgray;
}

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

.toast {
	border-radius: 2px;
	top: 150px;
	width: auto;
	clear: both;
	margin-top: 10px;
	position: relative;
	max-width: 100%;
	height: auto;
	min-height: 48px;
	line-height: 1.5em;
	word-break: break-all;
	background-color: #323232;
	padding: 10px 25px;
	font-size: 1.1rem;
	font-weight: 500;
	color: #fff;
	margin-right: 480px;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-justify-content: space-between;
	-ms-flex-pack: justify;
	justify-content: space-between;
}
</style>
</head>
<body>
	<!--  Breadcrumbs - 경로 표시 -->
	<!--  header 시작하기.. -->

	<div class="container">
		<div>
			<h4 class="">
				<b>도서상세정보</b>
			</h4>
		</div>
		<nav class="white z-depth-0">
			<div class="col s1 nav-wrapper left-align ">
				<a href="#!" class="breadcrumb black-text ">검색</a> <a href="#!"
					class="breadcrumb black-text ">소장자료검색</a> <a href="#!"
					class="breadcrumb black-text">도서상세정보</a>
			</div>
		</nav>

		<div class="row">
			<div class="col s4">
				<br> <img
					src="${pageContext.request.contextPath}/save/${requestScope.fName}"
					width="250" height="320">
			</div>

			<div class="col s7	">
				<table class="highlight bordered">
					<tbody>
						<br>
						<c:forEach items="${requestScope.list}" var="book">
							<tr>

								<td>도서명</td>
								<td>${book.title}</td>
							</tr>
							<tr>
								<td>도서 번호</td>
								<td id='bookNo'>${book.bookNo}</td>
							</tr>
							<tr>
								<td>작가</td>
								<td>${book.writer}</td>
							</tr>
							<tr>
								<td>출판사</td>
								<td>${book.publisher}</td>
							</tr>
							<tr>
								<td>출판일</td>
								<td>${book.writeDate}</td>
							</tr>
							<tr>
								<td>요약</td>
								<td>${book.description}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<Br>
				<div class="row">
					<div class="offset-s4 col s4">
						<c:if test="${sessionScope.id == null }" >
						<a class="waves-effect waves-light btn teal darken-4 "
							href="#bookModal">예약하기</a>
						</c:if>
						<c:if test="${sessionScope.id != null }" >
						<button class="waves-effect waves-light btn teal darken-4 "
							id="reserve" >예약하기</button>	
						</c:if>
						<!-- Modal Structure -->
						<div id="bookModal" class="modal">
							<div class="modal-content">
								<h4>로그인해주세요.</h4>
								<p>이동을 누르면 로그인창으로 이동합니다.</p>
							</div>
							<div class="modal-footer">
								<a href="login/loginForm.jsp" class="modal-action modal-close waves-effect waves-green btn-flat">이동</a>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



</body>
</html>