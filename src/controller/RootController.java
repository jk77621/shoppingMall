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
		// ��Ź�� �÷��ʱ�ȭ����
		stockViewColumnInitialize();
		// ����1 �÷��ʱ�ȭ����
		sellView1ColumnInitialize();
		// ����2 �÷��ʱ�ȭ����
		sellView2ColumnInitialize();
		// Ŀ����Ӻ� �÷��ʱ�ȭ����
		customerViewColumnInitialize();

		// ��Ź�� ��ü����
		stockViewTotalLoadList();
		// ����1 ��ü����
		sellView1TotalLoadList();
		// ����2 ��ü����
		sellView2TotalLoadList();
		// Ŀ����Ӻ� ��ü����
		customerViewTotalLoadList();

		// ��ǰ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnProductAdd.setOnAction(event -> handleBtnProductAddAction(event));
		// �������׹�ư �̺�Ʈ �� �ڵ鷯�Լ�		
		btnNotice.setOnAction(event -> handleBtnNoticeAction(event));
		// �������ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnExit.setOnAction(event -> Platform.exit());
		// ---------------------------------------�����Ȳ-------------------------------------------
		txtField.setDisable(true); // �ؽ�Ʈ �ʵ� ��Ȱ��ȭ

		// ã��1��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnSearch1.setOnAction(event -> handleBtnSearchAction(event));
		// ����Ʈ1��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnList.setOnAction(event -> handleBtnListAction(event));
		// �⺻���� �̹��� ���� �����ϱ�
		setDefaultImageView();
		// ��Ź�� �����ϸ� �̹��� ���� �̺�Ʈ
		stockView.setOnMouseClicked(event -> handleStockViewAction(event));
		// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnAdd.setOnAction(event -> handelBtnAddAction(event));
		// ����1��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnDelete1.setOnAction(event -> handleBtnDelete1Action(event));
		// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnEdit.setOnAction(event -> handleBtnEditAction(event));
		// ������ �����Ҽ� �ִ� ���� �����
		setDirectoryImageSaveImage();
		// ---------------------------------------/�����Ȳ-------------------------------------------
		// ---------------------------------------������Ȳ-------------------------------------------
		// ã��2��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnSearch2.setOnAction(event -> handleBtnSearch2Action(event));
		// ����1 �����ϸ� ���� �̺�Ʈ
		sellView1.setOnMouseClicked(event -> handleSellView1Action(event));
		// ����2 �����ϸ� ���� �̺�Ʈ
		sellView2.setOnMouseClicked(event -> handleSellView2Action(event));
		// ����Ʈ2��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnList2.setOnAction(event -> handleBtnList2Action(event));
		// ����Ʈ��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnBarChart.setOnAction(event -> handleBarChartAction(event));
		// ������Ʈ��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnPieChart.setOnAction(event -> handlePieChartAction(event));

		// ---------------------------------------/������Ȳ-------------------------------------------
		// ---------------------------------------������-------------------------------------------
		// ã��3��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnSearch3.setOnAction(event -> handleBtnSearch3Action(event));
		// ����2��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnDelete2.setOnAction(event -> handleBtnDelete2Action(event));
		// Ŀ����Ӻ� �����ϸ� ���� �̺�Ʈ
		customerView.setOnMousePressed(event -> handleCustomerViewAction(event));
		// Ŀ����Ӻ� ����Ŭ���ϸ� ���� �̺�Ʈ
		customerView.setOnMouseClicked(event -> handleCustomerViewAction2(event));
		// ����Ʈ3��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnList3.setOnAction(event -> handleBtnList3Action(event));
		// ��������Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnCustomerAdd.setOnAction(event -> handleBtnCustomerAddAction(event));
		// ������������ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnCustomerEdit.setOnAction(event -> handleBtnCustomerEditAction(event));
		// �������ʱ�ȭ��ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnCustomerClear.setOnAction(event -> handleBtnCustomerClearAction(event));
		// ����������ư �׷� �ʱ�ȭ ó��
		radioButtonGenderInitialize();
		// ---------------------------------------/������-------------------------------------------
	}



	// ��Ź�� ��ü����
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

	// ����1 ��ü����
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

	// ����2 ��ü����
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

	// Ŀ����Ӻ� ��ü����
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

	// ��Ź�� �÷��ʱ�ȭ����
	private void stockViewColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("��ǰ��ȣ");
		colProductNumber.setMaxWidth(80);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("st_productNumber"));

		TableColumn colProductName = new TableColumn("��ǰ��");
		colProductName.setMaxWidth(100);
		colProductName.setCellValueFactory(new PropertyValueFactory("st_productName"));

		TableColumn colProductColor = new TableColumn("����");
		colProductColor.setMaxWidth(80);
		colProductColor.setCellValueFactory(new PropertyValueFactory("st_productColor"));

		TableColumn colProductStock = new TableColumn("���");
		colProductStock.setMaxWidth(40);
		colProductStock.setCellValueFactory(new PropertyValueFactory("st_productStock"));

		TableColumn colCustomerPrice = new TableColumn("�Һ��ڰ�");
		colCustomerPrice.setMaxWidth(100);
		colCustomerPrice.setCellValueFactory(new PropertyValueFactory("st_customerPrice"));

		TableColumn colSupplyPrice = new TableColumn("���ް�");
		colSupplyPrice.setMaxWidth(100);
		colSupplyPrice.setCellValueFactory(new PropertyValueFactory("st_supplyPrice"));

		TableColumn colSupplyPriceTotal = new TableColumn("���ް�");
		colSupplyPriceTotal.setMaxWidth(100);
		colSupplyPriceTotal.setCellValueFactory(new PropertyValueFactory("st_supplyPriceTotal"));

		TableColumn colSellPrice = new TableColumn("�ǸŰ�");
		colSellPrice.setMaxWidth(100);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("st_sellPrice"));

		TableColumn colSellPriceTotal = new TableColumn("�ǸŰ�");
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

	// ����1 �÷��ʱ�ȭ����
	private void sellView1ColumnInitialize() {
		TableColumn colSellPeriod = new TableColumn("�Ǹ� ����");
		colSellPeriod.setPrefWidth(150);
		colSellPeriod.setCellValueFactory(new PropertyValueFactory("se_sellPeriod"));

		TableColumn colProductName = new TableColumn("��ǰ��");
		colProductName.setPrefWidth(150);
		colProductName.setCellValueFactory(new PropertyValueFactory("se_productName"));

		TableColumn colProductStock = new TableColumn("����");
		colProductStock.setPrefWidth(100);
		colProductStock.setCellValueFactory(new PropertyValueFactory("se_productStock"));

		TableColumn colSellPrice = new TableColumn("�Ǹ� �ݾ�");
		colSellPrice.setPrefWidth(150);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("se_sellPrice"));

		TableColumn colCustomerName = new TableColumn("����");
		colCustomerName.setPrefWidth(100);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("se_customerName"));

		TableColumn colPhoneNumber = new TableColumn("�ڵ��� ��ȣ");
		colPhoneNumber.setPrefWidth(150);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory("se_phoneNumber"));

		TableColumn colUserPoint = new TableColumn("���� ����Ʈ");
		colUserPoint.setPrefWidth(100);
		colUserPoint.setCellValueFactory(new PropertyValueFactory("se_userPoint"));

		sellView1.getColumns().addAll(colSellPeriod, colProductName, colProductStock, colSellPrice, colCustomerName,
				colPhoneNumber, colUserPoint);

		sellView1.setItems(obsList3);
	}

	// ����2 �÷��ʱ�ȭ����
	private void sellView2ColumnInitialize() {
		TableColumn colProductNumber = new TableColumn("��ǰ ��ȣ");
		colProductNumber.setPrefWidth(150);
		colProductNumber.setCellValueFactory(new PropertyValueFactory("ij_st_productNumber"));

		TableColumn colProductName = new TableColumn("��ǰ��");
		colProductName.setPrefWidth(150);
		colProductName.setCellValueFactory(new PropertyValueFactory("ij_st_productName"));

		TableColumn colProductColor = new TableColumn("�÷�");
		colProductColor.setPrefWidth(100);
		colProductColor.setCellValueFactory(new PropertyValueFactory("ij_st_productColor"));

		TableColumn colProductStock = new TableColumn("����");
		colProductStock.setPrefWidth(100);
		colProductStock.setCellValueFactory(new PropertyValueFactory("ij_st_productStock"));

		TableColumn colProductSize = new TableColumn("������");
		colProductSize.setPrefWidth(100);
		colProductSize.setCellValueFactory(new PropertyValueFactory("ij_st_productSize"));

		TableColumn colCustomerPrice = new TableColumn("�Һ��ڰ�");
		colCustomerPrice.setPrefWidth(150);
		colCustomerPrice.setCellValueFactory(new PropertyValueFactory("ij_st_customerPrice"));

		TableColumn colSellPrice = new TableColumn("�ǸŰ�");
		colSellPrice.setPrefWidth(150);
		colSellPrice.setCellValueFactory(new PropertyValueFactory("ij_st_sellPrice"));

		TableColumn colSellPriceTotal = new TableColumn("�Ǹ��Ѿ�");
		colSellPriceTotal.setPrefWidth(150);
		colSellPriceTotal.setCellValueFactory(new PropertyValueFactory("ij_st_sellPriceTotal"));

		TableColumn colSalePercent = new TableColumn("������");
		colSalePercent.setPrefWidth(100);
		colSalePercent.setCellValueFactory(new PropertyValueFactory("ij_st_salePercent"));

		sellView2.getColumns().addAll(colProductNumber, colProductName, colProductColor, colProductStock,
				colProductSize, colCustomerPrice, colSellPrice, colSellPriceTotal, colSalePercent);

		sellView2.setItems(obsList4);
	}

	// Ŀ����Ӻ� �÷��ʱ�ȭ����
	private void customerViewColumnInitialize() {
		TableColumn colCustomerNumber = new TableColumn("�� ��ȣ");
		colCustomerNumber.setMaxWidth(100);
		colCustomerNumber.setCellValueFactory(new PropertyValueFactory("cid"));
		colCustomerNumber.setStyle("-Fx-alignment: CENTER");

		TableColumn colCustomerName = new TableColumn("����");
		colCustomerName.setMaxWidth(100);
		colCustomerName.setCellValueFactory(new PropertyValueFactory("ct_customerName"));

		TableColumn colGender = new TableColumn("����");
		colGender.setMaxWidth(100);
		colGender.setCellValueFactory(new PropertyValueFactory("ct_gender"));

		TableColumn colBirthday = new TableColumn("�������");
		colBirthday.setPrefWidth(150);
		colBirthday.setCellValueFactory(new PropertyValueFactory("ct_birthday"));

		TableColumn colPhoneNumber = new TableColumn("�ڵ��� ��ȣ");
		colPhoneNumber.setPrefWidth(150);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory("ct_phoneNumber"));

		TableColumn colUserPoint = new TableColumn("���� ����Ʈ");
		colUserPoint.setPrefWidth(100);
		colUserPoint.setCellValueFactory(new PropertyValueFactory("ct_userPoint"));

		customerView.getColumns().addAll(colCustomerNumber, colCustomerName, colGender, colBirthday, colPhoneNumber,
				colUserPoint);

		customerView.setItems(obsList2);
	}

	// ��ǰ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			productAddStage.setTitle("���θ� ������ ���α׷�(�Ǹŵ��)");
			productAddStage.setScene(scene);
			productAddStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�Ǹŵ��â�����߻�!");
			alert.setHeaderText("RootController ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// �������׹�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			noticeStage.setTitle("���θ� ������ ���α׷�(��������)");
			noticeStage.setScene(scene);
			noticeStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��������â�����߻�!");
			alert.setHeaderText("RootController ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}		
	}
	// ---------------------------------------�����Ȳ-------------------------------------------------
	// ã��1��ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ã��1�Է¹����߻�!");
			alert.setHeaderText("stock ��ü ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ����Ʈ1��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnListAction(ActionEvent event) {
		obsList1.clear();
		stockViewTotalLoadList();
	}

	// �⺻���� �̹��� ���� �����ϱ�
	private void setDefaultImageView() {
		Image image = new Image("/image/default.png", false);
		imgView.setImage(image);		
	}

	// ��Ź�� �����ϸ� �̹��� ���� �̺�Ʈ
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
			alert.setTitle("���̺�伱�ù����߻�!");
			alert.setHeaderText("�����͸� �������ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handelBtnAddAction(ActionEvent event) {
		// edit.fxml ȭ���� �ε��ؾߵȴ�.
		try {
			// ȭ�鳻��->��->��������(���ν�������)->�����ָ�ȴ�.�� �����Ծ��.
			Parent root = FXMLLoader.load(getClass().getResource("/view/add.fxml"));
			// scene(ȭ�鳻��) �����.
			Scene scene = new Scene(root);
			Stage addStage = new Stage(StageStyle.DECORATED);
			// ------------------------------�̺�Ʈ��� �� �ڵ鷯 ó��--------------------------------
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
					// �̹�������ó������ 1. �̹������ϸ��� �����ؼ� �����ؼ� �ش���丮�� �����Ѵ�.
					// 1. �̹������ϸ��� �������Ѵ�.
					if (selectFile == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("�������þ���!");
						alert.setHeaderText("������������ �����߻�");
						alert.setContentText("�̹������ϸ� �����ϼ���!");
						alert.showAndWait();
						return;
					}
					// 2. ���������� �����ͼ� ���θ��� �̹��� ���ϸ� �����Ѵ�.
					BufferedInputStream bis = null; // ������ ������ ����ϴ� Ŭ����
					BufferedOutputStream bos = null; // ������ ���� ����ϴ� Ŭ����
					String fileName = null;
					try {
						// "stu1238452179ȫ�浿.jpg"
						fileName = "stock" + System.currentTimeMillis() + selectFile.getName();

						// �̹��������� ����Ʈ��Ʈ������ �ٲپ ���۸� Ȱ���ؼ� �д´�.
						// C:/images/stu1238452179ȫ�浿.jpg
						bis = new BufferedInputStream(new FileInputStream(selectFile));
						bos = new BufferedOutputStream(
								new FileOutputStream(directorySave.getAbsolutePath() + "\\" + fileName));
						// �̹��������� �о ������ġ�� �ִ� ���Ͽ��ٰ� ����.
						// -1 : ���̻� �������� ���ٴ� �ǹ��̴�.
						int data = -1;
						while ((data = bis.read()) != -1) {
							bos.write(data);
							bos.flush(); // ���ۿ� �ִ°��� �� �����ϱ����ؼ� ������.
						}
					} catch (Exception e) {
						System.out.println("���Ϻ��翡��" + e.getMessage());
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
						// ���õ� �̹��� ������ ������������ �����ش�.
						selectFile = fileChooser.showOpenDialog(addStage);
						selectFile.getName();
						if (selectFile != null) {
							// ������ ������ΰ� ���ڿ��� ��ȯ�ȴ�.
							String localURL = selectFile.toURI().toURL().toString();
							Image image = new Image(localURL, false);
							imgView1.setImage(image);
						}
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("�̹�����ư ����!");
						alert.setHeaderText("�̹�����ư ���˿��");
						alert.setContentText("�̹������ϸ� �������ñ� �ٶ��ϴ�.");
						alert.showAndWait();
					}
				}
			});

			// ------------------------------/�̺�Ʈ��� �� �ڵ鷯 ó��-------------------------------
			// ��������(���ν�������)�� �����. (*���, ��޸���), ��������(��)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			addStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			addStage.initModality(Modality.WINDOW_MODAL);
			addStage.initOwner(stage2);
			addStage.setScene(scene);
			addStage.setTitle("��ǰ���� ���");
			addStage.setResizable(false);
			addStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���â ����!");
			alert.setHeaderText("���˿��");
			alert.setContentText("������������!");
			alert.showAndWait();
		}
	}

	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnDelete1Action(ActionEvent event) {
		ModuleDAO moduleDAO = new ModuleDAO();
		// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�
		Stock stock = obsList1.get(stockViewSelectedIndex);
		int productNumber = stock.getSt_productNumber();
		int returnValue = moduleDAO.getStockDelete(productNumber);

		if (returnValue != 0) {
			obsList1.remove(stockViewSelectedIndex);
		}
	}

	// ������ �����Ҽ� �ִ� ���� ����� ("c:/images")
	private void setDirectoryImageSaveImage() {
		directorySave = new File("C:/images");
		if (!directorySave.exists()) {
			directorySave.mkdir();
			System.out.println("C:/images ���丮 �̹��� ����� ����!");
		}

		directorySave2 = new File("C:/images");
		if (!directorySave2.exists()) {
			directorySave2.mkdir();
			System.out.println("C:/images ���丮 �̹��� ����� ����!");
		}
	}

	// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnEditAction(ActionEvent event) {
		// edit.fxml ȭ���� �ε��ؾߵȴ�.
		try {
			// ȭ�鳻��->��->��������(���ν�������)->�����ָ�ȴ�.�� �����Ծ��.
			Parent root = FXMLLoader.load(getClass().getResource("/view/edit.fxml"));
			// scene(ȭ�鳻��) �����.
			Scene scene = new Scene(root);
			Stage editStage = new Stage(StageStyle.DECORATED);
			// ------------------------------�̺�Ʈ��� �� �ڵ鷯 ó��--------------------------------
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
					// �̹�������ó������ 1. �̹������ϸ��� �����ؼ� �����ؼ� �ش���丮�� �����Ѵ�.
					// 1. �̹������ϸ��� �������Ѵ�.
					if (selectFile2 == null) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("�������þ���!");
						alert.setHeaderText("������������ �����߻�");
						alert.setContentText("�̹������ϸ� �����ϼ���!");
						alert.showAndWait();
						return;
					}
					// 2. ���������� �����ͼ� ���θ��� �̹��� ���ϸ� �����Ѵ�.
					BufferedInputStream bis = null; // ������ ������ ����ϴ� Ŭ����
					BufferedOutputStream bos = null; // ������ ���� ����ϴ� Ŭ����
					String fileName = null;
					try {
						// "stu1238452179ȫ�浿.jpg"
						fileName = "stock" + System.currentTimeMillis() + selectFile2.getName();

						// �̹��������� ����Ʈ��Ʈ������ �ٲپ ���۸� Ȱ���ؼ� �д´�.
						// C:/images/stu1238452179ȫ�浿.jpg
						bis = new BufferedInputStream(new FileInputStream(selectFile2));
						bos = new BufferedOutputStream(
								new FileOutputStream(directorySave2.getAbsolutePath() + "\\" + fileName));
						// �̹��������� �о ������ġ�� �ִ� ���Ͽ��ٰ� ����.
						// -1 : ���̻� �������� ���ٴ� �ǹ��̴�.
						int data = -1;
						while ((data = bis.read()) != -1) {
							bos.write(data);
							bos.flush(); // ���ۿ� �ִ°��� �� �����ϱ����ؼ� ������.
						}
					} catch (Exception e) {
						System.out.println("���Ϻ��翡��" + e.getMessage());
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
						// ���̺�� obsList �ش�� ��ġ�� ������ ��ü���� �Է��Ѵ�.
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
						// ���õ� �̹��� ������ ������������ �����ش�.
						selectFile2 = fileChooser.showOpenDialog(editStage);
						selectFile2.getName();
						if (selectFile2 != null) {
							// ������ ������ΰ� ���ڿ��� ��ȯ�ȴ�.
							String localURL = selectFile2.toURI().toURL().toString();
							Image image = new Image(localURL, false);
							imgView2.setImage(image);
						}
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("�̹�����ư ����!");
						alert.setHeaderText("�̹�����ư ���˿��");
						alert.setContentText("�̹������ϸ� �������ñ� �ٶ��ϴ�.");
						alert.showAndWait();
					}
				}
			});

			// ------------------------------/�̺�Ʈ��� �� �ڵ鷯 ó��-------------------------------
			// ��������(���ν�������)�� �����. (*���, ��޸���), ��������(��)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			editStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(stage2);
			editStage.setScene(scene);
			editStage.setTitle("��ǰ���� ����");
			editStage.setResizable(false);
			editStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����â ����!");
			alert.setHeaderText("���˿��");
			alert.setContentText("������������!");
			alert.showAndWait();
		}
	}

	// ---------------------------------------/�����Ȳ-------------------------------------------
	// ---------------------------------------������Ȳ-----------------------------------------------
	// ã��2��ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ã��2�Է¹����߻�!");
			alert.setHeaderText("sellPeriod ��ü ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ����1 �����ϸ� ���� �̺�Ʈ
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
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����1���ù����߻�!");
			alert.setHeaderText("sellDetail ��ü ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}
	
	// ����2 �����ϸ� ���� �̺�Ʈ
	private void handleSellView2Action(MouseEvent event) {
		sellView2SelectedIndex = sellView2.getSelectionModel().getSelectedIndex();
	}

	// ����Ʈ2��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnList2Action(ActionEvent event) {
		obsList3.clear();
		sellView1TotalLoadList();
	}

	// ����Ʈ��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBarChartAction(ActionEvent event) {
		// ���� -> �� -> ��������(��Ÿ��, ���, ���ν�������, ������ũ�⺯��) -> �����ش�.
		try {
			if (obsList4.size() == 0)
				throw new Exception();
			Parent root = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));
			Scene scene = new Scene(root);
			Stage barChartStage = new Stage(StageStyle.UTILITY);
			// +++++++++++++++++++++++�̺�Ʈ��� �� �ڵ鷯ó��+++++++++++++++++++
			BarChart barChart = (BarChart) scene.lookup("#barChart");
			Button btnClose = (Button) scene.lookup("#btnClose");

			// 1. XYChart �ø�� �����. (�Һ��ڰ�)
			XYChart.Series seriesCustomerPrice = new XYChart.Series();
			seriesCustomerPrice.setName("�Һ��ڰ�");
			ObservableList customerPriceList = FXCollections.observableArrayList();
			for (int i = 0; i < obsList4.size(); i++) {
				SellDetail sellDetail = obsList4.get(i);
				customerPriceList.add(new XYChart.Data(sellDetail.getIj_se_productName(), sellDetail.getIj_st_customerPrice()));
			}
			seriesCustomerPrice.setData(customerPriceList);
			barChart.getData().add(seriesCustomerPrice);
			
			// 1. XYChart �ø�� �����. (���ް�)
			XYChart.Series seriesSupplyPrice = new XYChart.Series();
			seriesSupplyPrice.setName("���ް�");
			ObservableList supplyPriceList = FXCollections.observableArrayList();
			for (int i = 0; i < obsList4.size(); i++) {
				SellDetail sellDetail = obsList4.get(i);
				supplyPriceList.add(new XYChart.Data(sellDetail.getIj_se_productName(), sellDetail.getIj_st_supplyPrice()));
			}
			seriesSupplyPrice.setData(supplyPriceList);
			barChart.getData().add(seriesSupplyPrice);

			// 1. XYChart �ø�� �����. (�ǸŰ�)
			XYChart.Series seriesSellPrice = new XYChart.Series();
			seriesSellPrice.setName("�ǸŰ�");
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

			// +++++++++++++++++++++++/�̺�Ʈ��� �� �ڵ鷯ó��+++++++++++++++++++
			barChartStage.initModality(Modality.WINDOW_MODAL);
			barChartStage.initOwner(stage2);
			barChartStage.setScene(scene);
			barChartStage.setResizable(false);
			barChartStage.setTitle("�Ǹ���������׷���");
			barChartStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���̺�� ����Ʈ �Է¿��!");
			alert.setHeaderText("����Ÿ����Ʈ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ������Ʈ��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handlePieChartAction(ActionEvent event) {
		// ��������(��Ÿ��, ���, ���������, �ֽ�������) -> �� -> ȭ�鳻��
		try {
			if (sellView2.getSelectionModel().isEmpty())
				throw new Exception();
			Parent root = FXMLLoader.load(getClass().getResource("/view/piechart.fxml"));
			Scene scene = new Scene(root);
			Stage pieChartStage = new Stage(StageStyle.UTILITY);
			// +++++++++++++++++++++++�̺�Ʈ��� �� �ڵ鷯ó��+++++++++++++++++++
			PieChart pieChart = (PieChart) scene.lookup("#pieChart");
			Label lblText = (Label) scene.lookup("#lblText");
			Button btnClose = (Button) scene.lookup("#btnClose");

			SellDetail sellDetail = obsList4.get(sellView2SelectedIndex);
			
			lblText.setText(sellDetail.getIj_se_productName());
			
			// ������Ʈ�� �Է��� ������ observable list �Է��Ѵ�.
			ObservableList pieChartList = FXCollections.observableArrayList();
			pieChartList.add(new PieChart.Data("�Һ��ڰ�", sellDetail.getIj_st_customerPrice()));
			pieChartList.add(new PieChart.Data("���ް�", sellDetail.getIj_st_supplyPrice()));
			pieChartList.add(new PieChart.Data("�ǸŰ�", sellDetail.getIj_se_sellPrice()));

			pieChart.setData(pieChartList);

			btnClose.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					pieChartStage.close();
				}
			});
			// +++++++++++++++++++++++/�̺�Ʈ��� �� �ڵ鷯ó��+++++++++++++++++++
			pieChartStage.initModality(Modality.WINDOW_MODAL);
			pieChartStage.initOwner(stage2);
			pieChartStage.setScene(scene);
			pieChartStage.setResizable(false);
			pieChartStage.setTitle("���� ���̱׷���");
			pieChartStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���̺�� ����Ʈ �Է¿��!");
			alert.setHeaderText("����Ÿ����Ʈ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ----------------------------------------/������Ȳ-----------------------------------------------
	// ----------------------------------------������-----------------------------------------------
	// ã��3��ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ã��2�Է¹����߻�!");
			alert.setHeaderText("customer ��ü ������ ���ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ����2��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnDelete2Action(ActionEvent event) {
		ModuleDAO moduleDAO = new ModuleDAO();
		// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�
		Customer customer = obsList2.get(customerViewSelectedIndex);
		int cid = customer.getCid();
		int returnValue = moduleDAO.getCustomerDelete(cid);

		if (returnValue != 0) {
			obsList2.remove(customerViewSelectedIndex);
		}
	}

	// Ŀ����Ӻ� �����ϸ� ���� �̺�Ʈ
	private void handleCustomerViewAction(MouseEvent event) {
		customerViewSelectedIndex = customerView.getSelectionModel().getSelectedIndex();
	}

	// Ŀ����Ӻ� ����Ŭ���ϸ� ���� �̺�Ʈ
	private void handleCustomerViewAction2(MouseEvent event) {
		if (event.getClickCount() != 2)
			return;
		Customer customer = obsList2.get(customerViewSelectedIndex);
		txtNumber.setText(String.valueOf(customer.getCid()));
		txtName.setText(customer.getCt_customerName());
		switch (customer.getCt_gender()) {
		case "����":
			rdoMale.setSelected(true);
			break;
		case "����":
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

	// ����Ʈ3��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnList3Action(ActionEvent event) {
		obsList2.clear();
		customerViewTotalLoadList();
	}

	// ��������Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			alert.setTitle("��������Ϲ����߻�!");
			alert.setHeaderText("�����͸� �Է����ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ������������ư �̺�Ʈ �� �ڵ鷯�Լ�
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
				// ���̺�� obsList �ش�� ��ġ�� ������ ��ü���� �Է��Ѵ�.
				obsList2.set(customerViewSelectedIndex, cus);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���������������߻�!");
			alert.setHeaderText("�����͸� �Է����ּ���.");
			alert.setContentText("�������� �����ϼ���.");
			alert.showAndWait();
		}
	}

	// ����������ư �׷� �ʱ�ȭ ó��
	private void radioButtonGenderInitialize() {
		group = new ToggleGroup();
		rdoMale.setToggleGroup(group);
		rdoFemale.setToggleGroup(group);
		rdoMale.setSelected(true);
	}

	// �������ʱ�ȭ��ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnCustomerClearAction(ActionEvent event) {
		txtNumber.clear();
		txtName.clear();
		rdoMale.setSelected(true);
		datePicker3.setValue(null);
		txtPhoneNumber.clear();
		txtUserPoint.clear();
	}
	// ---------------------------------------/������-------------------------------------------

}
