package board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private Integer replyno;
	private String content;
	private String userid;
	private String regdate;
	private Integer boardno;
	
	private String username;

	public Reply(String content, String userid, Integer boardno) {
		super();
		this.content = content;
		this.userid = userid;
		this.boardno = boardno;
	}

	public Reply(Integer replyno, String content) {
		super();
		this.replyno = replyno;
		this.content = content;
	} 
}
