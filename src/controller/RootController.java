package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Customer;
import model.SellDetail;
import model.SellPeriod;
import model.Stock;

public class RootController implements Initializable {
	// -------------------------------Tab1--------------------------------------
	@FXML
	private TableView stockView;
	@FXML
	private TextField txtSearch1;
	@FXML
	private Button btnSearch1;
	@FXML
	private ImageView imgView;
	@FXML
	private TextField txtField;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnList;
	@FXML
	private Button btnDelete1;
	// -------------------------------Tab2--------------------------------------
	@FXML
	private TableView sellView1;
	@FXML
	private TableView sellView2;
	@FXML
	private DatePicker datePicker1;
	@FXML
	private DatePicker datePicker2;
	@FXML
	private Button btnSearch2;
	@FXML
	private Button btnList2;
	@FXML
	private Button btnBarChart;
	@FXML
	private Button btnPieChart;
	// -------------------------------Tab3-------------------------------------
	@FXML
	private TextField txtNumber;
	@FXML
	private TextField txtName;
	@FXML
	private RadioButton rdoMale;
	@FXML
	private RadioButton rdoFemale;
	@FXML
	private DatePicker datePicker3;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtUserPoint;
	@FXML
	private TableView customerView;
	@FXML
	private TextField txtSearch2;
	@FXML
	private Button btnSearch3;
	@FXML
	private Button btnDelete2;
	@FXML
	private Button btnCustomerEdit;
	@FXML
	private Button btnCustomerAdd;
	@FXML
	private Button btnCustomerClear;
	@FXML
	private Button btnList3;
	// -----------------------------Anchorpane---------------------------------
	@FXML
	private Button btnExit;
	@FXML
	private Button btnProductAdd;
	@FXML
	private Button btnNotice;
	
	
	private ObservableList<Stock> obsList1;
	private ObservableList<Customer> obsList2;
	private ObservableList<SellPeriod> obsList3;
	private ObservableList<SellDetail> obsList4;
	private int stockViewSelectedIndex;
	private int customerViewSelectedIndex;
	private int sellView1SelectedIndex;
	private int sellView2SelectedIndex;
	public Stage stage2;
	private File selectFile;
	private File directorySave;
	private File selectFile2;
	private File directorySave2;
	private ToggleGroup group;

	public RootController() {
		this.obsList1 = FXCollections.observableArrayList();
		this.obsList2 = FXCollections.observableArrayList();
		this.obsList3 = FXCollections.observableArrayList();
		this.obsList4 = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 스탁뷰 컬럼초기화셋팅
		stockViewColumnInitialize();
		// 셀뷰1 컬럼초기화셋팅
		sellView1ColumnInitialize();
		// 셀뷰2 컬럼초기화셋팅
		sellView2ColumnInitialize();
		// 커스토머뷰 컬럼초기화셋팅
		customerViewColumnInitialize();

		// 스탁뷰 전체보기
		stockViewTotalLoadList();
		// 셀뷰1 전체보기
		sellView1TotalLoadList();
		// 셀뷰2 전체보기
		sellView2TotalLoadList();
		// 커스토머뷰 전체보기
		customerViewTotalLoadList();

		// 상품등록버튼 이벤트 및 핸들러함수
		btnProductAdd.setOnAction(event -> handleBtnProductAddAction(event));
		// 공지사항버튼 이벤트 및 핸들러함수		
		btnNotice.setOnAction(event -> handleBtnNoticeAction(event));
		// 나가기버튼 이벤트 및 핸들러함수
		btnExit.setOnAction(event -> Platform.exit());
		// ---------------------------------------재고현황-------------------------------------------
		txtField.setDisable(true); // 텍스트 필드 비활성화

		// 찾기1버튼 이벤트 및 핸들러함수
		btnSearch1.setOnAction(event -> handleBtnSearchAction(event));
		// 리스트1버튼 이벤트 및 핸들러함수
		btnList.setOnAction(event -> handleBtnListAction(event));
		// 기본적인 이미지 사진 세팅하기
		setDefaultImageView();
		// 스탁뷰 선택하면 이미지 변경 이벤트
		stockView.setOnMouseClicked(event -> handleStockViewAction(event));
		// 등록버튼 이벤트 및 핸들러함수
		btnAdd.setOnAction(event -> handelBtnAddAction(event));
		// 삭제1버튼 이벤트 및 핸들러함수
		btnDelete1.setOnAction(event -> handleBtnDelete1Action(event));
		// 수정버튼 이벤트 및 핸들러함수
		btnEdit.setOnAction(event -> handleBtnEditAction(event));
		// 사진을 저장할수 있는 폴더 만들기
		setDirectoryImageSaveImage();
		// ---------------------------------------/재고현황-------------------------------------------
		// ---------------------------------------매출현황-------------------------------------------
		// 찾기2버튼 이벤트 및 핸들러함수
		btnSearch2.setOnAction(event -> handleBtnSearch2Action(event));
		// 셀뷰1 선택하면 변경 이벤트
		sellView1.setOnMouseClicked(event -> handleSellView1Action(event));
		// 셀뷰2 선택하면 변경 이벤트
		sellView2.setOnMouseClicked(event -> handleSellView2Action(event));
		// 리스트2버튼 이벤트 및 핸들러함수
		btnList2.setOnAction(event -> handleBtnList2Action(event));
		// 바차트버튼 이벤트 및 핸들러함수
		btnBarChart.setOnAction(event -> handleBarChartAction(event));
		// 파이차트버튼 이벤트 및 핸들러함수
		btnPieChart.setOnAction(event -> handlePieChartAction(event));

		// ---------------------------------------/매출현황-------------------------------------------
		// ---------------------------------------고객관리-------------------------------------------
		// 찾기3버튼 이벤트 및 핸들러함수
		btnSearch3.setOnAction(event -> handleBtnSearch3Action(event));
		// 삭제2버튼 이벤트 및 핸들러함수
		btnDelete2.setOnAction(event -> handleBtnDelete2Action(event));
		// 커스토머뷰 선택하면 변경 이벤트
		customerView.setOnMousePressed(event -> handleCustomerViewAction(event));
		// 커스토머뷰 더블클릭하면 변경 이벤트
		customerView.setOnMouseClicked(event -> handleCustomerViewAction2(event));
		// 리스트3버튼 이벤트 및 핸들러함수
		btnList3.setOnAction(event -> handleBtnList3Action(event));
		// 고객정보등록버튼 이벤트 및 핸들러함수
		btnCustomerAdd.setOnAction(event -> handleBtnCustomerAddAction(event));
		// 고객정보수정버튼 이벤트 및 핸들러함수
		btnCustomerEdit.setOnAction(event -> handleBtnCustomerEditAction(event));
		// 고객정보초기화버튼 이벤트 및 핸들러함수
		btnCustomerClear.setOnAction(event -> handleBtnCustomerClearAction(event));
		// 성별라디오버튼 그룹 초기화 처리
		radioButtonGenderInitialize();
		// ---------------------------------------/고객관리-------------------------------------------
	}



	// 스탁뷰 전체보기
	private void stockViewTotalLoadList() {
		ModuleDAO moduleDAO = new ModuleDAO();
		ArrayList<Stock> arrayList = moduleDAO.getStockViewTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			Stock s = arrayList.get(i);
			obsList1.add(s);
		}
		stockView.setItems(obsList1);
	}

	// 셀뷰1 전체보기
	private void sellView1TotalLoadList() {
		SellDAO sellDAO = new SellDAO();
		ArrayList<SellPeriod> arrayList = sellDAO.getTblList2TotalList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			SellPeriod s = arrayList.get(i);
			obsList3.add(s);
		}
		sellView1.setItems(obsList3);
	}

	// 셀뷰2 전체보기
	private void sellView2TotalLoadList() {
		SellDAO sellDAO = new SellDAO();
		ArrayList<SellDetail> arrayList = sellDAO.getStockSellTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			SellDetail s = arrayList.get(i);
			obsList4.add(s);
		}
		sellView2.setItems(obsList4);
	}

	// 커스토머뷰 전체보기
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

	// 스탁뷰 컬럼초기화셋팅
	private void stockViewColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("상품번호");
		colProductNumber.setMaxWidth(80);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("st_productNumber"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setMaxWidth(100);
		colProductName.setCellValueFactory(new PropertyValueFactory("st_productName"));

		TableColumn colProductColor = new TableColumn("색상");
		colProductColor.setMaxWidth(80);
		colProductColor.setCellValueFactory(new PropertyValueFactory("st_productColor"));

		TableColumn colProductStock = new TableColumn("재고");
		colProductStock.setMaxWidth(40);
		colProductStock.setCellValueFactory(new PropertyValueFactory("st_productStock"));

		TableColumn colCustomerPrice = new TableColumn("소비자가");
		colCustomerPrice.setMaxWidth(100);
		colCustomerPrice.setCellValueFactory(new PropertyValueFactory("st_customerPrice"));

		TableColumn colSupplyPrice = new TableColumn("공급가");
		colSupplyPrice.setMaxWidth(100);
		colSupplyPrice.setCellValueFactory(new PropertyValueFactory("st_supplyPrice"));

		TableColumn colSupplyPriceTotal = new TableColumn("공급계");
		colSupplyPriceTotal.setMaxWidth(100);
		colSupplyPriceTotal.setCellValueFactory(new PropertyValueFactory("st_supplyPriceTotal"));

		TableColumn colSellPrice = new TableColumn("판매가");
		colSellPrice.setMaxWidth(100);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("st_sellPrice"));

		TableColumn colSellPriceTotal = new TableColumn("판매계");
		colSellPriceTotal.setMaxWidth(100);
		colSellPriceTotal.setCellValueFactory(new PropertyValueFactory("st_sellPriceTotal"));

		TableColumn colStock85 = new TableColumn("85");
		colStock85.setMaxWidth(40);
		colStock85.setCellValueFactory(new PropertyValueFactory("st_stock85"));

		TableColumn colStock90 = new TableColumn("90");
		colStock90.setMaxWidth(40);
		colStock90.setCellValueFactory(new PropertyValueFactory("st_stock90"));

		TableColumn colStock95 = new TableColumn("95");
		colStock95.setMaxWidth(40);
		colStock95.setCellValueFactory(new PropertyValueFactory("st_stock95"));

		TableColumn colStock100 = new TableColumn("100");
		colStock100.setMaxWidth(40);
		colStock100.setCellValueFactory(new PropertyValueFactory("st_stock100"));

		TableColumn colStock105 = new TableColumn("105");
		colStock105.setMaxWidth(40);
		colStock105.setCellValueFactory(new PropertyValueFactory("st_stock105"));

		TableColumn colStock110 = new TableColumn("110");
		colStock110.setMaxWidth(40);
		colStock110.setCellValueFactory(new PropertyValueFactory("st_stock110"));

		TableColumn colStock115 = new TableColumn("115");
		colStock115.setMaxWidth(40);
		colStock115.setCellValueFactory(new PropertyValueFactory("st_stock115"));

		stockView.getColumns().addAll(colProductNumber, colProductName, colProductColor, colProductStock,
				colCustomerPrice, colSupplyPrice, colSupplyPriceTotal, colSellPrice, colSellPriceTotal, colStock85,
				colStock90, colStock95, colStock100, colStock105, colStock110, colStock115);

		stockView.setItems(obsList1);
	}

	// 셀뷰1 컬럼초기화셋팅
	private void sellView1ColumnInitialize() {
		TableColumn colSellPeriod = new TableColumn("판매 일자");
		colSellPeriod.setPrefWidth(150);
		colSellPeriod.setCellValueFactory(new PropertyValueFactory("se_sellPeriod"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setPrefWidth(150);
		colProductName.setCellValueFactory(new PropertyValueFactory("se_productName"));

		TableColumn colProductStock = new TableColumn("수량");
		colProductStock.setPrefWidth(100);
		colProductStock.setCellValueFactory(new PropertyValueFactory("se_productStock"));

		TableColumn colSellPrice = new TableColumn("판매 금액");
		colSellPrice.setPrefWidth(150);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("se_sellPrice"));

		TableColumn colCustomerName = new TableColumn("고객명");
		colCustomerName.setPrefWidth(100);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("se_customerName"));

		TableColumn colPhoneNumber = new TableColumn("핸드폰 번호");
		colPhoneNumber.setPrefWidth(150);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory("se_phoneNumber"));

		TableColumn colUserPoint = new TableColumn("적립 포인트");
		colUserPoint.setPrefWidth(100);
		colUserPoint.setCellValueFactory(new PropertyValueFactory("se_userPoint"));

		sellView1.getColumns().addAll(colSellPeriod, colProductName, colProductStock, colSellPrice, colCustomerName,
				colPhoneNumber, colUserPoint);

		sellView1.setItems(obsList3);
	}

	// 셀뷰2 컬럼초기화셋팅
	private void sellView2ColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("상품 번호");
		colProductNumber.setPrefWidth(150);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("ij_st_productNumber"));

		TableColumn colProductName = new TableColumn("상품명");
		colProductName.setPrefWidth(150);
		colProductName.setCellValueFactory(new PropertyValueFactory("ij_st_productName"));

		TableColumn colProductColor = new TableColumn("컬러");
		colProductColor.setPrefWidth(100);
		colProductColor.setCellValueFactory(new PropertyValueFactory("ij_st_productColor"));

		TableColumn colProductStock = new TableColumn("수량");
		colProductStock.setPrefWidth(100);
		colProductStock.setCellValueFactory(new PropertyValueFactory("ij_st_productStock"));

		TableColumn colProductSize = new TableColumn("사이즈");
		colProductSize.setPrefWidth(100);
		colProductSize.setCellValueFactory(new PropertyValueFactory("ij_st_productSize"));

		TableColumn colCustomerPrice = new TableColumn("소비자가");
		colCustomerPrice.setPrefWidth(150);
		colCustomerPrice.setCellValueFactory(new PropertyValueFactory("ij_st_customerPrice"));

		TableColumn colSellPrice = new TableColumn("판매가");
		colSellPrice.setPrefWidth(150);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("ij_st_sellPrice"));

		TableColumn colSellPriceTotal = new TableColumn("판매총액");
		colSellPriceTotal.setPrefWidth(150);
		colSellPriceTotal.setCellValueFactory(new PropertyValueFactory("ij_st_sellPriceTotal"));

		TableColumn colSalePercent = new TableColumn("할인율");
		colSalePercent.setPrefWidth(100);
		colSalePercent.setCellValueFactory(new PropertyValueFactory("ij_st_salePercent"));

		sellView2.getColumns().addAll(colProductNumber, colProductName, colProductColor, colProductStock,
				colProductSize, colCustomerPrice, colSellPrice, colSellPriceTotal, colSalePercent);

		sellView2.setItems(obsList4);
	}

	// 커스토머뷰 컬럼초기화셋팅
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

	// 상품등록버튼 이벤트 및 핸들러함수
	private void handleBtnProductAddAction(ActionEvent event) {
		try {
			Stage productAddStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/productAdd.fxml"));
			Parent root = fxmlLoader.load();
			ProductAddController productAddController = fxmlLoader.getController();
			productAddController.stage3 = productAddStage;

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			productAddStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			productAddStage.setTitle("쇼핑몰 재고관리 프로그램(판매등록)");
			productAddStage.setScene(scene);
			productAddStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("판매등록창문제발생!");
			alert.setHeaderText("RootController 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 공지사항버튼 이벤트 및 핸들러함수
	private void handleBtnNoticeAction(ActionEvent event) {
		try {
			Stage noticeStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/notice.fxml"));
			Parent root = fxmlLoader.load();
			NoticeController noticeController = fxmlLoader.getController();
			noticeController.stage4 = noticeStage;

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			noticeStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			noticeStage.setTitle("쇼핑몰 재고관리 프로그램(공지사항)");
			noticeStage.setScene(scene);
			noticeStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("공지사항창문제발생!");
			alert.setHeaderText("RootController 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}		
	}
	// ---------------------------------------재고현황-------------------------------------------------
	// 찾기1버튼 이벤트 및 핸들러함수
	private void handleBtnSearchAction(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			if (txtSearch1.getText().trim().equals("")) {
				throw new Exception();
			}
			ArrayList<Stock> arrayList = moduleDAO.getStockFind(txtSearch1.getText().trim());

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

	// 리스트1버튼 이벤트 및 핸들러함수
	private void handleBtnListAction(ActionEvent event) {
		obsList1.clear();
		stockViewTotalLoadList();
	}

	// 기본적인 이미지 사진 세팅하기
	private void setDefaultImageView() {
		Image image = new Image("/image/default.png", false);
		imgView.setImage(image);		
	}

	// 스탁뷰 선택하면 이미지 변경 이벤트
	private void handleStockViewAction(MouseEvent event) {
		try {
			ObservableList<Stock> observableList = stockView.getSelectionModel().getSelectedItems();
			String selectFileName = observableList.get(0).getSt_image();
			txtField.setText(String.valueOf(observableList.get(0).getSt_productNumber()));
			Image image = new Image("file:/C:/images/" + selectFileName, false);
			imgView.setImage(image);

			stockViewSelectedIndex = stockView.getSelectionModel().getSelectedIndex();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("테이블뷰선택문제발생!");
			alert.setHeaderText("데이터를 선택해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 등록버튼 이벤트 및 핸들러함수
	private void handelBtnAddAction(ActionEvent event) {
		// edit.fxml 화면을 로드해야된다.
		try {
			// 화면내용->씬->스테이지(주인스테이지)->보여주면된다.을 가져왔어요.
			Parent root = FXMLLoader.load(getClass().getResource("/view/add.fxml"));
			// scene(화면내용) 만든다.
			Scene scene = new Scene(root);
			Stage addStage = new Stage(StageStyle.DECORATED);
			// ------------------------------이벤트등록 및 핸들러 처리--------------------------------
			TextField txtProductNumber = (TextField) scene.lookup("#txtProductNumber");
			TextField txtProductName = (TextField) scene.lookup("#txtProductName");
			TextField txtProductColor = (TextField) scene.lookup("#txtProductColor");
			TextField txtProductSize = (TextField) scene.lookup("#txtProductSize");
			TextField txtCustomerPrice = (TextField) scene.lookup("#txtCustomerPrice");
			TextField txtSalePercent = (TextField) scene.lookup("#txtSalePercent");
			TextField txtSupplyPrice = (TextField) scene.lookup("#txtSupplyPrice");
			TextField txtSellPrice = (TextField) scene.lookup("#txtSellPrice");
			TextField txtStock85 = (TextField) scene.lookup("#txtStock85");
			TextField txtStock90 = (TextField) scene.lookup("#txtStock90");
			TextField txtStock95 = (TextField) scene.lookup("#txtStock95");
			TextField txtStock100 = (TextField) scene.lookup("#txtStock100");
			TextField txtStock105 = (TextField) scene.lookup("#txtStock105");
			TextField txtStock110 = (TextField) scene.lookup("#txtStock110");
			TextField txtStock115 = (TextField) scene.lookup("#txtStock115");
			ImageView imgView1 = (ImageView) scene.lookup("#imgView");
			Button btnImgView = (Button) scene.lookup("#btnImgView");
			Button btnAdd = (Button) scene.lookup("#btnAdd");
			Button btnExit = (Button) scene.lookup("#btnExit");
			
			Image image = new Image("/image/default.png", false);
			imgView1.setImage(image);
			
			btnAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ModuleDAO moduleDAO = new ModuleDAO();
					// 이미지저장처리순서 1. 이미지파일명을 생성해서 복사해서 해당디렉토리에 저장한다.
					// 1. 이미지파일명을 만들어야한다.
					if (selectFile == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("사진선택없음!");
						alert.setHeaderText("사진가져오기 문제발생");
						alert.setContentText("이미지파일만 선택하세요!");
						alert.showAndWait();
						return;
					}
					// 2. 실제파이을 가져와서 새로만든 이미지 파일명에 저장한다.
					BufferedInputStream bis = null; // 파일을 읽을때 사용하는 클래스
					BufferedOutputStream bos = null; // 파일을 쓸때 사용하는 클래스
					String fileName = null;
					try {
						// "stu1238452179홍길동.jpg"
						fileName = "stock" + System.currentTimeMillis() + selectFile.getName();

						// 이미지파일은 바이트스트림으로 바꾸어서 버퍼를 활용해서 읽는다.
						// C:/images/stu1238452179홍길동.jpg
						bis = new BufferedInputStream(new FileInputStream(selectFile));
						bos = new BufferedOutputStream(
								new FileOutputStream(directorySave.getAbsolutePath() + "\\" + fileName));
						// 이미지파일을 읽어서 저장위치에 있는 파일에다가 쓴다.
						// -1 : 더이상 읽을값이 없다는 의미이다.
						int data = -1;
						while ((data = bis.read()) != -1) {
							bos.write(data);
							bos.flush(); // 버퍼에 있는값을 다 저장하기위해서 보내라.
						}
					} catch (Exception e) {
						System.out.println("파일복사에러" + e.getMessage());
						return;
					} finally {
						try {
							if (bis != null)
								bis.close();
							if (bos != null)
								bis.close();
						} catch (IOException e) {
							System.out.println("bis.close(), bos.close() error!" + e.getMessage());
						}
					}

					int productNumber = Integer.parseInt(txtProductNumber.getText());
					int productSize = Integer.parseInt(txtProductSize.getText());
					int customerPrice = Integer.parseInt(txtCustomerPrice.getText());
					int sellPrice = Integer.parseInt(txtSellPrice.getText());
					int supplyPrice = Integer.parseInt(txtSupplyPrice.getText());
					int salePercent = Integer.parseInt(txtSalePercent.getText());
					int stock85 = Integer.parseInt(txtStock85.getText());
					int stock90 = Integer.parseInt(txtStock90.getText());
					int stock95 = Integer.parseInt(txtStock95.getText());
					int stock100 = Integer.parseInt(txtStock100.getText());
					int stock105 = Integer.parseInt(txtStock105.getText());
					int stock110 = Integer.parseInt(txtStock110.getText());
					int stock115 = Integer.parseInt(txtStock115.getText());

					int stockTotal = stock85 + stock90 + stock95 + stock100 + stock105 + stock110 + stock115;

					Stock stock = new Stock(productNumber, txtProductName.getText(), txtProductColor.getText(),
							stockTotal, productSize, customerPrice, supplyPrice, stockTotal * supplyPrice, sellPrice,
							stockTotal * sellPrice, stock85, stock90, stock95, stock100, stock105, stock110, stock115,
							fileName, salePercent);
					int returnValue = moduleDAO.getStockRegistry(stock);
					if (returnValue != 0) {
						obsList1.clear();
						stockViewTotalLoadList();
						setDefaultImageView();
					}
				}
			});

			btnExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					addStage.close();
				}
			});

			btnImgView.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						FileChooser fileChooser = new FileChooser();
						fileChooser.getExtensionFilters()
								.addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
						// 선택된 이미지 파일을 파일형식으로 돌려준다.
						selectFile = fileChooser.showOpenDialog(addStage);
						selectFile.getName();
						if (selectFile != null) {
							// 사진의 실제경로가 문자열로 전환된다.
							String localURL = selectFile.toURI().toURL().toString();
							Image image = new Image(localURL, false);
							imgView1.setImage(image);
						}
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("이미지버튼 에러!");
						alert.setHeaderText("이미지버튼 점검요망");
						alert.setContentText("이미지파일만 가져오시기 바랍니다.");
						alert.showAndWait();
					}
				}
			});

			// ------------------------------/이벤트등록 및 핸들러 처리-------------------------------
			// 스테이지(주인스테이지)를 만든다. (*모달, 모달리스), 스테이지(씬)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			addStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			addStage.initModality(Modality.WINDOW_MODAL);
			addStage.initOwner(stage2);
			addStage.setScene(scene);
			addStage.setTitle("상품정보 등록");
			addStage.setResizable(false);
			addStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("등록창 에러!");
			alert.setHeaderText("점검요망");
			alert.setContentText("정신차리세요!");
			alert.showAndWait();
		}
	}

	// 삭제버튼 이벤트 및 핸들러함수
	private void handleBtnDelete1Action(ActionEvent event) {
		ModuleDAO moduleDAO = new ModuleDAO();
		// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다
		Stock stock = obsList1.get(stockViewSelectedIndex);
		int productNumber = stock.getSt_productNumber();
		int returnValue = moduleDAO.getStockDelete(productNumber);

		if (returnValue != 0) {
			obsList1.remove(stockViewSelectedIndex);
		}
	}

	// 사진을 저장할수 있는 폴더 만들기 ("c:/images")
	private void setDirectoryImageSaveImage() {
		directorySave = new File("C:/images");
		if (!directorySave.exists()) {
			directorySave.mkdir();
			System.out.println("C:/images 디렉토리 이미지 만들기 성공!");
		}

		directorySave2 = new File("C:/images");
		if (!directorySave2.exists()) {
			directorySave2.mkdir();
			System.out.println("C:/images 디렉토리 이미지 만들기 성공!");
		}
	}

	// 수정버튼 이벤트 및 핸들러함수
	private void handleBtnEditAction(ActionEvent event) {
		// edit.fxml 화면을 로드해야된다.
		try {
			// 화면내용->씬->스테이지(주인스테이지)->보여주면된다.을 가져왔어요.
			Parent root = FXMLLoader.load(getClass().getResource("/view/edit.fxml"));
			// scene(화면내용) 만든다.
			Scene scene = new Scene(root);
			Stage editStage = new Stage(StageStyle.DECORATED);
			// ------------------------------이벤트등록 및 핸들러 처리--------------------------------
			TextField txtProductNumber = (TextField) scene.lookup("#txtProductNumber");
			TextField txtProductName = (TextField) scene.lookup("#txtProductName");
			TextField txtProductColor = (TextField) scene.lookup("#txtProductColor");
			TextField txtProductSize = (TextField) scene.lookup("#txtProductSize");
			TextField txtCustomerPrice = (TextField) scene.lookup("#txtCustomerPrice");
			TextField txtSalePercent = (TextField) scene.lookup("#txtSalePercent");
			TextField txtSupplyPrice = (TextField) scene.lookup("#txtSupplyPrice");
			TextField txtSellPrice = (TextField) scene.lookup("#txtSellPrice");
			TextField txtStock85 = (TextField) scene.lookup("#txtStock85");
			TextField txtStock90 = (TextField) scene.lookup("#txtStock90");
			TextField txtStock95 = (TextField) scene.lookup("#txtStock95");
			TextField txtStock100 = (TextField) scene.lookup("#txtStock100");
			TextField txtStock105 = (TextField) scene.lookup("#txtStock105");
			TextField txtStock110 = (TextField) scene.lookup("#txtStock110");
			TextField txtStock115 = (TextField) scene.lookup("#txtStock115");
			ImageView imgView2 = (ImageView) scene.lookup("#imgView");
			Button btnImgView = (Button) scene.lookup("#btnImgView");
			Button btnEdit = (Button) scene.lookup("#btnEdit");
			Button btnExit = (Button) scene.lookup("#btnExit");

			Image image = new Image("/image/default.png", false);
			imgView2.setImage(image);
			
			Stock stock = obsList1.get(stockViewSelectedIndex);
			txtProductNumber.setText(String.valueOf(stock.getSt_productNumber()));
			txtProductName.setText(stock.getSt_productName());
			txtProductColor.setText(stock.getSt_productColor());
			txtProductSize.setText(String.valueOf(stock.getSt_productSize()));
			txtCustomerPrice.setText(String.valueOf(stock.getSt_customerPrice()));
			txtSalePercent.setText(String.valueOf(stock.getSt_salePercent()));
			txtSupplyPrice.setText(String.valueOf(stock.getSt_supplyPrice()));
			txtSellPrice.setText(String.valueOf(stock.getSt_sellPrice()));
			txtStock85.setText(String.valueOf(stock.getSt_stock85()));
			txtStock90.setText(String.valueOf(stock.getSt_stock90()));
			txtStock95.setText(String.valueOf(stock.getSt_stock95()));
			txtStock100.setText(String.valueOf(stock.getSt_stock100()));
			txtStock105.setText(String.valueOf(stock.getSt_stock105()));
			txtStock110.setText(String.valueOf(stock.getSt_stock110()));
			txtStock115.setText(String.valueOf(stock.getSt_stock115()));

			txtProductNumber.setDisable(true);
			txtProductName.setDisable(true);
			txtSalePercent.setDisable(true);

			btnEdit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ModuleDAO moduleDAO = new ModuleDAO();
					// 이미지저장처리순서 1. 이미지파일명을 생성해서 복사해서 해당디렉토리에 저장한다.
					// 1. 이미지파일명을 만들어야한다.
					if (selectFile2 == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("사진선택없음!");
						alert.setHeaderText("사진가져오기 문제발생");
						alert.setContentText("이미지파일만 선택하세요!");
						alert.showAndWait();
						return;
					}
					// 2. 실제파이을 가져와서 새로만든 이미지 파일명에 저장한다.
					BufferedInputStream bis = null; // 파일을 읽을때 사용하는 클래스
					BufferedOutputStream bos = null; // 파일을 쓸때 사용하는 클래스
					String fileName = null;
					try {
						// "stu1238452179홍길동.jpg"
						fileName = "stock" + System.currentTimeMillis() + selectFile2.getName();

						// 이미지파일은 바이트스트림으로 바꾸어서 버퍼를 활용해서 읽는다.
						// C:/images/stu1238452179홍길동.jpg
						bis = new BufferedInputStream(new FileInputStream(selectFile2));
						bos = new BufferedOutputStream(
								new FileOutputStream(directorySave2.getAbsolutePath() + "\\" + fileName));
						// 이미지파일을 읽어서 저장위치에 있는 파일에다가 쓴다.
						// -1 : 더이상 읽을값이 없다는 의미이다.
						int data = -1;
						while ((data = bis.read()) != -1) {
							bos.write(data);
							bos.flush(); // 버퍼에 있는값을 다 저장하기위해서 보내라.
						}
					} catch (Exception e) {
						System.out.println("파일복사에러" + e.getMessage());
						return;
					} finally {
						try {
							if (bis != null)
								bis.close();
							if (bos != null)
								bis.close();
						} catch (IOException e) {
							System.out.println("bis.close(), bos.close() error!" + e.getMessage());
						}
					}

					Stock sto = obsList1.get(stockViewSelectedIndex);

					int customerPrice = Integer.parseInt(txtCustomerPrice.getText());
					int productSize = Integer.parseInt(txtProductSize.getText());
					int supplyPrice = Integer.parseInt(txtSupplyPrice.getText());
					int sellPrice = Integer.parseInt(txtSellPrice.getText());
					int stock85 = Integer.parseInt(txtStock85.getText());
					int stock90 = Integer.parseInt(txtStock90.getText());
					int stock95 = Integer.parseInt(txtStock95.getText());
					int stock100 = Integer.parseInt(txtStock100.getText());
					int stock105 = Integer.parseInt(txtStock105.getText());
					int stock110 = Integer.parseInt(txtStock110.getText());
					int stock115 = Integer.parseInt(txtStock115.getText());

					int stockTotal = stock85 + stock90 + stock95 + stock100 + stock105 + stock110 + stock115;
					int supplyPriceTotal = stockTotal * supplyPrice;
					int sellPriceTotal = stockTotal * sellPrice;

					sto.setSt_productColor(txtProductColor.getText());
					sto.setSt_productStock(stockTotal);
					sto.setSt_productSize(productSize);
					sto.setSt_customerPrice(customerPrice);
					sto.setSt_supplyPrice(supplyPrice);
					sto.setSt_supplyPriceTotal(supplyPriceTotal);
					sto.setSt_sellPrice(sellPrice);
					sto.setSt_sellPriceTotal(sellPriceTotal);
					sto.setSt_stock85(stock85);
					sto.setSt_stock90(stock90);
					sto.setSt_stock95(stock95);
					sto.setSt_stock100(stock100);
					sto.setSt_stock105(stock105);
					sto.setSt_stock110(stock110);
					sto.setSt_stock115(stock115);
					sto.setSt_image(fileName);

					int returnValue = moduleDAO.getStockUpdate(sto);
					if (returnValue != 0) {
						// 테이블뷰 obsList 해당된 위치에 수정된 객체값을 입력한다.
						obsList1.set(stockViewSelectedIndex, sto);
					}
				}
			});

			btnExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					editStage.close();
				}
			});

			btnImgView.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						FileChooser fileChooser = new FileChooser();
						fileChooser.getExtensionFilters()
								.addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
						// 선택된 이미지 파일을 파일형식으로 돌려준다.
						selectFile2 = fileChooser.showOpenDialog(editStage);
						selectFile2.getName();
						if (selectFile2 != null) {
							// 사진의 실제경로가 문자열로 전환된다.
							String localURL = selectFile2.toURI().toURL().toString();
							Image image = new Image(localURL, false);
							imgView2.setImage(image);
						}
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("이미지버튼 에러!");
						alert.setHeaderText("이미지버튼 점검요망");
						alert.setContentText("이미지파일만 가져오시기 바랍니다.");
						alert.showAndWait();
					}
				}
			});

			// ------------------------------/이벤트등록 및 핸들러 처리-------------------------------
			// 스테이지(주인스테이지)를 만든다. (*모달, 모달리스), 스테이지(씬)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			editStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(stage2);
			editStage.setScene(scene);
			editStage.setTitle("상품정보 수정");
			editStage.setResizable(false);
			editStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("수정창 에러!");
			alert.setHeaderText("점검요망");
			alert.setContentText("정신차리세요!");
			alert.showAndWait();
		}
	}

	// ---------------------------------------/재고현황-------------------------------------------
	// ---------------------------------------매출현황-----------------------------------------------
	// 찾기2버튼 이벤트 및 핸들러함수
	private void handleBtnSearch2Action(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			if (datePicker1.getValue().toString().trim().equals("")) {
				throw new Exception();
			}
			if (datePicker2.getValue().toString().trim().equals("")) {
				throw new Exception();
			}
			ArrayList<SellPeriod> arrayList = moduleDAO.getSellView1Find(datePicker1.getValue().toString(),
					datePicker2.getValue().toString());
			if (arrayList.size() != 0) {
				obsList3.clear();
				for (int i = 0; i < arrayList.size(); i++) {
					SellPeriod s = arrayList.get(i);
					obsList3.add(s);
				}
			}
		} catch (Exception e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("찾기2입력문제발생!");
			alert.setHeaderText("sellPeriod 객체 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 셀뷰1 선택하면 변경 이벤트
	private void handleSellView1Action(MouseEvent event) {
		sellView1SelectedIndex = sellView1.getSelectionModel().getSelectedIndex();
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			if (obsList3.get(sellView1SelectedIndex).getSe_productName().trim().equals("")) {
				throw new Exception();
			}
			ArrayList<SellDetail> arrayList = moduleDAO
					.getSellView2Find(obsList3.get(sellView1SelectedIndex).getSe_productName().trim());
			if (arrayList.size() != 0) {
				obsList4.clear();
				for (int i = 0; i < arrayList.size(); i++) {
					SellDetail s = arrayList.get(i);
					obsList4.add(s);
				}
			}
		} catch (Exception e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("셀뷰1선택문제발생!");
			alert.setHeaderText("sellDetail 객체 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}
	
	// 셀뷰2 선택하면 변경 이벤트
	private void handleSellView2Action(MouseEvent event) {
		sellView2SelectedIndex = sellView2.getSelectionModel().getSelectedIndex();
	}

	// 리스트2버튼 이벤트 및 핸들러함수
	private void handleBtnList2Action(ActionEvent event) {
		obsList3.clear();
		sellView1TotalLoadList();
	}

	// 바차트버튼 이벤트 및 핸들러함수
	private void handleBarChartAction(ActionEvent event) {
		// 내용 -> 신 -> 스테이지(스타일, 모달, 주인스테이지, 사이즈크기변경) -> 보여준다.
		try {
			if (obsList4.size() == 0)
				throw new Exception();
			Parent root = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));
			Scene scene = new Scene(root);
			Stage barChartStage = new Stage(StageStyle.UTILITY);
			// +++++++++++++++++++++++이벤트등록 및 핸들러처리+++++++++++++++++++
			BarChart barChart = (BarChart) scene.lookup("#barChart");
			Button btnClose = (Button) scene.lookup("#btnClose");

			// 1. XYChart 시리즈를 만든다. (소비자가)
			XYChart.Series seriesCustomerPrice = new XYChart.Series();
			seriesCustomerPrice.setName("소비자가");
			ObservableList customerPriceList = FXCollections.observableArrayList();
			for (int i = 0; i < obsList4.size(); i++) {
				SellDetail sellDetail = obsList4.get(i);
				customerPriceList.add(new XYChart.Data(sellDetail.getIj_se_productName(), sellDetail.getIj_st_customerPrice()));
			}
			seriesCustomerPrice.setData(customerPriceList);
			barChart.getData().add(seriesCustomerPrice);
			
			// 1. XYChart 시리즈를 만든다. (공급가)
			XYChart.Series seriesSupplyPrice = new XYChart.Series();
			seriesSupplyPrice.setName("공급가");
			ObservableList supplyPriceList = FXCollections.observableArrayList();
			for (int i = 0; i < obsList4.size(); i++) {
				SellDetail sellDetail = obsList4.get(i);
				supplyPriceList.add(new XYChart.Data(sellDetail.getIj_se_productName(), sellDetail.getIj_st_supplyPrice()));
			}
			seriesSupplyPrice.setData(supplyPriceList);
			barChart.getData().add(seriesSupplyPrice);

			// 1. XYChart 시리즈를 만든다. (판매가)
			XYChart.Series seriesSellPrice = new XYChart.Series();
			seriesSellPrice.setName("판매가");
			ObservableList sellPriceList = FXCollections.observableArrayList();
			for (int i = 0; i < obsList4.size(); i++) {
				SellDetail sellDetail = obsList4.get(i);
				sellPriceList.add(new XYChart.Data(sellDetail.getIj_se_productName(), sellDetail.getIj_se_sellPrice()));
			}
			seriesSellPrice.setData(sellPriceList);
			barChart.getData().add(seriesSellPrice);
			
			btnClose.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					barChartStage.close();
				}
			});

			// +++++++++++++++++++++++/이벤트등록 및 핸들러처리+++++++++++++++++++
			barChartStage.initModality(Modality.WINDOW_MODAL);
			barChartStage.initOwner(stage2);
			barChartStage.setScene(scene);
			barChartStage.setResizable(false);
			barChartStage.setTitle("판매정보막대그래프");
			barChartStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("테이블뷰 리스트 입력요망!");
			alert.setHeaderText("데이타리스트를 입력하시오.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 파이차트버튼 이벤트 및 핸들러함수
	private void handlePieChartAction(ActionEvent event) {
		// 스테이지(스타일, 모달, 사이즈고정, 주스테이지) -> 신 -> 화면내용
		try {
			if (sellView2.getSelectionModel().isEmpty())
				throw new Exception();
			Parent root = FXMLLoader.load(getClass().getResource("/view/piechart.fxml"));
			Scene scene = new Scene(root);
			Stage pieChartStage = new Stage(StageStyle.UTILITY);
			// +++++++++++++++++++++++이벤트등록 및 핸들러처리+++++++++++++++++++
			PieChart pieChart = (PieChart) scene.lookup("#pieChart");
			Label lblText = (Label) scene.lookup("#lblText");
			Button btnClose = (Button) scene.lookup("#btnClose");

			SellDetail sellDetail = obsList4.get(sellView2SelectedIndex);
			
			lblText.setText(sellDetail.getIj_se_productName());
			
			// 파이차트에 입력할 내용을 observable list 입력한다.
			ObservableList pieChartList = FXCollections.observableArrayList();
			pieChartList.add(new PieChart.Data("소비자가", sellDetail.getIj_st_customerPrice()));
			pieChartList.add(new PieChart.Data("공급가", sellDetail.getIj_st_supplyPrice()));
			pieChartList.add(new PieChart.Data("판매가", sellDetail.getIj_se_sellPrice()));

			pieChart.setData(pieChartList);

			btnClose.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					pieChartStage.close();
				}
			});
			// +++++++++++++++++++++++/이벤트등록 및 핸들러처리+++++++++++++++++++
			pieChartStage.initModality(Modality.WINDOW_MODAL);
			pieChartStage.initOwner(stage2);
			pieChartStage.setScene(scene);
			pieChartStage.setResizable(false);
			pieChartStage.setTitle("가격 파이그래프");
			pieChartStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("테이블뷰 리스트 입력요망!");
			alert.setHeaderText("데이타리스트를 입력하시오.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// ----------------------------------------/매출현황-----------------------------------------------
	// ----------------------------------------고객관리-----------------------------------------------
	// 찾기3버튼 이벤트 및 핸들러함수
	private void handleBtnSearch3Action(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			if (txtSearch2.getText().trim().equals("")) {
				throw new Exception();
			}
			ArrayList<Customer> arrayList = moduleDAO.getCustomerFind(txtSearch2.getText().trim());

			if (arrayList.size() != 0) {
				obsList2.clear();
				for (int i = 0; i < arrayList.size(); i++) {
					Customer c = arrayList.get(i);
					obsList2.add(c);
				}
			}
		} catch (Exception e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("찾기2입력문제발생!");
			alert.setHeaderText("customer 객체 점검을 해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 삭제2버튼 이벤트 및 핸들러함수
	private void handleBtnDelete2Action(ActionEvent event) {
		ModuleDAO moduleDAO = new ModuleDAO();
		// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다
		Customer customer = obsList2.get(customerViewSelectedIndex);
		int cid = customer.getCid();
		int returnValue = moduleDAO.getCustomerDelete(cid);

		if (returnValue != 0) {
			obsList2.remove(customerViewSelectedIndex);
		}
	}

	// 커스토머뷰 선택하면 변경 이벤트
	private void handleCustomerViewAction(MouseEvent event) {
		customerViewSelectedIndex = customerView.getSelectionModel().getSelectedIndex();
	}

	// 커스토머뷰 더블클릭하면 변경 이벤트
	private void handleCustomerViewAction2(MouseEvent event) {
		if (event.getClickCount() != 2)
			return;
		Customer customer = obsList2.get(customerViewSelectedIndex);
		txtNumber.setText(String.valueOf(customer.getCid()));
		txtName.setText(customer.getCt_customerName());
		switch (customer.getCt_gender()) {
		case "남성":
			rdoMale.setSelected(true);
			break;
		case "여성":
			rdoFemale.setSelected(true);
			break;
		}
		String[] array = customer.getCt_birthday().split("-");
		int number1 = Integer.parseInt(array[0]);
		int number2 = Integer.parseInt(array[1]);
		int number3 = Integer.parseInt(array[2]);
		datePicker3.setValue(LocalDate.of(number1, number2, number3));
		txtPhoneNumber.setText(customer.getCt_phoneNumber());
		txtUserPoint.setText(String.valueOf(customer.getCt_userPoint()));
	}

	// 리스트3버튼 이벤트 및 핸들러함수
	private void handleBtnList3Action(ActionEvent event) {
		obsList2.clear();
		customerViewTotalLoadList();
	}

	// 고객정보등록버튼 이벤트 및 핸들러함수
	private void handleBtnCustomerAddAction(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();

			int customerNumber = Integer.parseInt(txtNumber.getText());
			int userPoint = Integer.parseInt(txtUserPoint.getText());

			Customer customer = new Customer(customerNumber, txtName.getText(),
					((RadioButton) group.getSelectedToggle()).getText(), datePicker3.getValue().toString(),
					txtPhoneNumber.getText(), userPoint);
			int returnValue = moduleDAO.getCustomerRegistry(customer);
			if (returnValue != 0) {
				obsList2.clear();

				customerViewTotalLoadList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객정보등록문제발생!");
			alert.setHeaderText("데이터를 입력해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 고객정보수정버튼 이벤트 및 핸들러함수
	private void handleBtnCustomerEditAction(ActionEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();

			Customer cus = obsList2.get(customerViewSelectedIndex);

			int customerNumber = Integer.parseInt(txtNumber.getText());
			int userPoint = Integer.parseInt(txtUserPoint.getText());

			cus.setCid(customerNumber);
			cus.setCt_customerName(txtName.getText());
			cus.setCt_gender(((RadioButton) group.getSelectedToggle()).getText());
			cus.setCt_birthday(datePicker3.getValue().toString());
			cus.setCt_phoneNumber(txtPhoneNumber.getText());
			cus.setCt_userPoint(userPoint);

			int returnValue = moduleDAO.getCustomerUpdate(cus);
			if (returnValue != 0) {
				// 테이블뷰 obsList 해당된 위치에 수정된 객체값을 입력한다.
				obsList2.set(customerViewSelectedIndex, cus);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객정보수정문제발생!");
			alert.setHeaderText("데이터를 입력해주세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 성별라디오버튼 그룹 초기화 처리
	private void radioButtonGenderInitialize() {
		group = new ToggleGroup();
		rdoMale.setToggleGroup(group);
		rdoFemale.setToggleGroup(group);
		rdoMale.setSelected(true);
	}

	// 고객정보초기화버튼 이벤트 및 핸들러함수
	private void handleBtnCustomerClearAction(ActionEvent event) {
		txtNumber.clear();
		txtName.clear();
		rdoMale.setSelected(true);
		datePicker3.setValue(null);
		txtPhoneNumber.clear();
		txtUserPoint.clear();
	}
	// ---------------------------------------/고객관리-------------------------------------------

}
