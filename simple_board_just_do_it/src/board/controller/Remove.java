package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.BoardService;
import board.vo.Attach;
import board.vo.Board;
import util.Common;

@WebServlet("/board/remove")
public class Remove extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		//로그인된 아이디, 해당글의 작성자 아이디가 일치
		Board board = Common.getThisContent(arg0);
//		if(board==null){ //글번호가 없을 경우
//			arg1.sendRedirect(arg0.getContextPath() +"/index");
//			return;
//		}
		BoardService service = new BoardServiceImpl();
		int category = board.getCategory();
		List<Attach> attachs = service.remove(board.getBoardno());
		for(Attach attach : attachs) {
			File file = new File(arg0.getRealPath("upload")+ File.separator + attach.getUploadPath()
				+ File.separator + attach.getUuid());
				if(file.exists()) file.delete();
		}
	
		arg1.sendRedirect("list?category="+category);
	}	
}
