<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type="text/css">
    header, main {
      padding-left: 300px;
    }
    .tabs .indicator {
		   position: absolute;
		   bottom: 0;
		   height: 2px;
		   background-color: #004d40;
		   will-change: left, right;
		}

    @media only screen and (max-width : 992px) {
      header, main {
        padding-left: 0;
      }
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
  margin-right:480px;
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
<script>
$(document).ready(function(){
	if(${requestScope.tabState == 1}){
		$('ul.tabs').tabs('select_tab', 'reserveList');
	}else if(${requestScope.tabState == 2}){
		$('ul.tabs').tabs('select_tab', 'member');
	}else if(${requestScope.tabState == 3}){
		$('ul.tabs').tabs('select_tab', 'books');
	}else if(${requestScope.tabState == 4}){
		$('ul.tabs').tabs('select_tab', 'rental');
	}else if(${requestScope.tabState == 5}){
		$('ul.tabs').tabs('select_tab', 'category');
	}

	if(${requestScope.errorMsg != null}){
        Materialize.toast('${requestScope.errorMsg}', 3000);
   };
   if(${requestScope.successMsg != null}){
      Materialize.toast('${requestScope.successMsg}', 3000);
   };
  
   if(${sessionScope.code == null}){
	   alert("잘못된 접근");
	   history.back();
	   
   }
   
  });
</script>
</head>
<body>
      <c:choose>
      <c:when test="${sessionScope.code eq'관리자' }">
<div></div><Br>
<div class="container">
<div class="row">
    <div class="col s12">
      <ul class="tabs">

     	<li class="tab col s2"><a href="#rentList" name="rentListTab" class="black-text">대여현황</a></li>
        <li class="tab col s2"><a href="#reserveList" name="reserveListTab" class="black-text">예약현황</a></li>
        <li class="tab col s2"><a href="#member" name="bookInsertTab" class="black-text">도서등록</a></li>
        <li class="tab col s2"><a href="#books" name="bookUpdateTab" class="black-text">도서수정</a></li>
        <li class="tab col s2 "><a href="#rental" name="rentTab" class="black-text">대여/반납</a></li>
        <li class="tab col s2"><a href="#category" id="categoryTab" class="black-text">카테고리관리</a></li>
      </ul>
    </div>
     <div id="rentList" class="col s12" ><jsp:include page="../admin/rentList.jsp" /></div>
     <div id="reserveList" class="col s12" ><jsp:include page="../admin/reserveList.jsp" /></div>
    <div id="member" class="col s12" ><jsp:include page="../book/bookadd.jsp" /></div>
    <div id="books" class="col s12 "><jsp:include page="../book/bookManager.jsp" /></div>
    <div id="rental" class="col s12"><jsp:include page="../admin/rentState.jsp" /></div>
    <div id="category" class="col s12"><jsp:include page="../admin/category.jsp" /></div>
  </div>
</div>
	</c:when>
	</c:choose>
     
         
</body>
</html>