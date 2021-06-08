package member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String userid;
	private String password;
	private String name;
	private String email;
	private String birthDate;
	private String regDate;
	
	public Member(String userid, String password, String name, String email) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
}
