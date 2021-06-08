package board.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private Integer boardno;
	private String title;
	private String content;
	private String userid;
	private String regdate;
	private Integer hitcount;
	private Integer parentno;
	private Integer category;
	private String username;
	
	private List<Attach> attachs;
	
	
	
	public Board(String title, String content, String userid, Integer parentno, Integer category) {
		super();
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.parentno = parentno;
		this.category = category;
	}


	public Board(Integer boardno, String title, String content) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.content = content;
	}
	
	
	
}
