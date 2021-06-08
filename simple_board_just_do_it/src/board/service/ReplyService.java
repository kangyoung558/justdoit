package board.service;

import java.util.List;
import board.vo.Reply;

public interface ReplyService {
	
	//댓글 작성
	void wrtie(Reply reply);
	
	//댓글 수정
	void modify(Reply reply);
	
	//댓글 삭제 
	void remove(int replyno);
	
	//댓글 목록
	List<Reply> list(int boardno);

	Reply findBy(int replyno);
}
