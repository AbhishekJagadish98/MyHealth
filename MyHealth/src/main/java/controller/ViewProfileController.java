package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.User;

import java.io.IOException;

/** This class is where the profile details are shown
 /*
 * Classname : View Profile
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class ViewProfileController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private MenuItem viewProfile;
    @FXML
    private MenuItem updateProfile;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    @FXML
    private MenuItem logout;
    @FXML
    private Button okview;

    public ViewProfileController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    private void initialize() {
        okview.setOnAction(event -> {
            stage.close();
        });
    }


    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Profile Details");
        stage.show();
    }
}
