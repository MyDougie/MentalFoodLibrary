<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	margin-right: 480px;
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
		$('select').material_select();
	});
</script>
<script>
	function mv(th) {
		location.href = "mflp?command=boardSearchByNo&flag=1&boardNo=" + th.id;
	}
	function cl() {
		location.href = "mflp?command=boardSelectAll&category=1"
	}
	function checkValid() {
		if(${sessionScope.id == null})
		{
			Materialize.toast('로그인 후 이용하세요', 2000);
			return;
		}
		else
		{
			location.href = "main.jsp?contentPage=/board/boardWrite.jsp?category=2";
		}
	}
</script>
</head>


<body>
	<div class="container">
		<div class="section">
			<h4>
				<a href="mflp?command=boardSelectAll&category=2"
					style="color: black"> <b>자유 게시판</b>
				</a>
			</h4>

			<nav class="white row z-depth-0">
				<a href="#!" class="breadcrumb black-text ">게시판</a> <a href="#!"
					class="breadcrumb black-text">자유게시판</a>

				<ul class="tabs">
					<li class="tab col s4 offset-s2 "><a href="#"
						class="black-text" onclick="cl()">공지 게시판</a></li>
					<li class="tab col s4 "><a href="#test2"
						class="active black-text">자유 게시판</a></li>
				</ul>
			</nav>
		</div>
		<br>
		<br>
		<br>

		<div class="row">
			<div id="test2">
				<div class="row">
					<div style="text-align: right">
						<a class="waves-effect waves-light btn teal darken-4"
							onclick="checkValid()">글쓰기</a>
					</div>
					<table class="bordered highlight centered">
						<thead>
							<tr>
								<th style="width: 13%">말머리</th>
								<th style="width: 13%">글번호</th>
								<th>제목</th>
								<th style="width: 13%">작성자</th>
								<th style="width: 20%">작성일</th>
								<th style="width: 13%">조회수</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<c:choose>
									<c:when test="${requestScope.list.size()==0}">
										<tr>
											<th colspan="6">검색된 레코드는 없습니다.</th>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${list}" var="dto" varStatus="state">
											<tr onclick="mv(this)" id="${dto.boardNo}">
												<td><c:choose>
														<c:when test="${dto.category==2 }">[정보]</c:when>
														<c:when test="${dto.category==3 }">[잡담]</c:when>
														<c:otherwise>[기타]</c:otherwise>
													</c:choose></td>
												<td id="boardNo">${dto.boardNo}</td>
												<td style="text-align: left"><span><c:if
															test="${dto.parentNo != 0 }">
															
															<img src="${pageContext.request.contextPath}/img/arrow.png"
																style="height: 11px; width: 13px;">
															<span
																style="font-size: 13pt; font-weight: bold; color: #009688">[${dto.parentNo}의
																답글]</span>

														</c:if>${dto.title}</span></td>
												<td>${dto.writerId}</td>
												<td>${dto.writeDate}</td>
												<td>${dto.readNum}</td>

											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
				<jsp:include page="pageButton.jsp" />
				<form method="post"
				action="mflp?command=boardSearchByKeyField&category=2">
				<div class="row" style="text-align: center">
					<div class="input-field col s1 offset-s2">
						<select name="keyField">
							<option value="1" selected>전체</option>
							<option value="2">제목</option>
							<option value="3">작성자</option>
						</select>
					</div>

					<div class="col s7">
						<div class="input-field">

							<label class="label-icon"><i class="material-icons">search</i></label>
							<input id="search" name="keyWord" type="search" required>
							<label for="search" style="width: 100%">&nbsp;</label>
						</div>
					</div>
					<div class="col s2">
						<br>
						<button
							class="btn waves-effect waves-light teal darken-4 valign-wrapper"
							type="submit" name="action">검색</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>