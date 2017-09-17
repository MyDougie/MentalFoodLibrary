<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>
			<div class="row">
		<h4 class="col s4"><b>예약 현황</b></h4>
	</div>
	<nav class="z-depth-0" >
      <div class="nav-wrapper white	">
         <div class="col s12 ">
            <a href="#!" class="breadcrumb black-text">관리자 페이지</a>
            	<a href="#!" class="breadcrumb black-text">예약 현황</a>
         </div>
      </div>
   </nav>
			<div class="col s12 white">
				<!-- 대출예약/상황 -->
				<table class="highlight centered">
					<c:if test="${requestScope.searchReserveList != null}">
					<thead>
						<tr>
							<th>예약번호</th>
							<th>도서명</th>
							<th>예약자 ID</th>
							<th>예약일</th>
							<th>예약 순위</th>
						</tr>
					</thead>
					</c:if>
					<c:forEach items='${searchReserveList}' var='reserveItem'>
						<tr>
							<td>${reserveItem.reserveNo}</td>
							<td>${reserveItem.title}</td>
							<td>${reserveItem.reserver}</td>
							<td>${reserveItem.reserveDate}</td>
							<td>${reserveItem.order}</td>
						
						</tr>
					
					</c:forEach>
					
				</table>
			</div>
			
			<!-- 공백 -->
			<div class="row"> 
				<div class="col s12"> </div>
			</div>
			
			<!-- 조건검색-->
			<div id="searchForm" class="row">
            <form action="SelectSearchReserve" method="post" class="col s12">
               <div class="row">
                  <div class="input-field col s3">
                     <select name="opt">
                        <option value="0">도서명</option>
                        <option value="1">회원ID</option>
                     </select> 
                  </div>
                  <div class="input-field col s7">
                     <input type="text" name="keyWord"/>&nbsp; 	
                     
                  </div>
                  <div class="input-field col s2">
                    <button class="btn waves-effect teal darken-4" type="submit" name="action">검색</button>
                  </div>
               </div>
            </form>
         </div>

</body>
</html>