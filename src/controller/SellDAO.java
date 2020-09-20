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
	// 판매기간 전체보기
	public ArrayList<SellPeriod> getTblList2TotalList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getTblList2TotalList : DB 연결성공!");
			} else {
				System.out.println("RootController.getTblList2TotalList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14));
				arrayList.add(sellPeriod);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("tblList2TotalLoadList 점검요망!");
			alert.setHeaderText("tblList2TotalLoadList 문제발생!");
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
				System.out.println("RootController.getTblList2TotalList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// 재고, 판매정보 전체보기
	public ArrayList<SellDetail> getStockSellTotalLoadList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellDetail> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getStockSellTotalLoadList : DB 연결성공!");
			} else {
				System.out.println("RootController.getStockSellTotalLoadList : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "SELECT st.*,spt.* from stockTBL as st Inner JOIN sellPeriodTBL as spt on st.st_productNumber = spt.se_productNumber";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
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
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("sellView2TotalLoadList 점검요망!");
			alert.setHeaderText("sellView2TotalLoadList 문제발생!");
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
				System.out.println("RootController.sellView2TotalLoadList : " + e.getMessage());
			}
		}
		return arrayList;
	}

	// 등록버튼 이벤트 및 핸들러함수
	public int getSellRegistry(SellPeriod sellPeriod) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellRegistry : DB 연결 성공");
			} else
				System.out.println("RootController.getSellRegistry : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "insert into sellPeriodTBL values(Null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 4. 쿼리문 실행하기 위한 준비
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

			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("등록 성공");
				alert.setHeaderText(sellPeriod.getSe_customerName() + " 등록 성공");
				alert.setContentText(sellPeriod.getSe_customerName() + " 하이하이");
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
				System.out.println("RootController.handleBtnInsertAction : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// 삭제버튼 이벤트 및 핸들러함수
	public int getSellPeriodDelete(int productNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int returnValue = 0;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getSellPeriodDelete : DB 연결 성공");
			} else
				System.out.println("RootController.getSellPeriodDelete : DB 연결 실패");

			// 3. con 객체를 가지고 쿼리문 실행할수있다(select,insert,update,delete)
			String query = "delete from sellPeriodTBL where se_productNumber = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			pstmt.setInt(1, productNumber); // 1의 의미:첫번째 ?, ?가 두개 2로하면 두번째 ?에 들어간다
			// 5. 쿼리문을 실행한다
			// 쿼리문을 실행해서 레코드내용의 결과값을 배열로 가져온다 : executeQuery();
			// 또는 단지 실행만 한다 : executeUqdate();
			returnValue = pstmt.executeUpdate();// 아무것도안하면 0 리턴, 실행한 카운트만큼 int값 반환
			if (returnValue != 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("삭제 성공!");
				alert.setHeaderText(productNumber + " 삭제 성공");
				alert.setContentText(productNumber + " 삭제 완료");
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
				System.out.println("RootController.getSellPeriodDelete : " + e1.getMessage());
			}
		}
		return returnValue;
	}

	// CountView 찾기버튼 이벤트 및 핸들러함수
	public ArrayList<SellPeriod> getCountViewFind(String name) {
		// 데이터베이스를 실행합니다.=================================================
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SellPeriod> arrayList = null;
		try {
			con = DBUtil.getConnection();
			if (con != null) {
				System.out.println("RootController.getCountViewFind : DB 연결성공!");
			} else {
				System.out.println("RootController.getCountViewFind : DB 연결실패!");
			}
			// 3. con 객체를 가지고 쿼리문을 실행할수있다. (select, insert, update, delete)
			String query = "select * from sellPeriodTBL where se_customerName like ?";
			// 4. 쿼리문을 실행하기위한 준비
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");

			// 5. 쿼리문을 싱행한다. (결과값의 레코드내용을 배열로 가져온다.)
			rs = pstmt.executeQuery();
			// 6. ResultSet 값을 한개씩 가져와서 ArrayList에 저장한다.
			arrayList = new ArrayList<SellPeriod>();
			while (rs.next()) {
				SellPeriod sellPeriod = new SellPeriod(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14));
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
				System.out.println("RootController.getCountViewFind : " + e2.getMessage());
			}
		}
		// 데이터 베이스 종점======================================================
		return arrayList;
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
			String query = "update stockTBL set st_productStock = ? , st_stock85 = ?, st_stock90 = ?, st_stock95 = ?, st_stock100 = ?, \r\n" + 
					"st_stock105 = ?, st_stock110 = ?, st_stock115 = ? where st_productNumber = ?";
			// 4. 쿼리문 실행하기 위한 준비
			pstmt = con.prepareStatement(query); // 외워라
			// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다

			pstmt.setInt(1, stock.getSt_productStock());
			pstmt.setInt(2, stock.getSt_stock85());
			pstmt.setInt(3, stock.getSt_stock90());
			pstmt.setInt(4, stock.getSt_stock95());
			pstmt.setInt(5, stock.getSt_stock100());
			pstmt.setInt(6, stock.getSt_stock105());
			pstmt.setInt(7, stock.getSt_stock110());
			pstmt.setInt(8, stock.getSt_stock115());
			pstmt.setInt(9, stock.getSt_productNumber());
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
}
