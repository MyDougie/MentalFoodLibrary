<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>PopularBookList</title>

<style>

 
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
function bm(th) {
	location.href = "mflp?command=SearchBySelect&flag=0&bookNo=" + th;
}	

</script>
</head>
<body>
		
		<div class="container">
		<Br>
			<div><h4 class=""><b>인기도서목록</b></h4></div>
			</div>
			<nav class ="white z-depth-0">
			    <div class="col s1 nav-wrapper left-align container "> 
			        <a href="#!" class="breadcrumb black-text " >검색</a>
			        <a href="#!" class="breadcrumb black-text" >인기도서목록</a> 
			        </div>     
   			</nav> 

	<!--인기도서리스트 테이블 -->
	<div class="container">
	<div>
      <table class="bordered centered highlight">
        <thead>
          <tr >
              <th>순위</th>
              <th>도서명</th>
              <th>저자</th>
              <th>발행처</th>
              <th>대출횟수</th>
          </tr>
        </thead>
        <c:set var="sum" value="0"/>
		<c:forEach items="${list }" var="list" varStatus="state" begin="0" end="9"  step="1">
		<tr onclick="bm(${list.bookNo})">
            <td>${state.count }</td>
            <td>${list.title }</td>
            <td>${list.writer }</td>
            <td>${list.publisher }</td>
            <td>${list.count }</td>
             <c:set var="sum" value="${sum + list.count }"/>
          </tr>
		
		</c:forEach>
        <tfoot>
        	<tr>
        		<th class="right-align" colspan="4" >인기도서 대출 횟수</th>
        		<th class="center-align">${sum }</th>
        	</tr>
        </tfoot>
      </table>
   </div>      
</div>

</body>
</html>