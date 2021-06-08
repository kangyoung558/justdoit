package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.vo.Attach;
import board.vo.Board;
import util.DBConn;

public class BoardDao {
	
	public void write(Board board) {
		Connection conn = DBConn.getConnection();
		//쿼리문 작성
		try {
			conn.setAutoCommit(false);
			//전처리 문장을 선언 
			String sql = "INSERT INTO BOARD (BOARDNO, TITLE, CONTENT, USERID, PARENTNO, category) VALUES (SEQ_BOARD.NEXTVAL, ?, ?, ?, ?, ?)";
			
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getContent());
			pstmt.setString(index++, board.getUserid());
			pstmt.setObject(index++, board.getParentno());
			pstmt.setInt(index++, board.getCategory());
			
			//실행
			pstmt.executeUpdate();
			
			List<Attach> attachs = board.getAttachs();
			if(attachs.size()==0) {
				conn.commit();
				return;
			}
			
			//마지막 작성글의 글번호 조회
			sql = "select max(boardno) from board";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int boardno = rs.getInt(1);
			
			//첨부파일 테이블에 attachs 만큼 insert
			sql="INSERT INTO attach(uuid, uploadpath, filename, filetype, fileorder, boardno)\r\n" + 
					"VALUES(?, ?, ?, ?, ?, ? )";
			for(Attach attach : attachs) {
				pstmt= conn.prepareStatement(sql);
				index=1;
				pstmt.setString(index++, attach.getUuid());
				pstmt.setString(index++, attach.getUploadPath());
				pstmt.setString(index++, attach.getFileName());
				pstmt.setBoolean(index++, attach.isImage());
				pstmt.setInt(index++, attach.getFileOrder());
				pstmt.setInt(index++, boardno);
				
				//실행
				pstmt.executeUpdate();
			}
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<Board> list(Integer category) {
		List<Board> result = null;
		Connection conn = DBConn.getConnection();
		String sql = "SELECT boardno, title, content, userid, CASE\r\n" + 
				"    WHEN SYSDATE - REGDATE < 1 THEN TO_CHAR(REGDATE, 'HH24:MI:SS') \r\n" + 
				"    ELSE TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
				"END REGDATE, hitcount, parentno, category, (select name from member m where m.userid = b.userid) username \r\n" + 
				"FROM board b\r\n" + 
				"WHERE category = ? AND BOARDNO > 0\r\n" + 
				"order by 1 DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category);
		
			
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Board>();
			while(rs.next()) {
				int idx =1;
				Board board = new Board();
				board.setBoardno(rs.getInt(idx++));
				board.setTitle(rs.getString(idx++));
				board.setContent(rs.getString(idx++));
				board.setUserid(rs.getString(idx++));
				board.setRegdate(rs.getString(idx++));
				board.setHitcount(rs.getInt(idx++));
				board.setParentno(rs.getInt(idx++));
				board.setCategory(rs.getInt(idx++));
				board.setUsername(rs.getString(idx++));
				result.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public Board findBy(Integer boardno) {
		Board result = null;
		Connection conn = DBConn.getConnection();
		String sql = "SELECT boardno, title, content, userid, regdate, hitcount, parentno, category, (select name from member m where m.userid = b.userid) username \r\n" + 
				"FROM board b \r\n" + 
				"WHERE boardno =? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
		
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int idx =1;
				
				result = new Board();
				result.setBoardno(rs.getInt(idx++));
				result.setTitle(rs.getString(idx++));
				result.setContent(rs.getString(idx++));
				result.setUserid(rs.getString(idx++));
				result.setRegdate(rs.getString(idx++));
				result.setHitcount(rs.getInt(idx++));
				result.setParentno(rs.getInt(idx++));
				result.setCategory(rs.getInt(idx++));
				result.setUsername(rs.getString(idx++));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public void modify(Board board) {
		Connection conn = DBConn.getConnection();
		//쿼리문 작성
		try {
			//전처리 문장을 선언 
			String sql = "UPDATE board SET \r\n" + 
					"TITLE = ?,\r\n" + 
					"CONTENT = ?,\r\n" + 
					"REGDATE = SYSDATE\r\n" + 
					"WHERE BOARDNO = ?";
			
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getContent());
			pstmt.setInt(index++, board.getBoardno());
			
			//실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	
	public int remove(Integer boardno) {
		Connection conn = DBConn.getConnection();
		int ret= 0;
		//쿼리문 작성
		try {
			conn.setAutoCommit(false);
			//첨부파일 테이블에서의 삭제
			String sql = "DELETE attach WHERE BOARDNO = ?";
			//전처리 문장 생성 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//전처리 문장 파라미터 바인딩
			int index = 1;
			pstmt.setInt(index++, boardno);
			//실행
			pstmt.executeUpdate();
			pstmt.close();
			
			//게시판에서의 삭제
			//전처리 문장을 선언 
			sql = "DELETE BOARD WHERE BOARDNO = ?";
			
			//전처리 문장 생성 
			pstmt = conn.prepareStatement(sql);
			
			//전처리 문장 파라미터 바인딩
			index = 1;
			pstmt.setInt(index++, boardno);
			
			//실행
			ret= pstmt.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return ret;
	}
	
	//게시글 번호를 이용한 첨부파일 목록 가져오기 
	public List<Attach> getAttachs(Integer boardno) {
		List<Attach> result = null;
		Connection conn = DBConn.getConnection();
		String sql = "SELECT uuid, uploadpath, FILENAME, FILETYPE, FILEORDER, BOARDNO\r\n" + 
				"FROM attach\r\n" + 
				"WHERE boardno = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			
			
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Attach>();
			while(rs.next()) {
				int idx =1;
				Attach attach = new Attach();
				attach.setUuid(rs.getString(idx++));
				attach.setUploadPath(rs.getString(idx++));
				attach.setFileName(rs.getString(idx++));
				attach.setImage(rs.getBoolean(idx++));
				attach.setFileOrder(rs.getInt(idx++));
				attach.setBoardno(rs.getInt(idx++));
				result.add(attach);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		Board board = new Board("자바에서씀44", "자바에서 내용 씀", "hong",1,1);
		dao.write(board);
		
//		System.out.println("=====목록 조회=====");
//		dao.list(1).forEach(System.out::println);
//		
//		System.out.println("=====목록 조회=====");
//		System.out.println(dao.findBy(1));
//		System.out.println(dao.findBy(2));
//		
//		dao.modify(new Board(3, "자바에서 수정당함", "수정내용내용"));
//		System.out.println(dao.findBy(3));
//		
//		System.out.println("======삭제 후 반영 행의 갯수 확인=====");
//		System.out.println(dao.remove(3));
//		System.out.println(dao.remove(21));
//		
//		System.out.println("=====목록 조회=====");
//		dao.list(1).forEach(System.out::println);
	}

}
