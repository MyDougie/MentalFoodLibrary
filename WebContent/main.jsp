<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>MentalFoodLibraryProject</title>
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/materialize.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
<link href="${pageContext.request.contextPath}/css/style.css"
	type="text/css" rel="stylesheet" media="screen,projection" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script type="text/javascript">

</script>
</head>
<body>

    <div id="wrap">
    <!--  header 시작하기.. -->
        <div id="header">
            <jsp:include page="header.jsp" />
        </div>
        <!-- content 시작 -->
        <div id="main">
            <jsp:include page="${param.contentPage }" />
        </div>
        <!--  footer 시작 -->
        <div id="footer"> 
       		<jsp:include page="footer.jsp" /> 
        </div>
    </div>

</body>
</html>
