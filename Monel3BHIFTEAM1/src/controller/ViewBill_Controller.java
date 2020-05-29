package controller;

import app.SceneLoader;
import data.BillDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Bill;
import model.Client;

import java.io.IOException;

public class ViewBill_Controller extends SceneLoader {

    @FXML
    private Label lbClientOnBill;

    @FXML
    private Label lbMonthOnBill;

    @FXML
    private Label lbSingleEvents;

    @FXML
    private Label lbGroupEvents;

    @FXML
    private Label lbRideCosts;

    @FXML
    private Button btnPrint;

    @FXML
    private Label lbClient;

    @FXML
    private Label lbMonth;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private Label lbTitle;

    @FXML
    private Button btnBack;

    private Bill bill;

    public void setBill(Bill bill) {
        this.bill = bill;
        afterSet();
        System.out.println("Rechnung: "+bill.getNr());
    }

    public void afterSet () {
        //TODO
    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}
