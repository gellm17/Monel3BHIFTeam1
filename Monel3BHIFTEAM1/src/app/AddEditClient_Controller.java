package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddEditClient_Controller {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private Button btnDeleteImageClients;

    @FXML
    private Button btnSelectImageClients;

    @FXML
    private TextField tfTitleClient;

    @FXML
    private TextField tfFirstnameClient;

    @FXML
    private TextField tfLastnameClient;

    @FXML
    private TextField tfSsnrClient;

    @FXML
    private DatePicker dpBirthdateClient;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfHousenumber;

    @FXML
    private TextField tfPLZ;

    @FXML
    private TextField tfCity;

    @FXML
    private CheckBox cbPrivacy1Clients;

    @FXML
    private CheckBox cbPrivacy2Clients;

    @FXML
    private CheckBox cbPrivacy3Clients;

    @FXML
    private CheckBox cbPrivacy4Clients;

    @FXML
    private TabPane tbEmergencyContactsClients;

    @FXML
    private Tab tabEmergencyContact1;

    @FXML
    private TextField tfFirstnameClient1;

    @FXML
    private TextField tfLastnameClient1;

    @FXML
    private Tab tabEmergencyContact2;

    @FXML
    void btnDeleteImageClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSelectImageClients_Clicked(ActionEvent event) {

    }

}
