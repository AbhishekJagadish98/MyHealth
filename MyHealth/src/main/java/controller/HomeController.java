package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Model;
import model.Record;
import model.User;

import java.io.IOException;

/** This class is the main landing page with all functionalities
 /*
 * Classname : Home Page
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile;
	@FXML
	private MenuItem updateProfile;

	@FXML
	private MenuItem new_record;

	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem view_records;
	@FXML
	private MenuItem edit_rec;
	@FXML
	private MenuItem del_rec;
	@FXML
	private MenuItem about;
	@FXML
	private Menu export;
	@FXML
	private TableView records_table;
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	private void initialize(){
		viewProfile.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewProfile.fxml"));
				ViewProfileController viewProfileController =  new ViewProfileController(stage, model);
				loader.setController(viewProfileController);
				User user = model.getCurrentUser();
				Label label1 = new Label(user.getFirstname());
				label1.setText(user.getUsername());
				label1.setTranslateX(250);
				label1.setTranslateY(-277);
				Label label2 = new Label(user.getUsername());
				label2.setText(user.getFirstname());
				label2.setTranslateX(250);
				label2.setTranslateY(-187);
				Label label3 = new Label(user.getLastname());
				label3.setText(user.getLastname());
				label3.setTranslateX(250);
				label3.setTranslateY(-109);
				VBox root = loader.load();
				root.getChildren().add(label1);
				root.getChildren().add(label2);
				root.getChildren().add(label3);
				viewProfileController.showStage(root);
			} catch (IOException e) {
				message.setText(e.getMessage());
			}
		});

		updateProfile.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProfile.fxml"));
				UpdateProfileController updateProfileController =  new UpdateProfileController(stage, model);
				loader.setController(updateProfileController);
				User user = model.getCurrentUser();
				Label label1 = new Label(user.getUsername());
				label1.setText(user.getUsername());
				label1.setTranslateX(190);
				label1.setTranslateY(60);
				GridPane root = loader.load();
				root.getChildren().add(label1);
				UpdateProfileController.showStage(root);
			} catch (IOException e) {
				message.setText(e.getMessage());
			}
		});

		logout.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
				LoginController loginController =  new LoginController(stage, model);
				loader.setController(loginController);
				GridPane root = loader.load();
				loginController.showStage(root);
			} catch (IOException e) {
				message.setText(e.getMessage());
			}
		});

		new_record.setOnAction(event -> {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewRecord.fxml"));
				NewRecordController newRecordController =  new NewRecordController(stage, model);
				loader.setController(newRecordController);
			GridPane root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			newRecordController.showStage(root);
		});

		view_records.setOnAction(event -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRecord.fxml"));
			ViewRecordController viewRecordController =  new ViewRecordController(stage, model);
			loader.setController(viewRecordController);
			VBox root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			viewRecordController.showStage(root);
		});

		about.setOnAction(event -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/About.fxml"));
			AboutController viewRecordController =  new AboutController(stage, model);
			loader.setController(viewRecordController);
			AnchorPane root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			viewRecordController.showStage(root);
		});

		edit_rec.setOnAction(event -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRecord.fxml"));
			EditRecordController editRecordController =  new EditRecordController(stage, model);
			loader.setController(editRecordController);
			VBox root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			editRecordController.showStage(root);
		});

		del_rec.setOnAction(event -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewRecord.fxml"));
			DeleteRecordController deleteRecordController =  new DeleteRecordController(stage, model);
			loader.setController(deleteRecordController);
			VBox root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			deleteRecordController.showStage(root);
		});
	}


	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("MyHealth App");
		stage.show();
	}
}
