<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>BookList</title>
</head>
<body>   

<script type="text/javascript">
$(document).ready(function() {
    $('select').material_select();
  });
$(document).ready(function() {
    Materialize.updateTextFields();
  });
</script>

   <div class="container">
   
   <div class="row">

         <div class="input-field col s4">
            <select>
               <optgroup label="검색조건">
                  <option class="blue-text" disabled selected>선택하세요</option>
               </optgroup>
               <optgroup label="카테고리">
                  <option value="1">문학</option>
                  <option value="2">전공서</option>
               </optgroup>
               <optgroup label="검색">
                  <option value="3">제목</option>
                  <option value="4">작가</option>
               </optgroup>
            </select>
         </div>
         <div class="input-field col s6">
          <input placeholder="검색어" type="text" class="validate">
        </div>
      <div class="col s2">
         <p>
            <a class="white-text light-blue btn">검색</a>
      </div>
      
   </div>
      <c:forEach items ="${list}" var="list" varStatus="state">
      <div class="row light-blue lighten-4">
         <div class="col s3">
            <div class="card small">
               <div class="card-image">
                  <img src="${list.bookImg}">
               </div>
            </div>
         </div>

         <div class="col s7" >
            <p>
               <span>No</span>
            <ul>
               <li><a href="mflp?command=SearchBySelect&bookNo=${list.bookNo}&flag=0">${list.title}</a></li>
               <li> ${list.writer} | ${list.publisher} | ${list.writeDate} </li>
               <li> ${list.description} </li>
            </ul>
         </div>

         <div class="col s2">
            <p>
               <a class="white-text light-blue btn">보류</a>
         </div>
      </div>
   </c:forEach>
</div>
</body>
</html>