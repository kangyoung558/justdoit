package reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.service.BoardServiceImpl;
import board.service.ReplyService;
import board.service.ReplyServiceImpl;
import board.vo.Board;
import board.vo.Reply;
import util.Common;

@WebServlet("/board/writeReply")
public class Write extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀, 컨텐트, 카테고리, 작성자
		String userid = Common.getSessionMember(req).getUserid();
		String content = req.getParameter("content");
		int boardno = Integer.parseInt(req.getParameter("boardno"));

		Reply reply = new Reply(content, userid, boardno);
		ReplyService service = new ReplyServiceImpl();
		service.wrtie(reply);

		resp.sendRedirect("detail?boardno=" + boardno);
	}
}
