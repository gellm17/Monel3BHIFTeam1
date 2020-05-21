package controller;

import app.SceneLoader;
import data.EventDAO;
import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import model.Client;
import model.Employee;
import model.Event;
import model.EventProtocol;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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
        if (editableEventProtocol != null) {
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

        if (!tfCheck(tfHourlyRateEvent, "^\\d{1,8}([\\.,]\\d{2})?$")){
            eventProtocolToAdd.setHourlyRate(Double.parseDouble(tfHourlyRateEvent.getText()));
        }

        if (!tfCheck(tfRideCostsEvent, "^\\d{1,8}([\\.,]\\d{2})?$")){
            eventProtocolToAdd.setRideCosts(Double.parseDouble(tfRideCostsEvent.getText()));
        }

        eventProtocolToAdd.setEvent(assignedEvent);

        if (errorCounter == 0 && EventDAO.getInstance().addEventProtcol(eventProtocolToAdd)) {
            if (editableEventProtocol != null) {
                EventDAO.getInstance().deleteEventProtcol(editableEventProtocol);
            }

            showSceneBefore();
        }
    }

    private void showSceneBefore() {
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
            editController.setEditableEvent(assignedEvent);
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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboClientEvent.getItems().setAll(PersonDAO.getInstance().getClients());
        comboEmployeeEvent.getItems().setAll(PersonDAO.getInstance().getEmployees());
    }

}
