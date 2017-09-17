<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<div id="wrap">
		<!-- content 시작 -->
		<div id="main">
			<div class="row" style="text-align: center">
				<ul class="pagination">
					<c:if test="${count > 0}">
						<c:set var="pageCount"
							value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
						<c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}" />
						<c:set var="endPage" value="${startPage + pageGroupSize-1}" />

						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" />
						</c:if>
						
						<!--Search의 경우 아래 출력 -->
						<c:if test = "${keyField != null}">
							<c:if test="${numPageGroup > 1}">
								<li class="disabled"><a
									href="SelectSearchRent?pageNum=${(numPageGroup-2)*pageGroupSize+1 }&keyField=${keyField }&keyWord=${keyWord }"><i
										class="material-icons">chevron_left</i></a></li>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${requestScope.currentPage == i }">
										<li class="waves-effect"><a
											href="SelectSearchRent?opt=${opt}&pageNum=${i}&keyField=${keyField }&keyWord=${keyWord }">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li class="waves-effect"><a
											href="SelectSearchRent?opt=${opt}&pageNum=${i}&keyField=${keyField }&keyWord=${keyWord }">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test="${numPageGroup < pageGroupCount}">
								<li class="waves-effect"><a
									href="SelectSearchRent?pageNum=${numPageGroup*pageGroupSize+1}&keyField=${keyField }&keyWord=${keyWord }">
										<i class="material-icons">chevron_right</i>
								</a></li>
							</c:if>
						</c:if>
					</c:if>
				</ul>
			</div>
		</div>
	</div>


</body>
</html>