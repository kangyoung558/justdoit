package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.Member;
import util.Common;

@WebServlet("/remove")
public class Remove extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		//로그인된 아이디, 해당글의 작성자 아이디가 일치
		Member member = Common.getSessionMember(arg0);
//		if(board==null){ //글번호가 없을 경우
//			arg1.sendRedirect(arg0.getContextPath() +"/index");
//			return;
//		}
		MemberService service = new MemberServiceImpl();
		
		service.remove(member.getUserid());
		arg0.getSession().invalidate();
		arg1.sendRedirect(arg0.getContextPath() + "/index");
	}	
}
