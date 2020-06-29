package controller;

import app.SceneLoader;
import data.CostDAO;
import data.EventDAO;
import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddEditEventProtocol_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private TextArea taDescription;

    @FXML
    private ComboBox<Client> comboClientEvent;

    @FXML
    private ComboBox<Employee> comboEmployeeEvent;

    @FXML
    private TextField tfStartEvent;

    @FXML
    private TextField tfEndEvent;

    @FXML
    private ComboBox<Double> comboHourlyRate;

    @FXML
    private ToggleButton tglBtnHourlyRateBrutto;

    @FXML
    private ToggleButton tglBtnHourlyRateNetto;

    @FXML
    private ComboBox comboRideCostRate;

    @FXML
    private TextField tfRideCostKm;

    @FXML
    private ToggleButton tglBtnRideCostBrutto;

    @FXML
    private ToggleButton tglBtnRideCostNetto;

    @FXML
    private TextField tfNameOtherCost;

    @FXML
    private TextField tfValueOtherCost;

    @FXML
    private ComboBox<Integer> comboTaxesOtherCost;

    @FXML
    private Button btnAddOtherCost;

    @FXML
    private Button btnDeleteOtherCost;

    @FXML
    private ListView<Costs> lvOtherCosts;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelEvent;

    @FXML
    private Button btnOkEvent;

    private Object selectedItem;
    private EventProtocol editableEventProtocol = null;
    private Event assignedEvent = null;
    private int errorCounter = 0;

    public EventProtocol getEditableEventProtocol() {
        return editableEventProtocol;
    }

    public void setAssignedEvent(Event assignedEvent){
        this.assignedEvent = assignedEvent;
    }

    public void setEditableEvent(EventProtocol editableEventProtocol) {
        this.editableEventProtocol = editableEventProtocol;
        comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
        comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
        comboHourlyRate.getItems().setAll(Settings.getInstance().getHourlyRates());
        comboClientEvent.getSelectionModel().select(editableEventProtocol.getClient());
        comboEmployeeEvent.getSelectionModel().select(editableEventProtocol.getEmployee());
        if (editableEventProtocol != null) {
            tfStartEvent.setText(""+editableEventProtocol.getStartTime());
            tfEndEvent.setText(""+editableEventProtocol.getEndTime());
            comboHourlyRate.getSelectionModel().select(editableEventProtocol.getHourlyRate());
            tfRideCostKm.setText(""+editableEventProtocol.getKm());
            lvOtherCosts.setItems(CostDAO.getInstance().getCostsByEventProtocol(editableEventProtocol));
        }
    }

    @FXML
    void btnCancelEvent_Clicked(ActionEvent event) {
        showSceneBefore(assignedEvent);
    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkEvent_Clicked(ActionEvent event) throws SQLException {
        EventProtocol eventProtocolToAdd = new EventProtocol();
        errorCounter = 0;

        eventProtocolToAdd.setClient(comboClientEvent.getSelectionModel().getSelectedItem());
        eventProtocolToAdd.setEmployee(comboEmployeeEvent.getSelectionModel().getSelectedItem());

        if (!tfCheck(tfStartEvent, "^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            eventProtocolToAdd.setStartTime(LocalTime.parse(tfStartEvent.getText()));
        }

        if (!tfCheck(tfEndEvent, "^([0-1][0-9]|[2][0-3]):([0-5][0-9])$")){
            if (LocalTime.parse(tfEndEvent.getText()).isAfter(eventProtocolToAdd.getStartTime())){
                eventProtocolToAdd.setEndTime(LocalTime.parse(tfEndEvent.getText()));
            } else {
                lbMessage.setText(lbMessage.getText() + ", Endzeit später als Startzeit");
                errorCounter++;
            }
        }

        eventProtocolToAdd.setHourlyRate(comboHourlyRate.getSelectionModel().getSelectedItem());

        eventProtocolToAdd.setMileage(comboRideCostRate.getSelectionModel().getSelectedIndex());

        if (!tfCheck(tfRideCostKm, "^[1-9][0-9]*$")){
            eventProtocolToAdd.setKm(Integer.parseInt(tfRideCostKm.getText()));
        }

        if (assignedEvent.getDate().getMonthValue() < 10) {
            eventProtocolToAdd.setYear_month(assignedEvent.getDate().getYear() + "-0" + assignedEvent.getDate().getMonthValue());
        } else {
            eventProtocolToAdd.setYear_month(assignedEvent.getDate().getYear() + "-" + assignedEvent.getDate().getMonthValue());
        }

        eventProtocolToAdd.setEvent(assignedEvent);

        if (editableEventProtocol != null){
            eventProtocolToAdd.setId(editableEventProtocol.getId());
        }

        for (Costs c: lvOtherCosts.getItems()) {
            c.setEventprotocol(eventProtocolToAdd);
        }

        if (errorCounter == 0 && EventDAO.getInstance().addEventProtcol(eventProtocolToAdd)) {
            if (editableEventProtocol != null) {
                EventDAO.getInstance().deleteEventProtcol(editableEventProtocol);
            }

            showSceneBefore(eventProtocolToAdd.getEvent());
        }
    }

    private void showSceneBefore(Event event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditGroupEvent.fxml"));
            BorderPane root = fxml.load();
            Scene scene = new Scene(root);
            this.getPrimStage().setScene(scene);
            Screen screen = Screen.getPrimary();

            //Maximized
            Rectangle2D bounds = screen.getVisualBounds();
            this.getPrimStage().setX(bounds.getMinX());
            this.getPrimStage().setY(bounds.getMinY());
            this.getPrimStage().setWidth(bounds.getWidth());
            this.getPrimStage().setHeight(bounds.getHeight());
            this.getPrimStage().show();
            AddEditGroupEvent_Controller editController = fxml.getController();
            editController.setEditableEvent(event);
            SceneLoader loader = editController;
            loader.setPrimaryStage(this.getPrimStage());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean tfCheck(TextField tf, String regex) {
        boolean error = true;
        if (!tf.getText().matches(regex)) {
            error = true;
            errorCounter++;
            tf.setStyle("-FX-Border-Color: red");
            lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
        } else {
            error = false;
            tf.setStyle(null);
        }
        return error;
    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {
        openSettings();
    }

    private void tglBtnChange(ToggleButton currentTglBtn, ToggleButton pendantTglBtn){
        if (currentTglBtn.isPressed()){
            pendantTglBtn.setSelected(true);
            currentTglBtn.setSelected(false);
        } else {
            pendantTglBtn.setSelected(false);
            currentTglBtn.setSelected(true);
        }
    }

    @FXML
    void tglBtnHourlyRateBrutto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnHourlyRateBrutto, tglBtnHourlyRateNetto);
    }

    @FXML
    void tglBtnHourlyRateNetto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnHourlyRateNetto, tglBtnHourlyRateBrutto);
    }

    @FXML
    void tglBtnRideCostBrutto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnRideCostBrutto, tglBtnRideCostNetto);
    }

    @FXML
    void tglBtnRideCostNetto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnRideCostNetto, tglBtnRideCostBrutto);
    }

    @FXML
    void btnAddOtherCost_Clicked(ActionEvent event) {
        Costs cToAdd = new Costs();
        int counter = 0;

        if (!tfCheck(tfValueOtherCost, "^\\d{1,8}([\\.,]\\d{2})?$")){
            cToAdd.setamount(Double.parseDouble(tfValueOtherCost.getText()));
        } else {
            counter++;
        }

        cToAdd.setDescription(tfNameOtherCost.getText());

        cToAdd.setTaxrate(comboTaxesOtherCost.getSelectionModel().getSelectedItem());


        if (counter == 0) {
            lvOtherCosts.getItems().add(cToAdd);
        }
    }

    @FXML
    void btnDeleteOtherCost_Clicked(ActionEvent event) {
        try {
            CostDAO.getInstance().deleteCost((Costs) selectedItem);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
        comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
        comboHourlyRate.getItems().setAll(Settings.getInstance().getHourlyRates());
        comboRideCostRate.getItems().setAll(Settings.getInstance().getRideCostRate());
        comboTaxesOtherCost.getItems().setAll(Settings.getInstance().getTaxRates());

        lvOtherCosts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                selectedItem = newSelection;
                btnDeleteOtherCost.setDisable(false);
            }
        });
    }

}
