package util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import board.service.BoardServiceImpl;
import board.service.BoardService;
import board.vo.Board;
import member.vo.Member;

public class Common {
	public static int getDefaultValue(String source, int fixValue, int realValue) {
		int ret;
		if(Objects.isNull(source) || source.equals("")) {
			ret = fixValue;
		}else {
			ret = Integer.valueOf(source);			
		}
		return ret;
	}

	public static int getDefaultValue(String source) {
		return getDefaultValue(source, 1, 1);
	}
	
	public static Member getSessionMember(HttpServletRequest req) {
		Object memberObj = req.getSession().getAttribute("member");
		return memberObj == null ? null: (Member) memberObj;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.equals(""); 
	}
	
	public static Board getThisContent(HttpServletRequest req) {
		if(isEmpty(req.getParameter("boardno"))) {
			return null;
		}
		int boardno =Integer.parseInt(req.getParameter("boardno"));
		BoardService service = new BoardServiceImpl();
		
		Board board =  service.findBy(boardno);
		Member member = getSessionMember(req);
		if(member == null || !board.getUserid().equals(member.getUserid())) {
			return null;
		}
		else return board;
	}
}
