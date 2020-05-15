package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.Client;
import model.Employee;
import model.Event;
import model.EventProtocol;

public class AddEditEventProtocol_Controller {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private DatePicker dpDateProtocol;

    @FXML
    private TextField tfStartProtocol;

    @FXML
    private TextField tfEndProtocol;

    @FXML
    private ComboBox<Client> comboClientProtocol;

    @FXML
    private ComboBox<Employee> comboEmployeeProtocol;

    @FXML
    private TextField tfHourlyRateProtocol;

    @FXML
    private ComboBox<?> comboEventProtocol;

    @FXML
    private Button btnAddEvent;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelProtocol;

    @FXML
    private Button btnOkProtocol;

    private EventProtocol editableEventProtocol = null;
    private int errorCounter = 0;

    public EventProtocol getEditableEventProtocol() {
        return editableEventProtocol;
    }

    public void setEditableEventProtocol(EventProtocol editableEventProtocol) {
        this.editableEventProtocol = editableEventProtocol;
        if (editableEventProtocol != null){
            tfHourlyRateProtocol.setText(""+editableEventProtocol.getHourlyRate());
        }
    }

    @FXML
    void btnCancelProtocol_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkProtocol_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }


    private boolean tfCheck(TextField tf, String regex){
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

    private boolean taCheck(TextArea ta, String regex){
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
