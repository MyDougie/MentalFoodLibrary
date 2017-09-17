<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>MentalFoodLibraryProject</title>

<script type="text/javascript">
	$(document).ready(function() {
		$('select').material_select();
	});
</script>
<style type="text/css">
.tabs .indicator {
	position: absolute;
	bottom: 0;
	height: 2px;
	background-color: #004d40;
	will-change: left, right;
}

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
function cl() {
	location.href = "mflp?command=boardSelectAll&category=1"
}
function cl2() {
	location.href = "mflp?command=boardSelectAll&category=2"
}
</script>
</head>
<body>

	<div class="container">
	<c:if test="${param.category==1 }">
	<div class="section">
		
			<h4>
				<a onclick="location.reload(); "
					style="color: black"> <b>게시물 입력</b>
				</a>
			</h4>

			<nav class="white row z-depth-0">
				<a href="#!" class="breadcrumb black-text ">게시판</a> <a href="#!"
					class="breadcrumb black-text" onclick="cl()">공지게시판</a> <a href="location.reload()"
					class="breadcrumb black-text">게시물 작성</a>

				<ul class="tabs">
					<li class="tab col s4 offset-s2 "><a href="#"
						class="active black-text" onclick="cl()">공지 게시판</a></li>
					<li class="tab col s4 "><a href="#test2"
						class="black-text" onclick="cl2()">자유 게시판</a></li>
				</ul>
			</nav>
		</div>
	</c:if>	
	<c:if test="${param.category==2 }">
	<div class="section">
		
			<h4>
				<a onclick="location.reload(); "
					style="color: black"> <b>게시물 입력</b>
				</a>
			</h4>

			<nav class="white row z-depth-0">
				<a href="#!" class="breadcrumb black-text ">게시판</a> <a href="#!"
					class="breadcrumb black-text" onclick="cl2()">자유게시판</a> <a href="#!"
					class="breadcrumb black-text">게시물 작성</a>

				<ul class="tabs">
					<li class="tab col s4 offset-s2 "><a href="#"
						class="black-text" onclick="cl()">공지 게시판</a></li>
					<li class="tab col s4 "><a href="#test2"
						class="active black-text" onclick="cl2()">자유 게시판</a></li>
				</ul>
			</nav>
		</div>
	</c:if>
		<br><br>
		<form method="post" action="mflp?command=boardInsert"
			enctype="multipart/form-data">
			<!-- 제목 -->

			<div class="row">

				<div class="tagSelect col s1 offset-s1">
					<select name="category" required>
						<c:choose>
							<c:when test="${param.category eq 1}">
								<option value="1" selected>[공지]</option>
							</c:when>
							<c:otherwise>
								<option value="" disabled selected>--선택--</option>
								<option value="2">[정보]</option>
								<option value="3">[잡담]</option>
								<option value="4">[기타]</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="input-field col s9">
					<input placeholder="제목" id="title" name="title" type="text"
						class="validate"> <label for="title">제목</label>
				</div>
			</div>

			<!-- 글작성 -->
			<div class="row s12">
				<div class="input-field col s12">
					<textarea name="content" style="height: 350px"></textarea>
				</div>

			</div>
			<c:if test="${param.category eq 1}">
				<div class="row">
					<div class="file-field input-field col s10 offset-s1">
						<div class="btn teal darken-4">
							<span>File</span> <input type="file" name="file">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate" type="text"
								value="첨부파일은 2MB를 넘을 수 없습니다.">
						</div>
					</div>
				</div>
			</c:if>
			<p>
			<div class="container" style="text-align: right">
			<input type="hidden" name="parentNo" value="${param.parentNo}">
				<button class="btn waves-effect waves-light teal darken-4" type="submit"
					name="action">
					등록 </i>
				</button>
				&nbsp;&nbsp;&nbsp; <a class="waves-effect waves-light btn teal darken-4"
					onclick="history.back()">취소</a>
			</div>
			<p>
		</form>
	</div>
</body>
</html>