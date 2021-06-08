package member.service;

import lombok.Getter;
import member.dao.MemberDao;
import member.vo.Member;


@Getter
public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao = new MemberDao();
	
	@Override
	public void join(Member member) {
		memberDao.join(member);
	}

	@Override
	public Member login(Member member) {
		return memberDao.login(member);
	}

	@Override
	public void logout(Member member) {
		
	}

	@Override
	public Member findBy(String userid) {
		return memberDao.findBy(userid);
	}

	@Override
	public void modify(Member member) {
		memberDao.modify(member);
	}

	@Override
	public int remove(String userid) {
		return memberDao.remove(userid);
	}
	
	
}
