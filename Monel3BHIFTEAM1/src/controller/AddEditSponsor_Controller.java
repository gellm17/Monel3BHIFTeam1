package controller;

import app.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

public class AddEditSponsor_Controller extends SceneLoader {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private Accordion accordionEmployees;

    @FXML
    private TitledPane tPaneBasicData;

    @FXML
    private ComboBox<?> comboSalutationSponsor;

    @FXML
    private TextField tfTitleSponsor;

    @FXML
    private TextField tfFirstnameSponsor;

    @FXML
    private TextField tfLastnameSponsor;

    @FXML
    private DatePicker dpBirthdateSponsor;

    @FXML
    private TitledPane tPaneAddress;

    @FXML
    private TextField tfStreetSponsor;

    @FXML
    private TextField tfHousenumberSponsor;

    @FXML
    private TextField tfZipSponsor;

    @FXML
    private TextField tfPlaceSponsor;

    @FXML
    private TitledPane tPaneContact;

    @FXML
    private TextField tfTelNrSponsor;

    @FXML
    private TextField tfEmailSponsor;

    @FXML
    private TitledPane tPaneInformation;

    @FXML
    private TextField tfNameCompany;

    @FXML
    private TextField tfTelNrCompany;

    @FXML
    private TextField tfEmailCopany;

    @FXML
    private ImageView imgSponsor;

    @FXML
    private Button btnDeleteImageSponsor;

    @FXML
    private Button btnSelectImageSponsor;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelSponsor;

    @FXML
    private Button btnOkSponsor;

    @FXML
    void btnCancelSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteImageSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSelectImageSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {
        openSettings();
    }

}

