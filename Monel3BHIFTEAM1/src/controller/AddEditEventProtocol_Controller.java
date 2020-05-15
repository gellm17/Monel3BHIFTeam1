package controller;

import app.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddEditEventProtocol_Controller extends SceneLoader {

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

    @FXML
    void btnCancelEvent_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkEvent_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}
