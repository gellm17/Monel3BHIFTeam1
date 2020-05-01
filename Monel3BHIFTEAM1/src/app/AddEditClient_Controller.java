package app;

import data.PersonDAO;
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
    private ArrayList<Boolean> errors = new ArrayList<Boolean>();

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
            Privacy privacyOfEditableClient = new Privacy();
            if (editableClient.getPrivacy() != null) {
                privacyOfEditableClient = editableClient.getPrivacy();
            }
            cbPrivacy1Client.setSelected(privacyOfEditableClient.getPrivacies().get(0));
            cbPrivacy2Client.setSelected(privacyOfEditableClient.getPrivacies().get(1));
            cbPrivacy3Client.setSelected(privacyOfEditableClient.getPrivacies().get(2));
            cbPrivacy4Client.setSelected(privacyOfEditableClient.getPrivacies().get(3));
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

        /*if (comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Herr && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Frau && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Firma && comboSalutationClient.getSelectionModel().getSelectedItem() != Salutation.Sonstige) {
            comboSalutationClient.setStyle("-FX-Border-Color: red");
            errors.add(true);
        } else {
            comboSalutationClient.setStyle(null);
            errors.add(false);
        }*/

        errors.add(tfCheck(tfFirstnameClient, "^\\D+$", true));
        errors.add(tfCheck(tfLastnameClient, "^\\D+$", true));

        if (!errors.contains(true)){
            clientToAdd = new Client(   comboSalutationClient.getSelectionModel().getSelectedItem(),
                    tfFirstnameClient.getText(),
                    tfLastnameClient.getText()
            );}

        if (!tfCheck(tfTitleClient, "^\\D+$", false)) {
            clientToAdd.setTitle(tfTitleClient.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfSsnrClient, "^[1-9][0-9]{3}$", false)) {
            clientToAdd.setTitle(tfTitleClient.getText());
        } else {
            errors.add(true);
        }


        /*if (dpBirthdateClient.getValue() == null) {
            dpBirthdateClient.setStyle("-FX-Border-Color: red");
            errors.add(true);
        } else {
            dpBirthdateClient.setStyle(null);
            errors.add(false);
        }*/
        //errors.add(tfCheck(tfSsnrClient));



        errors.add(tfCheck(tfZipClient, "^[1-9][0-9]{3}$", false));


        errors.add(tfCheck(tfHousenumberClient, "^[1-9][0-9]{0-3}$", false));
        errors.add(tfCheck(tfStreetClient, "^\\D+$", false));
        errors.add(tfCheck(tfPlaceClient, "^\\D+$", false));

        /*errors.add(tfCheck(tfIbanClient));
        errors.add(tfCheck(tfBicClient));*/


        if (!tfIbanClient.getText().equals("") && clientToAdd != null) {
            errors.add(tfCheck(tfIbanClient, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", false));
            clientToAdd.setIban(tfIbanClient.getText());
        }
        if (!tfTitleClient.getText().equals("") && clientToAdd != null){
            clientToAdd.setTitle(tfTitleClient.getText());
        }
        if (!tfTelNrClient.getText().equals("") && clientToAdd != null) {
            clientToAdd.setTelNr(tfTelNrClient.getText());
        }
        if (!tfEmailClient.getText().equals("") && clientToAdd != null) {
            errors.add(tfCheck(tfEmailClient, "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", false));
            clientToAdd.setEmail(tfEmailClient.getText());
        }

        clientToAdd.setPrivacy(new Privacy(new ArrayList<Boolean>(){{add(cbPrivacy1Client.isSelected()); add(cbPrivacy2Client.isSelected()); add(cbPrivacy3Client.isSelected()); add(cbPrivacy4Client.isSelected());}}));

        if(PersonDAO.getInstance().addPerson(clientToAdd) && !errors.contains(true)){
            if (editableClient != null) { PersonDAO.getInstance().deletePerson(editableClient);
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

    private boolean tfCheck(TextField tf, String regex, boolean must){
        boolean error = true;
            if ((tf.getText().equals("") && must) || !tf.getText().matches(regex)) {
                //when it not matches the pattern (1.0 - 6.0)
                error = true;
                tf.setStyle("-FX-Border-Color: red");
            } else {
                error = false;
                tf.setStyle(null);
            }
        return error;
    }
    @FXML
    void btnDeleteImageClient_Clicked(ActionEvent event) {

    }

    private void addFocusedProperty(TextField tf, String regex, boolean must){
        tf.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                errors.add(tfCheck(tf, regex, must));
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboSalutationClient.getItems().setAll(Salutation.values());
        comboSalutationContact1.getItems().setAll(Salutation.values());
        comboSalutationContact2.getItems().setAll(Salutation.values());
        comboSalutationEsv.getItems().setAll(Salutation.values());

        comboSalutationClient.getSelectionModel().select(0);
        comboSalutationContact1.getSelectionModel().select(0);
        comboSalutationContact2.getSelectionModel().select(0);
        comboSalutationEsv.getSelectionModel().select(0);

        /*tfSsnrClient.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                errors.add(tfCheck(tfSsnrClient, "^[1-9][0-9]{3}$"));
            }
        });*/

        addFocusedProperty(tfSsnrClient, "^[1-9][0-9]{3}$", false);
        addFocusedProperty(tfZipClient, "^[1-9][0-9]{3}$", false);
        addFocusedProperty(tfEmailClient, "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", false);
        addFocusedProperty(tfIbanClient, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", false);

        /*tfZipClient.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                errors.add(tfCheck(tfZipClient, "^[1-9][0-9]{3}$"));
            }
        });

        tfEmailClient.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if (!tfEmailClient.getText().equals("")){
                    errors.add(tfCheck(tfEmailClient, "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
                }
            }
        });

        tfIbanClient.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if (!tfIbanClient.getText().equals("")){
                errors.add(tfCheck(tfIbanClient, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$"));
                }
            }
        });*/
    }
}
