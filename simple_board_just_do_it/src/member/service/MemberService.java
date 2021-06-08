package member.service;

import member.vo.Member;

public interface MemberService {
	//가입
	public abstract void join(Member member);
	//로그인
	Member login(Member member);	
	//로그아웃
	void logout(Member member);
	
	//회원찾기 아이디를 통한 탐색 
	Member findBy(String userid);
	
	void modify(Member member);
	
	int remove(String userid);
	
}
