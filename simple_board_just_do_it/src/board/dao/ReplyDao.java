package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.vo.Reply;
import util.DBConn;

public class ReplyDao {
	public static void main(String[] args) {
		ReplyDao dao = new ReplyDao();
		//댓글조회
		dao.list(102).forEach(System.out::println);
		
		//댓글 작성 
		Reply reply = new Reply("자바에서 작성 댓글", "hong", 102);
		dao.wrtie(reply);
		
		System.out.println();
		//댓글조회
		dao.list(102).forEach(System.out::println);
		
		reply = new Reply(24, "자바에서 수정 ");
		dao.modify(reply);
		
		System.out.println();
		//댓글조회
		dao.list(102).forEach(System.out::println);
		
		dao.remove(22);

		System.out.println();
		//댓글조회
		dao.list(102).forEach(System.out::println);
	}

	public void wrtie(Reply reply) {
		Connection conn = DBConn.getConnection();
		// 쿼리문 작성
		try {
			// 전처리 문장을 선언
			String sql = "INSERT INTO reply(REPLYNO, content, userid, regdate, boardno)\r\n"
					+ "VALUES(SEQ_REPLY.nextval, ?, ?, sysdate, ?)";

			// 전처리 문장 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, reply.getContent());
			pstmt.setString(index++, reply.getUserid());
			pstmt.setInt(index++, reply.getBoardno());

			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void modify(Reply reply) {
		Connection conn = DBConn.getConnection();
		// 쿼리문 작성
		try {
			// 전처리 문장을 선언
			String sql = "UPDATE reply SET \r\n" + "    CONTENT = ?,\r\n" + "    REGDATE = SYSDATE\r\n"
					+ "WHERE REPLYNO = ?";

			// 전처리 문장 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, reply.getContent());
			pstmt.setInt(index++, reply.getReplyno());

			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(int replyno) {
		Connection conn = DBConn.getConnection();
		// 쿼리문 작성
		try {
			// 전처리 문장을 선언
			String sql = "DELETE REPLY WHERE REPLYNO = ?";

			// 전처리 문장 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setInt(index++, replyno);

			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Reply> list(int boardno) {
		List<Reply> result = new ArrayList<Reply>();
		Connection conn = DBConn.getConnection();
		String sql = "select REPLYNO, content, userid, regdate, boardno, (select name from member m where m.userid = r.userid) username\r\n" + 
				"from reply r \r\n" + 
				"WHERE boardno = ? AND REPLYNO > 0";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
		
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				int idx =1;
				Reply reply = new Reply();
				reply.setReplyno(rs.getInt(idx++));
				reply.setContent(rs.getString(idx++));
				reply.setUserid(rs.getString(idx++));
				reply.setRegdate(rs.getString(idx++));
				reply.setBoardno(rs.getInt(idx++));
				reply.setUsername(rs.getString(idx++));
				result.add(reply);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Reply findBy(int replyno) {
		Reply result = null;
		Connection conn = DBConn.getConnection();
		String sql = "select REPLYNO, content, userid, regdate, boardno, (select name from member m where m.userid = r.userid) username\r\n" + 
				"from reply r \r\n" + 
				"WHERE REPLYNO = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyno);
		
			
			ResultSet rs = pstmt.executeQuery();
			result = new Reply();
			while(rs.next()) {
				int idx =1;
				result.setReplyno(rs.getInt(idx++));
				result.setContent(rs.getString(idx++));
				result.setUserid(rs.getString(idx++));
				result.setRegdate(rs.getString(idx++));
				result.setBoardno(rs.getInt(idx++));
				result.setUsername(rs.getString(idx++));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
