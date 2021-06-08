package reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ReplyService;
import board.service.ReplyServiceImpl;
import util.Common;

@WebServlet("/board/removeReply")
public class Remove extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀, 컨텐트, 카테고리, 작성자
		int replyno = Integer.parseInt(req.getParameter("replyno"));
		int boardno = Integer.parseInt(req.getParameter("boardno"));
	
		ReplyService service = new ReplyServiceImpl();
		
		if(!Common.getSessionMember(req).getUserid().equals(service.findBy(replyno).getUserid())) {
			resp.sendRedirect(req.getContextPath() +"/index");
			return;
		}
		service.remove(replyno);

		resp.sendRedirect("detail?boardno=" + boardno);
	}
}
