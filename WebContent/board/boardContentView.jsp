<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>MentalFoodLibraryProject</title>
<style type="text/css">
.tabs .indicator {
	position: absolute;
	bottom: 0;
	height: 2px;
	background-color: #004d40;
	will-change: left, right;
}

#btnCss {
	width: 25px;
	height: 40px
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

.btn-floating i {
	width: inherit;
	display: inline-block;
	text-align: center;
	color: #004d40;
	font-size: 1.6rem;
	line-height: 40px;
}

input[type=text] {
	background-color: transparent;
	border: none;
	border-bottom: 1px solid #9e9e9e;
	border-radius: 0;
	outline: none;
	height: 3rem;
	width: 90%;
	font-size: 1rem;
	margin: 0 0 20px 0;
	padding: 0;
	box-shadow: none;
	box-sizing: content-box;
	transition: all 0.3s;
}
</style>

<script>
function mv(th)
{
	location.href="mflp?command=boardSearchByNo&boardNo="+th.id;
}
function deleteComment(commentNo){

	location.href="mflp?command=boardCommentDelete&commentNo="+commentNo;
}
 function cl()
 {
	 location.href = "mflp?command=boardSelectAll&category=2"
 }
 function cl2()
 {
	 location.href = "mflp?command=boardSelectAll&category=1"
 }

function checkValid() {
	if(${sessionScope.id == null})
	{
		Materialize.toast('로그인 후 이용하세요', 4000);
		return;
	}
	else
	{
		location.href = "main.jsp?contentPage=/board/boardWrite.jsp?category=2&parentNo="+${dto.boardNo};
	}
}

</script>
<script>
$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
    
  });
</script>
<body>

	<div class="container">
		<c:if test="${dto.category==1 }">
			<div class="section">
				<h4>
					<a href="mflp?command=boardSelectAll&category=1"
						style="color: black"> <b>공지 게시판</b>
					</a>
				</h4>


				<nav class="white row z-depth-0">
					<a href="#!" class="breadcrumb black-text " onclick="cl2()">게시판</a>
					<a href="#!" class="breadcrumb black-text" onclick="cl2()">공지게시판</a>

					<ul class="tabs">
						<li class="tab col s4 offset-s2 "><a href="#"
							class="active black-text" onclick="cl2()">공지 게시판</a></li>
						<li class="tab col s4 "><a class="black-text" onclick="cl()">자유
								게시판</a></li>
					</ul>
				</nav>
			</div>
		</c:if>
		<c:if test="${dto.category!=1 }">
			<div class="section">
				<h4>
					<a href="mflp?command=boardSelectAll&category=2"
						style="color: black"> <b>자유 게시판</b>
					</a>
				</h4>

				<nav class="white row z-depth-0">
					<a href="#!" class="breadcrumb black-text " onclick="cl()">게시판</a>
					<a href="#!" class="breadcrumb black-text" onclick="cl()">자유게시판</a>

					<ul class="tabs">
						<li class="tab col s4 offset-s2 "><a href="#"
							class="black-text" onclick="cl2()">공지 게시판</a></li>
						<li class="tab col s4 "><a href="#test2"
							class="active black-text" onclick="cl()">자유 게시판</a></li>
					</ul>
				</nav>
			</div>
		</c:if>
		<br> <br> <br>
		<p>
		<table class="bordered striped col s5 ">
			<tr>
				<th style="width: 15%">제목</th>
				<td style="width: 85%"><c:choose>
						<c:when test="${dto.category==1 }">
							<span style="font-size: 13pt; font-weight: bold; color: black">[공지]&nbsp;&nbsp;</span>
						</c:when>
						<c:when test="${dto.category==2 }">
							<span style="font-size: 13pt; font-weight: bold; color: black">[정보]&nbsp;&nbsp;</span>
						</c:when>
						<c:when test="${dto.category==3 }">
							<span style="font-size: 13pt; font-weight: bold; color: black">[잡담]&nbsp;&nbsp;</span>
						</c:when>
						<c:otherwise>
							<span style="font-size: 13pt; font-weight: bold; color: black">[기타]&nbsp;&nbsp;</span>
						</c:otherwise>
					</c:choose> <c:if test="${dto.parentNo != 0 }">
						<span style="font-size: 13pt; font-weight: bold; color: #009688">[${dto.parentNo}의
							답글]</span>
					</c:if> ${dto.title}</td>
			</tr>

			<tr>
				<th>작성일</th>
				<td>${dto.writeDate}</td>
			</tr>

			<tr>
				<th>작성자</th>
				<td>${dto.writerId}</td>
			</tr>

			<tr>
				<th>내용</th>
				<td style="height: 300px">${dto.content}</td>
			</tr>

			<c:if test="${dto.fileName != null }">
				<tr>
					<th>파일</th>
					<td><a href="downLoad?fName=${dto.fileName }">${dto.fileName }
					</a></td>
				</tr>
			</c:if>
		</table>
		<p>
		<div class="container" style="text-align: right">
			<c:set var="writer_id" value="${dto.writerId }" />
			<c:if test="${sessionScope.id eq writer_id}">
				<a class="waves-effect waves-light btn teal darken-4"
					href="BoardUpdateTransport?boardNo=${dto.boardNo}">수정</a>&nbsp;&nbsp;&nbsp;
			<a class="waves-effect waves-light btn teal darken-4"
					href="mflp?command=boardDelete&boardNo=${dto.boardNo}">삭제</a>&nbsp;&nbsp;&nbsp;
							</c:if>
			<c:if test="${dto.category == 2 }">
				<a class="waves-effect waves-light btn teal darken-4 "
					onclick="cl()">목록</a>&nbsp;&nbsp;&nbsp;</c:if>
			<c:if test="${dto.category == 1 }">
				<a class="waves-effect waves-light btn teal darken-4 "
					onclick="cl2()">목록</a>&nbsp;&nbsp;&nbsp;</c:if>
			<c:if test="${dto.category != 1 }">
				<a class="waves-effect waves-light btn teal darken-4"
					onclick="checkValid()">답글</a>
			</c:if>
		</div>
		<p>
		<div class="container">


			<c:if test="${dto.category != 1 }">
				<!-- 댓글 부분 -->
				<div id="comment">
					<table class="bordered">
						<!-- 댓글 목록 -->
						<c:if test="${requestScope.commentList.size()!=0}">
							<c:forEach var="comment" items="${requestScope.commentList}">
								<tr>
									<!-- 아이디, 작성날짜 -->
									<td style="width: 20%">
										<div>
											${comment.memberId} <br> <font size="2"
												color="lightgray">${comment.writeDate}</font>
										</div>
									</td>
									<!-- 본문내용 -->
									<td style="width: 80%"><span class="text_wrapper">
											<c:if test="${comment.parentNo != 0}">
												<img src="${pageContext.request.contextPath}/img/arrow.png"
													style="height: 11px; width: 13px">
											</c:if> ${comment.content}
									</span> <!-- 버튼 --> <span class="right "> <c:if
												test="${comment.memberId == sessionScope.id}">
												<a href="#${comment.commentNo }1"
													class="btn-floating waves-effect waves-light white tooltipped z-depth-0"
													data-position="bottom" data-delay="50" data-tooltip="수정"
													id="btnCss"><i class="tiny material-icons">mode_edit</i></a>
												<div id="${comment.commentNo }1" class="modal">
													<form method="post"
														action="mflp?command=boardCommentUpdate">
														<input type="hidden" name="commentNo"
															value="${comment.commentNo}">
														<div class="modal-content">
															<h4>댓글 수정</h4>
															<p>
																<input type="text" name="content" required>
															</p>
														</div>
														<div class="modal-footer">
															<button
																class="btn waves-effect waves-light teal darken-4"
																type="submit" name="action">수정</button>
														</div>
													</form>
												</div>

												<!-- delete -->
												<a href="#!"
													class="btn-floating waves-effect waves-light white tooltipped z-depth-0"
													data-position="bottom" data-delay="50" data-tooltip="삭제"
													onclick="deleteComment(${comment.commentNo})" id="btnCss"><i
													class="tiny material-icons">delete</i></a>
											</c:if> 
											<c:if test="${sessionScope.id != null}">
												<a href="#${comment.commentNo }"
													class="btn-floating waves-effect waves-light white tooltipped z-depth-0"
													data-position="bottom" data-delay="50" data-tooltip="댓글"
													id="btnCss"><i class="tiny material-icons">comment</i></a>
												<div id="${comment.commentNo }" class="modal">
													<form method="post"
														action="mflp?command=boardCommentComment">
														<input type="hidden" name="parentNo"
															value="${comment.commentNo}">
														<div class="modal-content">
															<h4>댓글 작성</h4>
															<p>
																<input type="text" name="content" required>
															</p>
														</div>
														<div class="modal-footer">
															<button
																class="btn waves-effect waves-light teal darken-4"
																type="submit" name="action">댓글 작성</button>
														</div>
													</form>
												</div>

											</c:if>
									</span></td>
								</tr>

							</c:forEach>
						</c:if>

						<!-- 로그인 했을 경우만 댓글 작성가능 -->

						<c:if test="${sessionScope.id !=null}">


							<tr class="grey lighten-4">
								<form action="mflp?command=boardCommentInsert" method="post">
									<input type="hidden" name="comment_board"
										value="${dto.boardNo}" /> <input type="hidden"
										name="comment_id" value="${sessionScope.id}" />
									<td style="width: 20%">
										<div>${sessionScope.id}</div>
									</td>

									<td style="width: 80%"><span style="width: 60%"> <input
											type="text" name="comment_content" required>

									</span> <span class="right">
											<button
												class="grey lighten-4 btn-floating waves-effect waves-light z-depth-0"
												type="submit" name="action">
												<i class="tiny material-icons">comment</i>
											</button>
									</span></td>

								</form>
							</tr>

						</c:if>


					</table>
				</div>
			</c:if>
		</div>
	</div>
	<br>
	<br>
</body>
</html>