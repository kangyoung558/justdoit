<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../../../template/header.jsp" />
<div class="join-form">
			<form method="post" class="signin-form" novalidate>
				<fieldset>
					<legend class="leg">가입</legend>
							<label for="userid">
								<span>아이디</span>
								<input type="text" name="userid" id="userid"  placeholder="아이디는 영문으로 이루어진 8글자이내로작성해주세요" value="${member.userid}" readonly="readonly">
								<span class="error">아이디를 4~8글자로 입력하세요</span>
							</label>
							<label for="password">
								<span>비밀번호</span>
								<input type="password" name="password" id="password" placeholder="비밀번호는 4~8글자 이내로 작성하세요" value="${member.password}">
								<span class="error">비밀번호는 4~8글자 이내로 작성하세요</span>					
							</label>
							<label for="passwordck">
								<span>비밀번호 확인</span>
								<input type="password" name="passwordck" id="passwordck" placeholder="비밀번호는 4~8글자 이내로 작성하세요">
								<span class="error">비밀확인은 비밀번호와 일치 시켜주세요</span>					
							</label>
							<label for="name">
								<span>이름</span>
								<input type="text" name="name" id="name" placeholder="15글자 이내의 이름을 입력하세요" value="${member.name}">
								<span class="error">"15글자 이내의 이름을 입력하세요</span>
							</label>
							<label for="email">
								<span>이메일</span>
								<input type="text" name="email" id="email" placeholder="ex) asd@naever.com" value="${member.email}">
								<span class="error">ex) asd@naever.com</span>
							</label>
							<label for="birthDate">
								<span>생년월일</span>
								<input type="text" name="birthDate" id="birthDate" placeholder="ex) 000101" value="${member.birthDate}" readonly="readonly">
								<span class="error">생년월일의 형식을 맞춰주세요 ex) 000101 </span>
							</label>
						<div class="btn-box">
							<button class="join-btn" onClick="alert('수정이 완료되었습니다. 다시 로그인 해주세요.')">수정하기</button><a href="remove"><button type="button" onClick="alert('그동안 이용해 주셔서 감사합니다. 더 나은 서비스로 보답하겠습니다.')">탈퇴하기</button></a>
						</div>
				</fieldset>
			</form>
		</div>
<jsp:include page="../../../template/footer.jsp"/>