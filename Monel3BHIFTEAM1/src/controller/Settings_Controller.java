package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.FontStyle;
import model.Settings;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Settings_Controller implements Initializable {

    @FXML
    private Tab tabCommon;

    @FXML
    private ComboBox<FontStyle> comboFont;

    @FXML
    private ComboBox<?> comboFontSize;

    @FXML
    private ColorPicker colorAccent;

    @FXML
    private Tab tabAdministration;

    @FXML
    private ListView<Double> lvHourlyRates;

    @FXML
    private Button btnAddHourlyRate;

    @FXML
    private Button btnDeleteHourlyRate;

    @FXML
    private ListView<String> lvOccupationGroup;

    @FXML
    private TextField tfOccupationGroup;

    @FXML
    private Button btnAddOccupationGroup;

    @FXML
    private Button btnDeleteOccupationGroup;

    @FXML
    private ListView<String> lvSalaryLevel;

    @FXML
    private TextField tfSalaryLevel;

    @FXML
    private Button btnAddSalaryLevel;

    @FXML
    private Button btnDeleteSalaryLevel;

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
    private TextField tfHourlyRate;

    @FXML
    private TextField tfPlaceCompany;

    @FXML
    private Tab tabTables;

    @FXML
    private Tab tabColumnsClients;

    @FXML
    private ListView<String> lvAvailableColumnsClients;

    @FXML
    private Button btnAddColumnClients;

    @FXML
    private Button btnRemoveColumnClients;

    @FXML
    private Button btnMoveColumnClientsUp;

    @FXML
    private Button btnMoveColumnClientsDown;

    @FXML
    private ListView<String> lvSelectedColumnsClients;

    @FXML
    private Tab tabColumnsEmployees;

    @FXML
    private ListView<String> lvAvailableColumnsEmployees;

    @FXML
    private Button btnAddColumnEmployees;

    @FXML
    private Button btnRemoveColumnEmployees;

    @FXML
    private Button btnMoveColumnEmployeesUp;

    @FXML
    private Button btnMoveColumnEmployeesDown;

    @FXML
    private ListView<String> lvSelectedColumnsEmployees;

    @FXML
    private Tab tabColumnsSponsors;

    @FXML
    private ListView<String> lvAvailableColumnsSponsors;

    @FXML
    private Button btnAddColumnSponsors;

    @FXML
    private Button btnRemoveColumnSponsors;

    @FXML
    private Button btnMoveColumnSponsorsUp;

    @FXML
    private Button btnMoveColumnSponsorsDown;

    @FXML
    private ListView<String> lvSelectedColumnsSponsors;

    @FXML
    private Tab tabColumnsActivities;

    @FXML
    private ListView<String> lvAvailableColumnsEvents;

    @FXML
    private Button btnAddColumnEvents;

    @FXML
    private Button btnRemoveColumnEvents;

    @FXML
    private Button btnMoveColumnEventsUp;

    @FXML
    private Button btnMoveColumnEventsDown;

    @FXML
    private ListView<String> lvSelectedColumnsEvents;

    @FXML
    private Tab tabColumnsProtocols;

    @FXML
    private ListView<String> lvAvailableColumnsProtocols;

    @FXML
    private Button btnAddColumnProtocols;

    @FXML
    private Button btnRemoveColumnProtocols;

    @FXML
    private Button btnMoveColumnProtocolsUp;

    @FXML
    private Button btnMoveColumnProtocolsDown;

    @FXML
    private ListView<String> lvSelectedColumnsProtocols;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSaveSettings;

    private int companyCounter = 0;
    private int commonCounter = 0;
    private int verwaltungCounter = 0;

    private Object selectedItem;
    private int posOfSelectedItem;

    @FXML
    void btnAddColumnClients_Clicked(ActionEvent event) { Settings.getInstance().klientColumnLoadIn((String) selectedItem); }

    @FXML
    void btnAddColumnEmployees_Clicked(ActionEvent event) { Settings.getInstance().employeeColumnLoadIn((String) selectedItem); }

    @FXML
    void btnAddColumnEvents_Clicked(ActionEvent event) { Settings.getInstance().activityColumnLoadIn((String) selectedItem); }

    @FXML
    void btnAddColumnSponsors_Clicked(ActionEvent event) { Settings.getInstance().sponsorColumnLoadIn((String) selectedItem); }

    @FXML
    void btnAddColumnProtocols_Clicked(ActionEvent event) { Settings.getInstance().protocolColumnLoadIn((String) selectedItem); }

    @FXML
    void btnAddHourlyRate_Clicked(ActionEvent event) {
        if (tfCheck(tfHourlyRate,"^\\d{1,8}([\\.,]\\d{2})?$", tabAdministration, null, verwaltungCounter)){
            Settings.getInstance().addHourlyRate(Double.parseDouble(tfHourlyRate.getText()));
        }
    }

    @FXML
    void btnDeleteHourlyRate_Clicked(ActionEvent event) {
        Settings.getInstance().removeHourlyRate((Double) selectedItem);
    }

    @FXML
    void btnAddOccupationGroup_Clicked(ActionEvent event) {
        Settings.getInstance().addUserGroup(tfOccupationGroup.getText());
    }

    @FXML
    void btnDeleteOccupationGroup_Clicked(ActionEvent event) {
        Settings.getInstance().removeUserGroup((String) selectedItem);
    }

    @FXML
    void btnAddSalaryLevel_Clicked(ActionEvent event) {
        Settings.getInstance().addSalaryLevel(tfSalaryLevel.getText());
    }

    @FXML
    void btnDeleteSalaryLevel_Clicked(ActionEvent event) {
        Settings.getInstance().removeSalaryLevel((String) selectedItem);
    }

    @FXML
    void btnCancel_Clicked(ActionEvent event) {
        Stage stage = (Stage) btnSaveSettings.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnMoveColumnClientsDown_Clicked(ActionEvent event) {

        Settings.getInstance().klientColumnMoveDown((String) selectedItem);
        lvSelectedColumnsClients.getSelectionModel().select(posOfSelectedItem + 1);
    }

    @FXML
    void btnMoveColumnClientsUp_Clicked(ActionEvent event) {
        Settings.getInstance().klientColumnMoveUp((String) selectedItem);
        lvSelectedColumnsClients.getSelectionModel().select(posOfSelectedItem - 1);
    }

    @FXML
    void btnMoveColumnEmployeesDown_Clicked(ActionEvent event) {
        Settings.getInstance().employeeColumnMoveDown((String) selectedItem);
        lvSelectedColumnsEmployees.getSelectionModel().select(posOfSelectedItem + 1);
    }

    @FXML
    void btnMoveColumnEmployeesUp_Clicked(ActionEvent event) {
        Settings.getInstance().employeeColumnMoveUp((String) selectedItem);
        lvSelectedColumnsEmployees.getSelectionModel().select(posOfSelectedItem - 1);
    }

    @FXML
    void btnMoveColumnEventsUp_Clicked(ActionEvent event) {
        Settings.getInstance().activityColumnMoveUp((String) selectedItem);
        lvSelectedColumnsEvents.getSelectionModel().select(posOfSelectedItem + 1);
    }

    @FXML
    void btnMoveColumnEventsDown_Clicked(ActionEvent event) {
        Settings.getInstance().activityColumnMoveDown((String) selectedItem);
        lvSelectedColumnsEvents.getSelectionModel().select(posOfSelectedItem - 1);
    }

    @FXML
    void btnMoveColumnProtocolsDown_Clicked(ActionEvent event) {
        Settings.getInstance().protocolColumnMoveDown((String) selectedItem);
        lvSelectedColumnsProtocols.getSelectionModel().select(posOfSelectedItem + 1);
    }

    @FXML
    void btnMoveColumnProtocolsUp_Clicked(ActionEvent event) {
        Settings.getInstance().protocolColumnMoveUp((String) selectedItem);
        lvSelectedColumnsProtocols.getSelectionModel().select(posOfSelectedItem - 1);
    }

    @FXML
    void btnMoveColumnSponsorsDown_Clicked(ActionEvent event) {
        Settings.getInstance().sponsorColumnMoveDown((String) selectedItem);
        lvSelectedColumnsSponsors.getSelectionModel().select(posOfSelectedItem + 1);
    }

    @FXML
    void btnMoveColumnSponsorsUp_Clicked(ActionEvent event) {
        Settings.getInstance().sponsorColumnMoveUp((String) selectedItem);
        lvSelectedColumnsSponsors.getSelectionModel().select(posOfSelectedItem - 1);
    }

    @FXML
    void btnRemoveColumnClients_Clicked(ActionEvent event) { Settings.getInstance().klientColumnLoadOut((String) selectedItem); }

    @FXML
    void btnRemoveColumnEmployees_Clicked(ActionEvent event) { Settings.getInstance().employeeColumnLoadOut((String) selectedItem); }

    @FXML
    void btnRemoveColumnEvents_Clicked(ActionEvent event) { Settings.getInstance().activityColumnLoadOut((String) selectedItem); }

    @FXML
    void btnRemoveColumnSponsors_Clicked(ActionEvent event) { Settings.getInstance().sponsorColumnLoadOut((String) selectedItem); }

    @FXML
    void btnRemoveColumnProtocols_Clicked(ActionEvent event) { Settings.getInstance().protocolColumnLoadOut((String) selectedItem); }

    @FXML
    void btnSaveSettings_Clicked(ActionEvent event) {
        verwaltungCounter = 0;
        companyCounter = 0;
        commonCounter = 0;
        tfCheck(tfIbanCompany, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", tabCompanyData, null, companyCounter);
        tfCheck(tfBicCompany, "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?$", tabCompanyData, null, companyCounter);
        tfCheck(tfUidCompany, "^ATU(\\s)?[0-9]{8}$", tabCompanyData, null, companyCounter);
        tfCheck(tfHousenumberCompany, "(([0-9]+)([^0-9]*))?$", tabCompanyData, null, companyCounter);
        tfCheck(tfStreetCompany, "^\\D+$", tabCompanyData, null, companyCounter);
        tfCheck(tfZipCompany, "^[1-9][0-9]{3}$", tabCompanyData, null, companyCounter);
        tfCheck(tfPlaceCompany, "^\\D+$", tabCompanyData, null, companyCounter);

        if (companyCounter == 0 && commonCounter == 0){
            Settings.getInstance().setCompanyName(tfNameCompany.getText());
            Settings.getInstance().setIban(tfIbanCompany.getText());
            Settings.getInstance().setBic(tfBicCompany.getText());
            Settings.getInstance().setUid_Number(tfUidCompany.getText());
            Settings.getInstance().setStreet(tfStreetCompany.getText());
            Settings.getInstance().setNr(Integer.parseInt(tfHousenumberCompany.getText()));
            Settings.getInstance().setPlz(Integer.parseInt(tfZipCompany.getText()));
            Settings.getInstance().setLocation(tfPlaceCompany.getText());
            Stage stage = (Stage) btnSaveSettings.getScene().getWindow();
            stage.close();
        }
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
                case "tabAdministration":
                    verwaltungCounter++;
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
        //COMPANY DATA
        tfNameCompany.setText(Settings.getInstance().getCompanyName());
        tfUidCompany.setText(Settings.getInstance().getUid_Number());
        tfIbanCompany.setText(Settings.getInstance().getIban());
        tfBicCompany.setText(Settings.getInstance().getBic());
        tfStreetCompany.setText(Settings.getInstance().getStreet());
        tfHousenumberCompany.setText(""+Settings.getInstance().getNr());
        tfZipCompany.setText(""+Settings.getInstance().getPlz());
        tfPlaceCompany.setText(Settings.getInstance().getLocation());

        tabCompanyData.setText("");
        tabCompanyData.setGraphic(new Label("Firmendaten"));
        tabCommon.setText("");
        tabCommon.setGraphic(new Label("Allgemein"));
        tabAdministration.setText("");
        tabAdministration.setGraphic(new Label("Verwaltung"));

        //COMMON DATA
        comboFont.setItems(FXCollections.observableArrayList(Arrays.asList(FontStyle.values())));
        comboFont.getSelectionModel().select(FontStyle.STANDARD);

        colorAccent.setValue(Color.RED);

        //TABLES
        lvHourlyRates.setItems(Settings.getInstance().getHourlyRates());
        lvSalaryLevel.setItems(Settings.getInstance().getSalaryLevel());
        lvOccupationGroup.setItems(Settings.getInstance().getUserGroup());

        lvAvailableColumnsClients.setItems(Settings.getInstance().getKlientColumns());
        lvAvailableColumnsEmployees.setItems(Settings.getInstance().getEmployeeColumns());
        lvAvailableColumnsEvents.setItems(Settings.getInstance().getActivityColumns());
        lvAvailableColumnsProtocols.setItems(Settings.getInstance().getProtocolColumns());
        lvAvailableColumnsSponsors.setItems(Settings.getInstance().getSponsorColumns());

        lvSelectedColumnsClients.setItems(Settings.getInstance().getKlientColumnsLoaded());
        lvSelectedColumnsEmployees.setItems(Settings.getInstance().getEmployeeColumnsLoaded());
        lvSelectedColumnsEvents.setItems(Settings.getInstance().getActivityColumnsLoaded());
        lvSelectedColumnsProtocols.setItems(Settings.getInstance().getProtocolColumnsLoaded());
        lvSelectedColumnsSponsors.setItems(Settings.getInstance().getSponsorColumnsLoaded());

        setSelectionEvent(lvAvailableColumnsClients, lvSelectedColumnsClients, btnAddColumnClients, btnRemoveColumnClients, btnMoveColumnClientsUp, btnMoveColumnClientsDown);
        setSelectionEvent(lvAvailableColumnsEmployees, lvSelectedColumnsEmployees, btnAddColumnEmployees, btnRemoveColumnEmployees, btnMoveColumnEmployeesUp, btnMoveColumnEmployeesDown);
        setSelectionEvent(lvAvailableColumnsSponsors, lvSelectedColumnsSponsors, btnAddColumnSponsors, btnRemoveColumnSponsors, btnMoveColumnSponsorsUp, btnMoveColumnSponsorsDown);
        setSelectionEvent(lvAvailableColumnsProtocols, lvSelectedColumnsProtocols, btnAddColumnProtocols, btnRemoveColumnProtocols, btnMoveColumnProtocolsUp, btnMoveColumnProtocolsDown);
        setSelectionEvent(lvAvailableColumnsEvents, lvSelectedColumnsEvents, btnAddColumnEvents, btnRemoveColumnEvents, btnMoveColumnEventsUp, btnMoveColumnEventsDown);

        lvHourlyRates.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                selectedItem = newSelection;
                btnDeleteHourlyRate.setDisable(false);
                btnDeleteSalaryLevel.setDisable(true);
                btnDeleteOccupationGroup.setDisable(true);
            }
        });
        lvOccupationGroup.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                selectedItem = newSelection;
                btnDeleteOccupationGroup.setDisable(false);
                btnDeleteHourlyRate.setDisable(true);
                btnDeleteSalaryLevel.setDisable(true);

            }
        });
        lvSalaryLevel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                selectedItem = newSelection;
                btnDeleteSalaryLevel.setDisable(false);
                btnDeleteOccupationGroup.setDisable(true);
                btnDeleteHourlyRate.setDisable(true);
            }
        });
        }

    private void setSelectionEvent(ListView lvAvailable, ListView lvSelected, Button btnAdd, Button btnRemove, Button btnUp, Button btnDown){
        lvAvailable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAdd.setDisable(false);
                btnRemove.setDisable(true);
                btnUp.setDisable(true);
                btnDown.setDisable(true);
                selectedItem = newSelection;
                posOfSelectedItem = lvAvailable.getSelectionModel().getSelectedIndex();
                lvSelected.getSelectionModel().clearSelection();
            }
        });

        lvSelected.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAdd.setDisable(true);
                btnRemove.setDisable(false);
                btnUp.setDisable(false);
                btnDown.setDisable(false);
                selectedItem = newSelection;
                posOfSelectedItem = lvSelected.getSelectionModel().getSelectedIndex();
                lvAvailable.getSelectionModel().clearSelection();
            }
        });
    }
}
