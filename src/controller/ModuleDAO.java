package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Customer;
import model.Manager;
import model.SellDetail;
import model.SellPeriod;
import model.Stock;

public class ModuleDAO {
	// ������� ��ü����
	public ArrayList<Stock> getStockViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Stock> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.stockViewTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.stockViewTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from stockTBL";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Stock>();
			while (rs.next()) {
				Stock stock = new Stock(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
						rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
						rs.getInt(18), rs.getString(19), rs.getInt(20));
				arrayList.add(stock);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList ���˿��!");
			alert.setHeaderText("StockViewTotalLoadList �����߻�!");
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
				System.out.println("RootController.stockViewTotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ������ ��ü����
	public ArrayList<Customer> getCustomerViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerViewTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getCustomerViewTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from customerTBL";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				arrayList.add(customer);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("CustomerViewTotalLoadList ���˿��!");
			alert.setHeaderText("CustomerViewTotalLoadList �����߻�!");
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
				System.out.println("RootController.customerViewTotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ���������� ��ü����
	public ArrayList<Manager> getManagerViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.managerViewTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.managerViewTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from managerTBL";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList ���˿��!");
			alert.setHeaderText("StockViewTotalLoadList �����߻�!");
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
				System.out.println("RootController.stockViewTotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ---------------------------------------�Ŵ������-------------------------------------------
	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getManagerDelete(int mid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerDelete : DB ���� ����");
			} else
				System.out.println("RootController.getManagerDelete : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "delete from managerTBL where mid = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			pstmt.setInt(1, mid); // 1�� �ǹ�:ù��° ?, ?�� �ΰ� 2���ϸ� �ι�° ?�� ����
			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ����");
				alert.setHeaderText(mid + "�� ���� ����");
				alert.setContentText(mid + "���� �ȳ���������.");
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
				System.out.println("RootController.getManagerDelete : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getManagerRegistry(Manager manager) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerRegistry : DB ���� ����");
			} else
				System.out.println("RootController.getManagerRegistry : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "insert into managerTBL values(null, ?, ?, ?)";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, manager.getMt_managerName());
			pstmt.setString(2, manager.getMt_managerId());
			pstmt.setString(3, manager.getMt_managerPassword());

			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��� ����");
				alert.setHeaderText(manager.getMid() + "�� ��� ����");
				alert.setContentText(manager.getMid() + " ��������");
				alert.showAndWait();
			} else {
				throw new Exception("��Ͽ� ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�Ŵ������ ���˿��");
			alert.setHeaderText("�Ŵ������ �����߻�!\n");
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
				System.out.println("RootController.handleBtnAddAction : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// �Ŵ����� ���̵� ��ġ, ����ġ
	public ArrayList<Manager> getManagerIDTotalLoadList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerIDTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getManagerIDTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from managerTBL where mt_managerId = ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList ���˿��!");
			alert.setHeaderText("StockViewTotalLoadList �����߻�!");
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
				System.out.println("RootController.stockViewTotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// �Ŵ����� ��й�ȣ ��ġ, ����ġ
	public ArrayList<Manager> getManagerPasswordTotalLoadList(String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerPasswordTotalLoadList : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getManagerPasswordTotalLoadList : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from managerTBL where mt_managerPassword = ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, password);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList ���˿��!");
			alert.setHeaderText("StockViewTotalLoadList �����߻�!");
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
				System.out.println("RootController.stockViewTotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// ---------------------------------------/�Ŵ������-------------------------------------------
	// ---------------------------------------�����Ȳ-------------------------------------------
	// ã��1��ư �̺�Ʈ �� �ڵ鷯�Լ�
	public ArrayList<Stock> getStockFind(String name) {
		// �����ͺ��̽��� �����մϴ�.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Stock> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockFind : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getStockFind : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from stockTBL where st_productNumber like ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Stock>();
			while (rs.next()) {
				Stock stock = new Stock(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
						rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
						rs.getInt(18), rs.getString(19), rs.getInt(20));
				arrayList.add(stock);
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻� ���˿��!");
			alert.setHeaderText("�˻� �����߻�!");
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
				System.out.println("RootController.handleBtnSearchAction : " + e2.getMessage());
			}
		}
		// ������ ���̽� ����======================================================
		return arrayList;
	}

	// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getStockRegistry(Stock stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockRegistry : DB ���� ����");
			} else
				System.out.println("RootController.getStockRegistry : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "insert into stockTBL values(Null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, stock.getSt_productNumber());
			pstmt.setString(2, stock.getSt_productName());
			pstmt.setString(3, stock.getSt_productColor());
			pstmt.setInt(4, stock.getSt_productStock());
			pstmt.setInt(5, stock.getSt_productSize());
			pstmt.setInt(6, stock.getSt_customerPrice());
			pstmt.setInt(7, stock.getSt_supplyPrice());
			pstmt.setInt(8, stock.getSt_supplyPriceTotal());
			pstmt.setInt(9, stock.getSt_sellPrice());
			pstmt.setInt(10, stock.getSt_sellPriceTotal());
			pstmt.setInt(11, stock.getSt_stock85());
			pstmt.setInt(12, stock.getSt_stock90());
			pstmt.setInt(13, stock.getSt_stock95());
			pstmt.setInt(14, stock.getSt_stock100());
			pstmt.setInt(15, stock.getSt_stock105());
			pstmt.setInt(16, stock.getSt_stock110());
			pstmt.setInt(17, stock.getSt_stock115());
			pstmt.setString(18, stock.getSt_image());
			pstmt.setInt(19, stock.getSt_salePercent());

			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��� ����");
				alert.setHeaderText(stock.getSt_productName() + "�� ��� ����");
				alert.setContentText(stock.getSt_productName() + " ��������");
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
				System.out.println("RootController.handelBtnAddAction : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getStockDelete(int productNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockDelete : DB ���� ����");
			} else
				System.out.println("RootController.getStockDelete : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "delete from stockTBL where st_productNumber = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			pstmt.setInt(1, productNumber); // 1�� �ǹ�:ù��° ?, ?�� �ΰ� 2���ϸ� �ι�° ?�� ����
			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ����");
				alert.setHeaderText(productNumber + "�� ���� ����");
				alert.setContentText(productNumber + "���� �ȳ���������.");
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
				System.out.println("RootController.handleBtnDelete1Action : " + e1.getMessage());
			}
		}
		return returnValue;
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
			String query = "update stockTBL set st_productColor=?, st_customerPrice=?, st_supplyPrice=?, "
					+ "st_supplyPriceTotal=?, st_sellPriceTotal=?, st_sellPrice=?, st_stock85=?, st_stock90=?, "
					+ "st_stock95=?,  st_stock100=?, st_stock105=?, st_stock110=?, st_stock115=?, st_image=? "
					+ "where st_productNumber = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�

			pstmt.setString(1, stock.getSt_productColor());
			pstmt.setInt(2, stock.getSt_customerPrice());
			pstmt.setInt(3, stock.getSt_supplyPrice());
			pstmt.setInt(4, stock.getSt_supplyPriceTotal());
			pstmt.setInt(5, stock.getSt_sellPrice());
			pstmt.setInt(6, stock.getSt_sellPriceTotal());
			pstmt.setInt(7, stock.getSt_stock85());
			pstmt.setInt(8, stock.getSt_stock90());
			pstmt.setInt(9, stock.getSt_stock95());
			pstmt.setInt(10, stock.getSt_stock100());
			pstmt.setInt(11, stock.getSt_stock105());
			pstmt.setInt(12, stock.getSt_stock110());
			pstmt.setInt(13, stock.getSt_stock115());
			pstmt.setString(14, stock.getSt_image());
			pstmt.setInt(15, stock.getSt_productNumber());
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

	// ---------------------------------------/�����Ȳ-------------------------------------------
	// ---------------------------------------������Ȳ-------------------------------------------
	// ã��2��ư �̺�Ʈ �� �ڵ鷯�Լ�
	public ArrayList<SellPeriod> getSellView1Find(String date1, String date2) {
		// �����ͺ��̽��� �����մϴ�.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellView1Find : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getSellView1Find : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL where se_sellPeriod between ? and ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), 
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
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
				System.out.println("RootController.handleBtnSearch2Action : " + e2.getMessage());
			}
		}
		// ������ ���̽� ����======================================================
		return arrayList;
	}

	// ����1 �����ϸ� ���� �̺�Ʈ
	public ArrayList<SellDetail> getSellView2Find(String name) {
		// �����ͺ��̽��� �����մϴ�.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellDetail> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellFind : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getSellFind : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "SELECT st.*, spt.*\r\n" + 
					"from stockTBL as st \r\n" + 
					"LEFT JOIN sellPeriodTBL as spt \r\n" + 
					"on st.st_productNumber = spt.se_productNumber where se_productName = ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
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
				System.out.println("RootController.handleBtnSearch2Action : " + e2.getMessage());
			}
		}
		// ������ ���̽� ����======================================================
		return arrayList;
	}

	// ---------------------------------------/������Ȳ-------------------------------------------
	// ---------------------------------------������-------------------------------------------
	// ã��3��ư �̺�Ʈ �� �ڵ鷯�Լ�
	public ArrayList<Customer> getCustomerFind(String name) {
		// �����ͺ��̽��� �����մϴ�.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerFind : DB ���Ἲ��!");
			} else {
				System.out.println("RootController.getCustomerFind : DB �������!");
			}
			// 3. con ��ü�� ������ �������� �����Ҽ��ִ�. (select, insert, update, delete)
			String query = "select * from customerTBL where ct_customerName like ?";
			// 4. �������� �����ϱ����� �غ�
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. �������� �����Ѵ�. (������� ���ڵ峻���� �迭�� �����´�.)
			rs = pstmt.executeQuery();
			// 6. ResultSet ���� �Ѱ��� �����ͼ� ArrayList�� �����Ѵ�.
			arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				arrayList.add(customer);
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻�3 ���˿��!");
			alert.setHeaderText("�˻�3 �����߻�!");
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
				System.out.println("RootController.handleBtnSearch3Action : " + e2.getMessage());
			}
		}
		// ������ ���̽� ����======================================================
		return arrayList;
	}

	// ����2��ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getCustomerDelete(int cid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerDelete : DB ���� ����");
			} else
				System.out.println("RootController.getCustomerDelete : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "delete from customerTBL where cid = ?";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			pstmt.setInt(1, cid); // 1�� �ǹ�:ù��° ?, ?�� �ΰ� 2���ϸ� �ι�° ?�� ����
			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ����");
				alert.setHeaderText(cid + "�� ���� ����");
				alert.setContentText(cid + "���� �ȳ���������.");
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
				System.out.println("RootController.handleBtnDelete2Action : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// ��������Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getCustomerRegistry(Customer customer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerRegistry : DB ���� ����");
			} else
				System.out.println("RootController.getCustomerRegistry : DB ���� ����");

			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "insert into customerTBL values(?, ?, ?, ?, ?, ?)";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, customer.getCid());
			pstmt.setString(2, customer.getCt_customerName());
			pstmt.setString(3, customer.getCt_gender());
			pstmt.setString(4, customer.getCt_birthday());
			pstmt.setString(5, customer.getCt_phoneNumber());
			pstmt.setInt(6, customer.getCt_userPoint());

			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��� ����");
				alert.setHeaderText(customer.getCid() + "�� ��� ����");
				alert.setContentText(customer.getCid() + " ��������");
				alert.showAndWait();
			} else {
				throw new Exception("��Ͽ� ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����� ���˿��");
			alert.setHeaderText("����� �����߻�!\n");
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
				System.out.println("RootController.handleBtnCustomerAddAction : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// ������������ư �̺�Ʈ �� �ڵ鷯�Լ�
	public int getCustomerUpdate(Customer customer) {
		// �����ͺ��̽� �۾��� �����Ѵ�.
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerUpdate : DB ���� ����");
			} else
				System.out.println("RootController.getCustomerUpdate : DB ���� ����");
			// 3. con ��ü�� ������ ������ �����Ҽ��ִ�(select,insert,update,delete)
			String query = "update customerTBL set cid=?, ct_customerName=?, ct_gender=?, "
					+ "ct_birthday=?, ct_phoneNumber=?, ct_userPoint=? " + "where cid = ? and ct_userPoint > 0";
			// 4. ������ �����ϱ� ���� �غ�
			pstmt = con.prepareStatement(query); // �ܿ���
			// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�

			pstmt.setInt(1, customer.getCid());
			pstmt.setString(2, customer.getCt_customerName());
			pstmt.setString(3, customer.getCt_gender());
			pstmt.setString(4, customer.getCt_birthday());
			pstmt.setString(5, customer.getCt_phoneNumber());
			pstmt.setInt(6, customer.getCt_userPoint());
			pstmt.setInt(7, customer.getCid());

			// 5. �������� �����Ѵ�
			// �������� �����ؼ� ���ڵ峻���� ������� �迭�� �����´� : executeQuery();
			// �Ǵ� ���� ���ุ �Ѵ� : executeUqdate();
			returnValue = pstmt.executeUpdate();// �ƹ��͵����ϸ� 0 ����, ������ ī��Ʈ��ŭ int�� ��ȯ
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText(customer.getCid() + "�� ���� ����");
				alert.setContentText(customer.getCt_customerName() + "�� �����߽��ϴ�.");
				alert.showAndWait();
			} else {
				throw new Exception("������ ��������");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� ���˿�� ���˿��");
			alert.setHeaderText("���� �����߻�!\n RootController.handleBtnCustomerEditAction");
			alert.setContentText("RootController.handleBtnCustomerEditAction : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt �ݳ�
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("RootController.handleBtnCustomerEditAction : " + e2.getMessage());
			}
		}
		return returnValue;
	}

	// ---------------------------------------/������-------------------------------------------
}
