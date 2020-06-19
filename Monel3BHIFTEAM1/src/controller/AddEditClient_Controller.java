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
import java.sql.SQLException;
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
    private VBox vboxContact2;

    @FXML
    private CheckBox cbContact2;

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
    private VBox vboxContact1;

    @FXML
    private Tab tabEmergencyContact1;

    @FXML
    private CheckBox cbContact1isEsv;

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
    private TextArea taOtherClient;

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

    private int basicErrorCounter = 0;
    private int addressErrorCounter = 0;
    private int contactErrorCounter = 0;
    private int infoErrorCounter = 0;
    private int bankErrorCounter = 0;
    private int esvErrorCounter = 0;

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
                cbSelfDetermined.setSelected(false);
                vboxEsv.setDisable(false);
            } else {
                cbSelfDetermined.setSelected(true);
                vboxEsv.setDisable(true);
            }

            loadPersonProperties(editableClient.getEmergencyContact1(), tfTitleContact1, tfFirstnameContact1, tfLastnameContact1, tfStreetContact1, tfHousenumberContact1, tfZipContact1, tfPlaceContact1, tfTelNrContact1, tfEmailContact1, dpBirthdateContact1, comboSalutationContact1);

            if (editableClient.getEmergencyContact2() != null) {
                loadPersonProperties(editableClient.getEmergencyContact2(), tfTitleContact2, tfFirstnameContact2, tfLastnameContact2, tfStreetContact2, tfHousenumberContact2, tfZipContact2, tfPlaceContact2, tfTelNrContact2, tfEmailContact2, dpBirthdateContact2, comboSalutationContact2);
                cbContact2.setSelected(true);
                vboxContact2.setDisable(false);
            } else {
                cbContact2.setSelected(false);
                vboxContact2.setDisable(true);
            }

            //INFO
            taDiagnoseClient.setText(editableClient.getDiagnose());
            tfJobClient.setText(editableClient.getJob());
            taAllergiesClient.setText(editableClient.getAllergies());
            taOtherClient.setText(editableClient.getOther());
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
    void btnOkClient_Clicked(ActionEvent event) throws SQLException {
        Client clientToAdd = new Client();
        basicErrorCounter = 0;
        addressErrorCounter = 0;
        contactErrorCounter = 0;
        infoErrorCounter = 0;
        bankErrorCounter = 0;
        esvErrorCounter = 0;
        lbMessage.setText("");
        errors.clear();

        errors.add(tfCheck(tfFirstnameClient, "^\\D+$", tPaneBasicData, basicErrorCounter));
        if (errors.get(0) == true){
            basicErrorCounter++;
        }
        errors.add(tfCheck(tfLastnameClient, "^\\D+$", tPaneBasicData, basicErrorCounter));
        if (errors.get(1) == true){
            basicErrorCounter++;
        }

        if (!errors.contains(true)){
            clientToAdd = new Client(   comboSalutationClient.getSelectionModel().getSelectedItem(),
                    tfFirstnameClient.getText(),
                    tfLastnameClient.getText()
            );}

        setAllPersonFieldsMandatory(clientToAdd, tfTitleClient, tfFirstnameClient, tfLastnameClient, tfSsnrClient, tfStreetClient, tfHousenumberClient, tfZipClient, tfPlaceClient, tfTelNrClient, tfEmailClient, dpBirthdateClient, comboSalutationClient, tPaneBasicData, tPaneAddress, tPaneContact, false);

        clientToAdd.setPrivacy(new Privacy(new ArrayList<Boolean>(){{add(cbPrivacy1Client.isSelected()); add(cbPrivacy2Client.isSelected()); add(cbPrivacy3Client.isSelected()); add(cbPrivacy4Client.isSelected());}}));

        if (!cbSelfDetermined.isSelected()){
            Person esv = new Person();
            setAllPersonFieldsMandatory(esv, tfTitleEsv, tfFirstnameEsv, tfLastnameEsv, tfSsnrEsv, tfStreetEsv, tfHousenumberEsv, tfZipEsv, tfPlaceEsv, tfTelNrEsv, tfEmailEsv, dpBirthdateEsv, comboSalutationEsv, tPaneEsv, tPaneEsv, tPaneEsv, true);
            clientToAdd.setEsv(esv);
        }

        Person ec1 = new Person();
        setAllPersonFieldsMandatory(ec1, tfTitleContact1, tfFirstnameContact1, tfLastnameContact1, tfSsnrContact1, tfStreetContact1, tfHousenumberContact1, tfZipContact1, tfPlaceContact1, tfTelNrContact1, tfEmailContact1, dpBirthdateContact1, comboSalutationContact1, tPaneEsv, tPaneEsv, tPaneEsv, true);
        clientToAdd.setEmergencyContact1(ec1);
        
        if (cbContact2.isSelected()){
            Person ec2 = new Person();
            setAllPersonFields(ec2, tfTitleContact2, tfFirstnameContact2, tfLastnameContact2, tfSsnrContact2, tfStreetContact2, tfHousenumberContact2, tfZipContact2, tfPlaceContact2, tfTelNrContact2, tfEmailContact2, dpBirthdateContact2, comboSalutationContact2, tPaneEsv, tPaneEsv, tPaneEsv);
            clientToAdd.setEmergencyContact2(ec2);
        }


        if (!tfCheck(tfSsnrClient, "^[1-9][0-9]{9}$", tPaneBasicData, basicErrorCounter)) {
            try {
                clientToAdd.setSsnr(Long.parseLong(tfSsnrClient.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errors.add(true);
            basicErrorCounter++;
        }

        if (!taCheck(taAllergiesClient,"^(\\D+)?$", tPaneInformation, infoErrorCounter)) {
            clientToAdd.setAllergies(taAllergiesClient.getText());
        } else {
            errors.add(true);
            infoErrorCounter++;
        }

        if (!tfCheck(tfJobClient,"^(\\D+)?$", tPaneInformation, infoErrorCounter)) {
            clientToAdd.setJob(tfJobClient.getText());
        } else {
            errors.add(true);
            infoErrorCounter++;
        }

        if (!taCheck(taDiagnoseClient,"^\\D+$", tPaneInformation, infoErrorCounter)) {
            clientToAdd.setDiagnose(taDiagnoseClient.getText());
        } else {
            errors.add(true);
            infoErrorCounter++;
        }

        if (!taCheck(taOtherClient, "^(\\D+)?$", tPaneInformation, infoErrorCounter)){
            clientToAdd.setOther(taOtherClient.getText());
        } else {
            errors.add(true);
            infoErrorCounter++;
        }


        /*if (!tfCheck(tfIbanClient, "^([A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?)?$", tPaneBank)) {
            clientToAdd.setIban(tfIbanClient.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfBicClient, "^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)?$", tPaneBank)){
            clientToAdd.setBic(tfBicClient.getText());
        } else {
            errors.add(true);
        }*/

        if (editableClient != null){
            clientToAdd.setId(editableClient.getId());
            clientToAdd.getEmergencyContact1().setId(editableClient.getEmergencyContact1().getId());
            if (!cbSelfDetermined.isSelected() && editableClient.getEsv() != null) {
                clientToAdd.getEsv().setId(editableClient.getEsv().getId());
            }
            if (cbContact2.isSelected() && editableClient.getEmergencyContact2() != null) {
                clientToAdd.getEmergencyContact2().setId(editableClient.getEmergencyContact2().getId());
            }
        }


        if(!errors.contains(true) && PersonDAO.getInstance().addPerson(clientToAdd)) {
            /*if (editableClient != null) { PersonDAO.getInstance().deletePerson(editableClient);
            }*/
            super.showScene("ClientList");
        }

    }

    private void setAllPersonFields(Person p, TextField tfTitle, TextField tfFirstname, TextField tfLastname, TextField tfSsnr, TextField tfStreet, TextField tfHousenumber, TextField tfZip, TextField tfPlace, TextField tfTelNr, TextField tfEmail, DatePicker dpBirthdate, ComboBox<Salutation> comboSalutation, TitledPane tpBasic, TitledPane tpAddress, TitledPane tpContact) {
        p.setSalutation(comboSalutation.getSelectionModel().getSelectedItem());
        if (!tfCheck(tfTitle, "^(\\D+)?$", tpBasic, esvErrorCounter)) {
            p.setTitle(tfTitle.getText());
        } else {
            errors.add(true);
            esvErrorCounter++;
        }
        if (!(p instanceof Client)) {
            if (!tfCheck(tfFirstname, "^(\\D+)?$", tpBasic, esvErrorCounter)) {
                p.setFirstName(tfFirstname.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }

            if(!tfCheck(tfLastname, "^(\\D+)?$", tpBasic, esvErrorCounter)){
                p.setLastName(tfLastname.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }
        }

            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                p.setBirthDate(ldsc.fromString(dpBirthdate.getEditor().getText()));
                dpBirthdate.setStyle(null);
            } catch (Exception e) {
                dpBirthdate.setStyle("-FX-Border-Color: red");
                errors.add(true);
                esvErrorCounter++;
            }



        if (!tfCheck(tfStreet, "^(\\D+)?$", tpAddress, esvErrorCounter) && !tfCheck(tfHousenumber, "^(([0-9]+)([^0-9]*))?$", tpAddress, esvErrorCounter)) {
            p.setStreetAndNr(tfStreet.getText() + " " + tfHousenumber.getText());
        } else {
            errors.add(true);
            esvErrorCounter++;
        }

        if (!tfCheck(tfZip, "^([1-9][0-9]{3})?$", tpAddress, esvErrorCounter)) {
            try {
                p.setZipCode(Integer.parseInt(tfZip.getText()));
            } catch (Exception e) { }

        } else {
            errors.add(true);
            esvErrorCounter++;
        }

        if (!tfCheck(tfPlace, "^(\\D+)?$", tpAddress, esvErrorCounter)){
            p.setPlace(tfPlace.getText());
        } else {
            errors.add(true);
            esvErrorCounter++;
        }

        if (!tfCheck(tfTelNr, "^([0-9]*)?$", tpContact, esvErrorCounter)) {
            p.setTelNr(tfTelNr.getText());
        } else {
            errors.add(true);
            esvErrorCounter++;
        }

        if (!tfCheck(tfEmail, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tpContact, esvErrorCounter)) {
            p.setEmail(tfEmail.getText());
        } else {
            errors.add(true);
            esvErrorCounter++;
        }
    }

    private void setAllPersonFieldsMandatory(Person p, TextField tfTitle, TextField tfFirstname, TextField tfLastname, TextField tfSsnr, TextField tfStreet, TextField tfHousenumber, TextField tfZip, TextField tfPlace, TextField tfTelNr, TextField tfEmail, DatePicker dpBirthdate, ComboBox<Salutation> comboSalutation, TitledPane tpBasic, TitledPane tpAddress, TitledPane tpContact, boolean esv) {
        p.setSalutation(comboSalutation.getSelectionModel().getSelectedItem());
        if (!esv){
            if (!tfCheck(tfTitle, "^(\\D+)?$", tpBasic, basicErrorCounter)) {
                p.setTitle(tfTitle.getText());
            } else {
                errors.add(true);
            }
            if (!(p instanceof Client)) {
                if (!tfCheck(tfFirstname, "^\\D+$", tpBasic, basicErrorCounter)) {
                    p.setFirstName(tfFirstname.getText());
                } else {
                    errors.add(true);
                    basicErrorCounter++;
                }

                if(!tfCheck(tfLastname, "^\\D+$", tpBasic, basicErrorCounter)){
                    p.setLastName(tfLastname.getText());
                } else {
                    errors.add(true);
                    basicErrorCounter++;
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
                    basicErrorCounter++;
                }
            }


            if (!tfCheck(tfStreet, "^\\D+$", tpAddress, addressErrorCounter) && !tfCheck(tfHousenumber, "^([0-9]+)([^0-9]*)$", tpAddress, addressErrorCounter)) {
                p.setStreetAndNr(tfStreet.getText() + " " + tfHousenumber.getText());
            } else {
                errors.add(true);
                addressErrorCounter++;
            }

            if (!tfCheck(tfZip, "^[1-9][0-9]{3}$", tpAddress, addressErrorCounter)) {
                try {
                    p.setZipCode(Integer.parseInt(tfZip.getText()));
                } catch (Exception e) { }

            } else {
                errors.add(true);
                addressErrorCounter++;
            }

            if (!tfCheck(tfPlace, "^\\D+$", tpAddress, addressErrorCounter)){
                p.setPlace(tfPlace.getText());
            } else {
                errors.add(true);
                addressErrorCounter++;
            }

            if (!tfCheck(tfTelNr, "^[0-9]+$", tpContact, contactErrorCounter)) {
                p.setTelNr(tfTelNr.getText());
            } else {
                errors.add(true);
                contactErrorCounter++;
            }

            if (!tfCheck(tfEmail, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tpContact, contactErrorCounter)) {
                p.setEmail(tfEmail.getText());
            } else {
                errors.add(true);
                contactErrorCounter++;
            }
        } else {
            if (!tfCheck(tfTitle, "^(\\D+)?$", tpBasic, esvErrorCounter)) {
                p.setTitle(tfTitle.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }
            if (!(p instanceof Client)) {
                comboSalutation.getSelectionModel().getSelectedItem();
                if (!tfCheck(tfFirstname, "^\\D+$", tpBasic, esvErrorCounter)) {
                    p.setFirstName(tfFirstname.getText());
                } else {
                    errors.add(true);
                    esvErrorCounter++;
                }

                if(!tfCheck(tfLastname, "^\\D+$", tpBasic, esvErrorCounter)){
                    p.setLastName(tfLastname.getText());
                } else {
                    errors.add(true);
                    esvErrorCounter++;
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
                    esvErrorCounter++;
                }
            }


            if (!tfCheck(tfStreet, "^\\D+$", tpAddress, esvErrorCounter) && !tfCheck(tfHousenumber, "^([0-9]+)([^0-9]*)$", tpAddress, esvErrorCounter)) {
                p.setStreetAndNr(tfStreet.getText() + " " + tfHousenumber.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }

            if (!tfCheck(tfZip, "^[1-9][0-9]{3}$", tpAddress, esvErrorCounter)) {
                try {
                    p.setZipCode(Integer.parseInt(tfZip.getText()));
                } catch (Exception e) { }

            } else {
                errors.add(true);
                esvErrorCounter++;
            }

            if (!tfCheck(tfPlace, "^\\D+$", tpAddress, esvErrorCounter)){
                p.setPlace(tfPlace.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }

            if (!tfCheck(tfTelNr, "^[0-9]+$", tpContact, esvErrorCounter)) {
                p.setTelNr(tfTelNr.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }

            if (!tfCheck(tfEmail, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tpContact, esvErrorCounter)) {
                p.setEmail(tfEmail.getText());
            } else {
                errors.add(true);
                esvErrorCounter++;
            }
        }

    }

    @FXML
    void btnSelectImageClient_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettingsClient_Clicked(ActionEvent event) {
        openSettings();
    }

    private boolean tfCheck(TextField tf, String regex, TitledPane tp, int counter){
        boolean error = true;
            if (!tf.getText().matches(regex)) {
                error = true;
                tf.setStyle("-FX-Border-Color: red");
                tp.setStyle("-FX-Text-Fill: red");
                lbMessage.setText("Es gibt Fehler!");
            } else {
                error = false;
                tf.setStyle(null);
                if (counter == 0){
                    tp.setStyle(null);
                }
            }
        return error;
    }

    private boolean taCheck(TextArea ta, String regex, TitledPane tp, int counter) {
        boolean error = true;
        if (!ta.getText().matches(regex)) {
            error = true;
            ta.setStyle("-FX-Border-Color: red");
            tp.setStyle("-FX-Text-Fill: red");
            lbMessage.setText("Es gibt Fehler!");
        } else {
            error = false;
            ta.setStyle(null);
            if (counter == 0){
                tp.setStyle(null);
            }
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

        vboxContact2.setDisable(true);
        this.accordionClients.setExpandedPane(this.tPaneBasicData);

        this.cbSelfDetermined.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                vboxEsv.setDisable(newValue);
            }
        });

        this.cbContact1isEsv.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                vboxContact1.setDisable(newValue);
            }
        });

        this.cbContact2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                vboxContact2.setDisable(!newValue);
            }
        });
    }
}
