<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="template/header.jsp"></jsp:include>
	<h1>회원가입</h1>
	<div class="join-wrapper">
		<div class="check-group">
			<div class="check-wrap">
				<label for="ck1">이용약관 동의(필수)</label>
		        <input type="checkbox" name="ck" id="ck1" required>
		        <hr>
		        <div>
		        	 JUST Do It 서비스 및 제품(이하 ‘JUST Do It’)을 이용해 주셔서 감사합니다.<br>
		        	 본 약관은 다양한 JUST Do It 서비스의 이용과 관련하여 JUST Do It 서비스를 제공하는 JUST Do It 주식회사(이하 ‘JUST Do It’)와 이를 이용하는 JUST Do It 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 JUST Do It 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.<br>
		        	 JUST Do It 서비스를 이용하시거나 JUST Do It 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.
		        </div>
			</div>
			<div class="check-wrap">
	        	<label for="ck2">개인정보 수집 및 이용에 대한 안내(필수)</label>
	        	<input type="checkbox" name="ck" id="ck2" required>
	        	<hr>
	         	<div>
	         		개인정보보호법에 따라 JUST Do It에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
	         	</div>
	        </div>
		</div>
		<div class="join-form">
			<form method="post" class="signin-form" novalidate>
				<fieldset>
					<legend class="leg">가입</legend>
							<label for="userid">
								<span>아이디</span>
								<input type="text" name="userid" id="userid"  placeholder="아이디는 영문으로 이루어진 8글자이내로작성해주세요">
								<span class="error">아이디를 4~8글자로 입력하세요</span>
							</label>
							<label for="password">
								<span>비밀번호</span>
								<input type="password" name="password" id="password" placeholder="비밀번호는 4~8글자 이내로 작성하세요">
								<span class="error">비밀번호는 4~8글자 이내로 작성하세요</span>					
							</label>
							<label for="passwordck">
								<span>비밀번호 확인</span>
								<input type="password" name="passwordck" id="passwordck" placeholder="비밀번호는 4~8글자 이내로 작성하세요">
								<span class="error">비밀확인은 비밀번호와 일치 시켜주세요</span>					
							</label>
							<label for="name">
								<span>이름</span>
								<input type="text" name="name" id="name" placeholder="15글자 이내의 이름을 입력하세요">
								<span class="error">"15글자 이내의 이름을 입력하세요</span>
							</label>
							<label for="email">
								<span>이메일</span>
								<input type="text" name="email" id="email" placeholder="ex) asd@naever.com">
								<span class="error">ex) asd@naever.com</span>
							</label>
							<label for="birthDate">
								<span>생년월일</span>
								<input type="text" name="birthDate" id="birthDate" placeholder="ex) 000101">
								<span class="error">생년월일의 형식을 맞춰주세요 ex) 000101 </span>
							</label>
						<div class="btn-box">
							<button class="join-btn">가입하기</button><a href="<%=request.getContextPath()%>/index"><button type="button">가입취소</button></a>
						</div>
				</fieldset>
			</form>
		</div>
	</div>
	<script>
		$(function(){
            $(".signin-form").on("submit",function(){
                //기본 이벤트제거
                //event.preventDefault();
                var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;


                if(this.userid.value.length < 4 || this.userid.value.length > 8){
                    $(this.userid).addClass("error-box").next().show();
                    $(this.userid).focus();
                    return false;
                }
                if(this.password.value.length < 4 || this.password.value.length > 8){
                    $(this.password).addClass("error-box").next().show();
                    $(this.password).focus();
                    return false;
                }
                if(this.password.value != this.passwordck.value){
                    $(this.passwordck).addClass("error-box").next().show();
                    $(this.passwordck).focus();
                    return false;
                }
                if(this.name.value.length < 1 || this.name.value.length > 15){
                    $(this.name).addClass("error-box").next().show();
                    $(this.name).focus();
                    return false;
                }
                if(!emailRegExp.test(this.email.value)){
                    $(this.email).addClass("error-box").next().show();
                    $(this.email).focus();
                    return false;
                }
    			alert('가입을 축하드립니다.');
           		//this.submit();
            });

            $(".signin-form input, .signin-form select").on("change", function(){
                $(this).removeClass("error-box").next().hide();
            })
        });
		
		<%-- $(".join-btn").click(function() {
			var URI ='<%=request.getContextPath()%>/index';
			var check = confirm('입력하신 정보로 가입 하시겠습니까?');
			if(check){
				 alert('가입을 축하드립니다.');
                 location.href = URI;
			}else {
				alert('첫 페이지로이동합니다.');
                event.preventDefault(); 
                location.href = URI;
			}
			}); --%>
	</script>
<jsp:include page="template/footer.jsp"></jsp:include>