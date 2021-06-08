package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.Member;
import util.DBConn;

public class MemberDao {
	
	public void join(Member member) {
		Connection conn = DBConn.getConnection();
		//쿼리문 작성
		try {
//			Statement stmt = conn.createStatement();
//			String sql = "INSERT INTO member(USERID, PASSWORD, NAME, EMAIL, BIRTHDATE) \r\n" + 
//					"VALUES ('"+member.getUserid()+"','"+member.getPassword()+"', '"+member.getName()+"', '"+member.getEmail()+"', TO_DATE('"+member.getBirthDate()+"', 'YYMMDD'))";
//			
//			//퀀리문장 실행 
//			stmt.executeUpdate(sql);
			//전처리 문장을 선언 
			String sql = "INSERT INTO member(USERID, PASSWORD, NAME, EMAIL, BIRTHDATE) \r\n" + 
					"VALUES (?,?, ?, ?, TO_DATE(?, 'YYMMDD'))";
			
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//wjscjfl answk vkfkalxj qkdls
			int index = 1;
			pstmt.setString(index++, member.getUserid());
			pstmt.setString(index++, member.getPassword());
			pstmt.setString(index++, member.getName());
			pstmt.setString(index++, member.getEmail());
			pstmt.setString(index++, member.getBirthDate());
			
			//실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public Member login(Member member) {
		Member result = null;
		Connection conn = DBConn.getConnection();
		String sql = "SELECT userid, password, name, email, to_char(BIRTHDATE, 'YYMMDD'), to_char(REGDATE, 'YYMMDD') FROM member WHERE USERID = ? and password = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int index = 1 ;
				result = new Member(
						rs.getString(index++),
						rs.getString(index++),
						rs.getString(index++),
						rs.getString(index++),
						rs.getString(index++),
						rs.getString(index++)
						);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Member findBy(String userid) {
		Member result = null;
		Connection conn = DBConn.getConnection();
		String sql = "SELECT userid, password, name, email, to_char(BIRTHDATE, 'YYMMDD') \r\n" + 
				"FROM member\r\n" + 
				"WHERE USERID = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
		
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int idx =1;
				
				result = new Member();
				result.setUserid(rs.getString(idx++));
				result.setPassword(rs.getString(idx++));
				result.setName(rs.getString(idx++));
				result.setEmail(rs.getString(idx++));
				result.setBirthDate(rs.getString(idx++));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void modify(Member member) {
		Connection conn = DBConn.getConnection();
		//쿼리문 작성
		try {
			//전처리 문장을 선언 
			String sql = "UPDATE member SET \r\n" + 
					"password = ?,\r\n" + 
					"name = ?,\r\n" + 
					"email = ?\r\n" + 
					"WHERE userid = ?";
			
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, member.getPassword());
			pstmt.setString(index++, member.getName());
			pstmt.setString(index++, member.getEmail());
			pstmt.setString(index++, member.getUserid());
			
			//실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public int remove(String userid) {
		Connection conn = DBConn.getConnection();
		int ret= 0;
		//쿼리문 작성
		try {
			//첨부파일 테이블에서의 삭제
			String sql = "DELETE member WHERE userid = ?";
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, userid);
			//실행
			ret= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return ret;
	}
	public static void main(String[] args) {
//		Member member = new Member("kang", "1234", null ,null, null, null);
//		System.out.println(new MemberDao().login(member));
		MemberDao dao = new MemberDao();
		
		System.out.println(dao.findBy("hong"));
		dao.modify(new Member("hong", "2222", "다시바", "dd@naver.com"));
		System.out.println(dao.findBy("hong"));
		
//		System.out.println(dao.remove("qqqq")); 
	}
}
