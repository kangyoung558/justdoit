package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.BoardService;
import board.vo.Board;
import util.Common;

@WebServlet("/board/modify")
public class Modify extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if(Common.getSessionMember(req) == null || Common.isEmpty(req.getParameter("boardno"))) {
//			resp.sendRedirect(req.getContextPath() +"/index");
//			return;
//		}
		BoardService service = new BoardServiceImpl();
		Board board = service.findBy(Integer.parseInt(req.getParameter("boardno"))); //자유게시판 목록
		req.setAttribute("board", board);
		req.setAttribute("title", "글수정");
		
		req.getRequestDispatcher("../WEB-INF/jsp/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//타이틀, 컨텐트, 카테고리, 작성자, 
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int boardno = Common.getDefaultValue(req.getParameter("boardno"));
		BoardService service = new BoardServiceImpl();
		
		if(!Common.getSessionMember(req).getUserid().equals(service.findBy(boardno).getUserid())) {
			resp.sendRedirect(req.getContextPath() +"/index");
			return;
		}
		
		Board board = new Board(boardno, title, content);
		service.modify(board);
		resp.sendRedirect("detail?boardno=" + boardno);
	}
}
