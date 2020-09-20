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
				System.out.println("RootControoler.getTotalLoadList :DB ���Ἲ��");
			} else {
				System.out.println("RootControoler.getTotalLoadList :DB �������");
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
			alert.setTitle("TotalList ���˿��");
			alert.setHeaderText("ToatalList �����߻�!");
			alert.setContentText("��������: " + e.getMessage());
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

	// DB�����ϰ� ���ִ°�
	public int getNoticeRegistry(Notices notices) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.BtnDeleteAction :DB ���Ἲ��");
			} else {
				System.out.println("RootControoler.BtnDeleteAction :DB �������");
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
				alert.setTitle("��� ����");
				alert.setHeaderText(notices.getNo() + "�� ��� ����");
				alert.setContentText(notices.getUserID() + "�� ��ϿϷ�.");
				alert.showAndWait();
			} else {
				throw new Exception("������ ��������");
			}

		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���˿��");
			alert.setHeaderText("���� �����߻�! \n RootController.BtnEditAction");
			alert.setContentText("��������: " + e1.getMessage());
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

	// �����ϱ�
	public int getNoticeDelete(int noti) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.BtnDeleteAction :DB ���Ἲ��");
			} else {
				System.out.println("RootControoler.BtnDeleteAction :DB �������");
			}

			String query = "delete from noticeTBL where ntid = ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noti);

			returnValue = pstmt.executeUpdate();

			if (returnValue != 0) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText(noti + "�� ���� ����");
				alert.setContentText(noti + "�� �� �ȳ�");
				alert.showAndWait();
			} else {
				throw new Exception("������ ��������");
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���˿��");
			alert.setHeaderText("���� �����߻�! \n RootController.BtnDeleteAction");
			alert.setContentText("��������: " + e.getMessage());
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

	// UserIDã��
	public ArrayList<Notices> getNoticeFindUserID(String userID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notices> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.totalLoadList :DB ���Ἲ��");
			} else {
				System.out.println("RootControoler.totalLoadList :DB �������");
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
			alert.setTitle("�˻� ���˿��");
			alert.setHeaderText("�˻� �����߻�!");
			alert.setContentText("��������: " + e1.getMessage());
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

	//Titleã��
	public ArrayList<Notices> getNoticeFindTitle(String title) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notices> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootControoler.totalLoadList :DB ���Ἲ��");
			} else {
				System.out.println("RootControoler.totalLoadList :DB �������");
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
			alert.setTitle("�˻� ���˿��");
			alert.setHeaderText("�˻� �����߻�!");
			alert.setContentText("��������: " + e1.getMessage());
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
