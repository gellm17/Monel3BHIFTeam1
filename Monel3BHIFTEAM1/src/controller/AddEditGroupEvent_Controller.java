package controller;

import app.SceneLoader;
import data.EventDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.util.converter.LocalDateStringConverter;
import model.Employee;
import model.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEditGroupEvent_Controller extends SceneLoader implements Initializable {

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
    private TableView<?> tableProtocols;

    @FXML
    private Button btnAddProtocol;

    @FXML
    private Button btnDeleteProtocol;

    @FXML
    private Button btnEditProtocol;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelEvent;

    @FXML
    private Button btnOkEvent;

    private Event editableEvent = null;
    private Event thisEvent = null;
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
    void btnAddProtocol_Clicked(ActionEvent event) {
        try {

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditEmployee.fxml"));
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


            AddEditEventProtocol_Controller editController = fxml.getController();
            editController.setAssignedEvent(thisEvent);


            SceneLoader loader = editController;
            loader.setPrimaryStage(this.getPrimStage());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void btnCancelEvent_Clicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteProtocol_Clicked(ActionEvent event) {

    }

    @FXML
    void btnEditProtocol_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkEvent_Clicked(ActionEvent event) {
        errorCounter = 0;

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            thisEvent.setDate(ldsc.fromString(dpDateEvent.getEditor().getText()));
            dpDateEvent.setStyle(null);
        } catch (Exception e) {
            dpDateEvent.setStyle("-FX-Border-Color: red");
            errorCounter++;
            lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
        }

        if (!tfCheck(tfNameEvent, "^.+$")) {
            thisEvent.setName(tfNameEvent.getText());
        }

        thisEvent.setIsGroup(true);

        if (errorCounter == 0 && EventDAO.getInstance().addEvent(thisEvent)) {
            if (editableEvent != null) {
                EventDAO.getInstance().deleteEvent(editableEvent);
            }
            super.showScene("EventList");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thisEvent = new Event();
    }
}
