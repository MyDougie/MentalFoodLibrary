<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<html>
<head>
<style type="text/css">

</style>
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/materialize.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
<link href="${pageContext.request.contextPath}/css/style.css"
	type="text/css" rel="stylesheet" media="screen,projection" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
	</head>
	<body>
		
		<nav>
		<div class="nav-wrapper teal darken-4" >
		<a href="../index.html" class="brand-logo center" ><i class="large material-icons">thumb_up</i></a>
		</div>
		</nav>
	
	<div class="container">

		<div class="row">
			<form class="col s12" action="../mflp?command=login" method="post" name="f">
				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="id" type="text" class="validate" name="id"> <label
							for="id">User ID</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="password" type="password" class="validate" name="password"> <label
							for="password">Password</label>
					</div>
				</div>

				<div class="row">
					<div align="center">
						<button class="btn waves-effect waves-light teal darken-4" 
							type="submit" alt="로그인">로그인</button>
					</div>
					</div>
			</form>
			<hr width="300">
			<div align="center">
		

			<span><a href="joinForm.jsp" style="color: #004d40">회원가입</a></span>
		
			</div>
		</div>
	</div>
	</body>	
	</html>