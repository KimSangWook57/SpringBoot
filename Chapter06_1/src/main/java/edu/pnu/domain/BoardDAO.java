package edu.pnu.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	Connection con;

	public BoardDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Board> getBoardList() {

		List<Board> boardList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String query = "select * from board";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Board board = new Board();
				Long seq = rs.getLong(1);
				Long cnt = rs.getLong(2);
				String content = rs.getString(3);
				Date createDate = rs.getDate(4);
				String title = rs.getString(5);
				String member_id = rs.getString(6);

				board.setSeq(seq);
				board.setCnt(cnt);
				board.setContent(content);
				board.setCreateDate(createDate);
				board.setTitle(title);
				board.setMember_id(member_id);

				boardList.add(board);
			}
			return boardList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public Board getBoard(Long seq) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Board board = new Board();

		try {
			String query = "select * from board where seq = ?";
			psmt = con.prepareStatement(query);
			psmt.setLong(1, seq);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Long idx = rs.getLong(1);
				Long cnt = rs.getLong(2);
				String content = rs.getString(3);
				Date createDate = rs.getDate(4);
				String title = rs.getString(5);
				String member_id = rs.getString(6);

				board.setSeq(idx);
				board.setCnt(cnt);
				board.setContent(content);
				board.setCreateDate(createDate);
				board.setTitle(title);
				board.setMember_id(member_id);

			}
			return board;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}
}
