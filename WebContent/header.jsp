<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>MentalFoodLibraryProject</title>
<style type="text/css">
.dropdown-content li > a, .dropdown-content li > span {
    color: #898989;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
    $('#checkModal').modal();
    
    if(${signUpState != null}){
    $('#checkModal2').modal('open');}
  });
</script>
</head>
<body>
	<nav class="nav-extended  white">
		<div class="nav-wrapper black-text">
			<div class="container">
			
			<a href="mflp" class="brand-logo ">
			<p style="font-size:4;"></p>
			<img alt="logo" src="img/logo3.jpg">
			</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				
				<c:choose >
				<c:when test="${sessionScope.code eq'일반' }"><li>${id }님 환영합니다.</li>
				<li><a href="login/logout.jsp" class="black-text">로그아웃</a></li></c:when>
				<c:when test="${sessionScope.code eq'관리자' }"><li>관리자님 환영합니다.</li>
				<li><a href="login/logout.jsp" class="black-text">로그아웃</a></li></c:when>
				<c:otherwise>
				<li>
				
				<a href="login/loginForm.jsp" class ="black-text"><i class="large material-icons">perm_identity</i></a>
				</li>
				</c:otherwise>
				</c:choose>
				
				
			</ul>
		</div>
		</div>
		<div class="nav-wrapper teal darken-4">
			<div class="container">
				<ul class="right hide-on-med-and-down">
					<li><a class="dropdown-button" href="#!"
						data-activates="dropdown1">　검색　<i class="material-icons right">list</i></a>
						<!-- Dropdown Structure -->
						<ul id="dropdown1" class="dropdown-content">
							<li><a href="mflp?command=bookList">소장자료검색</a></li>
							<li><a href="mflp?command=popularBookList">인기도서목록</a></li>
						</ul></li>
					<li><a class="dropdown-button" href="#!"
						data-activates="dropdown2">　게시판　<i class="material-icons right">list</i></a>
						<!-- Dropdown Structure -->
						<ul id="dropdown2" class="dropdown-content">
							<li><a href="mflp?command=boardSelectAll&category=1">공지 게시판</a></li>
							<li><a href="mflp?command=boardSelectAll&category=2">자유 게시판</a></li>
						</ul></li>
					<li><a class="dropdown-button" href="#!"
						data-activates="dropdown3">　My　Page　<i
							class="material-icons right">list</i></a> <!-- Dropdown Structure -->
						<ul id="dropdown3" class="dropdown-content">
						<c:choose>
						<c:when test="${id == null}">
						<li><a href="#checkModal">개인 정보</a></li> 
						<li><a href="#checkModal">대출 현황 조회</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="main.jsp?contentPage=/login/changeInfoForm.jsp">개인 정보</a></li>
						<li><a href="mflp?command=myPageSelect">대출 현황 조회</a></li>
						</c:otherwise>		
						</c:choose>

						</ul></li>
						<li><a class="dropdown-button" href="#!" data-activates="dropdown4">　도서관　정보　<i
							class="material-icons right">list</i></a> <!-- Dropdown Structure -->
							<ul id="dropdown4" class="dropdown-content">
								<li><a href="main.jsp?contentPage=/info/libraryGuide.jsp">도서관 안내</a></li>
								<li><a href="main.jsp?contentPage=/info/libraryGallery.jsp">도서관 갤러리</a></li>
								<li><a href="main.jsp?contentPage=/info/libraryLocation.jsp">찾아오시는 길</a></li>
							</ul></li>
						<c:if test="${sessionScope.code eq'관리자'}">
						<li><a class="button" href="main.jsp?contentPage=/admin/adminPage.jsp" >관리자</a>
						</c:if>
				</ul>
			</div>
		</div>
	</nav>
	
	
<div id="checkModal" class="modal">
		<div class="modal-content">
			<h4>로그인해주세요.</h4>
			<p>이동을 누르면 로그인창으로 이동합니다.</p>
		</div>
		<div class="modal-footer">
			<a href="login/loginForm.jsp" class="modal-action modal-close waves-effect waves-green btn-flat">이동</a>
		</div>
  </div>
<div id="checkModal2" class="modal">
		<div class="modal-content">
			<h4>가입완료</h4>
			<p>가입을 축하합니다. 이동을 누르면 로그인창으로 이동합니다.</p>
		</div>
		<div class="modal-footer">
			<a href="login/loginForm.jsp" class="modal-action modal-close waves-effect waves-green btn-flat">이동</a>
		</div>
  </div>  
	
</body>
</html>