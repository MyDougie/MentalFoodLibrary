<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>

</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();

			$('.datepicker').pickadate({
				selectMonths : true, // Creates a dropdown to control month
				selectYears : 15 // Creates a dropdown of 15 years to control year
			});
		});
	</script>
	

	<!-- <div class="container"> -->
		<form name="insertForm" method="post" action="mflp?command=bookInsert"
			enctype="multipart/form-data">
	<div class="row">
		<h4 class="col s4"><b>도서 등록</b></h4>
	</div>
	<nav class="z-depth-0" >
      <div class="nav-wrapper white	">
         <div class="col s12 ">
            <a href="#!" class="breadcrumb black-text">관리자 페이지</a>
            	<a href="#!" class="breadcrumb black-text">도서 등록</a>
         </div>
      </div>
   </nav>
<!-- 			<div class="row">
				<div class="card small">
					<div class="card-image center-align">
						<img src="">
					</div>
				</div>
			</div> -->
			<div class="file-field input-field">
				<div class="waves-effect waves-light btn teal darken-4">
					<span>Picture</span> <input type="file" name="file" multiple>
				</div>
				<div class="file-path-wrapper">
					<input class="file-path validate" type="text"
						placeholder="업로드할 사진들을 선택하세요">
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<input name="title" id="title" type="text" class="validate">
					<label for="제목">제목</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input name="writer" id="writer" type="text" class="validate">
					<label for="작가">작가</label>
				</div>
				<div class="input-field col s6">
					<select name="category">
						<option value="" disabled selected>등록할 카테고리를 선택하세요</option>
						<option value="0">철학</option>
						<option value="1">과학</option>
						<option value="2">문학</option>
						<option value="3">수학</option>
						<option value="4">생활정보</option>
						<option value="5">자격증</option>
						<option value="6">외국어</option>
						<option value="7">잡지</option>
						
					</select> <label>카테고리</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input name="publisher" id="publisher" type="text" class="validate">
					<label for="출판사">출판사</label>
				</div>
				<div class="input-field col s6">
					<input name="writeDate" type="date" class="datepicker" /> <label for="datepicker">출판일</label>
				</div>
			</div>
			<div>
				<div class="row">
					<div class="input-field col s12">
						<textarea name="description" id="description"
							class="materialize-textarea" data-length="200"
							placeholder="200자 이내로 작성하세요"></textarea>
						<label for="textarea1">설명</label>
					</div>
					<div class="center-align">
						<button class="waves-effect waves-light btn teal darken-4" type=submit>
							확인
						</button>
						<button class="waves-effect waves-light btn teal darken-4" type=reset>
							취소
						</button>
					</div>
				</div>
			</div>

		</form>
	<!-- </div> -->


</body>
</html>