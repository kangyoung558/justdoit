

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.service.BoardServiceImpl;
import board.vo.Board;
import util.Common;

@WebServlet("/index")
public class Index extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardService service = new BoardServiceImpl();
		
		
		List<Board> list = service.list(1); //자유게시판 목록
		req.setAttribute("list", list);
		List<Board> list2 = service.list(2); //공지사항 목록
		req.setAttribute("list2", list2);
		List<Board> list3 = service.list(3); //큐앤에이 목록
		req.setAttribute("list3", list3);
		List<Board> list4 = service.list(4); //자료실 목록
		req.setAttribute("list4", list4);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
