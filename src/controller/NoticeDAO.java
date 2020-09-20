package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Notices;

public class NoticeDAO {
	public ArrayList<Notices> getTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notices> arrayList = null;

		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.getTotalLoadList :DB 연결성공");
			} else {
				System.out.println("RootControoler.getTotalLoadList :DB 연결실패");
			}
			String query = "select * from noticeTBL";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			arrayList = new ArrayList<Notices>();

			while (rs.next()) {
				Notices notices = new Notices(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				arrayList.add(notices);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("TotalList 점검요망");
			alert.setHeaderText("ToatalList 문제발생!");
			alert.setContentText("문제사항: " + e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("RootController.totalLoadList: " + e.getMessage());
			}
		}
		return arrayList;
	}

	// DB간섭하게 해주는것
	public int getNoticeRegistry(Notices notices) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.BtnDeleteAction :DB 연결성공");
			} else {
				System.out.println("RootControoler.BtnDeleteAction :DB 연결실패");
			}

			String query = "insert into noticeTBL values(Null, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, notices.getUserID());
			pstmt.setString(2, notices.getDate());
			pstmt.setString(3, notices.getTitle());
			pstmt.setString(4, notices.getContents());

			returnValue = pstmt.executeUpdate();

			if (returnValue != 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("등록 성공");
				alert.setHeaderText(notices.getNo() + "번 등록 성공");
				alert.setContentText(notices.getUserID() + "님 등록완료.");
				alert.showAndWait();
			} else {
				throw new Exception("수정에 문제있음");
			}

		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("수정 점검요망");
			alert.setHeaderText("수정 문제발생! \n RootController.BtnEditAction");
			alert.setContentText("문제사항: " + e1.getMessage());
			alert.showAndWait();
			System.out.println(e1.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("RootController.BtnEditAction: " + e2.getMessage());
			}

		}
		return returnValue;

	}

	// 삭제하기
	public int getNoticeDelete(int noti) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.BtnDeleteAction :DB 연결성공");
			} else {
				System.out.println("RootControoler.BtnDeleteAction :DB 연결실패");
			}

			String query = "delete from noticeTBL where ntid = ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noti);

			returnValue = pstmt.executeUpdate();

			if (returnValue != 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("삭제 성공");
				alert.setHeaderText(noti + "번 삭제 성공");
				alert.setContentText(noti + "번 님 안녕");
				alert.showAndWait();
			} else {
				throw new Exception("삭제에 문제있음");
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("삭제 점검요망");
			alert.setHeaderText("삭제 문제발생! \n RootController.BtnDeleteAction");
			alert.setContentText("문제사항: " + e.getMessage());
			alert.showAndWait();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("RootController.BtnDeleteAction: " + e.getMessage());
			}
		}

		return returnValue;
	}

	// UserID찾기
	public ArrayList<Notices> getNoticeFindUserID(String userID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notices> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.totalLoadList :DB 연결성공");
			} else {
				System.out.println("RootControoler.totalLoadList :DB 연결실패");
			}
			String query = "select * from noticeTBL where nt_userID like ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + userID + "%");

			rs = pstmt.executeQuery();

			arrayList = new ArrayList<Notices>();
			while (rs.next()) {
				Notices noti = new Notices(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				arrayList.add(noti);
			}

		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("검색 점검요망");
			alert.setHeaderText("검색 문제발생!");
			alert.setContentText("문제사항: " + e1.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("RootController.BtnSearchAction: " + e2.getMessage());
			}
		}

		return arrayList;
	}

	//Title찾기
	public ArrayList<Notices> getNoticeFindTitle(String title) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notices> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.totalLoadList :DB 연결성공");
			} else {
				System.out.println("RootControoler.totalLoadList :DB 연결실패");
			}
			String query = "select * from noticeTBL where nt_title like ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + title + "%");

			rs = pstmt.executeQuery();

			arrayList = new ArrayList<Notices>();
			while (rs.next()) {
				Notices noti = new Notices(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				arrayList.add(noti);
			}

		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("검색 점검요망");
			alert.setHeaderText("검색 문제발생!");
			alert.setContentText("문제사항: " + e1.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("RootController.BtnSearchAction: " + e2.getMessage());
			}
		}

		return arrayList;
	}

	
}
