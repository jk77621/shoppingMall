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

	// 이벤트 등록 -> 핸들러 함수 연결
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 알림창 테이블 뷰 컬럼 리스트
		tblList1ColumnInitialize();
		// 검색 할 대상을 입력하는 초기화 처리 (작성자, 번호, 제목)
		comboBoxSearchInitialize();
		// 데이터 베이스에 있는 것 가져오기
		totalLoadList();

		// 닫기버튼 이벤트
		btnClose.setOnAction(e -> stage4.close());
		// 선택한 테이블을 삭제한다.
		btnDel.setOnAction(e -> handleBtnDelAction(e));
		// 등록 버튼
		btnSet.setOnAction(e -> handleBtnSetAction(e));
		// 찾기버튼 이벤트 등록 및 핸들러함수
		btnSearch.setOnAction(e -> handleBtnSearchAction(e));
		// 테이블 뷰 클릭시 이벤트
		tblList.setOnMousePressed(e -> handleTableViewPressedAction(e));
		// 테이블 뷰 더블클릭시 이벤트
		tblList.setOnMouseClicked(e -> handleTableViewClickedAction(e));
		// 전체리스트버튼 이벤트 등록 및 핸들러함수
		btnList.setOnAction(e -> handleBtnListAction(e));
	}

	// 테이블 뷰 더블클릭시 이벤트
	private void handleTableViewClickedAction(MouseEvent e) {
		if (e.getClickCount() != 2)
			return;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/noticeShow.fxml"));
			Scene scene = new Scene(root);
			Stage noticeStage = new Stage(StageStyle.DECORATED);

			// 이벤트 등록 및 핸들러 처리
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
			noticeStage.setTitle("공지사항 등록창");
			noticeStage.setResizable(false);
			noticeStage.show();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	// 전체리스트버튼 이벤트 등록 및 핸들러함수
	private void handleBtnListAction(ActionEvent e) {
		obsList.clear();
		totalLoadList();
	}

	// 찾기버튼 이벤트 등록 핸들러함수처리
	private void handleBtnSearchAction(ActionEvent e) {
		try {
			NoticeDAO notiD = new NoticeDAO();
			if (txtSearch.getText().trim().equals("")) {
				throw new Exception();
			}
			if (cbxSearch.getSelectionModel().isEmpty()) {
				throw new Exception();
			} else if (cbxSearch.getSelectionModel().getSelectedItem().toString().trim().equals("작성자")) {
				ArrayList<Notices> arrayList = notiD.getNoticeFindUserID(txtSearch.getText().trim());
				if (arrayList.size() != 0) {
					obsList.clear();
					for (int i = 0; i < arrayList.size(); i++) {
						Notices n = arrayList.get(i);
						obsList.add(n);
					}
				}
			} else if (cbxSearch.getSelectionModel().getSelectedItem().toString().trim().equals("제목")) {
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
			// 경고창이 만들어짐. 스테이지, 씬, 화면내용(고정화시킴)
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("입력되지 않았습니다");
			alert.setHeaderText("검색어를 입력하시오!");
			alert.setContentText("다음에는 주의하세요. ");
			alert.showAndWait();
		}

	}

	// 테이블 뷰 클릭시 이벤트
	private void handleTableViewPressedAction(MouseEvent e) {
		tblListSel = tblList.getSelectionModel().getSelectedIndex();
	}

	// 검색 할 대상을 입력하는 초기화 처리 (작성자, 번호, 제목)
	private void comboBoxSearchInitialize() {
		ObservableList<String> obsList = FXCollections.observableArrayList();
		obsList.addAll("작성자", "제목");
		cbxSearch.setItems(obsList);
	}

	// 등록 버튼
	private void handleBtnSetAction(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/noticeAdd.fxml"));
			Scene scene = new Scene(root);
			Stage noticeStage = new Stage(StageStyle.DECORATED);

			// 이벤트 등록 및 핸들러 처리
			TextArea txaTitle = (TextArea) scene.lookup("#txaTitle");
			TextArea txaContents = (TextArea) scene.lookup("#txaContents");
			TextField txtWit = (TextField) scene.lookup("#txtWit");
			TextField txtDate = (TextField) scene.lookup("#txtDate");
			Button btnCan = (Button) scene.lookup("#btnCan");
			Button btnSett = (Button) scene.lookup("#btnSett");
			txtDate.setText(NOW_LOCAL_DATE());

			// 등록 버튼 이벤트 등록
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

			// 취소 버튼을 누르면 창이 닫힌다.
			btnCan.setOnAction(e2 -> {
				noticeStage.close();
			});

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			noticeStage.getIcons()
					.add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			noticeStage.initModality(Modality.WINDOW_MODAL);
			noticeStage.initOwner(stage4);
			noticeStage.setScene(scene);
			noticeStage.setTitle("공지사항 등록창");
			noticeStage.setResizable(false);
			noticeStage.show();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	// 데이터 베이스에 있는 것 가져오기
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

	// 선택한 테이블을 삭제한다.
	private void handleBtnDelAction(ActionEvent e) {
		NoticeDAO notiD = new NoticeDAO();

		Notices noti = obsList.get(tblListSel);
		int no = noti.getNo();
		int returnValue = notiD.getNoticeDelete(no);

		if (returnValue != 0) {
			obsList.remove(tblListSel);
		}

	}

	// 알림창 테이블 뷰 컬럼 리스트
	private void tblList1ColumnInitialize() {
		TableColumn colNo = new TableColumn("NO.");
		colNo.setMaxWidth(50);
		colNo.setCellValueFactory(new PropertyValueFactory("no"));

		TableColumn colUser = new TableColumn("작성자");
		colUser.setPrefWidth(120);
		colUser.setCellValueFactory(new PropertyValueFactory("userID"));

		TableColumn colDate = new TableColumn("작성 날짜");
		colDate.setPrefWidth(120);
		colDate.setCellValueFactory(new PropertyValueFactory("date"));

		TableColumn colTitle = new TableColumn("제목");
		colTitle.setPrefWidth(150);
		colTitle.setCellValueFactory(new PropertyValueFactory("title"));

		TableColumn colContents = new TableColumn("내용");
		colContents.setPrefWidth(350);
		colContents.setCellValueFactory(new PropertyValueFactory("contents"));

		tblList.getColumns().addAll(colNo, colUser, colDate, colTitle, colContents);
		tblList.setItems(obsList);
	}

	// 현재날짜
	public String NOW_LOCAL_DATE() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date time = new Date();

		String time1 = format1.format(time);

		return time1;
	}

}
