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
import model.*;
import model.Event;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;

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
        openSettings();
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

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        Locale locale = Locale.GERMANY;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String padding = "";
        if (numberFormat.format(Math.round(((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts)*1.2) * 100.0) / 100.0).replace("€", "").replace(",", "").replace(" ", "").length() == 5) {
            padding = "style=\"padding-left:228px; \"";
        } else {
            padding = "style=\"padding-left:210px; \"";
        }


        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap\" rel=\"stylesheet\">" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Roboto', sans-serif;" +
                "               margin: 0;" +
                "               padding: 0;" +
                "        }\n" +
                "\n" +
                "        #bill {\n" +
                "            background-repeat: no-repeat;\n" +
                "            position: relative;\n" +
                "            width: 1000px;\n" +
                "            height: auto;\n" +
                "              margin-left: -95px" +

                "        }\n" +
                "\n" +
                "        img {\n" +
                "            display: block;\n" +
                "            width: auto;\n" +
                "            height: auto;\n" +
                "        }\n" +
                "\n" +
                "        #text {\n" +
                "            width: 900px;\n" +
                "            height: 920px;           \n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            margin: 6em;\n" +
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
                "       #datum {" +
                "           text-align: right;" +
                "}" +
                "#customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #FF0000;\n" +
                "  color: white;\n" +
                "}" +
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
                "            bottom: -280px;\n" +
                "            margin-left: 280px;\n" +
                "        }\n" +
                "\n"+
                "\n" +
                "        /*table, th, td, tr {\n" +
                "            border: 1px solid gray;\n" +
                "        }*/\n" +
                "       #billimg {" +
                "           margin-top:150px;" +
                "           width:1205px;" +
                "}" +
                "@page {" +
                "   margin: 0;" +
                "   padding:0" +
                "}" +

                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div id=\"text\">\n" +

                "           <img src=\"./src/resources/logo_Monel_invoice.png\" style=\"margin-left:250px\">" +
                "        <div id=\"datum\" class=\"underline\">\n" +
                "            <h4>Rechnung Monel</h4>\n" +
                "            <table style=\" float: right;\">\n" +
                "                <tr>\n" +
                "                    <td>Rechnungsdatum:</td>\n" +
                "                    <td id=\"rechnungsdatum\">" + bill.getDateOfIssue().format(formatters) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ausstellungsdatum:</td>\n" +
                "                    <td id=\"ausstellungsdatum\">" + LocalDate.now().format(formatters) + "</td>\n" +
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
                "                    <td id=\"uidNr\" class=\"right\">" + Settings.getInstance().getUid_Number() + "</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    \n" +
                "        <div id=\"betraege\">\n" +
                "            <table id=\"customers\">\n" +
                "                <tr>\n" +
                "                      <th>Kategorie</th>" +
                "                    <th>Beträge</th>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Einzel:</td>\n" +
                "                    <td id=\"einzel\" class=\"right\">" +numberFormat.format(Math.round((assistanceCostsSingle)*100.0)/100.0) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Gruppen:</td>\n" +
                "                    <td id=\"gruppen\" class=\"right\">" +numberFormat.format(Math.round((assistanceCostsGroup)*100.0)/100.0) +"</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Fahrtkosten:</td>\n" +
                "                    <td id=\"fahrtkosten\" class=\"right\">" + numberFormat.format(Math.round((wholeRideCosts)*100.0)/100.0) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"final\">Nettobetrag:</td>\n" +
                "                    <td id=\"gesamtpreis\" class=\"right final\">" + numberFormat.format(Math.round((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts)*100.0)/100.0) + "</td>\n" +
                "                </tr>\n" +
                "       <tr>\n" +
                "            <td class=\"final\">Umsatzsteuer 20%:</td>\n" +
                "                    <td id=\"gesamtpreis\" class=\"right final\">" +  numberFormat.format(Math.round(((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts)*0.2) * 100.0) / 100.0) +  "</td>\n" +
                "                </tr>\n" +
                "<tr>\n" +
        "                    <td class=\"final\">Rechnungsbetrag:</td>\n" +
                "                    <td id=\"gesamtpreis\" class=\"right final\">" + numberFormat.format(Math.round(((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts)*1.2) * 100.0) / 100.0) + "</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "\n" + "    <div id=\"bill\">\n" +
                "        <img src=\"./src/resources/RechnungZahlschein-1.png\" id=\"billimg\" />\n" +
                "    </div>\n" +
                "        <div id=\"rechnung\">\n" +
                "            <table style=\"letter-spacing: 7.5px\">\n" +
                "                <tr>\n" +
                "                  <td style=\"padding-bottom:25px\" id=\"firma\">" + Settings.getInstance().getCompanyName() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"padding-bottom:25px; min-width:500px\" id=\"iban\">" + Settings.getInstance().getIban() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td id=\"bic\">" + Settings.getInstance().getBic() + "</td><td " + padding + ">" +numberFormat.format(Math.round(((assistanceCostsGroup + assistanceCostsSingle + wholeRideCosts)*1.2) * 100.0) / 100.0).replace("€", "").replace(",", "").replace(" ", "") +"</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        <div id=\"rechnung\" style=\"margin-left:-60px\" >\n" +
                "            <table style=\"letter-spacing: 7.5px\">\n" +
                "                <tr>\n" +
                "                  <td style=\"padding-bottom:25px\" id=\"firma\">" + Settings.getInstance().getCompanyName() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"padding-bottom:25px; min-width:500px; letter-spacing:2.5px\" >" + Settings.getInstance().getIban() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                   <td id=\"bic\">" + Settings.getInstance().getBic() + "</td>" +
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
