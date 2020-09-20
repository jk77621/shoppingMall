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
		// �����ڸ���Ʈ�����ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnManagerList.setOnAction(event -> handleBtnManagerListAction(event));
		// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
		btnAdd.setOnAction(event -> handleBtnAddAction(event));
		// �������ư �̺�Ʈ
		btnCancel.setOnAction(event -> stage3.close());
	}

	// �����ڸ���Ʈ�����ư �̺�Ʈ �� �ڵ鷯�Լ�
	private void handleBtnManagerListAction(ActionEvent event) {
		try {
			// ȭ�鳻��->��->��������(���ν�������)->�����ָ�ȴ�.�� �����Ծ��.
			Parent root = FXMLLoader.load(getClass().getResource("/view/managerList.fxml"));
			// scene(ȭ�鳻��) �����.
			Scene scene = new Scene(root);
			Stage managerListStage = new Stage(StageStyle.DECORATED);
			// ------------------------------�̺�Ʈ��� �� �ڵ鷯 ó��--------------------------------
			managerView = (TableView) scene.lookup("#managerView");
			Button btnExit = (Button) scene.lookup("#btnExit");
			Button btnDelete = (Button) scene.lookup("#btnDelete");

			// �Ŵ����� �÷��ʱ�ȭ����
			managerViewColumnInitialize();
			// �Ŵ����� �ʱ�ȭ
			obsList.clear();
			// �Ŵ����� ��ü����
			managerViewTotalLoadList();
			// �Ŵ����� �����ϸ� ���� �̺�Ʈ
			managerView.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					managerViewSelectedIndex = managerView.getSelectionModel().getSelectedIndex();
				}
			});
			// �������ư �̺�Ʈ �� �ڵ鷯�Լ�
			btnExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					managerListStage.close();
				}
			});
			// ������ư �̺�Ʈ �� �ڵ鷯�Լ�
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ModuleDAO moduleDAO = new ModuleDAO();
					// 5. ����ڵ带 ���������� �ش�� �������� ? ��ȣ�� �����Ѵ�
					Manager manager = obsList.get(managerViewSelectedIndex);
					int mid = manager.getMid();
					int returnValue = moduleDAO.getManagerDelete(mid);

					if (returnValue != 0) {
						obsList.remove(managerViewSelectedIndex);
					}
				}
			});

			// ------------------------------/�̺�Ʈ��� �� �ڵ鷯 ó��-------------------------------
			// ��������(���ν�������)�� �����. (*���, ��޸���), ��������(��)
			scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
			managerListStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
			managerListStage.initModality(Modality.WINDOW_MODAL);
			managerListStage.initOwner(stage3);
			managerListStage.setScene(scene);
			managerListStage.setTitle("������ ����Ʈ");
			managerListStage.setResizable(false);
			managerListStage.show();
		} catch (IndexOutOfBoundsException | IOException e) {
			// ���â�� �������(��������, ��, ȭ�鳻�� �� �������)
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("������ ����Ʈâ ����!");
			alert.setHeaderText("���˿��");
			alert.setContentText("������������!");
			alert.showAndWait();
		}
	}

	// �Ŵ����� �÷��ʱ�ȭ����
	private void managerViewColumnInitialize() {
		TableColumn colMid = new TableColumn("NO.");
		colMid.setPrefWidth(50);
		colMid.setCellValueFactory(new PropertyValueFactory("mid"));

		TableColumn colManagerName = new TableColumn("�����ڸ�");
		colManagerName.setPrefWidth(80);
		colManagerName.setCellValueFactory(new PropertyValueFactory("mt_managerName"));

		TableColumn colManagerId = new TableColumn("������ ���̵�");
		colManagerId.setPrefWidth(120);
		colManagerId.setCellValueFactory(new PropertyValueFactory("mt_managerId"));

		TableColumn colManagerPassword = new TableColumn("������ ��й�ȣ");
		colManagerPassword.setPrefWidth(120);
		colManagerPassword.setCellValueFactory(new PropertyValueFactory("mt_managerPassword"));

		managerView.getColumns().addAll(colMid, colManagerName, colManagerId, colManagerPassword);

		managerView.setItems(obsList);
	}

	// �Ŵ����� ��ü����
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

	// ��Ϲ�ư �̺�Ʈ �� �ڵ鷯�Լ�
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
