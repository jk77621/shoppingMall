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
		// ���̵�, �н����� ��ġ ����ġ �̺�Ʈ �� �ڵ鷯�Լ�
		txtId.setOnKeyReleased(event -> handleTxtIdAction(event));
		txtPassword.setOnKeyReleased(event -> handleTxtPasswordAction(event));
		// �α��ι�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnLogin.setOnAction(event -> handleBtnLoginAction(event));
		// �������ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnCancel.setOnAction(event -> Platform.exit());
		// �����ڵ�Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnManagerAdd.setOnAction(event -> handleBtnManagerAddAction(event));
	}

	// ���̵� ��ġ ����ġ �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleTxtIdAction(KeyEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			ArrayList<Manager> arrayList = moduleDAO.getManagerIDTotalLoadList(txtId.getText());
			if (arrayList == null) {
				return;
			}
			id = arrayList.get(0).getMt_managerId();
			if (txtId.getText().equals(id)) {
				lblStatus1.setText("���̵� ��ġ�մϴ�.");
				lblStatus1.setStyle("-fx-text-fill : blue");
			}
		} catch (Exception e) {
			lblStatus1.setText("���̵� ��ġ���� �ʽ��ϴ�.");
			lblStatus1.setStyle("-fx-text-fill : red");
		}
	}

	// �н����� ��ġ ����ġ �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleTxtPasswordAction(KeyEvent event) {
		try {
			ModuleDAO moduleDAO = new ModuleDAO();
			ArrayList<Manager> arrayList = moduleDAO.getManagerPasswordTotalLoadList(txtPassword.getText());
			if (arrayList == null) {
				return;
			}
			password = arrayList.get(0).getMt_managerPassword();
			if (txtPassword.getText().equals(password)) {
				lblStatus2.setText("�н����尡 ��ġ�մϴ�.");
				lblStatus2.setStyle("-fx-text-fill : blue");
			}
		} catch (Exception e) {
			lblStatus2.setText("�н����尡 ��ġ���� �ʽ��ϴ�.");
			lblStatus2.setStyle("-fx-text-fill : red");
		}
	}

	// �α��ι�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
				mainStage.setTitle("���θ� ������ ���α׷�(������)");
				mainStage.setScene(scene);
				mainStage.show();

				Stage window = (Stage) btnLogin.getScene().getWindow();
				window.close();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Login");
				alert.setHeaderText("�����ڵ�� ��й�ȣ�� �߸��Է��Ͻ� ������ �ֽ��ϴ�.");
				alert.setContentText("�����ڵ�� ��й�ȣ�� Ȯ���� �ּ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�α���");
			alert.setHeaderText("�α��� ����!");
			alert.setContentText("MainController�� Ȯ�����ּ���.");
			alert.showAndWait();
		}
	}

	// �����ڵ�Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
			mainStage.setTitle("������ ���");
			mainStage.setScene(scene);
			mainStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("������ ���");
			alert.setHeaderText("�����ڵ�Ϲ�ư ����!");
			alert.setContentText("MainController�� Ȯ�����ּ���.");
			alert.showAndWait();
		}
	}

}
