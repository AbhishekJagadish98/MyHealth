package controller;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.Record;
import model.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/** This class is where the records are displayed
 /*
 * Classname : View Record
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */
public class ViewRecordController {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private TableView<Record> records_table;
    @FXML
    private TableColumn<Record, String> record_date;
    @FXML
    private TableColumn<Record,String> record_weight;
    @FXML
    private TableColumn<Record,String> record_temp;
    @FXML
    private TableColumn<Record,String> record_lbp;
    @FXML
    private TableColumn<Record,String> record_hbp;
    @FXML
    private TableColumn<Record,String> record_note;

    @FXML
    private Button okay_view;




    private User user;
    private Record record;

    public ViewRecordController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }
    @FXML
    public void initialize(){

        Object String;

        record_date.setCellValueFactory(new PropertyValueFactory<Record, String>("Date"));
        record_weight.setCellValueFactory(new PropertyValueFactory<Record, String>("Weight"));
        record_temp.setCellValueFactory(new PropertyValueFactory<Record, String>("Temperature"));
        record_lbp.setCellValueFactory(new PropertyValueFactory<Record, String>("Lowbp"));
        record_hbp.setCellValueFactory(new PropertyValueFactory<Record, String>("Highbp"));
        record_note.setCellValueFactory(new PropertyValueFactory<Record, String>("Notes"));
        String user=model.getCurrentUser().getUsername();
        ObservableList<Record> record_obj=FXCollections.observableList(model.getUserDao().view_record(user));
        records_table.setItems(record_obj);

        okay_view.setOnAction(event -> {
            stage.close();
        });
    }


    public void showStage(Pane root) {
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Record Details");
        stage.show();
    }
}
