$("#btn_join").click(function() {
       
            var tel1_pattern = /(^01[016789]$)/; //정규식
 
            
            if ($("#id").val() == "") {
                alert("아이디를 꼭 입력하세요!");
                $("#id").focus();
            } else if ($("#password").val() == "") {
                alert("비밀번호를 꼭 입력하세요!");
                $("#password").focus();
 
            } else if ($("#passwordCheck").val() == "") {
                alert("비밀번호확인 을 꼭 입력하세요!");
                $("#passwordCheck").focus();
 
            } else if ($("#password").val() != $("#passwordCheck").val()) {
                alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
                $("#password").val("");
                $("#passwordCheck").val("");
                $("#password").focus();
 
            } else if ($("#name").val() == "") {
                alert("이름을 꼭 입력하세요!");
                $("#name").focus();
 
            } else if ($("#tel1").val() == "") {
                alert("전화번호 첫번째자리 입력하세요!");
                $("#tel1").focus();
 
            } else if ($("#tel1").val().length != 3) {
 
                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                $("#tel1").val("");
                $("#tel1").focus();
 
            } else if (isNaN($("#tel1").val())) {
                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                $("#tel1").val("");
                $("#tel1").focus();
 
            } else if (!tel1_pattern.test($("#tel1").val())) {
                alert("010, 011, 016, 017, 018, 019,o19만 가능합니다.")
                $("#tel1").val("")
                $("#tel1").focus();
            }
            /*
            if($("#tel1").val() != "" & $("#tel1").val().length ==3 & !isNaN($("#tel1").val()) ){
            alert("okkkkkkk");
            var pattern = /(^01[016789]$)/; //정규식 슬래쉬(/)로 시작해서, 슬래쉬(/)로 끝났다.
            var tel1=$("#tel1").val();
            if(! pattern.test(tel1)){
            //틀리면
            alert("010, 011, 016, 017, 018, 019,o19만 가능합니다.")  
            $("#tel").val("")
            $("#tel").focus();
                   
                }
            }*/
 
            else if ($("#tel2").val() == "") {
                alert("전화번호 두번째자리를 입력하세요!");
                $("#tel2").focus();
 
            } else if ($("#tel1").val().length != 3) {
 
                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                $("#tel2").val("");
                $("#tel2").focus();
 
            } else if (isNaN($("#tel2").val())) {
                alert("전화2을 3~4개의 숫자로 꼭 입력하세요!");
                $("#tel2").val("");
                $("#tel2").focus();
 
            } else if ($("#tel2").val() != "" & $("#tel2").val().length > 4 & isNaN($("#tel2").val())) {
                alert("okkkkkkk");
                var pattern = /(^01[016789]$)/; //정규식 슬래쉬(/)로 시작해서, 슬래쉬(/)로 끝났다.
                var tel1 = $("#tel2").val();
                if (!pattern.test(tel1)) {
                    //틀리면
                    alert("010, 011, 016, 017, 018, 019,o19만 가능합니다.")
                    $("#te2").val("")
                    $("#te2").focus();
 
                }
            }
 
            else if ($("#tel3").val() == "") {
                alert("전화번호 세번째자리를 입력하세요!");
                $("#tel3").focus();
 
            } else if ($("#addr").val() == "") {
                alert("주소를 꼭 입력하세요!");
                $("#addr").focus();
 
            } else if ($("#birth").val() == "") {
                alert("생일을  꼭 입력하세요!");
                $("#birth").focus();
 
            } else if ($("#job").val() == "") {
                alert("직업을  꼭 선택하세요!");
                $("#job").focus();
 
            } else if ($("#man").is(':checked') == false && $("#woman").is(':checked') == false) {
                alert("남자또는 여자를  꼭 선택하세요!");
            } else if ($("#email").val() == "") {
                alert("이메일을 꼭 입력하세요!");
                $("#email").focus();
            } else if ($("#intro").val() == "") {
                alert("자기소개를 꼭 입력하세요!");
                $("#intro").focus();
            } else {
                alert("ok");
            }
 
 
        });
