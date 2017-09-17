<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {

		$("#update").click(function() {

			$("form").attr("action", "mflp?command=categoryUpdate");
		});

		$("#insert").click(function() {

			$("form").attr("action", "mflp?command=categoryInsert");
		});

		$("#delete").click(function() {

			$("form").attr("action", "mflp?command=categoryDelete");
		});

	})
</script>
</head>
<body>
	<div class="row">
		<h4 class="col s4">
			<b>카테고리 관리</b>
		</h4>
	</div>
	<nav class="z-depth-0">
		<div class="nav-wrapper white	">
			<div class="col s12 ">
				<a href="#!" class="breadcrumb black-text">관리자 페이지</a> <a href="#!"
					class="breadcrumb black-text">카테고리 관리</a>
			</div>
		</div>
	</nav>
	<div class="row">
		<div class="col s5">
			<div class="input-field col s12">
				<form action="" method="post">
					<input id="categoryNo" type="text" class="validate"
						name="categoryNo"> <label for="categoryNo">Category
						No</label>
			</div>
			<div class="input-field col s12">
				<input id="categoryName" type="text" class="validate"
					name="categoryName"> <label for="categoryName">Category
					Name</label>
			</div>
			<p style="text-align: center;">
				<button class="btn waves-effect teal darken-4" type="submit"
					name="insert" id="insert">추가</button>
				<button class="btn waves-effect teal darken-4" type="submit"
					name="update" id="update">수정</button>
				<button class="btn waves-effect teal darken-4" type="submit"
					name="delete" id="delete">삭제</button>
			</p>
		</div>

		</form>
		<div class="col offset-s1 s6">
			<table class="highlight bordered centered">
				<thead>
					<tr>
						<th>카테고리 번호</th>
						<th>카테고리 이름</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${catedoryList }" var="categoryList">
						<tr>
							<td>${categoryList.categoryNo }</td>
							<td>${categoryList.categoryName }</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<div>
				<p style="text-align: center;">
					<a class="waves-effect teal darken-4 btn"
						href="mflp?command=categorySelectAll" id="categorySelectAll">전체조회</a>
				</p>
			</div>
		</div>
	</div>


</body>
</html>