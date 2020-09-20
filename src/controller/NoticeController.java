package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Notices;

public class NoticeController implements Initializable {
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnSet;
	@FXML
	private Button btnDel;
	@FXML
	private Button btnClose;
	@FXML
	private ComboBox cbxSearch;
	@FXML
	private TableView tblList;
	@FXML
	private Button btnList;

	public Stage stage4;
	private ObservableList<Notices> obsList;
	private int tblListSel;

	public NoticeController() {
		this.obsList = FXCollections.observableArrayList();
	}

	// �̺�Ʈ ��� -> �ڵ鷯 �Լ� ����
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// �˸�â ���̺� �� �÷� ����Ʈ
		tblList1ColumnInitialize();
		// �˻� �� ����� �Է��ϴ� �ʱ�ȭ ó�� (�ۼ���, ��ȣ, ����)
		comboBoxSearchInitialize();
		// ������ ���̽��� �ִ� �� ��������
		totalLoadList();

		// �ݱ��ư �̺�Ʈ
		btnClose.setOnAction(e -> stage4.close());
		// ������ ���̺��� �����Ѵ�.
		btnDel.setOnAction(e -> handleBtnDelAction(e));
		// ��� ��ư
		btnSet.setOnAction(e -> handleBtnSetAction(e));
		// ã���ư �̺�Ʈ ��� �� �ڵ鷯�Լ�
		btnSearch.setOnAction(e -> handleBtnSearchAction(e));
		// ���̺� �� Ŭ���� �̺�Ʈ
		tblList.setOnMousePressed(e -> handleTableViewPressedAction(e));
		// ���̺� �� ����Ŭ���� �̺�Ʈ
		tblList.setOnMouseClicked(e -> handleTableViewClickedAction(e));
		// ��ü����Ʈ��ư �̺�Ʈ ��� �� �ڵ鷯�Լ�
		btnList.setOnAction(e -> handleBtnListAction(e));
	}

	// ���̺� �� ����Ŭ���� �̺�Ʈ
	private void handleTableViewClickedAction(MouseEvent e) {
		if (e.getClickCount() != 2)
			return;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/noticeShow.fxml"));
			Scene scene = new Scene(root);
			Stage noticeStage = new Stage(StageStyle.DECORATED);

			// �̺�Ʈ ��� �� �ڵ鷯 ó��
			TextArea txaTitle = (TextArea) scene.lookup("#txaTitle");
			TextArea txaContents = (TextArea) scene.lookup("#txaContents");
			TextField txtWit = (TextField) scene.lookup("#txtWit");
			TextField txtDate = (TextField) scene.lookup("#txtDate");
			Button btnCan = (Button) scene.lookup("#btnCan");

			Notices notices = obsList.get(tblListSel);
			txtWit.setText(notices.getUserID());
			txtDate.setText(notices.getDate());
			txaTitle.setText(notices.getTitle());
			txaContents.setText(notices.getContents());

			btnCan.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					noticeStage.close();
				}
			});

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			noticeStage.getIcons()
					.add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			noticeStage.initModality(Modality.WINDOW_MODAL);
			noticeStage.initOwner(stage4);
			noticeStage.setScene(scene);
			noticeStage.setTitle("�������� ���â");
			noticeStage.setResizable(false);
			noticeStage.show();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	// ��ü����Ʈ��ư �̺�Ʈ ��� �� �ڵ鷯�Լ�
	private void handleBtnListAction(ActionEvent e) {
		obsList.clear();
		totalLoadList();
	}

	// ã���ư �̺�Ʈ ��� �ڵ鷯�Լ�ó��
	private void handleBtnSearchAction(ActionEvent e) {
		try {
			NoticeDAO notiD = new NoticeDAO();
			if (txtSearch.getText().trim().equals("")) {
				throw new Exception();
			}
			if (cbxSearch.getSelectionModel().isEmpty()) {
				throw new Exception();
			} else if (cbxSearch.getSelectionModel().getSelectedItem().toString().trim().equals("�ۼ���")) {
				ArrayList<Notices> arrayList = notiD.getNoticeFindUserID(txtSearch.getText().trim());
				if (arrayList.size() != 0) {
					obsList.clear();
					for (int i = 0; i < arrayList.size(); i++) {
						Notices n = arrayList.get(i);
						obsList.add(n);
					}
				}
			} else if (cbxSearch.getSelectionModel().getSelectedItem().toString().trim().equals("����")) {
				ArrayList<Notices> arrayList = notiD.getNoticeFindTitle(txtSearch.getText().trim());
				if (arrayList.size() != 0) {
					obsList.clear();
					for (int i = 0; i < arrayList.size(); i++) {
						Notices n = arrayList.get(i);
						obsList.add(n);
					}
				}
			}
		} catch (Exception event) {
			// ���â�� �������. ��������, ��, ȭ�鳻��(����ȭ��Ŵ)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�Էµ��� �ʾҽ��ϴ�");
			alert.setHeaderText("�˻�� �Է��Ͻÿ�!");
			alert.setContentText("�������� �����ϼ���. ");
			alert.showAndWait();
		}

	}

	// ���̺� �� Ŭ���� �̺�Ʈ
	private void handleTableViewPressedAction(MouseEvent e) {
		tblListSel = tblList.getSelectionModel().getSelectedIndex();
	}

	// �˻� �� ����� �Է��ϴ� �ʱ�ȭ ó�� (�ۼ���, ��ȣ, ����)
	private void comboBoxSearchInitialize() {
		ObservableList<String> obsList = FXCollections.observableArrayList();
		obsList.addAll("�ۼ���", "����");
		cbxSearch.setItems(obsList);
	}

	// ��� ��ư
	private void handleBtnSetAction(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/noticeAdd.fxml"));
			Scene scene = new Scene(root);
			Stage noticeStage = new Stage(StageStyle.DECORATED);

			// �̺�Ʈ ��� �� �ڵ鷯 ó��
			TextArea txaTitle = (TextArea) scene.lookup("#txaTitle");
			TextArea txaContents = (TextArea) scene.lookup("#txaContents");
			TextField txtWit = (TextField) scene.lookup("#txtWit");
			TextField txtDate = (TextField) scene.lookup("#txtDate");
			Button btnCan = (Button) scene.lookup("#btnCan");
			Button btnSett = (Button) scene.lookup("#btnSett");
			txtDate.setText(NOW_LOCAL_DATE());

			// ��� ��ư �̺�Ʈ ���
			btnSett.setOnAction(e1 -> {
				NoticeDAO notiD = new NoticeDAO();

				Notices notices = new Notices(txtWit.getText(), txtDate.getText(), txaTitle.getText(),
						txaContents.getText());

				int returnValue = notiD.getNoticeRegistry(notices);

				if (returnValue != 0) {
					obsList.clear();
					totalLoadList();
				}

				noticeStage.close();
			});

			txtDate.setDisable(true);

			// ��� ��ư�� ������ â�� ������.
			btnCan.setOnAction(e2 -> {
				noticeStage.close();
			});

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			noticeStage.getIcons()
					.add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			noticeStage.initModality(Modality.WINDOW_MODAL);
			noticeStage.initOwner(stage4);
			noticeStage.setScene(scene);
			noticeStage.setTitle("�������� ���â");
			noticeStage.setResizable(false);
			noticeStage.show();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	// ������ ���̽��� �ִ� �� ��������
	private void totalLoadList() {
		NoticeDAO noticeD = new NoticeDAO();
		ArrayList<Notices> arrayList = noticeD.getTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			Notices n = arrayList.get(i);
			obsList.add(n);
		}
	}

	// ������ ���̺��� �����Ѵ�.
	private void handleBtnDelAction(ActionEvent e) {
		NoticeDAO notiD = new NoticeDAO();

		Notices noti = obsList.get(tblListSel);
		int no = noti.getNo();
		int returnValue = notiD.getNoticeDelete(no);

		if (returnValue != 0) {
			obsList.remove(tblListSel);
		}

	}

	// �˸�â ���̺� �� �÷� ����Ʈ
	private void tblList1ColumnInitialize() {
		TableColumn colNo = new TableColumn("NO.");
		colNo.setMaxWidth(50);
		colNo.setCellValueFactory(new PropertyValueFactory("no"));

		TableColumn colUser = new TableColumn("�ۼ���");
		colUser.setPrefWidth(120);
		colUser.setCellValueFactory(new PropertyValueFactory("userID"));

		TableColumn colDate = new TableColumn("�ۼ� ��¥");
		colDate.setPrefWidth(120);
		colDate.setCellValueFactory(new PropertyValueFactory("date"));

		TableColumn colTitle = new TableColumn("����");
		colTitle.setPrefWidth(150);
		colTitle.setCellValueFactory(new PropertyValueFactory("title"));

		TableColumn colContents = new TableColumn("����");
		colContents.setPrefWidth(350);
		colContents.setCellValueFactory(new PropertyValueFactory("contents"));

		tblList.getColumns().addAll(colNo, colUser, colDate, colTitle, colContents);
		tblList.setItems(obsList);
	}

	// ���糯¥
	public String NOW_LOCAL_DATE() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date time = new Date();

		String time1 = format1.format(time);

		return time1;
	}

}
