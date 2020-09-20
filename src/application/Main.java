package application;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Parent root = fxmlLoader.load();
		MainController mainController=fxmlLoader.getController();
		mainController.stage1 = primaryStage;
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/main.css").toString());
		primaryStage.getIcons().add(new Image(getClass().getResource("/image/ShoppingMallLogoSimple.png").toString()));
		primaryStage.setTitle("쇼핑몰 재고관리 프로그램(로그인)");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
