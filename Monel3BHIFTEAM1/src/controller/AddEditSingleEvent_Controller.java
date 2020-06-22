package controller;

import app.SceneLoader;
import data.EventDAO;
import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddEditSingleEvent_Controller extends SceneLoader implements Initializable {


    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private DatePicker dpDateEvent;

    @FXML
    private TextField tfNameEvent;

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
    private ComboBox<?> comboTaxesHourlyRate;

    @FXML
    private TextField tfRideCosts;

    @FXML
    private ToggleButton tglBtnRideCostBrutto;

    @FXML
    private ToggleButton tglBtnRideCostNetto;

    @FXML
    private ComboBox<?> comboTaxesRideCost;

    @FXML
    private TextField tfOtherCosts;

    @FXML
    private ToggleButton tglBtnOtherCostsBrutto;

    @FXML
    private ToggleButton tglBtnOtherCostsNetto;

    @FXML
    private ComboBox<?> comboTaxesOtherCosts;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelEvent;

    @FXML
    private Button btnOkEvent;

        private Event editableEvent = null;
        private EventProtocol editableEventProtocol = null;
        private int errorCounter = 0;

        public Event getEditableEvent() {
            return editableEvent;
        }
        public EventProtocol getEditableEventProtocol() {
        return editableEventProtocol;
    }

        public void setEditableEvent(Event editableEvent, EventProtocol editableEventProtocol) {
            this.editableEvent = editableEvent;
            this.editableEventProtocol = editableEventProtocol;
            comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
            comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
            if (editableEvent != null) {
                dpDateEvent.setValue(editableEvent.getDate());
                tfNameEvent.setText(editableEvent.getName());
            }
            if (editableEventProtocol != null){
                comboClientEvent.getSelectionModel().select(editableEventProtocol.getClient());
                comboEmployeeEvent.getSelectionModel().select(editableEventProtocol.getEmployee());
                tfStartEvent.setText(""+editableEventProtocol.getStartTime());
                tfEndEvent.setText(""+editableEventProtocol.getEndTime());
                //TODO tfHourlyRateEvent.setText(""+String.format(Locale.ROOT, "%.2f", editableEventProtocol.getHourlyRate())); // TODO
                tfRideCosts.setText(""+String.format(Locale.ROOT, "%.2f", editableEventProtocol.getRideCosts()));
            }
        }

        @FXML
        void btnCancelEvent_Clicked(ActionEvent event) {
            showScene("EventList");
        }

        @FXML
        void btnInfo_Clicked(ActionEvent event) {

        }

        @FXML
        void btnOkEvent_Clicked(ActionEvent event) throws SQLException {
            Event eventToAdd = new Event();
            EventProtocol eventProtocolToAdd = new EventProtocol();
            errorCounter = 0;

            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                LocalDate date = ldsc.fromString(dpDateEvent.getEditor().getText());
                eventToAdd.setDate(date);
                if (date.getMonthValue() < 10){
                    eventProtocolToAdd.setYear_month("0" + date.getMonthValue() + "/" + date.getYear());
                } else {
                    eventProtocolToAdd.setYear_month(date.getMonthValue() + "/" + date.getYear());
                }

                dpDateEvent.setStyle(null);
            } catch (Exception e) {
                dpDateEvent.setStyle("-FX-Border-Color: red");
                errorCounter++;
                lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
            }

            if (!tfCheck(tfNameEvent, "^.+$")) {
                eventToAdd.setName(tfNameEvent.getText());
            }

            eventToAdd.setIsGroup(false);
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
            //TODO
            /*if (!tfCheck(tfHourlyRateEvent, "^\\d{1,8}([\\.,]\\d{2})?$")){  // TODO
                eventProtocolToAdd.setHourlyRate(Double.parseDouble(tfHourlyRateEvent.getText()));
            }*/

            if (!tfCheck(tfRideCosts, "^\\d{1,8}([\\.,]\\d{2})?$")){
                eventProtocolToAdd.setRideCosts(Double.parseDouble(tfRideCosts.getText()));
            }

            eventProtocolToAdd.setEvent(eventToAdd);

            if (editableEventProtocol != null){
                eventProtocolToAdd.setId(editableEventProtocol.getId());
            }
            if (editableEvent != null){
                eventToAdd.setId(editableEvent.getId());
            }


            if (errorCounter == 0 && EventDAO.getInstance().addEvent(eventToAdd) && EventDAO.getInstance().addEventProtcol(eventProtocolToAdd)) {
                if (editableEvent != null && editableEventProtocol != null) {
                    EventDAO.getInstance().deleteEvent(editableEvent);
                    EventDAO.getInstance().deleteEventProtcol(editableEventProtocol);
                }
                super.showScene("EventList");
            }
        }

        @FXML
        void btnSettings_Clicked(ActionEvent event) {
            openSettings();
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

        private boolean taCheck(TextArea ta, String regex) {
            boolean error = true;
            if (!ta.getText().matches(regex)) {
                error = true;
                errorCounter++;
                ta.setStyle("-FX-Border-Color: red");
                lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
            } else {
                error = false;
                ta.setStyle(null);
            }
            return error;
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
    void tglBtnOtherCostsBrutto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnOtherCostsBrutto, tglBtnOtherCostsNetto);
    }

    @FXML
    void tglBtnOtherCostsNetto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnOtherCostsNetto, tglBtnOtherCostsBrutto);
    }

    @FXML
    void tglBtnRideCostBrutto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnRideCostBrutto, tglBtnRideCostNetto);
    }

    @FXML
    void tglBtnRideCostNetto_onAction(ActionEvent event) {
        tglBtnChange(tglBtnRideCostNetto, tglBtnRideCostBrutto);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
        comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
        comboHourlyRate.getItems().setAll(Settings.getInstance().getHourlyRates());
    }
}
