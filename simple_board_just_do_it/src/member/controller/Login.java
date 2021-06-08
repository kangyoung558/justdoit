package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.Member;


// controller 역할 
@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 페이지 포워딩 
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//아이디 패스워드 값 확인
		req.setCharacterEncoding("utf-8");
	
		Member member = new Member();
		member.setUserid(req.getParameter("id"));
		member.setPassword(req.getParameter("pw"));
		
		MemberService service = new MemberServiceImpl();
		
		//분기 
		Member result = service.login(member);
		System.out.println(result);
		if(result != null) {
			//로그인 성공시
			HttpSession session = req.getSession(); //세션생성
			session.setAttribute("member", result);
			
			// 쿠키
			String idsave = req.getParameter("idsave");
			if(idsave!=null) {
				Cookie cookie = new Cookie("idsave", member.getUserid());
				cookie.setMaxAge(60 * 60 * 24 * 365);
				resp.addCookie(cookie);
			}
			else {
				
				for(Cookie cookie : req.getCookies()){
					if(cookie.getName().equals("idsave")) {
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
					}
				}
			}
			System.out.println(idsave);
		}
		resp.sendRedirect(req.getParameter("uri"));
	}
	
}
