package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Customer;
import model.SellPeriod;
import model.Stock;

public class ProductAddController implements Initializable {
	@FXML
	private Button btnExit;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private TableView tblList1;
	@FXML
	private Button btnIns;
	@FXML
	private Button btnDel;
	@FXML
	private TableView tblList2;
	@FXML
	private TextField txtCount;
	@FXML
	private Button btnCount;
	// --------------------------------------------------------------------
	@FXML
	private TextField txtCustomerNumber;
	@FXML
	private TextField txtCustomerName;
	@FXML
	private TextField txtGender;
	@FXML
	private TextField txtBirthday;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtUserPoint;
	@FXML
	private Button btnSearch2;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField txtUsePoint;
	@FXML
	private Button btnUse;
	@FXML
	private TextField txtCustomerName2;
	@FXML
	private Button btnList;

	public Stage stage3;
	private ObservableList<Stock> obsList1;
	private ObservableList<Customer> obsList2;
	private ObservableList<SellPeriod> obsList3;
	private ObservableList<SellPeriod> obsList4;
	private TableView customerView;
	private int customerViewSelectedIndex;
	private int tblList1ViewSelectedIndex;
	private int tblList2ViewSelectedIndex;
	private TableView countView;
	

	public ProductAddController() {
		this.obsList1 = FXCollections.observableArrayList();
		this.obsList2 = FXCollections.observableArrayList();
		this.obsList3 = FXCollections.observableArrayList();
		this.obsList4 = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 텍스트필드창 비활성화
		txtCustomerNumber.setDisable(true);
		txtCustomerName.setDisable(true);
		txtGender.setDisable(true);
		txtBirthday.setDisable(true);
		txtPhoneNumber.setDisable(true);
		txtUserPoint.setDisable(true);
		txtCount.setDisable(true);
		txtCustomerName2.setDisable(true);

		// tblList1 컬럼초기화셋팅
		tblList1ColumnInitialize();
		// tblList2 컬럼초기화셋팅
		tblList2ColumnInitialize();
		// tblList1 전체보기
		tblList1TotalLoadList();
		// tblList2 전체보기
		tblList2TotalLoadList();

		// 테이블리스트1 선택하면 변경 이벤트
		tblList1.setOnMouseClicked(event -> handleTblList1Action(event));
		// 테이블리스트2 선택하면 변경 이벤트
		tblList2.setOnMouseClicked(event -> handleTblList2Action(event));

		// 조회버튼 이벤트 및 핸들러함수
		btnSearch.setOnAction(event -> handleBtnSearchAction(event));
		// 전체리스트버튼 이벤트 및 핸들러함수
		btnList.setOnAction(event -> handleBtnListAction(event));
		// 등록버튼 이벤트 및 핸들러함수
		btnIns.setOnAction(event -> handleBtnInsertAction(event));
		// 삭제버튼 이벤트 및 핸들러함수
		btnDel.setOnAction(event -> handleBtnDelAction(event));
		// 사용버튼 이벤트 및 핸들러함수
		btnUse.setOnAction(event -> handleBtnUseAction(event));
		// 계산하기버튼 이벤트 및 핸들러함수
		btnCount.setOnAction(event -> handleBtnCountAction(event));

		// 고객정보조회버튼 이벤트 및 핸들러함수
		btnSearch2.setOnAction(event -> handleBtnSearch2Action(event));
		// 닫기버튼 이벤트
		btnExit.setOnAction(event -> stage3.close());
	}

	// 조회버튼 이벤트 및 핸들러함수
	private void handleBtnSearchAction(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			if (txtSearch.getText().trim().equals("")) {
				throw new Exception();
			}
			ArrayList<Stock> arrayList = moduleDAO.getStockFind(txtSearch.getText().trim());

			if (arrayList.size() != 0) {
				obsList1.clear();
				for (int i = 0; i < arrayList.size(); i++) {
					Stock s = arrayList.get(i);
					obsList1.add(s);
				}
			}
		} catch (Exception e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("찾기1입력문제발생!");
			alert.setHeaderText("stock 객체 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 전체리스트버튼 이벤트 및 핸들러함수
	private void handleBtnListAction(ActionEvent event) {
		obsList1.clear();
		tblList1TotalLoadList();
	}

	// 등록버튼 이벤트 및 핸들러함수
	private void handleBtnInsertAction(ActionEvent event) {
		try {
			if (tblList1.getSelectionModel().isEmpty()) {
				throw new Exception();
			}
			if (obsList1.get(tblList1ViewSelectedIndex).getSt_stock85() < 0 || obsList1.get(tblList1ViewSelectedIndex).getSt_stock90() < 0 
					|| obsList1.get(tblList1ViewSelectedIndex).getSt_stock95() < 0 || obsList1.get(tblList1ViewSelectedIndex).getSt_stock100() < 0
					|| obsList1.get(tblList1ViewSelectedIndex).getSt_stock105() < 0 || obsList1.get(tblList1ViewSelectedIndex).getSt_stock110() < 0
					|| obsList1.get(tblList1ViewSelectedIndex).getSt_stock115() < 0)
				throw new Exception();
			// 등록하기
			ModuleDAO moduleDAO = new ModuleDAO();

			SellDAO sellDAO = new SellDAO();

			ArrayList<Stock> arrayList = moduleDAO.getStockViewTotalLoadList();
			int productNumber = arrayList.get(tblList1ViewSelectedIndex).getSt_productNumber();
			String productName = arrayList.get(tblList1ViewSelectedIndex).getSt_productName();
			String productColor = arrayList.get(tblList1ViewSelectedIndex).getSt_productColor();
			int productStock = 1;
			int produceSize = arrayList.get(tblList1ViewSelectedIndex).getSt_productSize();
			int sellPrice = arrayList.get(tblList1ViewSelectedIndex).getSt_sellPrice();

			ArrayList<Customer> arrayList2 = moduleDAO.getCustomerViewTotalLoadList();
			int cid = arrayList2.get(customerViewSelectedIndex).getCid();
			String customerName = arrayList2.get(customerViewSelectedIndex).getCt_customerName();
			String gender = arrayList2.get(customerViewSelectedIndex).getCt_gender();
			String birthday = arrayList2.get(customerViewSelectedIndex).getCt_birthday();
			String phoneNumber = arrayList2.get(customerViewSelectedIndex).getCt_phoneNumber();
			int userPoint = arrayList2.get(customerViewSelectedIndex).getCt_userPoint();

			SellPeriod sellPeriod = new SellPeriod(productNumber, datePicker.getValue().toString(), productName,
					productColor, productStock, produceSize, sellPrice, cid, customerName, gender, birthday,
					phoneNumber, userPoint);
			int returnValue2 = sellDAO.getSellRegistry(sellPeriod);
			if (returnValue2 != 0) {
				obsList3.clear();
				tblList2TotalLoadList();
			}

			// 재고수정
			Stock sto = obsList1.get(tblList1ViewSelectedIndex);
			sto.setSt_productStock(sto.getSt_productStock() - 1);
			switch (sto.getSt_productSize()) {
			case 85:
				sto.setSt_stock85(sto.getSt_stock85() - 1);
				break;
			case 90:
				sto.setSt_stock90(sto.getSt_stock90() - 1);
				break;
			case 95:
				sto.setSt_stock95(sto.getSt_stock95() - 1);
				break;
			case 100:
				sto.setSt_stock100(sto.getSt_stock100() - 1);
				break;
			case 105:
				sto.setSt_stock105(sto.getSt_stock105() - 1);
				break;
			case 110:
				sto.setSt_stock110(sto.getSt_stock110() - 1);
				break;
			case 115:
				sto.setSt_stock115(sto.getSt_stock115() - 1);
				break;
			}
			sto.setSt_productNumber(sto.getSt_productNumber());
			int returnValue = sellDAO.getStockUpdate(sto);
			if (returnValue != 0) {
				// 테이블뷰 obsList 해당된 위치에 수정된 객체값을 입력한다.
				obsList1.set(tblList1ViewSelectedIndex, sto);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("판매정보등록문제발생!");
			alert.setHeaderText("데이터를 입력해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 삭제버튼 이벤트 및 핸들러함수
	private void handleBtnDelAction(ActionEvent event) {
		try {
			if (tblList1.getSelectionModel().isEmpty()) {
				throw new Exception();
			}

			SellDAO sellDAO = new SellDAO();
			// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다
			SellPeriod sellPeriod = obsList3.get(tblList2ViewSelectedIndex);
			int productNumber = sellPeriod.getSe_productNumber();
			int returnValue = sellDAO.getSellPeriodDelete(productNumber);

			if (returnValue != 0) {
				obsList3.remove(tblList2ViewSelectedIndex);
			}

			// 재고수정
			Stock sto = obsList1.get(tblList1ViewSelectedIndex);
			sto.setSt_productStock(sto.getSt_productStock() + 1);
			switch (sto.getSt_productSize()) {
			case 85:
				sto.setSt_stock85(sto.getSt_stock85() + 1);
				break;
			case 90:
				sto.setSt_stock90(sto.getSt_stock90() + 1);
				break;
			case 95:
				sto.setSt_stock95(sto.getSt_stock95() + 1);
				break;
			case 100:
				sto.setSt_stock100(sto.getSt_stock100() + 1);
				break;
			case 105:
				sto.setSt_stock105(sto.getSt_stock105() + 1);
				break;
			case 110:
				sto.setSt_stock110(sto.getSt_stock110() + 1);
				break;
			case 115:
				sto.setSt_stock115(sto.getSt_stock115() + 1);
				break;
			}
			sto.setSt_productNumber(sto.getSt_productNumber());
			int returnValue2 = sellDAO.getStockUpdate(sto);
			if (returnValue2 != 0) {
				// 테이블뷰 obsList 해당된 위치에 수정된 객체값을 입력한다.
				obsList1.set(tblList1ViewSelectedIndex, sto);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("판매정보삭제문제발생!");
			alert.setHeaderText("테이블을 선택해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 사용버튼 이벤트 및 핸들러함수
	private void handleBtnUseAction(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();

			Customer cus = obsList2.get(customerViewSelectedIndex);

			int usePoint = Integer.parseInt(txtUsePoint.getText());

			cus.setCt_userPoint(cus.getCt_userPoint() - usePoint);

			int returnValue = moduleDAO.getCustomerUpdate(cus);
			if (returnValue != 0) {
				// 테이블뷰 obsList 해당된 위치에 수정된 객체값을 입력한다.
				obsList2.set(customerViewSelectedIndex, cus);
				String userPoint = String.valueOf(cus.getCt_userPoint());
				txtUserPoint.setText(userPoint);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객정보수정문제발생!");
			alert.setHeaderText("데이터를 입력해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 계산하기버튼 이벤트 및 핸들러함수
	private void handleBtnCountAction(ActionEvent event) {
		try {
			// 화면내용->씬->스테이지(주인스테이지)->보여주면된다.을 가져왔어요.
			Parent root = FXMLLoader.load(getClass().getResource("/view/count3.fxml"));
			// scene(화면내용) 만든다.
			Scene scene = new Scene(root);
			Stage countStage = new Stage(StageStyle.DECORATED);
			// ------------------------------이벤트등록 및 핸들러 처리--------------------------------
			countView = (TableView) scene.lookup("#countView");
			TextField txtCount2 = (TextField) scene.lookup("#txtCount2");
			Button btnExit2 = (Button) scene.lookup("#btnExit2");
			TextField txtCustomerSearch = (TextField) scene.lookup("#txtCustomerSearch");
			Button btnCustomerSearch = (Button) scene.lookup("#btnCustomerSearch");
			TextField txtUsePoint2 = (TextField) scene.lookup("#txtUsePoint2");
			
			txtCount2.setDisable(true);
			txtUsePoint2.setDisable(true);
			obsList4.clear();
			countViewColumnInitialize();
			countViewTotalLoadList();

			btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						int usePoint = 0;
						SellDAO sellDAO = new SellDAO();
						if (txtCustomerSearch.getText().trim().equals("")) {
							throw new Exception();
						}
						ArrayList<SellPeriod> arrayList = sellDAO.getCountViewFind(txtCustomerSearch.getText().trim());
						if (arrayList.size() != 0) {
							int sum = 0;
							obsList4.clear();
							for (int i = 0; i < arrayList.size(); i++) {
								SellPeriod s = arrayList.get(i);
								int number = arrayList.get(i).getSe_sellPrice();
								sum += number;
								obsList4.add(s);
							}
							if(txtUsePoint.getText().equals("")) {
								usePoint = 0;
							}else {								
								usePoint = Integer.parseInt(txtUsePoint.getText());
							}
							txtUsePoint2.setText(String.valueOf(usePoint));
							String sum2 = String.valueOf(sum-usePoint);
							txtCount.setText(sum2);
							txtCount2.setText(sum2);
							txtCustomerName2.setText(txtCustomerSearch.getText().trim());
						}
					} catch (Exception e) {
						// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("CountView찾기입력문제발생!");
						alert.setHeaderText("SellPeriod 객체 점검을 해주세요.");
						alert.setContentText("다음에는 주의하세요.");
						alert.showAndWait();
					}
				}
			});

			btnExit2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					countStage.close();
				}
			});

			// ------------------------------/이벤트등록 및 핸들러 처리-------------------------------
			// 스테이지(주인스테이지)를 만든다. (*모달, 모달리스), 스테이지(씬)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			countStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			countStage.initModality(Modality.WINDOW_MODAL);
			countStage.initOwner(stage3);
			countStage.setScene(scene);
			countStage.setTitle("계 산 창");
			countStage.setResizable(false);
			countStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("계산창 에러!");
			alert.setHeaderText("점검요망");
			alert.setContentText("정신차리세요!");
			alert.showAndWait();
		}
	}
	
	// tblList1 컬럼초기화셋팅
	private void tblList1ColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("상품번호");
		colProductNumber.setPrefWidth(100);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("st_productNumber"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setPrefWidth(100);
		colProductName.setCellValueFactory(new PropertyValueFactory("st_productName"));

		TableColumn colProductColor = new TableColumn("색상");
		colProductColor.setPrefWidth(100);
		colProductColor.setCellValueFactory(new PropertyValueFactory("st_productColor"));

		TableColumn colProductStock = new TableColumn("재고");
		colProductStock.setPrefWidth(100);
		colProductStock.setCellValueFactory(new PropertyValueFactory("st_productStock"));

		TableColumn colSellPrice = new TableColumn("판매가");
		colSellPrice.setPrefWidth(100);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("st_sellPrice"));

		TableColumn colProductSize = new TableColumn("사이즈");
		colProductSize.setPrefWidth(100);
		colProductSize.setCellValueFactory(new PropertyValueFactory("st_productSize"));

		tblList1.getColumns().addAll(colProductNumber, colProductName, colProductColor, colProductStock, colSellPrice,
				colProductSize);

		tblList1.setItems(obsList1);
	}

	// tblList2 컬럼초기화셋팅
	private void tblList2ColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("상품번호");
		colProductNumber.setMaxWidth(80);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("se_productNumber"));

		TableColumn colSellPeriod = new TableColumn("판매날짜");
		colSellPeriod.setPrefWidth(110);
		colSellPeriod.setCellValueFactory(new PropertyValueFactory("se_sellPeriod"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setMaxWidth(100);
		colProductName.setCellValueFactory(new PropertyValueFactory("se_productName"));

		TableColumn colProductColor = new TableColumn("색상");
		colProductColor.setMaxWidth(80);
		colProductColor.setCellValueFactory(new PropertyValueFactory("se_productColor"));

		TableColumn colProductStock = new TableColumn("재고");
		colProductStock.setMaxWidth(40);
		colProductStock.setCellValueFactory(new PropertyValueFactory("se_productStock"));

		TableColumn colProductSize = new TableColumn("사이즈");
		colProductSize.setMaxWidth(100);
		colProductSize.setCellValueFactory(new PropertyValueFactory("se_productSize"));

		TableColumn colSellPrice = new TableColumn("판매가");
		colSellPrice.setMaxWidth(100);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("se_sellPrice"));

		TableColumn colCustomerNumber = new TableColumn("고객 번호");
		colCustomerNumber.setMaxWidth(100);
		colCustomerNumber.setCellValueFactory(new PropertyValueFactory("se_cid"));
		colCustomerNumber.setStyle("-Fx-alignment: CENTER");

		TableColumn colCustomerName = new TableColumn("고객명");
		colCustomerName.setMaxWidth(100);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("se_customerName"));

		TableColumn colGender = new TableColumn("성별");
		colGender.setMaxWidth(100);
		colGender.setCellValueFactory(new PropertyValueFactory("se_gender"));

		TableColumn colBirthday = new TableColumn("생년월일");
		colBirthday.setPrefWidth(110);
		colBirthday.setCellValueFactory(new PropertyValueFactory("se_birthday"));

		TableColumn colPhoneNumber = new TableColumn("핸드폰 번호");
		colPhoneNumber.setPrefWidth(150);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory("se_phoneNumber"));

		TableColumn colUserPoint = new TableColumn("적립 포인트");
		colUserPoint.setPrefWidth(100);
		colUserPoint.setCellValueFactory(new PropertyValueFactory("se_userPoint"));

		tblList2.getColumns().addAll(colProductNumber, colSellPeriod, colProductName, colProductColor,
				colProductStock, colProductSize, colSellPrice, colCustomerNumber, colCustomerName, colGender,
				colBirthday, colPhoneNumber, colUserPoint);

		tblList2.setItems(obsList3);
	}

	// customerView 컬럼초기화셋팅
	private void customerViewColumnInitialize() {
		TableColumn colCustomerNumber = new TableColumn("고객 번호");
		colCustomerNumber.setMaxWidth(100);
		colCustomerNumber.setCellValueFactory(new PropertyValueFactory("cid"));
		colCustomerNumber.setStyle("-Fx-alignment: CENTER");

		TableColumn colCustomerName = new TableColumn("고객명");
		colCustomerName.setMaxWidth(100);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("ct_customerName"));

		TableColumn colGender = new TableColumn("성별");
		colGender.setMaxWidth(100);
		colGender.setCellValueFactory(new PropertyValueFactory("ct_gender"));

		TableColumn colBirthday = new TableColumn("생년월일");
		colBirthday.setPrefWidth(150);
		colBirthday.setCellValueFactory(new PropertyValueFactory("ct_birthday"));

		TableColumn colPhoneNumber = new TableColumn("핸드폰 번호");
		colPhoneNumber.setPrefWidth(150);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory("ct_phoneNumber"));

		TableColumn colUserPoint = new TableColumn("적립 포인트");
		colUserPoint.setPrefWidth(100);
		colUserPoint.setCellValueFactory(new PropertyValueFactory("ct_userPoint"));

		customerView.getColumns().addAll(colCustomerNumber, colCustomerName, colGender, colBirthday, colPhoneNumber,
				colUserPoint);

		customerView.setItems(obsList2);
	}

	// countView 컬럼초기화셋팅
	private void countViewColumnInitialize() {
		TableColumn colSellPeriod = new TableColumn("판매날짜");
		colSellPeriod.setPrefWidth(110);
		colSellPeriod.setCellValueFactory(new PropertyValueFactory("sid"));

		TableColumn colCustomerName = new TableColumn("고객명");
		colCustomerName.setMaxWidth(80);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("se_customerName"));

		TableColumn colProductNumber = new TableColumn("상품번호");
		colProductNumber.setMaxWidth(80);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("se_productNumber"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setMaxWidth(100);
		colProductName.setCellValueFactory(new PropertyValueFactory("se_productName"));

		TableColumn colProductColor = new TableColumn("색상");
		colProductColor.setMaxWidth(80);
		colProductColor.setCellValueFactory(new PropertyValueFactory("se_productColor"));

		TableColumn colProductStock = new TableColumn("재고");
		colProductStock.setMaxWidth(40);
		colProductStock.setCellValueFactory(new PropertyValueFactory("se_productStock"));

		TableColumn colSellPrice = new TableColumn("판매가");
		colSellPrice.setMaxWidth(100);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("se_sellPrice"));

		countView.getColumns().addAll(colProductNumber, colCustomerName, colProductName, colProductColor,
				colProductStock, colSellPrice);

		countView.setItems(obsList4);
	}

	// tblList1 전체보기
	private void tblList1TotalLoadList() {
		ModuleDAO moduleDAO = new ModuleDAO();
		ArrayList<Stock> arrayList = moduleDAO.getStockViewTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			Stock s = arrayList.get(i);
			obsList1.add(s);
		}
		tblList1.setItems(obsList1);
	}

	// tblList2 전체보기
	private void tblList2TotalLoadList() {
		SellDAO sellDAO = new SellDAO();
		ArrayList<SellPeriod> arrayList = sellDAO.getTblList2TotalList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			SellPeriod s = arrayList.get(i);
			obsList3.add(s);
		}
		tblList2.setItems(obsList3);
	}

	// customerView 전체보기
	private void customerViewTotalLoadList() {
		ModuleDAO moduleDAO = new ModuleDAO();
		ArrayList<Customer> arrayList = moduleDAO.getCustomerViewTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			Customer c = arrayList.get(i);
			obsList2.add(c);
		}
		customerView.setItems(obsList2);
	}

	// countView 전체보기
	private void countViewTotalLoadList() {
		SellDAO sellDAO = new SellDAO();
		ArrayList<SellPeriod> arrayList = sellDAO.getTblList2TotalList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			SellPeriod s = arrayList.get(i);
			obsList4.add(s);
		}
		countView.setItems(obsList4);
	}

	// 테이블리스트1 선택하면 변경 이벤트
	private void handleTblList1Action(MouseEvent event) {
		tblList1ViewSelectedIndex = tblList1.getSelectionModel().getSelectedIndex();
	}

	// 테이블리스트2 선택하면 변경 이벤트
	private void handleTblList2Action(MouseEvent event) {
		tblList2ViewSelectedIndex = tblList2.getSelectionModel().getSelectedIndex();
	}

	// 고객정보조회버튼 이벤트 및 핸들러함수
	private void handleBtnSearch2Action(ActionEvent event) {
		try {
			// 화면내용->씬->스테이지(주인스테이지)->보여주면된다.을 가져왔어요.
			Parent root = FXMLLoader.load(getClass().getResource("/view/customerList.fxml"));
			// scene(화면내용) 만든다.
			Scene scene = new Scene(root);
			Stage customerListStage = new Stage(StageStyle.UTILITY);
			// ------------------------------이벤트등록 및 핸들러 처리--------------------------------
			customerView = (TableView) scene.lookup("#customerView");
			Button btnExit = (Button) scene.lookup("#btnExit");

			customerViewColumnInitialize();
			obsList2.clear();
			customerViewTotalLoadList();

			customerView.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					customerViewSelectedIndex = customerView.getSelectionModel().getSelectedIndex();
					Customer customer = obsList2.get(customerViewSelectedIndex);
					txtCustomerNumber.setText(customer.getCt_phoneNumber());
					txtCustomerName.setText(customer.getCt_customerName());
					txtGender.setText(customer.getCt_gender());
					txtBirthday.setText(customer.getCt_birthday());
					txtPhoneNumber.setText(customer.getCt_phoneNumber());
					txtUserPoint.setText(String.valueOf(customer.getCt_userPoint()));
				}
			});

			btnExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					customerListStage.close();
				}
			});

			// ------------------------------/이벤트등록 및 핸들러 처리-------------------------------
			// 스테이지(주인스테이지)를 만든다. (*모달, 모달리스), 스테이지(씬)
			customerListStage.initModality(Modality.WINDOW_MODAL);
			customerListStage.initOwner(stage3);
			customerListStage.setScene(scene);
			customerListStage.setTitle("고객정보조회");
			customerListStage.setResizable(false);
			customerListStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("등록창 에러!");
			alert.setHeaderText("점검요망");
			alert.setContentText("정신차리세요!");
			alert.showAndWait();
		}
	}

}
