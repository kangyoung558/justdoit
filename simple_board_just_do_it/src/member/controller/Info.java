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

@WebServlet("/info")
public class Info  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberService service = new MemberServiceImpl();
		
		String userid = req.getParameter("userid");
		
		Member member = service.findBy(userid); //자유게시판 목록
		 
		req.setAttribute("member", member);
		req.getRequestDispatcher("/WEB-INF/jsp/member/info.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String userid = req.getParameter("userid");
		
		MemberService service = new MemberServiceImpl();
		
		Member member = new Member(userid, password, name, email);
		service.modify(member);
		req.getSession().invalidate();
		resp.sendRedirect("index");
	}
	
	

}
