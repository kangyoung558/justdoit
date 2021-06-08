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


// controller 역할 
@WebServlet("/join")
public class Join extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 페이지 포워딩 
		req.getRequestDispatcher("join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//아이디 패스워드 값 확인
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String birthDate = req.getParameter("birthDate");
		
		
		Member member = new Member(userid, password, name, email, birthDate, null);
		MemberService service = new MemberServiceImpl();
		System.out.println(member);
		service.join(member);
		
		// get으로 리다이렉트
		resp.sendRedirect("index");
		
	}
	
}
