package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Model;
import model.User;
import java.io.IOException;
import java.sql.SQLException;

/** This class is where the user details is edited
 /*
 * Classname : Update Profile
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class UpdateProfileController {
    private Model model;
    private static Stage stage;
    private Stage parentStage;
    @FXML
    private TextField firstname_update;
    @FXML
    private TextField lastname_update;
    @FXML
    private Label status;
    @FXML
    private MenuItem logout;
    @FXML
    private Button setprofile;
    @FXML
    private Button cancelupdate;

    public UpdateProfileController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    public void initialize() {
        setprofile.setOnAction(event -> {
            if (!firstname_update.getText().isEmpty() || !lastname_update.getText().isEmpty()) {
                User user = model.getCurrentUser();
                if (firstname_update.getText().isEmpty()) {
                    try {
                        String username = user.getUsername();
                        String password = user.getPassword();
                        String firstname = user.getFirstname();
                        model.getCurrentUser().setLastname(lastname_update.getText());
                        user = model.getUserDao().updateUser(firstname, lastname_update.getText(), username);
                        user = model.getUserDao().getUser(username,password);
                        status.setText("Lastname updated");
                        status.setTextFill(Color.GREEN);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (lastname_update.getText().isEmpty()) {
                    try {
                        String username = user.getUsername();
                        String password = user.getPassword();
                        String lastname = user.getLastname();
                        model.getCurrentUser().setFirstname(firstname_update.getText());
                        user = model.getUserDao().updateUser(firstname_update.getText(), lastname, username);
                        user = model.getUserDao().getUser(username,password);
                        status.setText("Firstname updated");
                        status.setTextFill(Color.GREEN);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    try {
                        String username = user.getUsername();
                        String password = user.getPassword();
                        model.getCurrentUser().setFirstname(firstname_update.getText());
                        model.getCurrentUser().setLastname(lastname_update.getText());
                        user = model.getUserDao().updateUser(firstname_update.getText(), lastname_update.getText(), username);
                        user = model.getUserDao().getUser(username,password);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                try{
                    stage.close();
                    parentStage.close();
                    User user1 = model.getCurrentUser();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
                    HomeController homeController = new HomeController(stage, model);
                    Label label = new Label(user1.getFirstname());
                    label.setText("Hello" + " " +user1.getFirstname()+ " "+user1.getLastname());
                    label.setTranslateY(50);
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setAlignment(Pos.CENTER);
                    label.setTextFill(Color.DEEPSKYBLUE);
                    label.setFont(Font.font ("Verdana", FontWeight.BOLD, 14));
                    Reflection r = new Reflection();
                    r.setFraction(0.7f);
                    loader.setController(homeController);
                    label.setEffect(r);
                    VBox root = loader.load();
                    root.getChildren().add(label);
                    homeController.showStage(root);
                    stage.close();
                    status.setText("Details updated");
                    status.setTextFill(Color.GREEN);
                }catch(IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                status.setText("One of the fields should be filled");
                status.setTextFill(Color.RED);
            }
        });

        cancelupdate.setOnAction(event -> {
            stage.close();
        });
    }

    public static void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Profile Details");
        stage.show();
    }
}
