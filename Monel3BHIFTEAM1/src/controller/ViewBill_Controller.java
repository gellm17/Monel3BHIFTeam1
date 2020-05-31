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
import model.Event;
import model.EventProtocol;

import java.io.IOException;
import java.util.Iterator;

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
    private Label lbTotalCost;

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
        System.out.println("Rechnung: " + bill.getNr());
        lbClient.setText(bill.getClient().getFirstName() + " " + bill.getClient().getLastName());
        lbClientOnBill.setText(bill.getClient().getFirstName() + " " + bill.getClient().getLastName());
        if (bill.getEventProtocols().size() != 0) {
            lbMonth.setText(bill.getEventProtocols().iterator().next().getYear_month().getMonthValue() + "/" + bill.getEventProtocols().iterator().next().getYear_month().getYear());
        }
        lbMonthOnBill.setText(lbMonth.getText());
        Iterator<EventProtocol> it = bill.getEventProtocols().iterator();
        double wholeRideCosts = 0;
        while (it.hasNext()){
            EventProtocol current = it.next();
            if (current.getEvent().getIsGroup()){
                lbGroupEvents.setText(lbGroupEvents.getText() + current.getEvent().getName() + " " + current.getEmployee().getFirstName() + " " + current.getEmployee().getLastName() + " " + current.getRideCosts() + " €\n");
            } else {
                lbSingleEvents.setText(lbSingleEvents.getText() + current.getEvent().getName() + " " + current.getEmployee().getFirstName() + " " + current.getEmployee().getLastName() + " " + current.getRideCosts() + " €\n");
            }
            wholeRideCosts += current.getRideCosts();
        }
        lbRideCosts.setText(wholeRideCosts + " €");
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
