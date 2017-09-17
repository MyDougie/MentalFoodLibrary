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
		<h4 class="col s4"><b>대여 현황</b></h4>
	</div>
	<nav class="z-depth-0" >
      <div class="nav-wrapper white	">
         <div class="col s12 ">
            <a href="#!" class="breadcrumb black-text">관리자 페이지</a>
            	<a href="#!" class="breadcrumb black-text">대여 현황</a>
         </div>
      </div>
   </nav>
			<div class="col s12 white">
				<!-- 대출예약/상황 -->
				<table class="highlight centered">
				<c:if test="${requestScope.searchRentList != null}">
					<thead>
						<tr>
							<th>대여자 ID</th>
							<th>도서번호</th>
							<th>도서명</th>
							<th>대여일</th>
							<th>반납예정일</th>
							<th>연장횟수</th>
							<th>연체료</th>
						</tr>
					</thead>
				</c:if>
					<c:forEach items='${searchRentList}' var='rentItem'>
						<tr>
							<td>${rentItem.memberId}</td>
							<td>${rentItem.bookNo}</td>
							<td>${rentItem.title}</td>
							<td>${rentItem.startDate}</td>
							<td>${rentItem.endDate}</td>
							<td>${rentItem.extensionCount}</td>
							<td>
								<c:choose>
									<c:when test="${rentItem.lateFee <= 0}" > 
										0
									</c:when>
									<c:otherwise>
										${rentItem.lateFee}
									</c:otherwise>
								</c:choose>								
							</td>
					
						</tr>
					
					</c:forEach>
					
					
				</table>
			</div>
			
			<div class="row"> 
				<div class="col s12"> </div>
			</div>
			<jsp:include page="pagination.jsp" />
			
			<!-- 조건검색-->
			<div id="searchForm" class="row">
            <form action="SelectSearchRent" method="post" class="col s12">
               <div class="row">
                  <div class="input-field col s3">
                     <select name="opt">
                        <option value="0">도서명</option>
                        <option value="1">대여자ID</option>
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