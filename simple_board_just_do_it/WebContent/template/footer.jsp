<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer>
	<div class="madalwrap">
		<div class="modal" id="modal">
			<div class="modal_content">
				<div class="modal-img">
					<img alt="아이콘" src="/member_board/assets/image/icon-white-comment.png">
					<img alt="아이콘" src="/member_board/assets/image/icon-white-group.png">
					<img alt="아이콘" src="/member_board/assets/image/icon-white-view.png">
				</div>
				<p>JUST Do It은 2021년 3월에 시작하여 앞으로 한국을 대표하는 개발자 커뮤니티가 될것 입니다.</p>
				<p>단순히 기술 커뮤니티를 넘어, SW개발자의 기술과 삶을 나누는 공간으로서의 역할을 담당하고 있습니다.</p>
				<p>한국을 넘어 전세계적으로 한국의 SW개발자들의 위상을 널리 펼칠 수 있는 공간이 되도록 노력할것 입니다.</p>
			</div>
		</div>
	</div>
	<div class="madalwrap">
		<div class="modal" id="modal2">
			<div class="modal_content">
				<h2>개인정보 취급 방침</h2>
				<div>
					JUST Do It은(는) 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
				</div>
				<div>
					 JUST Do It은(는) 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다.
				</div>
				<div>
					JUST Do It은(는) 개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전동의를 구할 예정입니다.
				</div>
				<div>
					가. 홈페이지 회원가입 및 관리<br>
					회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리, 서비스 부정이용 방지, 고충처리, 분쟁 조정을 위한 기록 보존 등을 목적으로 개인정보를 처리합니다.
				</div>
				<div>
					나. 민원사무 처리<br>
					민원인의 신원 확인, 민원사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보 등을 목적으로 개인정보를 처리합니다.
				</div>
				<div>
					다. 재화 또는 서비스 제공<br>
					서비스 제공, 콘텐츠 제공, 맞춤 서비스 제공, 본인인증 등을 목적으로 개인정보를 처리합니다.
				</div>
			</div>
		</div>
	</div>
	<div class="madalwrap">
		<div class="modal" id="modal3">
			<div class="modal_content">
				<div>
					<h2>광고 문의</h2>
					<p>전화 번호: 010-1234-1234</p>
					<p>이메일: ad@naver.com</p>
					<p>광고주님들의 사랑은 JUST Do It의 발전에 큰 힘이 됩니다.</p>
					<p>많은 문의 부탁드립니다.</p>
				</div>
			</div>
		</div>
	</div>
	<a href="#modal" rel="modal:open" class="madallink">About JUST Do It</a> 
	<a href="#modal2" rel="modal:open" class="madallink">개인정보 처리 방침</a> 
	<a href="#modal3" rel="modal:open" class="madallink">광고 문의</a>

	<p>copyright &copy; 2021</p>
	<address>충청남도 천안시 신부동 JUST Do It</address>
</footer>
</div>
<script>
	$(".do-prev").click(function() {
		confirm("정말 뒤로 가시겠습니까?") ? history.back() : "";
	});
/* 	$(".madallink").click(function() {
		$(".modal").fadeIn();

	});

	$(".modal_content").click(function() {
		$(".modal").fadeOut();

	}); */
</script>
</body>
</html>