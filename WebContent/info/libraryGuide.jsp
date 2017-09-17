<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
.collection .collection-item.active {
    background-color: #4b7162;
    color: #ffffff;
}
.collection a.collection-item {
    display: block;
    transition: .25s;
    color: #4b7162;
}


</style>

<script>

</script>
</head>
<body>
<div class="fixed-action-btn horizontal">
    <a class="btn-floating btn-small teal" href="#top">
      <i class="material-icons">publish</i>
    </a>
</div>

	<div class="container">
		<Br>
			<div><h4 class=""><b>도서관 정보</b></h4></div>
			</div>
			<nav class ="white z-depth-0">
			    <div class="col s1 nav-wrapper left-align container "> 
			        <a href="#!" class="breadcrumb black-text " >도서관 정보</a>
			        <a href="#!" class="breadcrumb black-text" >도서관 안내</a> 
			        </div>     
   			</nav> 
   			
   			<div class="container">
			<div class="section">
			<div class="row">
				<div class="col s3">
					<div class="collection">
						<a href="#introduce" class="collection-item active">목차</a>
						<a href="#introduce" class="collection-item">도서관장 인사의 말</a> 
						<a href="#rule" class="collection-item ">도서관 규칙</a>
						<a href="#people" class="collection-item">조직 및 연락처</a> 
					</div>
				</div>
				<div class="col s9" id="introduce">
					 우리 도서관은 1923년 판교역 작은 도서관에서 시작하였으며, 그 후 1935년 판교캠퍼스 파이퍼홀, 1957년 헬렌관을 거쳐 1984년 현재의 도서관으로 이전하였습니다.<p>
					도서관은 정보환경 변화에 따른 이용자의 요구를 충족시키고, 연구학습 활동을 지원한다는 목표 아래, 국내‧외 단행본, 연속간행물, 시청각자료를 비롯한 학술 데이터베이스, 전자저널, 전자책, 동영상강좌 등의 풍부한 컨텐츠를 소장하고, 각종 프로그램을 개발하여 도서관 서비스의 수준을 높이기 위해 노력하고 있습니다.<p>
					또한 중앙도서관을 중심으로 공학, 법학, 신학, 음악, 의학도서관과 같이 주제 분관을 특성화하고, 주제전담 서비스 체제를 구축하여 전공별 주제가이드, 개인 및 특정그룹에 맞춘 정보검색‧활용교육, 정보조사 지원 서비스 등의 심도 있는 주제서비스를 제공하고 있습니다.<p>
					도서관은 한 걸음 더 나아가 더 많은 연구성과를 낼 수 있도록 교내 연구기관과의 협력은 물론 타대학 도서관 및 학술기관까지 확대된 상호협력 체제를 통해 분산되어 있는 방대한 자료를 효과적으로 수집, 활용하여 적극적으로 서비스하고 있습니다.<p>	
				</div>
				
				<div class="col s10 offset-s3" id="rule">
				<br><br><br><br><br><h5>  <i class="tiny material-icons">dashboard</i> 대출책수 및 대출 기간</h5><p>
				 <table class="bordered">
			        <thead>
			          <tr>
			              <th>구분</th>
			              <th>대출한도</th>
			              <th>대출기간</th>
			          </tr>
			        </thead>
			
			        <tbody>
			          <tr>
			            <td>일반</td>
			            <td>2책</td>
			            <td>14일</td>
			          </tr>
			          <tr>
			            <td>직원</td>
			            <td>2책</td>
			            <td>14일</td>
			          </tr>
			        </tbody>
			      </table>
				
				<div>
				 <br><br><p><h5>  <i class="tiny material-icons">dashboard</i> 연체료</h5><p> 
				 <table class="bordered">
			        <thead>
			          <tr>
			              <th>자료</th>
			              <th>연체료</th>
			          </tr>
			        </thead>
			
			        <tbody>
			          <tr>
			            <td>일반자료</td>
			            <td>1책당 1일 100원</td>
			          </tr>
			        </tbody>
			      </table>
				</div>
				</div>
				
				<div class="col s10 offset-s3" id="people">
				<br><br><br><br><h5>  <i class="tiny material-icons">dashboard</i> 조직 및 연락처</h5><p>
				<table class="bordered">
			        <thead>
			          <tr>
			              <th>부서</th>
			              <th>직위</th>
			              <th>이름</th>
			              <th>담당업무</th>
			              <th>전화번호</th>
			          </tr>
			        </thead>
			
			        <tbody>
			          <tr>
			            <td>총괄</td>
			            <td>도서관장</td>
			            <td>최영섭</td>
			            <td>도서관 총괄</td>
			            <td>010-1111-1111</td>
			          </tr>
			          <tr>
			            <td>학술정보지원팀</td>
			            <td>사서</td>
			            <td>강문식</td>
			            <td>학술정보지원팀 총괄</td>
			            <td>010-2222-2222</td>
			          </tr>
			          <tr>
			            <td>수서실</td>
			            <td>사서</td>
			            <td>이하늘</td>
			            <td>도서관 예산관리/ 구입관리/ 자료교환 관리</td>
			            <td>010-3333-3333</td>
			          </tr>
			          <tr>
			            <td>대출실</td>
			            <td>사서</td>
			            <td>김혜림</td>
			            <td>대출, 예약, 이용자관리</td>
			            <td>010-4444-4444</td>
			          </tr>
			          <tr>
			            <td>기획홍보실</td>
			            <td>사서</td>
			            <td>윤진영</td>
			            <td>홍보기획,운영, 도서관소식 관리</td>
			            <td>010-5555-5555</td>
			          </tr>
			          <tr>
			            <td>전산지원실</td>
			            <td>사서</td>
			            <td>박태규</td>
			            <td>서버 및 전산관리, 간행물 디지털컨텐츠 구축, 운영</td>
			            <td>010-6666-6666</td>
			          </tr>
			        </tbody>
			      </table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>