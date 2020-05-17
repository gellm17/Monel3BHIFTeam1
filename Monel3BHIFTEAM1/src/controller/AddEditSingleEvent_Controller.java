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
import java.time.LocalTime;
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
        private ComboBox<Client> comboClientEvent;

        @FXML
        private ComboBox<Employee> comboEmployeeEvent;

        @FXML
        private TextField tfStartEvent;

        @FXML
        private TextField tfEndEvent;

        @FXML
        private TextField tfHourlyRateEvent;

        @FXML
        private TextField tfRideCostsEvent;

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
            if (editableEvent != null && editableEventProtocol != null) {
                dpDateEvent.setValue(editableEvent.getDate());
                tfNameEvent.setText(editableEvent.getName());
                tfStartEvent.setText(""+editableEventProtocol.getStartTime());
                tfEndEvent.setText(""+editableEventProtocol.getStartTime());
                tfHourlyRateEvent.setText(""+editableEventProtocol.getHourlyRate());
                tfRideCostsEvent.setText(""+editableEventProtocol.getRideCosts());
            }
        }

        @FXML
        void btnCancelEvent_Clicked(ActionEvent event) {

        }

        @FXML
        void btnInfo_Clicked(ActionEvent event) {

        }

        @FXML
        void btnOkEvent_Clicked(ActionEvent event) {
            Event eventToAdd = new Event();
            EventProtocol eventProtocolToAdd = new EventProtocol();
            errorCounter = 0;

            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                eventToAdd.setDate(ldsc.fromString(dpDateEvent.getEditor().getText()));
                eventProtocolToAdd.setYear_month(ldsc.fromString(dpDateEvent.getEditor().getText()));
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

            if (!tfCheck(tfHourlyRateEvent, "^\\d{1,8}([\\.,]\\d{2})?$")){
                eventProtocolToAdd.setHourlyRate(Double.parseDouble(tfHourlyRateEvent.getText()));
            }

            if (!tfCheck(tfRideCostsEvent, "^\\d{1,8}([\\.,]\\d{2})?$")){
                eventProtocolToAdd.setRideCosts(Double.parseDouble(tfRideCostsEvent.getText()));
            }

            if (errorCounter == 0 && EventDAO.getInstance().addEvent(eventToAdd) && EventDAO.getInstance().addEventProtcol(eventProtocolToAdd)) {
                if (editableEvent != null && editableEventProtocol != null) {
                    EventDAO.getInstance().deleteEvent(editableEvent);
                    EventDAO.getInstance().deleteEventProtcol(editableEventProtocol);
                }
                super.showScene("EmployeeList");
            }
        }

        @FXML
        void btnSettings_Clicked(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
        comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
    }
}
