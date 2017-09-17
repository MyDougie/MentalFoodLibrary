<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>BookList</title>
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
.pagination li.active {
    background-color: #004d40;
}
</style>
</head>
<body>

	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();

			Materialize.updateTextFields();

		});
		function bm(th) {
			location.href = "mflp?command=SearchBySelect&flag=0&bookNo=" + th;
		}	
		
		
	</script>

   

	<div id="wrap">
		<!-- 게시글 목록 부분 -->
		<br>
		<div class="container">
			<div><h4 class=""><b>소장자료검색</b></h4></div>
			</div>
			
			<nav class ="white z-depth-0">
			    <div class="col s1 nav-wrapper left-align container "> 
			        <a href="#!" class="breadcrumb black-text " >검색</a>
			        <a href="#!" class="breadcrumb black-text" >소장자료검색</a> 
			        </div>     
   			</nav> 
			
			<div class="container">
			<div id="board">
				<table id="bList" class="bordered centered highlight">
					<tr>
						<td><b>책번호</b></td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>출판일</td>
						<td>출판사</td>
					</tr>
					<c:forEach var="book" items="${requestScope.list}" varStatus="state">
						<tr onclick="bm(${book.bookNo})">
							<td>${book.bookNo}</td>
							<td>${book.title}</td>
							<td>${book.writer} </td>
							<td>${book.writeDate}</td>
							<td>${book.publisher}</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<!-- 페이지 넘버 부분 -->
			<br>
			<div id="pageForm" >
				<div class="container">
				<ul class="center-align pagination">
				<!-- < 버튼 -->
				<c:if test="${startPage != 1}">
					<li class="waves-effect"><a href='mflp?command=bookList&page=${startPage-1}'><i class="material-icons">chevron_left</i></a></li>
				</c:if>
				<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
					<c:if test="${pageNum == spage}">
					<!-- 선택페이지 -->
                    <li class="waves-effect active" ><a>${pageNum}</a> </li>
         			 </c:if>
         			 <!-- 선택되지 않은 페이지 -->
					<c:if test="${pageNum != spage}">
						<li class="waves-effect"><a href='mflp?command=bookList&page=${pageNum}'>${pageNum}&nbsp;</a></li>
						
					</c:if>
				</c:forEach>
				<!-- > 버튼 -->
				<c:if test="${endPage != maxPage }">
					<li class="waves-effect"><a href='mflp?command=bookList&page=${endPage+1 }'><i class="material-icons">chevron_right</i></a></li>
				</c:if>

				</ul>
			</div>
		</div>
			<!--  검색 부분 -->

			<div id="searchForm" class="container">
				<form action="mflp?command=bookList" class="col s12" method="post">
					<div class="row">
						<div class="input-field col s3">
							<select name="opt">
								<option value="4">전체</option>
								<option value="0">제목</option>
								<option value="1">내용</option>
								<option value="2">제목+내용</option>
								<option value="3">글쓴이</option>
							</select> 
						</div>
						<div class="input-field col s7">
							<input type="text" name="condition" />&nbsp; 
							
						</div>
						<div class="input-field col s2">
						 <button class="btn waves-effect waves-light teal darken-4" type="submit" name="action">검색</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
