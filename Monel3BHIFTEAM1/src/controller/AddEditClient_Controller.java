package controller;

import app.SceneLoader;
import data.PersonDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Client;
import model.Person;
import model.Privacy;
import model.Salutation;
import org.w3c.dom.Text;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.SQLInvalidAuthorizationSpecException;
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
    private Accordion accordionClients;

    @FXML
    private TitledPane tPaneBasicData;

    @FXML
    private TitledPane tPaneAddress;

    @FXML
    private TitledPane tPanePrivacy;

    @FXML
    private TitledPane tPaneEsv;

    @FXML
    private TitledPane tPaneContact;

    @FXML
    private TitledPane tPaneInformation;

    @FXML
    private TitledPane tPaneBank;

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
    private CheckBox cbSelfDetermined;

    @FXML
    private VBox vboxEsv;

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
    private TextArea taDiagnoseClient;

    @FXML
    private TextField tfJobClient;

    @FXML
    private TextArea taAllergiesClient;

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

    @FXML
    private Label lbMessage;


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
            if (editableClient.getSsnr() != 0){
                tfSsnrClient.setText(""+editableClient.getSsnr());
            }
            loadPersonProperties(editableClient, tfTitleClient, tfFirstnameClient,tfLastnameClient,tfStreetClient,tfHousenumberClient,tfZipClient,tfPlaceClient,tfTelNrClient, tfEmailClient, dpBirthdateClient, comboSalutationClient);
            //PRIVACY
            Privacy privacyOfEditableClient = new Privacy();
            if (editableClient.getPrivacy() != null) {
                privacyOfEditableClient = editableClient.getPrivacy();
            }
            cbPrivacy1Client.setSelected(privacyOfEditableClient.getPrivacies().get(0));
            cbPrivacy2Client.setSelected(privacyOfEditableClient.getPrivacies().get(1));
            cbPrivacy3Client.setSelected(privacyOfEditableClient.getPrivacies().get(2));
            cbPrivacy4Client.setSelected(privacyOfEditableClient.getPrivacies().get(3));

            if (editableClient.getEsv() != null) {
                loadPersonProperties(editableClient.getEsv(), tfTitleEsv, tfFirstnameEsv, tfLastnameEsv, tfStreetEsv, tfHousenumberEsv, tfZipEsv, tfPlaceEsv, tfTelNrEsv, tfEmailEsv, dpBirthdateEsv, comboSalutationEsv);
            }
            loadPersonProperties(editableClient.getEmergencyContact1(), tfTitleContact1, tfFirstnameContact1, tfLastnameContact1, tfStreetContact1, tfHousenumberContact1, tfZipContact1, tfPlaceContact1, tfTelNrContact1, tfEmailContact1, dpBirthdateContact1, comboSalutationContact1);
            if (editableClient.getEmergencyContact2() != null) {
                loadPersonProperties(editableClient.getEmergencyContact2(), tfTitleContact2, tfFirstnameContact2, tfLastnameContact2, tfStreetContact2, tfHousenumberContact2, tfZipContact2, tfPlaceContact2, tfTelNrContact2, tfEmailContact2, dpBirthdateContact2, comboSalutationContact2);
            }

            //INFO
            taDiagnoseClient.setText(editableClient.getDiagnose());
            tfJobClient.setText(editableClient.getJob());
            taAllergiesClient.setText(editableClient.getAllergies());
            tfOtherClient.setText(editableClient.getOther());
            //BANK
            tfBicClient.setText(editableClient.getBic());
            tfIbanClient.setText(editableClient.getIban());
        }
    }

    private void loadPersonProperties(Person p, TextField tfTitle, TextField tfFirstname, TextField tfLastname, TextField tfStreet, TextField tfHousenumber, TextField tfZip, TextField tfPlace, TextField tfTelNr, TextField tfEmail, DatePicker dpBirthdate, ComboBox<Salutation> comboSalutation){
        comboSalutation.getSelectionModel().select(p.getSalutation());
        tfTitle.setText(p.getTitle());
        tfFirstname.setText(p.getFirstName());
        tfLastname.setText(p.getLastName());

        dpBirthdate.setValue(p.getBirthDate());
        //ADDRESS
        try {
            tfStreet.setText(p.getStreetAndNr().substring(0, p.getStreetAndNr().lastIndexOf(' ')));
            tfHousenumber.setText(p.getStreetAndNr().split(" ")[p.getStreetAndNr().split(" ").length-1]);
        } catch (Exception ex) {}
        if (p.getZipCode() != 0){
            tfZip.setText(""+p.getZipCode());
        }
        tfPlace.setText(p.getPlace());
        //CONTACT
        tfTelNr.setText(p.getTelNr());
        tfEmail.setText(p.getEmail());
    }

    @FXML
    void btnCancelClient_Clicked(ActionEvent event) {
        showScene("ClientList");
    }

    @FXML
    void btnDeleteImageClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkClient_Clicked(ActionEvent event) {
        Client clientToAdd = new Client();
        lbMessage.setText("");
        errors.clear();

        errors.add(tfCheck(tfFirstnameClient, "^\\D+$", tPaneBasicData));
        errors.add(tfCheck(tfLastnameClient, "^\\D+$", tPaneBasicData));

        if (!errors.contains(true)){
            clientToAdd = new Client(   comboSalutationClient.getSelectionModel().getSelectedItem(),
                    tfFirstnameClient.getText(),
                    tfLastnameClient.getText()
            );}

        setAllPersonFieldsMandatory(clientToAdd, tfTitleClient, tfFirstnameClient, tfLastnameClient, tfSsnrClient, tfStreetClient, tfHousenumberClient, tfZipClient, tfPlaceClient, tfTelNrClient, tfEmailClient, dpBirthdateClient, comboSalutationClient, tPaneBasicData, tPaneAddress, tPaneContact);

        clientToAdd.setPrivacy(new Privacy(new ArrayList<Boolean>(){{add(cbPrivacy1Client.isSelected()); add(cbPrivacy2Client.isSelected()); add(cbPrivacy3Client.isSelected()); add(cbPrivacy4Client.isSelected());}}));

        Person esv = new Person();
        setAllPersonFields(esv, tfTitleEsv, tfFirstnameEsv, tfLastnameEsv, tfSsnrEsv, tfStreetEsv, tfHousenumberEsv, tfZipEsv, tfPlaceEsv, tfTelNrEsv, tfEmailEsv, dpBirthdateEsv, comboSalutationEsv, tPaneEsv, tPaneEsv, tPaneEsv);
        clientToAdd.setEsv(esv);

        Person ec1 = new Person();
        setAllPersonFieldsMandatory(ec1, tfTitleContact1, tfFirstnameContact1, tfLastnameContact1, tfSsnrContact1, tfStreetContact1, tfHousenumberContact1, tfZipContact1, tfPlaceContact1, tfTelNrContact1, tfEmailContact1, dpBirthdateContact1, comboSalutationContact1, tPaneEsv, tPaneEsv, tPaneEsv);
        clientToAdd.setEmergencyContact1(ec1);

        Person ec2 = new Person();
        setAllPersonFields(ec2, tfTitleContact2, tfFirstnameContact2, tfLastnameContact2, tfSsnrContact2, tfStreetContact2, tfHousenumberContact2, tfZipContact2, tfPlaceContact2, tfTelNrContact2, tfEmailContact2, dpBirthdateContact2, comboSalutationContact2, tPaneEsv, tPaneEsv, tPaneEsv);
        clientToAdd.setEmergencyContact2(ec2);

        if (!tfCheck(tfSsnrClient, "^[1-9][0-9]{9}$", tPaneBasicData)) {
            try {
                clientToAdd.setSsnr(Integer.parseInt(tfSsnrClient.getText()));
            } catch (Exception e) { }
        } else {
            errors.add(true);
        }

        if (!taCheck(taAllergiesClient,"^(\\D+)?$", tPaneInformation)) {
            clientToAdd.setAllergies(taAllergiesClient.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfJobClient,"^(\\D+)?$", tPaneInformation)) {
            clientToAdd.setJob(tfJobClient.getText());
        } else {
            errors.add(true);
        }

        if (!taCheck(taDiagnoseClient,"^\\D+$", tPaneInformation)) {
            clientToAdd.setDiagnose(taDiagnoseClient.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfOtherClient, "^(\\D+)?$", tPaneInformation)){
            clientToAdd.setOther(tfOtherClient.getText());
        } else {
            errors.add(true);
        }


        if (!tfCheck(tfIbanClient, "^([A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?)?$", tPaneBank)) {
            clientToAdd.setIban(tfIbanClient.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfBicClient, "^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)?$", tPaneBank)){
            clientToAdd.setBic(tfBicClient.getText());
        } else {
            errors.add(true);
        }


        if(!errors.contains(true) && PersonDAO.getInstance().addPerson(clientToAdd)) {
            if (editableClient != null) { PersonDAO.getInstance().deletePerson(editableClient);
            }
            super.showScene("ClientList");
        }

    }

    private void setAllPersonFields(Person p, TextField tfTitle, TextField tfFirstname, TextField tfLastname, TextField tfSsnr, TextField tfStreet, TextField tfHousenumber, TextField tfZip, TextField tfPlace, TextField tfTelNr, TextField tfEmail, DatePicker dpBirthdate, ComboBox<Salutation> comboSalutation, TitledPane tpBasic, TitledPane tpAddress, TitledPane tpContact) {

        if (!tfCheck(tfTitle, "^(\\D+)?$", tpBasic)) {
            p.setTitle(tfTitle.getText());
        } else {
            errors.add(true);
        }
        if (!(p instanceof Client)) {
            comboSalutation.getSelectionModel().getSelectedItem();
            if (!tfCheck(tfFirstname, "^(\\D+)?$", tpBasic)) {
                p.setFirstName(tfFirstname.getText());
            } else {
                errors.add(true);
            }

            if(!tfCheck(tfLastname, "^(\\D+)?$", tpBasic)){
                p.setLastName(tfLastname.getText());
            } else {
                errors.add(true);
            }
        }

            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                p.setBirthDate(ldsc.fromString(dpBirthdate.getEditor().getText()));
                dpBirthdate.setStyle(null);
            } catch (Exception e) {
                dpBirthdate.setStyle("-FX-Border-Color: red");
                errors.add(true);
            }



        if (!tfCheck(tfStreet, "^(\\D+)?$", tpAddress) && !tfCheck(tfHousenumber, "^(([0-9]+)([^0-9]*))?$", tpAddress)) {
            p.setStreetAndNr(tfStreet.getText() + " " + tfHousenumber.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfZip, "^([1-9][0-9]{3})?$", tpAddress)) {
            try {
                p.setZipCode(Integer.parseInt(tfZip.getText()));
            } catch (Exception e) { }

        } else {
            errors.add(true);
        }

        if (!tfCheck(tfPlace, "^(\\D+)?$", tpAddress)){
            p.setPlace(tfPlace.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfTelNr, "^([0-9]*)?$", tpContact)) {
            p.setTelNr(tfTelNr.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfEmail, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tpContact)) {
            p.setEmail(tfEmail.getText());
        } else {
            errors.add(true);
        }
    }

    private void setAllPersonFieldsMandatory(Person p, TextField tfTitle, TextField tfFirstname, TextField tfLastname, TextField tfSsnr, TextField tfStreet, TextField tfHousenumber, TextField tfZip, TextField tfPlace, TextField tfTelNr, TextField tfEmail, DatePicker dpBirthdate, ComboBox<Salutation> comboSalutation, TitledPane tpBasic, TitledPane tpAddress, TitledPane tpContact) {

        if (!tfCheck(tfTitle, "^(\\D+)?$", tpBasic)) {
            p.setTitle(tfTitle.getText());
        } else {
            errors.add(true);
        }
        if (!(p instanceof Client)) {
            comboSalutation.getSelectionModel().getSelectedItem();
            if (!tfCheck(tfFirstname, "^\\D+$", tpBasic)) {
                p.setFirstName(tfFirstname.getText());
            } else {
                errors.add(true);
            }

            if(!tfCheck(tfLastname, "^\\D+$", tpBasic)){
                p.setLastName(tfLastname.getText());
            } else {
                errors.add(true);
            }
        }

        if (dpBirthdate.getEditor().getText() == ""){
            dpBirthdate.setStyle("-FX-Border-Color: red");
        } else {
            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                p.setBirthDate(ldsc.fromString(dpBirthdate.getEditor().getText()));
                dpBirthdate.setStyle(null);
            } catch (Exception e) {
                dpBirthdate.setStyle("-FX-Border-Color: red");
                errors.add(true);
            }
        }


        if (!tfCheck(tfStreet, "^\\D+$", tpAddress) && !tfCheck(tfHousenumber, "^([0-9]+)([^0-9]*)$", tpAddress)) {
            p.setStreetAndNr(tfStreet.getText() + " " + tfHousenumber.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfZip, "^[1-9][0-9]{3}$", tpAddress)) {
            try {
                p.setZipCode(Integer.parseInt(tfZip.getText()));
            } catch (Exception e) { }

        } else {
            errors.add(true);
        }

        if (!tfCheck(tfPlace, "^\\D+$", tpAddress)){
            p.setPlace(tfPlace.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfTelNr, "^[0-9]+$", tpContact)) {
            p.setTelNr(tfTelNr.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfEmail, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tpContact)) {
            p.setEmail(tfEmail.getText());
        } else {
            errors.add(true);
        }
    }

    @FXML
    void btnSelectImageClient_Clicked(ActionEvent event) {

    }


    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

    private boolean tfCheck(TextField tf, String regex, TitledPane tp){
        boolean error = true;
            if (!tf.getText().matches(regex)) {
                error = true;
                tf.setStyle("-FX-Border-Color: red");
                tp.setStyle("-FX-Text-Fill: red");
                lbMessage.setText(lbMessage.getText() + tf.getId() + ",");
            } else {
                error = false;
                tf.setStyle(null);
                tp.setStyle(null);
            }
        return error;
    }

    private boolean taCheck(TextArea ta, String regex, TitledPane tp) {
        boolean error = true;
        if (!ta.getText().matches(regex)) {
            error = true;
            ta.setStyle("-FX-Border-Color: red");
            tp.setStyle("-FX-Text-Fill: red");
            lbMessage.setText(lbMessage.getText() + ta.getId() + ",");
        } else {
            error = false;
            ta.setStyle(null);
            tp.setStyle(null);
        }
        return error;
    }

    @FXML
    void btnDeleteImageClient_Clicked(ActionEvent event) {

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


        this.accordionClients.setExpandedPane(this.tPaneBasicData);

        this.cbSelfDetermined.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                vboxEsv.setDisable(newValue);
            }
        });
    }
}
