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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Manager;

public class ManagerController implements Initializable {
	@FXML
	private TextField txtManagerName;
	@FXML
	private TextField txtManagerId;
	@FXML
	private TextField txtManagerPassword;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnManagerList;

	public Stage stage3;
	private ObservableList<Manager> obsList;
	private TableView managerView;
	private int managerViewSelectedIndex;

	public ManagerController() {
		this.stage3 = null;
		this.obsList = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 관리자리스트보기버튼 이벤트 및 핸들러함수
		btnManagerList.setOnAction(event -> handleBtnManagerListAction(event));
		// 등록버튼 이벤트 및 핸들러함수
		btnAdd.setOnAction(event -> handleBtnAddAction(event));
		// 나가기버튼 이벤트
		btnCancel.setOnAction(event -> stage3.close());
	}

	// 관리자리스트보기버튼 이벤트 및 핸들러함수
	private void handleBtnManagerListAction(ActionEvent event) {
		try {
			// 화면내용->씬->스테이지(주인스테이지)->보여주면된다.을 가져왔어요.
			Parent root = FXMLLoader.load(getClass().getResource("/view/managerList.fxml"));
			// scene(화면내용) 만든다.
			Scene scene = new Scene(root);
			Stage managerListStage = new Stage(StageStyle.DECORATED);
			// ------------------------------이벤트등록 및 핸들러 처리--------------------------------
			managerView = (TableView) scene.lookup("#managerView");
			Button btnExit = (Button) scene.lookup("#btnExit");
			Button btnDelete = (Button) scene.lookup("#btnDelete");

			// 매니저뷰 컬럼초기화셋팅
			managerViewColumnInitialize();
			// 매니저뷰 초기화
			obsList.clear();
			// 매니저뷰 전체보기
			managerViewTotalLoadList();
			// 매니저뷰 선택하면 변경 이벤트
			managerView.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					managerViewSelectedIndex = managerView.getSelectionModel().getSelectedIndex();
				}
			});
			// 나가기버튼 이벤트 및 핸들러함수
			btnExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					managerListStage.close();
				}
			});
			// 삭제버튼 이벤트 및 핸들러함수
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ModuleDAO moduleDAO = new ModuleDAO();
					// 5. 어떤레코드를 지워야할지 해당된 쿼리문에 ? 번호를 연결한다
					Manager manager = obsList.get(managerViewSelectedIndex);
					int mid = manager.getMid();
					int returnValue = moduleDAO.getManagerDelete(mid);

					if (returnValue != 0) {
						obsList.remove(managerViewSelectedIndex);
					}
				}
			});

			// ------------------------------/이벤트등록 및 핸들러 처리-------------------------------
			// 스테이지(주인스테이지)를 만든다. (*모달, 모달리스), 스테이지(씬)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			managerListStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			managerListStage.initModality(Modality.WINDOW_MODAL);
			managerListStage.initOwner(stage3);
			managerListStage.setScene(scene);
			managerListStage.setTitle("관리자 리스트");
			managerListStage.setResizable(false);
			managerListStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// 경고창이 만들어짐(스테이지, 씬, 화면내용 싹 만들어짐)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("관리자 리스트창 에러!");
			alert.setHeaderText("점검요망");
			alert.setContentText("정신차리세요!");
			alert.showAndWait();
		}
	}

	// 매니저뷰 컬럼초기화셋팅
	private void managerViewColumnInitialize() {
		TableColumn colMid = new TableColumn("NO.");
		colMid.setPrefWidth(50);
		colMid.setCellValueFactory(new PropertyValueFactory("mid"));

		TableColumn colManagerName = new TableColumn("관리자명");
		colManagerName.setPrefWidth(80);
		colManagerName.setCellValueFactory(new PropertyValueFactory("mt_managerName"));

		TableColumn colManagerId = new TableColumn("관리자 아이디");
		colManagerId.setPrefWidth(120);
		colManagerId.setCellValueFactory(new PropertyValueFactory("mt_managerId"));

		TableColumn colManagerPassword = new TableColumn("관리자 비밀번호");
		colManagerPassword.setPrefWidth(120);
		colManagerPassword.setCellValueFactory(new PropertyValueFactory("mt_managerPassword"));

		managerView.getColumns().addAll(colMid, colManagerName, colManagerId, colManagerPassword);

		managerView.setItems(obsList);
	}

	// 매니저뷰 전체보기
	public void managerViewTotalLoadList() {
		ModuleDAO moduleDAO = new ModuleDAO();
		ArrayList<Manager> arrayList = moduleDAO.getManagerViewTotalLoadList();
		if (arrayList == null) {
			return;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			Manager m = arrayList.get(i);
			obsList.add(m);
		}
	}

	// 등록버튼 이벤트 및 핸들러함수
	private void handleBtnAddAction(ActionEvent event) {
		ModuleDAO moduleDAO = new ModuleDAO();
		Manager manager = new Manager(txtManagerName.getText(), txtManagerId.getText(), txtManagerPassword.getText());
		int returnValue = moduleDAO.getManagerRegistry(manager);
		if (returnValue != 0) {
			obsList.clear();
			managerViewTotalLoadList();
		}
	}

}
