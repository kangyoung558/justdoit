package board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.service.BoardServiceImpl;
import board.service.BoardService;
import board.vo.Board;
import util.Common;

@WebServlet("/board/list")
public class BoardList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardService service = new BoardServiceImpl();
		
		Integer category = Common.getDefaultValue(req.getParameter("category"));
		List<Board> list = service.list(category); //자유게시판 목록
		req.setAttribute("list", list);
		req.getRequestDispatcher("../WEB-INF/jsp/board/list.jsp").forward(req, resp);
	}
	
}
