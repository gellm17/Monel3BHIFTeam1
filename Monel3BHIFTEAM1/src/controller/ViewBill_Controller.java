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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    @FXML
    void btnPrint_Clicked(ActionEvent event) {
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
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Courier New', Courier, monospace;\n" +
                "        }\n" +
                "\n" +
                "        /*#bill {\n" +
                "            background-image: url(\"backg.png\");\n" +
                "            background-repeat: no-repeat;\n" +
                "            position: relative;\n" +
                "            width: 652px;\n" +
                "            height: 920px;\n" +
                "        }*/\n" +
                "\n" +
                "        /*img {\n" +
                "            display: block;\n" +
                "            width: auto;\n" +
                "            height: auto;\n" +
                "        }*/\n" +
                "\n" +
                "        #text {\n" +
                "            width: 652px;\n" +
                "            height: 920px;           \n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            margin: 2em;\n" +
                "        }\n" +
                "\n" +
                "        #betraege {\n" +
                "            /*margin-right: 3em*/\n" +
                "        }\n" +
                "\n" +
                "        #briefkopf {\n" +
                "            margin-top: 1.2em;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .right {\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        .final {\n" +
                "            border-top: 1px solid black;\n" +
                "            padding-top: 0.7em;\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .underline {\n" +
                "            border-bottom: 1px solid rgb(230, 230, 230);\n" +
                "            padding-bottom: 1em;\n" +
                "            margin-bottom: 1em;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        #rechnung {\n" +
                "            position: absolute;\n" +
                "            bottom: 30px;\n" +
                "            right: 200px;\n" +
                "        }\n" +
                "\n" +
                "        #rechnung td {\n" +
                "            padding-bottom: 1em;\n" +
                "        }\n" +
                "\n" +
                "        /*table, th, td, tr {\n" +
                "            border: 1px solid gray;\n" +
                "        }*/\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <!--<div id=\"bill\">\n" +
                "        <img src=\"backg.png\" style=\"visibility: hidden;\" />\n" +
                "    </div>-->\n" +
                "\n" +
                "    <div id=\"text\">\n" +
                "        <div id=\"datum\" class=\"underline\">\n" +
                "            <h4>Rechnung Monel</h4>\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td>Rechnungsdatum:</td>\n" +
                "                    <td id=\"rechnungsdatum\">07.07.2200</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ausstellungsdatum:</td>\n" +
                "                    <td id=\"ausstellungsdatum\">07.07.2200</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "\n" +
                "        <div id=\"briefkopf\" class=\"underline\">\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td id=\"anrede\">" + bill.getClient().getSalutation() + " " + bill.getClient().getTitle() +" " +"</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"name\">" + bill.getClient().getFirstName() + " " + bill.getClient().getLastName() +" " +"</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"strasse\">" + bill.getClient().getStreetAndNr() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"ort\">" + bill.getClient().getPlace() + "</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "\n" +
                "        <div id=\"briefkopf\" class=\"underline\">\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td>Rechnungsnummer:</td>\n" +
                "                    <td id=\"rechnungsNr\" class=\"right\">" + bill.getNr() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>UID-Nummer:</td>\n" +
                "                    <td id=\"uidNr\" class=\"right\">AEB123456789</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    \n" +
                "        <div id=\"betraege\">\n" +
                "            <table style=\"float: right;\">\n" +
                "                <tr>\n" +
                "                    <th>Beträge</th>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Einzel:</td>\n" +
                "                    <td id=\"einzel\" class=\"right\">" + assistanceCostsSingle + " €\n" + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Gruppen:</td>\n" +
                "                    <td id=\"gruppen\" class=\"right\">" + assistanceCostsGroup + " €\n" + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Fahrtkosten:</td>\n" +
                "                    <td id=\"fahrtkosten\" class=\"right\">" + wholeRideCosts + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"final\">Gesamt:</td>\n" +
                "                    <td id=\"gesamtpreis\" class=\"right final\">" + (assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts) + " €\n" + "</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "\n" +
                "        <div id=\"rechnung\">\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td id=\"firma\">Monel AG</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"iban\">AT0012345689</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"bic\">AUABBWCDE</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";


        File file = new File(bill.getDateOfIssue().toString() +' '+bill.getClient().getLastName() + "_" + bill.getClient().getFirstName() + ".html");
        try {
            Files.write(file.toPath(), html.getBytes());
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

}
