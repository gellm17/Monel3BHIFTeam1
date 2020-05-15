package controller;

import app.SceneLoader;
import data.EventDAO;
import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import model.Employee;
import model.Event;

public class AddEditSingleEvent_Controller extends SceneLoader {

    public class AddEditEvent_Controller extends SceneLoader {


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
        private ComboBox<?> comboClientEvent;

        @FXML
        private ComboBox<?> comboEmployeeEvent;

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
        private int errorCounter = 0;

        public Event getEditableEvent() {
            return editableEvent;
        }

        public void setEditableEvent(Event editableEvent) {
            this.editableEvent = editableEvent;
            if (editableEvent != null) {
                dpDateEvent.setValue(editableEvent.getDate());
                tfNameEvent.setText(editableEvent.getName());
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
            errorCounter = 0;

            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                eventToAdd.setDate(ldsc.fromString(dpDateEvent.getEditor().getText()));
                dpDateEvent.setStyle(null);
            } catch (Exception e) {
                dpDateEvent.setStyle("-FX-Border-Color: red");
                errorCounter++;
                lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
            }

            if (!tfCheck(tfNameEvent, "^.+$")) {
                eventToAdd.setName(tfNameEvent.getText());
            }

            if (errorCounter == 0 && EventDAO.getInstance().addEvent(eventToAdd)) {
                if (editableEvent != null) {
                    EventDAO.getInstance().deleteEvent(editableEvent);
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
    }
}
