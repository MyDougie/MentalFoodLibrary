<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
   rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/materialize.css"
   type="text/css" rel="stylesheet" media="screen,projection" />
<link href="${pageContext.request.contextPath}/css/style.css"
   type="text/css" rel="stylesheet" media="screen,projection" />
<style type="text/css">
   
</style>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script type="text/javascript">
   $(document).ready(function() {

      $('.datepicker').pickadate({
         selectMonths : true, // Creates a dropdown to control month
         selectYears : 100
      // Create a dropdown of 15 years to control year
      });
      $(":submit").click(function(){
          if($("#idCheck").text() == "아이디 중복"){
             alert ("아아디 중복 아이디를 확인하세요")
          }
       });
      $("#id").blur(function() {
         
         $.ajax({
            type : "post",
            url : "../mflp?command=idCheck",
            data : "id=" + $(this).val(),
            dataType : "text",
            success : function(result) {
               console.log(result);
                  if($("#id").val() != null && $("#id").val() != ""){ 
                  if (result == 0) {
                     $("#idCheck").empty();
                     $("#idCheck").append("가입가능");
                  } else {
                     $("#idCheck").empty();
                     $("#idCheck").append("아이디 중복");
                  } 
               }
            },
            error : function(err) {
               console.log(err);

            }
         });
      });//아이디 중복체크
      
      $("#password").blur(function() {
         if($("#password").val().length > 7){
            $("#pw").empty();
            $("#pw").append("사용가능");
         }else if($("#password").val().length ==0){
            $("#pw").empty();
            $("#pw").append("사용불가능");
            
         }else if($("#password").val().length <=7){
            $("#pw").empty();
            $("#pw").append("사용불가능");
         }
      });//비밀번호 사용체큰

      $("#passwordCheck").blur(function() {
         if($("#password").val().length > 7){
            
            if($("#password").val() == $("#passwordCheck").val()){
               $("#pwCheck").empty();
               $("#pwCheck").append("일치");               
            }else{
               $("#pwCheck").empty();
               $("#pwCheck").append("불일치");      
            }
         }             
      });//비밀번호재확인
      
      $("#name").blur(function(){
         if($("#name").val().length == 0){
            $("#nameCheck").empty();
            $("#nameCheck").append("이름을 입력해주세요");
         }else{
            $("#nameCheck").empty();
         }
      });//이름 체크  
   });//document끝
</script>
</head>
<body>

      <nav>
      <div class="nav-wrapper teal darken-4" >
      <a href="../index.html" class="brand-logo center" ><i class="large material-icons">thumb_up</i></a>
      </div>
      </nav>

   <div class="container">
      <div class="row">
         <form class="col s12" action="../mflp?command=signUp" method="post">
            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input id="id" type="text" class="validate" name=id>
                  <label    for="id">User ID</label>
                  <div id="idCheck"></div>
               </div>
               
            </div>

            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input required id="password" type="password" class="validate" name=password> <label
                     for="password">Password</label>
               <div id=pw>
               </div>
               </div>
            </div>

            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input required id="passwordCheck" type="password" class="validate">
                  <label for="passwordCheck">PasswordCheck</label>
               <div id=pwCheck>
               
               
               </div>
               </div>

            </div>

            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input id="name" type="text" class="name" name=name>
                   <label for="name">Name</label>
                   <div id="nameCheck"></div>
               </div>
            </div>

            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input required type="date" class="datepicker" name=date>
                   
                  <label id="dateLb" for="datepicker">Birthdate</label>
               </div>
            </div>

            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input required id="telephone" type="tel" class="validate" name=tel> 
                  <label for="telephone">Telephone</label>
               </div>
            </div>
            
            <div class="row">
               <div class="input-field col s4 offset-s4">
                  <input required id="address" type="text" class="validate" name=addr> 
                  <label   for="address">Address</label>
               </div>
            </div>
            <div class="row">
               <div class="input-field col s4 offset-s5">
                  <button class="btn waves-effect waves-light teal darken-4" type="submit" alt="회원가입">가입하기</button>
               </div>
            </div>
         </form>
      </div>
   </div>

</body>
</html>