<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>
			<div class="row">
		<h4 class="col s4"><b>���� ��Ȳ</b></h4>
	</div>
	<nav class="z-depth-0" >
      <div class="nav-wrapper white	">
         <div class="col s12 ">
            <a href="#!" class="breadcrumb black-text">������ ������</a>
            	<a href="#!" class="breadcrumb black-text">���� ��Ȳ</a>
         </div>
      </div>
   </nav>
			<div class="col s12 white">
				<!-- ���⿹��/��Ȳ -->
				<table class="highlight centered">
					<c:if test="${requestScope.searchReserveList != null}">
					<thead>
						<tr>
							<th>�����ȣ</th>
							<th>������</th>
							<th>������ ID</th>
							<th>������</th>
							<th>���� ����</th>
						</tr>
					</thead>
					</c:if>
					<c:forEach items='${searchReserveList}' var='reserveItem'>
						<tr>
							<td>${reserveItem.reserveNo}</td>
							<td>${reserveItem.title}</td>
							<td>${reserveItem.reserver}</td>
							<td>${reserveItem.reserveDate}</td>
							<td>${reserveItem.order}</td>
						
						</tr>
					
					</c:forEach>
					
				</table>
			</div>
			
			<!-- ���� -->
			<div class="row"> 
				<div class="col s12"> </div>
			</div>
			
			<!-- ���ǰ˻�-->
			<div id="searchForm" class="row">
            <form action="SelectSearchReserve" method="post" class="col s12">
               <div class="row">
                  <div class="input-field col s3">
                     <select name="opt">
                        <option value="0">������</option>
                        <option value="1">ȸ��ID</option>
                     </select> 
                  </div>
                  <div class="input-field col s7">
                     <input type="text" name="keyWord"/>&nbsp; 	
                     
                  </div>
                  <div class="input-field col s2">
                    <button class="btn waves-effect teal darken-4" type="submit" name="action">�˻�</button>
                  </div>
               </div>
            </form>
         </div>

</body>
</html>