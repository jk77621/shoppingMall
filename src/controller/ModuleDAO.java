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
	// 재고정보 전체보기
	public ArrayList<Stock> getStockViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Stock> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.stockViewTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.stockViewTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from stockTBL";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
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
			alert.setTitle("StockViewTotalLoadList 점검요망!");
			alert.setHeaderText("StockViewTotalLoadList 문제발생!");
			alert.setContentText("문제사항 : " + e.getMessage());
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

	// 고객정보 전체보기
	public ArrayList<Customer> getCustomerViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerViewTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.getCustomerViewTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from customerTBL";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				arrayList.add(customer);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("CustomerViewTotalLoadList 점검요망!");
			alert.setHeaderText("CustomerViewTotalLoadList 문제발생!");
			alert.setContentText("문제사항 : " + e.getMessage());
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

	// 관리자정보 전체보기
	public ArrayList<Manager> getManagerViewTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.managerViewTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.managerViewTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from managerTBL";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList 점검요망!");
			alert.setHeaderText("StockViewTotalLoadList 문제발생!");
			alert.setContentText("문제사항 : " + e.getMessage());
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

	// ---------------------------------------매니저등록-------------------------------------------
	// 삭제버튼 이벤트 및 핸들러함수
	public int getManagerDelete(int mid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerDelete : DB 연결 성공");
			} else
				System.out.println("RootController.getManagerDelete : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "delete from managerTBL where mid = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			pstmt.setInt(1, mid); // 1의 의미:첫번째 ?, ?가 두개 2로하면 두번째 ?에 들어간다
			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("삭제 성공");
				alert.setHeaderText(mid + "번 삭제 성공");
				alert.setContentText(mid + "번님 안녕히가세요.");
				alert.showAndWait();
			} else {
				throw new Exception("삭제에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("삭제 점검요망 점검요망");
			alert.setHeaderText("삭제 문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 등록버튼 이벤트 및 핸들러함수
	public int getManagerRegistry(Manager manager) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerRegistry : DB 연결 성공");
			} else
				System.out.println("RootController.getManagerRegistry : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "insert into managerTBL values(null, ?, ?, ?)";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, manager.getMt_managerName());
			pstmt.setString(2, manager.getMt_managerId());
			pstmt.setString(3, manager.getMt_managerPassword());

			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("등록 성공");
				alert.setHeaderText(manager.getMid() + "번 등록 성공");
				alert.setContentText(manager.getMid() + " 하이하이");
				alert.showAndWait();
			} else {
				throw new Exception("등록에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("매니저등록 점검요망");
			alert.setHeaderText("매니저등록 문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 매니저뷰 아이디 일치, 불일치
	public ArrayList<Manager> getManagerIDTotalLoadList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerIDTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.getManagerIDTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from managerTBL where mt_managerId = ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList 점검요망!");
			alert.setHeaderText("StockViewTotalLoadList 문제발생!");
			alert.setContentText("문제사항 : " + e.getMessage());
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

	// 매니저뷰 비밀번호 일치, 불일치
	public ArrayList<Manager> getManagerPasswordTotalLoadList(String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Manager> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getManagerPasswordTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.getManagerPasswordTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from managerTBL where mt_managerPassword = ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, password);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				arrayList.add(manager);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("StockViewTotalLoadList 점검요망!");
			alert.setHeaderText("StockViewTotalLoadList 문제발생!");
			alert.setContentText("문제사항 : " + e.getMessage());
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

	// ---------------------------------------/매니저등록-------------------------------------------
	// ---------------------------------------재고현황-------------------------------------------
	// 찾기1버튼 이벤트 및 핸들러함수
	public ArrayList<Stock> getStockFind(String name) {
		// 데이터베이스를 실행합니다.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Stock> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockFind : DB 연결성공!");
			} else {
				System.out.println("RootController.getStockFind : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from stockTBL where st_productNumber like ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
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
			alert.setTitle("검색 점검요망!");
			alert.setHeaderText("검색 문제발생!");
			alert.setContentText("문제사항 : " + e1.getMessage());
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
		// 데이터 베이스 종점======================================================
		return arrayList;
	}

	// 등록버튼 이벤트 및 핸들러함수
	public int getStockRegistry(Stock stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockRegistry : DB 연결 성공");
			} else
				System.out.println("RootController.getStockRegistry : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "insert into stockTBL values(Null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 4. 쿼리문 실행하기 위한 준비
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

			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("등록 성공");
				alert.setHeaderText(stock.getSt_productName() + "번 등록 성공");
				alert.setContentText(stock.getSt_productName() + " 하이하이");
				alert.showAndWait();
			} else {
				throw new Exception("등록에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("등록 점검요망");
			alert.setHeaderText("등록문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 삭제버튼 이벤트 및 핸들러함수
	public int getStockDelete(int productNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockDelete : DB 연결 성공");
			} else
				System.out.println("RootController.getStockDelete : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "delete from stockTBL where st_productNumber = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			pstmt.setInt(1, productNumber); // 1의 의미:첫번째 ?, ?가 두개 2로하면 두번째 ?에 들어간다
			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("삭제 성공");
				alert.setHeaderText(productNumber + "번 삭제 성공");
				alert.setContentText(productNumber + "번님 안녕히가세요.");
				alert.showAndWait();
			} else {
				throw new Exception("삭제에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("삭제 점검요망 점검요망");
			alert.setHeaderText("삭제 문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 수정버튼 이벤트 및 핸들러함수
	public int getStockUpdate(Stock stock) {
		// 데이터베이스 작업을 진행한다.
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockUpdate : DB 연결 성공");
			} else
				System.out.println("RootController.getStockUpdate : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "update stockTBL set st_productColor=?, st_customerPrice=?, st_supplyPrice=?, "
					+ "st_supplyPriceTotal=?, st_sellPriceTotal=?, st_sellPrice=?, st_stock85=?, st_stock90=?, "
					+ "st_stock95=?,  st_stock100=?, st_stock105=?, st_stock110=?, st_stock115=?, st_image=? "
					+ "where st_productNumber = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다

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
			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("수정 성공");
				alert.setHeaderText(stock.getSt_productNumber() + "번 수정 성공");
				alert.setContentText(stock.getSt_productName() + "님 수정했습니다.");
				alert.showAndWait();
			} else {
				throw new Exception("수정에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("수정 점검요망 점검요망");
			alert.setHeaderText("수정 문제발생!\n RootController.handleBtnEditAction");
			alert.setContentText("RootController.handleBtnEditAction : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// ---------------------------------------/재고현황-------------------------------------------
	// ---------------------------------------매출현황-------------------------------------------
	// 찾기2버튼 이벤트 및 핸들러함수
	public ArrayList<SellPeriod> getSellView1Find(String date1, String date2) {
		// 데이터베이스를 실행합니다.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellView1Find : DB 연결성공!");
			} else {
				System.out.println("RootController.getSellView1Find : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL where se_sellPeriod between ? and ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), 
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
				arrayList.add(sellPeriod);
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("검색2 점검요망!");
			alert.setHeaderText("검색2 문제발생!");
			alert.setContentText("문제사항 : " + e1.getMessage());
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
		// 데이터 베이스 종점======================================================
		return arrayList;
	}

	// 셀뷰1 선택하면 변경 이벤트
	public ArrayList<SellDetail> getSellView2Find(String name) {
		// 데이터베이스를 실행합니다.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellDetail> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellFind : DB 연결성공!");
			} else {
				System.out.println("RootController.getSellFind : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "SELECT st.*, spt.*\r\n" + 
					"from stockTBL as st \r\n" + 
					"LEFT JOIN sellPeriodTBL as spt \r\n" + 
					"on st.st_productNumber = spt.se_productNumber where se_productName = ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
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
			alert.setTitle("검색2 점검요망!");
			alert.setHeaderText("검색2 문제발생!");
			alert.setContentText("문제사항 : " + e1.getMessage());
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
		// 데이터 베이스 종점======================================================
		return arrayList;
	}

	// ---------------------------------------/매출현황-------------------------------------------
	// ---------------------------------------고객관리-------------------------------------------
	// 찾기3버튼 이벤트 및 핸들러함수
	public ArrayList<Customer> getCustomerFind(String name) {
		// 데이터베이스를 실행합니다.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerFind : DB 연결성공!");
			} else {
				System.out.println("RootController.getCustomerFind : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from customerTBL where ct_customerName like ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
				arrayList.add(customer);
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("검색3 점검요망!");
			alert.setHeaderText("검색3 문제발생!");
			alert.setContentText("문제사항 : " + e1.getMessage());
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
		// 데이터 베이스 종점======================================================
		return arrayList;
	}

	// 삭제2버튼 이벤트 및 핸들러함수
	public int getCustomerDelete(int cid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerDelete : DB 연결 성공");
			} else
				System.out.println("RootController.getCustomerDelete : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "delete from customerTBL where cid = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			pstmt.setInt(1, cid); // 1의 의미:첫번째 ?, ?가 두개 2로하면 두번째 ?에 들어간다
			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("삭제 성공");
				alert.setHeaderText(cid + "번 삭제 성공");
				alert.setContentText(cid + "번님 안녕히가세요.");
				alert.showAndWait();
			} else {
				throw new Exception("삭제에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("삭제 점검요망 점검요망");
			alert.setHeaderText("삭제 문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 고객정보등록버튼 이벤트 및 핸들러함수
	public int getCustomerRegistry(Customer customer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerRegistry : DB 연결 성공");
			} else
				System.out.println("RootController.getCustomerRegistry : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "insert into customerTBL values(?, ?, ?, ?, ?, ?)";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, customer.getCid());
			pstmt.setString(2, customer.getCt_customerName());
			pstmt.setString(3, customer.getCt_gender());
			pstmt.setString(4, customer.getCt_birthday());
			pstmt.setString(5, customer.getCt_phoneNumber());
			pstmt.setInt(6, customer.getCt_userPoint());

			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("등록 성공");
				alert.setHeaderText(customer.getCid() + "번 등록 성공");
				alert.setContentText(customer.getCid() + " 하이하이");
				alert.showAndWait();
			} else {
				throw new Exception("등록에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객등록 점검요망");
			alert.setHeaderText("고객등록 문제발생!\n");
			alert.setContentText("문제사항 : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// 고객정보수정버튼 이벤트 및 핸들러함수
	public int getCustomerUpdate(Customer customer) {
		// 데이터베이스 작업을 진행한다.
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCustomerUpdate : DB 연결 성공");
			} else
				System.out.println("RootController.getCustomerUpdate : DB 연결 실패");
			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "update customerTBL set cid=?, ct_customerName=?, ct_gender=?, "
					+ "ct_birthday=?, ct_phoneNumber=?, ct_userPoint=? " + "where cid = ? and ct_userPoint > 0";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다

			pstmt.setInt(1, customer.getCid());
			pstmt.setString(2, customer.getCt_customerName());
			pstmt.setString(3, customer.getCt_gender());
			pstmt.setString(4, customer.getCt_birthday());
			pstmt.setString(5, customer.getCt_phoneNumber());
			pstmt.setInt(6, customer.getCt_userPoint());
			pstmt.setInt(7, customer.getCid());

			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("수정 성공");
				alert.setHeaderText(customer.getCid() + "번 수정 성공");
				alert.setContentText(customer.getCt_customerName() + "님 수정했습니다.");
				alert.showAndWait();
			} else {
				throw new Exception("수정에 문제있음");
			}
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("수정 점검요망 점검요망");
			alert.setHeaderText("수정 문제발생!\n RootController.handleBtnCustomerEditAction");
			alert.setContentText("RootController.handleBtnCustomerEditAction : " + e1.getMessage());
			alert.showAndWait();
		} finally {
			// con,pstmt 반납
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

	// ---------------------------------------/고객관리-------------------------------------------
}
