<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
      
      $("#return").click(function(){
         location.href="mflp?command=returns&memberId="+$('#returnMemberId').val()+"&bookNo="+$('#returnBookNo').val();
      });
      
      $("#rentBtn").click(function(){
         location.href="mflp?command=rent&memberId="+$('#rentMemberId').val()+"&bookNo="+$('#rentBookNo').val();
      });
      
      
      $("#rentBookNo").keyup(function() {
         $.ajax({
            type : "post",
            url : "RentStateServlet",
            data : "keyWord=" + $(this).val(),
            dataType : "json", 
            success : function(result) {
               //console.log("result = " + result);
               if(result ==0){
                  $("#listTable tr:gt(0)").remove();
               }else{
                  
               var str = "";
               $.each(result, function(index, item) {
                  
                  str+="<table>";
                  str+="<tr>";
                  str+="<td>"+item.bookNo+"</td>";
                  str+="<td><a href='mflp?command=SearchBySelect&flag=1&bookNo="+item.bookNo+
                        "' target='_blank'>"+item.title+"</a></td>";  
                  str+="</tr>";
                  str+="</table>";
               
               })
               }
               $("#listTable tr").remove();
               $("#listTable").append(str);
            },
            error : function(err) {
               console.log("책 번호 잘못됨");
               
            }
         }); //ajax End
     
       })
       //////////
       
       $("#returnBookNo").keyup(function() {
         $.ajax({
            type : "post",
            url : "RentStateServlet",
            data : "keyWord=" + $(this).val(),
            dataType : "json", 
            success : function(result) {
               if(result ==0){
                  $("#listTable2 tr:gt(0)").remove();
               }else{
                  
               var str = "";
               $.each(result, function(index, item) {
                  
                  str+="<table>";
                  str+="<tr>";
                  str+="<td>"+item.bookNo+"</td>";
                  str+="<td><a href='mflp?command=SearchBySelect&flag=1&bookNo="+item.bookNo+
                "' target='_blank'>"+item.title+"</a></td>";  
                  str+="</tr>";
                  str+="</table>";
               
               })
               }
               $("#listTable2 tr").remove();
               $("#listTable2").append(str);
            },
            error : function(err) {
               console.log("책 번호 잘못됨");
               
            }
         }); //ajax End
         
       });
       
       
       
   })
   ///////////
</script>




</head>
<body>
   <!--  header 시작하기.. -->

   <nav class="z-depth-0" >
      <div class="nav-wrapper white   ">
      <div><h4 class="black-text"><b>대여관리</b></h4></div>
         <div class="col s12 ">
            <a href="#!" class="breadcrumb black-text">관리자 페이지</a>
               <a href="#!" class="breadcrumb black-text">대여관리</a>
         </div>
      </div>
   </nav>
   <div class="container">
      <div class="row">
         <!-- main.jsp?contentPage=/admin/adminPage.jsp/ mflp?command=SearchBySelect -->
         <form class="col s12" action="#!" method="post">
            <div class="row">
               <div class="input-field col s3">
                  <input id="rentMemberId" name="rentMemberId" type="text" class="validate"> <label
                     for="아이디">대여 아이디</label>
               </div>



               <div class="input-field col s3">
                  <div id="print">
                     <table id="listTable" cellspacing="0">
                        <!-- <tr bgcolor="pink">
                           <th>책번호</th>
                           <th>제목</th>
                        </tr> -->
                     </table>
                  </div>
               </div>
               
               <div class="input-field col s3">
                  <input id="returnMemberId" type="text" class="validate" name="returnMemberId"> <label
                     for="아이디">반납 아이디</label>
               </div>
               
               <div class="input-field col s3">
                  <div id="print">
                     <table id="listTable2" cellspacing="0">
                        <!-- <tr bgcolor="pink">
                           <th>책번호</th>
                           <th>제목</th>
                        </tr> -->
                     </table>
                  </div>
               </div>
               
               

            </div>
            <div class="row">
               <div class="input-field col s3">
                  <input type="text" id="rentBookNo" class="autocomplete" name="rentBookNo">
                  <!-- <input id="bookNo" type="text" class="bookNo" name="bookNo"> -->
                  <label for="책번호">대여 책번호</label>
               </div>
               
               <div class="input-field col s3">
                  <div id="print">
                     <table id="listTable" cellspacing="0">
                        <!-- <tr bgcolor="pink">
                           <th>책번호</th>
                           <th>제목</th>
                        </tr> -->
                     </table>
                  </div>
               </div>
               
               <div class="input-field col s3">
                  <input type="text" id="returnBookNo" class="autocomplete" name="returnBookNo">
                  <label for="책번호">반납 책번호</label>
               </div>
               
               
               
            </div>
            <div>
               <div class="input-field col s3">
                  <button class="btn waves-effect teal darken-4" type="button" id="rentBtn">
                     대여 
                  </button>
               </div>
               
               <div class="input-field col s3">
               </div>
            
               <div class="input-field col s3">
                  
                  <button class="btn waves-effect teal darken-4" type="button"   id="return">
                     반납 
                  </button>
               </div>
               
               <div class="input-field col s3">
               </div>
               
               
            </div>
         </form>
      </div>
   </div>

</body>
</html>