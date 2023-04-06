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

/** This class controls the About page
 /*
 * Classname : About
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class AboutController {
    private Model model;
    private Stage stage;
    private Stage parentStage;

    public AboutController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("About");
        stage.show();
    }
}
