package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.ReplyService;
import board.service.ReplyServiceImpl;
import board.service.BoardService;
import board.vo.Board;
import board.vo.Reply;

@WebServlet("/board/detail")
public class Detail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardService service = new BoardServiceImpl();
		ReplyService replyService = new ReplyServiceImpl();
		
		int boardno = Integer.parseInt(req.getParameter("boardno"));
		
		Board board = service.findBy(boardno); //자유게시판 목록
		List<Reply> replies = replyService.list(boardno);
		
		req.setAttribute("board", board);
		req.setAttribute("replies", replies);
		req.getRequestDispatcher("../WEB-INF/jsp/board/detail.jsp").forward(req, resp);
	}

}
