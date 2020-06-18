package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings_Controller implements Initializable {

    @FXML
    private Tab tabCommon;

    @FXML
    private ListView<?> lvHourlyRates;

    @FXML
    private Button btnAddHourlyRate;

    @FXML
    private Button btnDeleteHourlyRate;

    @FXML
    private ComboBox<?> comboFont;

    @FXML
    private ComboBox<?> comboFontSize;

    @FXML
    private ColorPicker colorAccent;

    @FXML
    private Tab tabCompanyData;

    @FXML
    private TextField tfNameCompany;

    @FXML
    private TextField tfUidCompany;

    @FXML
    private TextField tfIbanCompany;

    @FXML
    private TextField tfBicCompany;

    @FXML
    private TextField tfStreetCompany;

    @FXML
    private TextField tfHousenumberCompany;

    @FXML
    private TextField tfZipCompany;

    @FXML
    private TextField tfPlaceCompany;

    @FXML
    private Tab tabTables;

    @FXML
    private Tab tabColumnsClients;

    @FXML
    private ListView<?> lvAvailableColumnsClients;

    @FXML
    private Button btnAddColumnClients;

    @FXML
    private Button btnRemoveColumnClients;

    @FXML
    private Button btnMoveColumnClientsUp;

    @FXML
    private Button btnMoveColumnClientsDown;

    @FXML
    private ListView<?> lvSelectedColumnsClients;

    @FXML
    private Tab tabColumnsEmployees;

    @FXML
    private ListView<?> lvAvailableColumnsEmployees;

    @FXML
    private Button btnAddColumnEmployees;

    @FXML
    private Button btnRemoveColumnEmployees;

    @FXML
    private Button btnMoveColumnEmployeesUp;

    @FXML
    private Button btnMoveColumnEmployeesDown;

    @FXML
    private ListView<?> lvSelectedColumnsEmployees;

    @FXML
    private Tab tabColumnsSponsors;

    @FXML
    private ListView<?> lvAvailableColumnsSponsors;

    @FXML
    private Button btnAddColumnSponsors;

    @FXML
    private Button btnRemoveColumnSponsors;

    @FXML
    private Button btnMoveColumnSponsorsUp;

    @FXML
    private Button btnMoveColumnSponsorsDown;

    @FXML
    private ListView<?> lvSelectedColumnsSponsors;

    @FXML
    private Tab tabColumnsActivities;

    @FXML
    private ListView<?> lvAvailableColumnsEvents;

    @FXML
    private Button btnAddColumnEvents;

    @FXML
    private Button btnRemoveColumnEvents;

    @FXML
    private Button btnMoveColumnEventsUp;

    @FXML
    private ListView<?> lvSelectedColumnsEvents;

    @FXML
    private Tab tabColumnsProtocols;

    @FXML
    private ListView<?> lvAvailableColumnsProtocols;

    @FXML
    private Button btnAddColumnProtocols;

    @FXML
    private Button btnRemoveColumnProtocols;

    @FXML
    private Button btnMoveColumnProtocolsUp;

    @FXML
    private Button btnMoveColumnProtocolsDown;

    @FXML
    private ListView<?> lvSelectedColumnsProtocols;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSaveSettings;

    private int companyCounter = 0;
    private int commonCounter = 0;

    @FXML
    void btnAddColumnClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnAddColumnEmployees_Clicked(ActionEvent event) {

    }

    @FXML
    void btnAddColumnEvents_Clicked(ActionEvent event) {

    }

    @FXML
    void btnAddColumnSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnAddHourlyRate_Clicked(ActionEvent event) {

    }

    @FXML
    void btnCancel_Clicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteHourlyRate_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnClientsDown_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnClientsUp_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnEmployeesDown_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnEmployeesUp_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnEventsUp_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnProtocolsDown_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnProtocolsUp_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnSponsorsDown_Clicked(ActionEvent event) {

    }

    @FXML
    void btnMoveColumnSponsorsUp_Clicked(ActionEvent event) {

    }

    @FXML
    void btnRemoveColumnClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnRemoveColumnEmployees_Clicked(ActionEvent event) {

    }

    @FXML
    void btnRemoveColumnEvents_Clicked(ActionEvent event) {

    }

    @FXML
    void btnRemoveColumnSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSaveSettings_Clicked(ActionEvent event) {
        companyCounter = 0;
        commonCounter = 0;
        tfCheck(tfIbanCompany, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", tabCompanyData, null, companyCounter);
        tfCheck(tfBicCompany, "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?$", tabCompanyData, null, companyCounter);
        tfCheck(tfUidCompany, "^ATU(\\s)?[0-9]{8}$", tabCompanyData, null, companyCounter);
        tfCheck(tfHousenumberCompany, "(([0-9]+)([^0-9]*))?$", tabCompanyData, null, companyCounter);
        tfCheck(tfStreetCompany, "^\\D+$", tabCompanyData, null, companyCounter);
        tfCheck(tfZipCompany, "^[1-9][0-9]{3}$", tabCompanyData, null, companyCounter);
        tfCheck(tfPlaceCompany, "^\\D+$", tabCompanyData, null, companyCounter);
    }

    private boolean tfCheck(TextField tf, String regex, Tab mainTab, Tab subTab, int counter){
        boolean success = false;
        if (!tf.getText().matches(regex)) {
            success = false;
            switch (mainTab.getId()) {
                case "tabCompanyData":
                    companyCounter++;
                    break;
                case "tabCommon":
                    commonCounter++;
                    break;
            }
            tf.setStyle("-FX-Border-Color: red");
            if (subTab != null){
                subTab.getGraphic().setStyle("-fx-text-fill: red;");
            }
            mainTab.getGraphic().setStyle("-fx-text-fill: red;");
            lbMessage.setText("Es gibt Fehler!");
        } else {
            success = true;
            tf.setStyle(null);
            if (counter == 0){
                if (subTab != null){
                    subTab.getGraphic().setStyle(null);
                }
                mainTab.getGraphic().setStyle(null);
            }
        }
        return success;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfNameCompany.setText(Settings.getCompanyName());
        tfUidCompany.setText(Settings.getUid_Number());
        tfIbanCompany.setText(Settings.getIban());
        tfBicCompany.setText(Settings.getBic());
        tfStreetCompany.setText(Settings.getStreet());
        tfHousenumberCompany.setText(""+Settings.getNr());
        tfZipCompany.setText(""+Settings.getPlz());
        tfPlaceCompany.setText(Settings.getLocation());

        tabCompanyData.setText("");
        tabCompanyData.setGraphic(new Label("Firmendaten"));
    }
}
