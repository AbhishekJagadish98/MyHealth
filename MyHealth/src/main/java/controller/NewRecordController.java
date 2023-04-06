package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Record;
import model.User;

/** This class is where the application accepts new entry
 /*
 * Classname : New Record
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class NewRecordController {
    @FXML
    private TextField new_weight;
    @FXML
    private TextField date;
    @FXML
    private TextField new_temperature;
    @FXML
    private TextField new_details;
    @FXML
    private TextField LBP;
    @FXML
    private TextField HBP;
    @FXML
    private TextArea notes;
    @FXML
    private Button new_cancel;
    @FXML
    private Button newr;
    @FXML
    private Label status;

    private Stage stage;
    private Stage parentStage;
    private Model model;

    public NewRecordController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }
    public void initialize(){
        newr.setOnAction(event -> {
            if(!notes.getText().isEmpty()||!new_temperature.getText().isEmpty()||!HBP.getText().isEmpty()||!LBP.getText().isEmpty()||!new_weight.getText().isEmpty()){
                    Record record;
                    User user = model.getCurrentUser();
                    if(date.getText().isEmpty()) {
                        status.setText("Date is missing");
                        status.setTextFill(Color.RED);
                    } else if (Integer.parseInt(LBP.getText())>Integer.parseInt(HBP.getText())) {
                        status.setText("LBP can't be > HBP");
                        status.setTextFill(Color.RED);
                    } else{
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate Start;
                            Start = LocalDate.parse(date.getText(),formatter);
                            record = model.getUserDao().NewRecord(date.getText(), user.getUsername(), new_weight.getText(), new_temperature.getText(), LBP.getText(), HBP.getText(), notes.getText());
                            status.setText("Successfully Updated");
                            status.setTextFill(Color.GREEN);
                        } catch (Exception e) {
                            status.setText("Invalid Date Entry use dd/mm/yyyy");
                            status.setTextFill(Color.RED);
                        }
                    }
            }
            else{
                status.setText("Need at least one other value");
                status.setTextFill(Color.RED);
            }

        });

        new_cancel.setOnAction(event -> {
            stage.close();
        });
    }


    public void showStage(Pane root) {
        Scene scene = new Scene(root, 550, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("New Record");
        stage.show();
    }
}
