package app;

import data.PersonDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.Client;
import model.Privacy;
import model.Salutation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddEditClient_Controller extends SceneLoader implements Initializable{

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private ComboBox<Salutation> comboSalutationClient;

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
    private TextField tfStreetClient;

    @FXML
    private TextField tfHousenumberClient;

    @FXML
    private TextField tfZipClient;

    @FXML
    private TextField tfPlaceClient;

    @FXML
    private CheckBox cbPrivacy1Client;

    @FXML
    private CheckBox cbPrivacy2Client;

    @FXML
    private CheckBox cbPrivacy3Client;

    @FXML
    private CheckBox cbPrivacy4Client;

    @FXML
    private TabPane tbEmergencyContactsClients;

    @FXML
    private Tab tabEsv;

    @FXML
    private ComboBox<Salutation> comboSalutationEsv;

    @FXML
    private TextField tfTitleEsv;

    @FXML
    private TextField tfFirstnameEsv;

    @FXML
    private TextField tfLastnameEsv;

    @FXML
    private TextField tfSsnrEsv;

    @FXML
    private DatePicker dpBirthdateEsv;

    @FXML
    private TextField tfStreetEsv;

    @FXML
    private TextField tfHousenumberEsv;

    @FXML
    private TextField tfZipEsv;

    @FXML
    private TextField tfPlaceEsv;

    @FXML
    private TextField tfTelNrEsv;

    @FXML
    private TextField tfEmailEsv;

    @FXML
    private Tab tabEmergencyContact1;

    @FXML
    private ComboBox<Salutation> comboSalutationContact1;

    @FXML
    private TextField tfTitleContact1;

    @FXML
    private TextField tfFirstnameContact1;

    @FXML
    private TextField tfLastnameContact1;

    @FXML
    private TextField tfSsnrContact1;

    @FXML
    private DatePicker dpBirthdateContact1;

    @FXML
    private TextField tfStreetContact1;

    @FXML
    private TextField tfHousenumberContact1;

    @FXML
    private TextField tfZipContact1;

    @FXML
    private TextField tfPlaceContact1;

    @FXML
    private TextField tfTelNrContact1;

    @FXML
    private TextField tfEmailContact1;

    @FXML
    private Tab tabEmergencyContact2;

    @FXML
    private ComboBox<Salutation> comboSalutationContact2;

    @FXML
    private TextField tfTitleContact2;

    @FXML
    private TextField tfFirstnameContact2;

    @FXML
    private TextField tfLastnameContact2;

    @FXML
    private TextField tfSsnrContact2;

    @FXML
    private DatePicker dpBirthdateContact2;

    @FXML
    private TextField tfStreetContact2;

    @FXML
    private TextField tfHousenumberContact2;

    @FXML
    private TextField tfZipContact2;

    @FXML
    private TextField tfPlaceContact2;

    @FXML
    private TextField tfTelNrContact2;

    @FXML
    private TextField tfEmailContact2;

    @FXML
    private TextField tfTelNrClient;

    @FXML
    private TextField tfEmailClient;

    @FXML
    private TextField tfDiagnoseClient;

    @FXML
    private TextField tfJobClient;

    @FXML
    private TextField tfAllergiesClient;

    @FXML
    private TextField tfOtherClient;

    @FXML
    private TextField tfIbanClient;

    @FXML
    private TextField tfBicClient;

    @FXML
    private Button btnDeleteImageClients;

    @FXML
    private Button btnSelectImageClients;

    @FXML
    private Button btnOkClient;

    @FXML
    private Button btnCancelClient;


    private Client editableClient = null;

    public Client getEditableClient() {
        return editableClient;
    }

    public void setEditableClient(Client editableClient) {
        this.editableClient = editableClient;
        comboSalutationClient.getItems().setAll(Salutation.values());
        comboSalutationContact1.getItems().setAll(Salutation.values());
        comboSalutationContact2.getItems().setAll(Salutation.values());
        comboSalutationEsv.getItems().setAll(Salutation.values());
        if (editableClient != null) {
            comboSalutationClient.getSelectionModel().select(editableClient.getSalutation());
            tfTitleClient.setText(editableClient.getTitle());
            tfFirstnameClient.setText(editableClient.getFirstName());
            tfLastnameClient.setText(editableClient.getLastName());
            tfSsnrClient.setText(""+editableClient.getSsnr());
            dpBirthdateClient.setValue(editableClient.getBirthDate());
            //ADDRESS
            tfStreetClient.setText(editableClient.getStreetAndNr().split(" ")[0]);
            tfHousenumberClient.setText(editableClient.getStreetAndNr().split(" ")[1]);
            tfZipClient.setText(""+editableClient.getZipCode());
            tfPlaceClient.setText(editableClient.getPlace());
            //PRIVACY
            Privacy privacyOfEditableClient = editableClient.getPrivacy();
            /*cbPrivacy1Client.setSelected(cbPrivacy1Client.isSelected() == privacyOfEditableClient.getPrivacies().get(0));
            cbPrivacy2Client.setSelected(cbPrivacy2Client.isSelected() == privacyOfEditableClient.getPrivacies().get(1));
            cbPrivacy3Client.setSelected(cbPrivacy3Client.isSelected() == privacyOfEditableClient.getPrivacies().get(2));
            cbPrivacy4Client.setSelected(cbPrivacy4Client.isSelected() == privacyOfEditableClient.getPrivacies().get(3));*/
            //CONTACT
            tfTelNrClient.setText(editableClient.getTelNr());
            tfEmailClient.setText(editableClient.getEmail());
            //INFO
            tfDiagnoseClient.setText(editableClient.getDiagnose());
            tfJobClient.setText(editableClient.getJob());
            tfAllergiesClient.setText(editableClient.getAllergies());
            //BANK
            tfBicClient.setText(editableClient.getBic());
            tfIbanClient.setText(editableClient.getIban());
        }
    }

    @FXML
    void btnCancelClient_Clicked(ActionEvent event) {
        showScene("MainWindow");
    }

    @FXML
    void btnDeleteImageClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkClient_Clicked(ActionEvent event) {
        Client clientToAdd = null;
        ArrayList<Boolean> errors = new ArrayList<Boolean>();

        if (comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Herr && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Frau && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Firma && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Sonstige) {
            comboSalutationClient.setStyle("-FX-Border-Color: red");
            errors.add(true);
        } else {
            comboSalutationClient.setStyle(null);
            errors.add(false);
        }

        errors.add(tfCheck(tfFirstnameClient));
        errors.add(tfCheck(tfLastnameClient));
        errors.add(tfCheck(tfSsnrClient));

        if (dpBirthdateClient.getValue() == null) {
            dpBirthdateClient.setStyle("-FX-Border-Color: red");
            errors.add(true);
        } else {
            dpBirthdateClient.setStyle(null);
            errors.add(false);
        }

        errors.add(tfCheck(tfZipClient));
        errors.add(tfCheck(tfHousenumberClient));
        errors.add(tfCheck(tfStreetClient));
        errors.add(tfCheck(tfPlaceClient));

        errors.add(tfCheck(tfIbanClient));
        errors.add(tfCheck(tfBicClient));

        if (!errors.contains(true)){
            clientToAdd = new Client(   comboSalutationClient.getSelectionModel().getSelectedItem(),
                                        tfFirstnameClient.getText(),
                                        tfLastnameClient.getText(),
                                        tfStreetClient.getText(),
                                        tfHousenumberClient.getText(),
                                        Integer.parseInt(tfZipClient.getText()),
                                        tfPlaceClient.getText(),
                                        dpBirthdateClient.getValue(),
                                        Integer.parseInt(tfSsnrClient.getText()),
                                        tfBicClient.getText(),
                                        tfIbanClient.getText());
        }

        if (!tfTitleClient.getText().equals("") && clientToAdd != null){
            clientToAdd.setTitle(tfTitleClient.getText());
        }
        if (!tfTelNrClient.getText().equals("") && clientToAdd != null) {
            clientToAdd.setTelNr(tfTelNrClient.getText());
        }
        if (!tfEmailClient.getText().equals("") && clientToAdd != null) {
            clientToAdd.setEmail(tfEmailClient.getText());
        }

        if(PersonDAO.getInstance().addPerson(clientToAdd)){
            if (editableClient != null) {
                PersonDAO.getInstance().deletePerson(editableClient);
            }
            super.showScene("MainWindow");
        }

    }

    @FXML
    void btnSelectImageClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

    private boolean tfCheck(TextField tf){
        boolean error = true;
        if (tf.getText().equals("")){
            tf.setStyle("-FX-Border-Color: red");
            error = true;
        } else {
            tf.setStyle(null);
            error = false;
        }
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboSalutationClient.getItems().setAll(Salutation.values());
        comboSalutationContact1.getItems().setAll(Salutation.values());
        comboSalutationContact2.getItems().setAll(Salutation.values());
        comboSalutationEsv.getItems().setAll(Salutation.values());
    }
}
