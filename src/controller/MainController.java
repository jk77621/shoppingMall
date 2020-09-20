package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Manager;
import model.Stock;

public class MainController implements Initializable {
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnManagerAdd;
	@FXML
	private Label lblStatus1;
	@FXML
	private Label lblStatus2;

	private ObservableList<Manager> obsList;
	public Stage stage1;
	String id;
	String password;

	public MainController() {
		this.stage1 = null;
		this.obsList = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 아이디, 패스워드 일치 불일치 이벤트 및 핸들러함수
		txtId.setOnKeyReleased(event -> handleTxtIdAction(event));
		txtPassword.setOnKeyReleased(event -> handleTxtPasswordAction(event));
		// 로그인버튼 이벤트 및 핸들러함수
		btnLogin.setOnAction(event -> handleBtnLoginAction(event));
		// 나가기버튼 이벤트 및 핸들러함수
		btnCancel.setOnAction(event -> Platform.exit());
		// 관리자등록버튼 이벤트 및 핸들러함수
		btnManagerAdd.setOnAction(event -> handleBtnManagerAddAction(event));
	}

	// 아이디 일치 불일치 이벤트 및 핸들러함수
	private void handleTxtIdAction(KeyEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			ArrayList<Manager> arrayList = moduleDAO.getManagerIDTotalLoadList(txtId.getText());
			if (arrayList == null) {
				return;
			}
			id = arrayList.get(0).getMt_managerId();
			if (txtId.getText().equals(id)) {
				lblStatus1.setText("아이디가 일치합니다.");
				lblStatus1.setStyle("-fx-text-fill : blue");
			}
		} catch (Exception e) {
			lblStatus1.setText("아이디가 일치하지 않습니다.");
			lblStatus1.setStyle("-fx-text-fill : red");
		}
	}

	// 패스워드 일치 불일치 이벤트 및 핸들러함수
	private void handleTxtPasswordAction(KeyEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			ArrayList<Manager> arrayList = moduleDAO.getManagerPasswordTotalLoadList(txtPassword.getText());
			if (arrayList == null) {
				return;
			}
			password = arrayList.get(0).getMt_managerPassword();
			if (txtPassword.getText().equals(password)) {
				lblStatus2.setText("패스워드가 일치합니다.");
				lblStatus2.setStyle("-fx-text-fill : blue");
			}
		} catch (Exception e) {
			lblStatus2.setText("패스워드가 일치하지 않습니다.");
			lblStatus2.setStyle("-fx-text-fill : red");
		}
	}

	// 로그인버튼 이벤트 및 핸들러함수
	private void handleBtnLoginAction(ActionEvent event) {
		try {
			if (txtId.getText().equals(id) && txtPassword.getText().equals(password)) {
				Stage mainStage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/manager.fxml"));
				Parent root = fxmlLoader.load();
				RootController rootController = fxmlLoader.getController();
				rootController.stage2 = mainStage;

				Scene scene = new Scene(root);

				scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
				mainStage.getIcons()
						.add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
				mainStage.setTitle("쇼핑몰 재고관리 프로그램(관리자)");
				mainStage.setScene(scene);
				mainStage.show();

				Stage window = (Stage) btnLogin.getScene().getWindow();
				window.close();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Login");
				alert.setHeaderText("매장코드와 비밀번호중 잘못입력하신 사항이 있습니다.");
				alert.setContentText("매장코드와 비밀번호를 확인해 주세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 오류!");
			alert.setContentText("MainController를 확인해주세요.");
			alert.showAndWait();
		}
	}

	// 관리자등록버튼 이벤트 및 핸들러함수
	private void handleBtnManagerAddAction(ActionEvent event) {
		try {
			Stage mainStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/managerAdd.fxml"));
			Parent root = fxmlLoader.load();
			ManagerController managerController = fxmlLoader.getController();
			managerController.stage3 = mainStage;

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			mainStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			mainStage.setTitle("관리자 등록");
			mainStage.setScene(scene);
			mainStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("관리자 등록");
			alert.setHeaderText("관리자등록버튼 오류!");
			alert.setContentText("MainController를 확인해주세요.");
			alert.showAndWait();
		}
	}

}
