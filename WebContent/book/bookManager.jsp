<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function() {

		$("#bookSearch").click(function() {

			$("#form2").attr("action", "mflp?command=searchBySelect2");
		});

		$("#bookUpdate").click(function() {

			$("form").attr("action", "mflp?command=bookUpdate");
		});

/* 		$("#bookDelete").click(function() {
			
			$("form").attr("action", "mflp?command=categoryDelete");
		}); */
	})
</script>
</head>
<body>

	<div class="row">
		<h4 class="col s4">
			<b>도서수정</b>
		</h4>
	</div>
	<nav class="z-depth-0">
		<div class="nav-wrapper white">
			<div class="col s12 ">
				<a href="#!" class="breadcrumb black-text">관리자 페이지</a> <a href="#!"
					class="breadcrumb black-text">도서수정</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<form action="" method="post" id="form2">
			<div class="row">
				<div class="input-field col s4">
					<input id="bookNo" type="text" class="validate" name="bookNo">
					<label for="책번호">책번호</label>
				</div>
				<div class="input-field  col s8">
					<button class="btn waves-effect waves-light teal darken-4 " type="submit"
						id="bookSearch">검색</button>
					<button class="btn waves-effect waves-light teal darken-4 " type="submit"
						id="bookUpdate">수정</button>
					<!-- <button class="btn waves-effect waves-light teal darken-4 " type="submit"
						id="bookDelete">삭제</button> -->
				</div>
			</div>
			<div class="row">
				<c:if test="${adminBookList ne null }">
					<div class="input-field col s6">
						책번호<input type="text" value="${adminBookList.get(0).bookNo }" name="bookNo2"> 
						제목<input type="text" value="${adminBookList.get(0).title }" name="title"> 
						내용<input type="text" value="${adminBookList.get(0).description }"name="description"> 
						글쓴이<input type="text" value="${adminBookList.get(0).writer }" name="writer">
						출판일<input type="date" class="datepicker" value="${adminBookList.get(0).writeDate }" name="writeDate">
					</div>
					<div class="input-field col s6">
						출판사<input type="text" value="${adminBookList.get(0).publisher }" name="publisher"> 
<!-- 						책이미지 <img src="${pageContext.request.contextPath}/save/${adminBookList.get(0).bookImg }" width="150" height="150">
					 <div class="file-field input-field">	
						<div class="btn teal darken-4 ">
							<span>File</span> 
							<input type="file" multiple>
						</div>
						<div class="file-path-wrapper ">
							<input class="file-path validate" type="text">
						</div>
						</div> -->
						카테고리번호<input type="text" value="${adminBookList.get(0).categoryNo }" name="categoryNo33">
					</div>

				</c:if>

			</div>


		</form>
	</div>
</body>
</html>