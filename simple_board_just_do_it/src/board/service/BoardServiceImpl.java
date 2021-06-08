package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.vo.Attach;
import board.vo.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = new BoardDao();
	
	@Override
	public void write(Board board) {
		boardDao.write(board);
	}

	@Override
	public List<Board> list(Integer category) {
		return boardDao.list(category);
	}

	@Override
	public Board findBy(Integer boardno) {
		Board board = boardDao.findBy(boardno);
		List<Attach> attachs = boardDao.getAttachs(boardno);
		board.setAttachs(attachs);
		return board;
	}

	@Override
	public void modify(Board board) {
		boardDao.modify(board);
	}

	@Override
	public List<Attach> remove(Integer boardno) {
		//실제파일 삭제를 위한 attach 객체 생성
		List<Attach> attachs = boardDao.getAttachs(boardno);
		
		//DB상에 있던 첨부파일테이블 내의 글 삭제 
		//게시글 삭제
		boardDao.remove(boardno);
		//파일 삭제 로직
		return attachs;
	}
	
	public static void main(String[] args) {
		BoardService service = new BoardServiceImpl();
		System.out.println(service.findBy(70));
	}
}
