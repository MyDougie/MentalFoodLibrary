<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    .tabs .indicator {
    position: absolute;
    bottom: 0;
    height: 2px;
    background-color: #004d40;
    will-change: left, right;
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
	$(document).ready(function() {
		 $('.carousel').carousel();
		
		/* $("input[name='cancleReservation']").click(function(){
			  var reserveNo = $(this).parent().parent().children().eq(0).text();
			location.href = "mflp?command=cancleReserve&reserveNo="+reserveNo;  
		}); */
		
		<%-- $("input[name='extension']").click(function(){
			    var bookNo = $(this).parent().parent().children().eq(0).text();
			     var memberId = '<%=request.getSession().getAttribute("id")%>';
			 location.href = "mflp?command=extension&bookNo="+bookNo+"&memberId="+memberId;    
			alert($(this).parent().parent().children().eq(0).text()+"yyyy");
		}); --%>
		
		if(${requestScope.tabState == 1}){
			$('ul.tabs').tabs('select_tab', 'myReserveList');
		}

		if(${requestScope.errorMsg != null}){
	  		Materialize.toast('${requestScope.errorMsg}', 3000);
		};
		if(${requestScope.successMsg != null}){
			Materialize.toast('${requestScope.successMsg}', 3000);
		};
	});
</script>
<script>
	function extent(bookNo){
	    var memberId = '<%=request.getSession().getAttribute("id")%>';
		 location.href = "mflp?command=extension&bookNo="+bookNo+"&memberId="+memberId; 
	}
	
	function cancle(reserveNo){
		location.href = "mflp?command=cancleReserve&reserveNo="+reserveNo;
	}

</script>
</head>
<body>
	<!--  header 시작하기.. -->
<br>
		<div class="container">
			<div><h4 class=""><b>대출현황조회</b></h4></div>
			</div>
			
			<nav class ="white z-depth-0">
			    <div class="col s1 nav-wrapper left-align container "> 
			        <a href="#!" class="breadcrumb black-text " >My Page</a>
			        <a href="#!" class="breadcrumb black-text" >대출현황조회</a> 
			        </div>     
   			</nav>
	<div class="container">
		<div class="row">
			<br>

			<div class="col s12">
				<ul id="g f " class="tabs">
					<li class="tab col s3 offset-s2 "><a href="#test-swipe-1" class = "black-text">대여 목록</a></li>
					<li class="tab col s3" ><a href="#myReserveList" class = "black-text">예약 목록</a></li>
				</ul>
			</div>
			</div>
			<div id="test-swipe-1" class="col s12 white">
				<!-- 대출예약/상황 -->
				<table class="highlight centered">
					<thead>
						<tr>
							<th>도서번호</th>
							<th>도서명</th>
							<th>대출일</th>
							<th>반납예정일</th>
							<th>도서연장 횟수</th>
							<th>연체료</th>
							<th class="right-align">연장하기<th>
						</tr>
					</thead>
					<c:forEach items='${rentList}' var='rentItem'>
						<tr>
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
							<!-- <td><input type="button" name="extension" value="연장하기"></td> -->
							<td>		
								<a class="waves-effect waves-light btn teal darken-4 col s1" onclick="extent(${rentItem.bookNo})">연장</a>
							</td>
						</tr>
					
					</c:forEach>
					
					
				</table>
			</div>
			<div id="myReserveList" class="col s12 white">
				<table class="highlight centered">
					<thead> 
						<tr>
							<th>예약번호</th>
							<th>도서명</th>
							<th>예약자 ID</th>
							<th>예약일</th>
							<th>예약순위</th>
							<th>예약취소<th>
						</tr>
					</thead>
						<c:forEach items='${reserveList}' var='item'>
							<tr>
								<td>${item.reserveNo}</td>
								<td>${item.title}</td>
								<td>${item.reserver}</td>
								<td>${item.reserveDate}</td>
								<td>${item.order}</td>
								<td>
									<!-- <input type='button' name='cancleReservation' value='취소'> -->
									<a id="cancleReservation" class="waves-effect waves-light btn teal darken-4 col s1" 
									onclick="cancle(${item.reserveNo})">취소</a>
								</td>
							</tr>
						</c:forEach>
						
					
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>