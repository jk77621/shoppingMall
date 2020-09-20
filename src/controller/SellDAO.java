package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.SellDetail;
import model.SellPeriod;
import model.Stock;

public class SellDAO {
	// �ǸűⰣ ��ü����
	public ArrayList<SellPeriod> getTblList2TotalList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getTblList2TotalList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getTblList2TotalList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14));
				arrayList.add(sellPeriod);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("tblList2TotalLoadList ���˿��!");
			alert.setHeaderText("tblList2TotalLoadList �����߻�!");
			alert.setContentText("�������� : " + e.getMessage());
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
				System.out.println("RootController.getTblList2TotalList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ���, �Ǹ����� ��ü����
	public ArrayList<SellDetail> getStockSellTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellDetail> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockSellTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getStockSellTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "SELECT st.*,spt.* from stockTBL as st Inner JOIN sellPeriodTBL as spt on st.st_productNumber = spt.se_productNumber";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<SellDetail>();
			while (rs.next()) {
				SellDetail sellDetail = new SellDetail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), 
						rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18), rs.getString(19), 
						rs.getInt(20), rs.getInt(21), rs.getInt(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getInt(26), 
						rs.getInt(27), rs.getInt(28), rs.getInt(29), rs.getString(30), rs.getString(31), rs.getString(32), 
						rs.getString(33), rs.getInt(34));
				arrayList.add(sellDetail);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("sellView2TotalLoadList ���˿��!");
			alert.setHeaderText("sellView2TotalLoadList �����߻�!");
			alert.setContentText("�������� : " + e.getMessage());
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
				System.out.println("RootController.sellView2TotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getSellRegistry(SellPeriod sellPeriod) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellRegistry : DB ���� ����");
			} else
				System.out.println("RootController.getSellRegistry : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "insert into sellPeriodTBL values(Null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, sellPeriod.getSe_productNumber());
			pstmt.setString(2, sellPeriod.getSe_sellPeriod());
			pstmt.setString(3, sellPeriod.getSe_productName());
			pstmt.setString(4, sellPeriod.getSe_productColor());
			pstmt.setInt(5, sellPeriod.getSe_productStock());
			pstmt.setInt(6, sellPeriod.getSe_productSize());
			pstmt.setInt(7, sellPeriod.getSe_sellPrice());
			pstmt.setInt(8, sellPeriod.getSe_cid());
			pstmt.setString(9, sellPeriod.getSe_customerName());
			pstmt.setString(10, sellPeriod.getSe_gender());
			pstmt.setString(11, sellPeriod.getSe_birthday());
			pstmt.setString(12, sellPeriod.getSe_phoneNumber());
			pstmt.setInt(13, sellPeriod.getSe_userPoint());

			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��� ����");
				alert.setHeaderText(sellPeriod.getSe_customerName() + " ��� ����");
				alert.setContentText(sellPeriod.getSe_customerName() + " ��������");
				alert.showAndWait();
			} else {
				throw new Exception("��Ͽ� ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� ���˿��");
			alert.setHeaderText("��Ϲ����߻�!\n");
			alert.setContentText("�������� : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt �ݳ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				System.out.println("RootController.handleBtnInsertAction : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getSellPeriodDelete(int productNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellPeriodDelete : DB ���� ����");
			} else
				System.out.println("RootController.getSellPeriodDelete : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "delete from sellPeriodTBL where se_productNumber = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			pstmt.setInt(1, productNumber); // 1�� �ǹ�:ù��° ?, ?�� �ΰ� 2���ϸ� �ι�° ?�� ����
			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ����!");
				alert.setHeaderText(productNumber + " ���� ����");
				alert.setContentText(productNumber + " ���� �Ϸ�");
				alert.showAndWait();
			} else {
				throw new Exception("������ ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���˿�� ���˿��");
			alert.setHeaderText("���� �����߻�!\n");
			alert.setContentText("�������� : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt �ݳ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				System.out.println("RootController.getSellPeriodDelete : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// CountView ã���ư �̺�Ʈ �� �ڵ鷯�Լ�
	public ArrayList<SellPeriod> getCountViewFind(String name) {
		// �����ͺ��̽��� �����մϴ�.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCountViewFind : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getCountViewFind : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL where se_customerName like ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14));
				arrayList.add(sellPeriod);
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻�2 ���˿��!");
			alert.setHeaderText("�˻�2 �����߻�!");
			alert.setContentText("�������� : " + e1.getMessage());
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
				System.out.println("RootController.getCountViewFind : " + e2.getMessage());
			}
		}
		// ������ ���̽� ����======================================================
		return arrayList;
	}

	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getStockUpdate(Stock stock) {
		// �����ͺ��̽� �۾��� �����Ѵ�.
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockUpdate : DB ���� ����");
			} else
				System.out.println("RootController.getStockUpdate : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "update stockTBL set st_productStock = ? , st_stock85 = ?, st_stock90 = ?, st_stock95 = ?, st_stock100 = ?, \r\n" + 
					"st_stock105 = ?, st_stock110 = ?, st_stock115 = ? where st_productNumber = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�

			pstmt.setInt(1, stock.getSt_productStock());
			pstmt.setInt(2, stock.getSt_stock85());
			pstmt.setInt(3, stock.getSt_stock90());
			pstmt.setInt(4, stock.getSt_stock95());
			pstmt.setInt(5, stock.getSt_stock100());
			pstmt.setInt(6, stock.getSt_stock105());
			pstmt.setInt(7, stock.getSt_stock110());
			pstmt.setInt(8, stock.getSt_stock115());
			pstmt.setInt(9, stock.getSt_productNumber());
			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText(stock.getSt_productNumber() + "�� ���� ����");
				alert.setContentText(stock.getSt_productName() + "�� �����߽��ϴ�.");
				alert.showAndWait();
			} else {
				throw new Exception("������ ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���˿�� ���˿��");
			alert.setHeaderText("���� �����߻�!\n RootController.handleBtnEditAction");
			alert.setContentText("RootController.handleBtnEditAction : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt �ݳ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("RootController.handleBtnEditAction : " + e2.getMessage());
			}
		}
		return returnValue;
	}
}
