<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>MentalFoodLibraryProject</title>

<script>
function mv(th)
{
	location.href="mflp?command=boardSearchByNo&flag=1&boardNo="+th.id;
}
</script>
<body>
<!-- 게시판 -->
	<div class="container">
		<div class="section">
			<div class="row">
			
				<div class="col s12 m6" >
					<div class="card small   grey lighten-3 z-depth-0">
						<div class="card-content gray-text ">
							<span class="card-title ">공지 게시판</span>
								<p>
							<table class="bordered ">
								<c:forEach items="${bulletinList}" var="dto" varStatus="state">
								<tr onclick="mv(this)" id="${dto.boardNo }"  style="color:gray">
								<td style="text-align:left">${dto.title }</td> 
								<td style="text-align:right">${dto.writeDate }</td>
								</tr>
								</c:forEach>	
							</table>	
						</div>
						<div class="card-action" style="text-align: right">
							<a class="btn-floating pulse waves-effect waves-light grey lighten-1" href="mflp?command=boardSelectAll&category=1"><i
								class="material-icons">add</i></a>
						</div>
					</div>
				</div>

				<div class="col s12 m6" >
					<div class="card small   grey lighten-3  z-depth-0">
						<div class="card-content gray-text ">
							<span class="card-title">자유 게시판</span>
							<p>
							<table class="bordered">
								<c:forEach items="${freeList}" var="dto" varStatus="state">
								<tr onclick="mv(this)" id="${dto.boardNo }" style="color:gray" >
								<td style="text-align:left">${dto.title }</td> 
								<td style="text-align:right">${dto.writeDate }</td>
								</tr>
								</c:forEach>	
							</table>							
						
						<div class="card-action" style="text-align: right">
							<a href="mflp?command=boardSelectAll&category=2"
								class="btn-floating  pulse waves-effect waves-light grey lighten-1">
								<i class="material-icons">add</i>
							</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
	</div>


</body>
</html>