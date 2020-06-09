package controller;

import app.SceneLoader;
import data.BillDAO;
import data.EventDAO;
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
import java.time.Duration;
import java.time.LocalDate;
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
        //bill.setEventProtocols(EventDAO.getInstance().getEventProtocolsByBill(bill));
        System.out.println("Rechnung: " + bill.getNr());
        lbClient.setText(bill.getClient().getFirstName() + " " + bill.getClient().getLastName());
        lbClientOnBill.setText(bill.getClient().getFirstName() + " " + bill.getClient().getLastName());
        if (bill.getEventProtocols().size() != 0) {
            lbMonth.setText(bill.getEventProtocols().iterator().next().getYear_month());
        }
        lbMonthOnBill.setText(lbMonth.getText());
        Iterator<EventProtocol> it = bill.getEventProtocols().iterator();
        double wholeRideCosts = 0;
        double assistanceCostsGroup = 0;
        double assistanceCostsSingle = 0;
        while (it.hasNext()){
            EventProtocol current = it.next();
            if (current.getEvent().getIsGroup()){
                assistanceCostsGroup += Duration.between(current.getStartTime(), current.getEndTime()).toHours() * current.getHourlyRate();
            } else {
                assistanceCostsSingle += Duration.between(current.getStartTime(), current.getEndTime()).toHours() * current.getHourlyRate();
            }
            wholeRideCosts += current.getRideCosts();
        }
        lbSingleEvents.setText(assistanceCostsSingle + " €\n");
        lbGroupEvents.setText(assistanceCostsGroup +  " €\n");
        lbRideCosts.setText(wholeRideCosts + " €");
        lbTotalCost.setText((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts) + " €\n");
    }

    @FXML
    void btnBack_Clicked(ActionEvent event) {
        showScene("BillList");
    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}
