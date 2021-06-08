package board.service;

import java.util.List;

import board.vo.Attach;
import board.vo.Board;

public interface BoardService {
	//다섯개의 기능
	// CRUD
	
	void write(Board board);
	
	List<Board> list(Integer category);
	
	Board findBy(Integer boardno);
	
	void modify(Board board);
	
	List<Attach> remove(Integer boardno);
}
