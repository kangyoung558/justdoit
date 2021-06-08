package board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean image;
	private Integer fileOrder;
	private Integer boardno;
}
