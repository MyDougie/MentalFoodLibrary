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
<script type="text/javascript">
	$(document).ready(function() {
		  $('.modal').modal({
			//  dismissible: false;
			  
		  });
		if(${id == null}){	
			
			$('#infoModal').modal('open');
		}

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		
		});

		$("#password").blur(function() {
			
			$.ajax({
				type : "post",
				url : "mflp?command=passwordCheck",
				data : "id=" + $("#id").val()+ "&pw=" + $("#password").val(),
				dataType : "text",
				success : function(result) {
					console.log(result);
						if($("#password").val() != null){
						if (result == 1) {		
							$("#pw").empty();
							$("#pw").append("일치");
						} else {
							$("#pw").empty();
							$("#pw").append("불일치");
						}
					}
				},
				error : function(err) {
					console.log(err);

				}
			});
		});//기존 패스워드 체크
		
		$("#newPassword").blur(function() {
			if ($("#newPassword").val().length > 7) {
				$("#newPw").empty();
				$("#newPw").append("사용가능");
			} else {
				$("#newPw").empty();
				$("#newPw").append("사용불가능");

			}
		});//비밀번호 사용체큰

		$("#newPasswordCheck").blur(function() {
			if ($("#newPassword").val().length > 7) {

				if ($("#newPassword").val() == $("#newPasswordCheck").val()) {
					$("#newPwCheck").empty();
					$("#newPwCheck").append("일치");
				} else {
					$("#newPwCheck").empty();
					$("#newPwCheck").append("불일치");
				}
			}
		});//비밀번호재확인

	});//document끝
</script>
</head>
<body>

	<br>
	<div class="container">
		<div>
			<h4 class="">
				<b>회원정보수정</b>
			</h4>
		</div>
	</div>

	<nav class="white z-depth-0">
	<div class="col s1 nav-wrapper left-align container ">
		<a href="#!" class="breadcrumb black-text ">My Page</a> <a href="#!"
			class="breadcrumb black-text">회원정보수정</a>
	</div>
	</nav>


	<div class="container">
		<div class="row">
			<form class="col s12" action="mflp?command=changeInfo" method="post">
				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="id" type="text" class="validate" name=id value="${id }"
							readonly="readonly"> <label for="id">User ID</label>
						<div id="idCheck"></div>
					</div>

				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="password" type="password" class="validate"
							name=password> <label for="password">Password</label>
						<div id=pw></div>
					</div>

				</div>
				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="newPassword" type="password" class="validate"
							name=newPassword> <label for="newPassword">NewPassword</label>
						<div id=newPw></div>
					</div>

				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="newPasswordCheck" type="password" class="validate">
						<label for="newPasswordCheck">NewPasswordCheck</label>
						<div id=newPwCheck></div>
					</div>

				</div>



				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="name" type="text" class="validate" name=name> <label
							for="name">Name</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input type="date" class="datepicker" name=date> <label
							id="dateLb" for="datepicker">Birthdate</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="telephone" type="tel" class="validate" name=tel>
						<label for="telephone">Telephone</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 offset-s4">
						<input id="address" type="text" class="validate" name=addr>
						<label for="address">Address</label>
					</div>
				</div>


				<div class="row">
					<div class="input-field col s4 offset-s5">
						<button class="btn waves-effect waves-light teal darken-4"
							type="submit" alt="정보변경">정보변경</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="infoModal" class="modal">
		<div class="modal-content">
			<h4>로그인해주세요.</h4>
			<p>이동을 누르면 로그인창으로 이동합니다.</p>
		</div>
		<div class="modal-footer">
			<a href="login/loginForm.jsp"
				class="modal-action modal-close waves-effect waves-green btn-flat">이동</a>
		</div>
	</div>
</body>
</html>