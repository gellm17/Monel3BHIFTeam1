package controller;

import app.SceneLoader;
import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEditEmployee_Controller extends SceneLoader implements Initializable {

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
    private TitledPane tPaneAddress;

    @FXML
    private TitledPane tPanePrivacy;

    @FXML
    private TitledPane tPaneContact;

    @FXML
    private TitledPane tPaneInformation;

    @FXML
    private TitledPane tPaneBank;

    @FXML
    private ComboBox<Salutation> comboSalutationEmployee;

    @FXML
    private TextField tfTitleEmployee;

    @FXML
    private TextField tfFirstnameEmployee;

    @FXML
    private TextField tfLastnameEmployee;

    @FXML
    private TextField tfSsnrEmployee;

    @FXML
    private DatePicker dpBirthdateEmployee;

    @FXML
    private TextField tfStreetEmployee;

    @FXML
    private TextField tfHousenumberEmployee;

    @FXML
    private TextField tfZipEmployee;

    @FXML
    private TextField tfPlaceEmployee;

    @FXML
    private CheckBox cbPrivacy1Employee;

    @FXML
    private CheckBox cbPrivacy2Employee;

    @FXML
    private CheckBox cbPrivacy3Employee;

    @FXML
    private CheckBox cbPrivacy4Employee;

    @FXML
    private TextField tfTelNrEmployee;

    @FXML
    private TextField tfEmailEmployee;

    @FXML
    private ToggleButton tglBtnFulltimeEmployee;

    @FXML
    private ToggleGroup groupVolunteering;

    @FXML
    private ToggleButton tglBtnVolunteeringEmployee;

    @FXML
    private ComboBox<OccupationGroup> comboOccupationGroupEmployee;

    @FXML
    private ComboBox<SalaryLevel> comboSalaryLevelEmployee;

    @FXML
    private TextField tfHoursPerWeekEmployee;

    @FXML
    private DatePicker dpDateSalaryLevelEmployee;

    @FXML
    private DatePicker dpDateOfEmploymentEmployee;

    @FXML
    private TextField tfIbanEmployee;

    @FXML
    private TextField tfBicEmployee;

    @FXML
    private ImageView imgEmployee;

    @FXML
    private Button btnDeleteImageEmployee;

    @FXML
    private Button btnSelectImageEmployee;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnCancelEmployee;

    @FXML
    private Button btnOkEmployee;

    private Employee editableEmployee = null;
    private ArrayList<Boolean> errors = new ArrayList<Boolean>();
    private int basicErrorCounter = 0;
    private int addressErrorCounter = 0;
    private int contactErrorCounter = 0;
    private int infoErrorCounter = 0;
    private int bankErrorCounter = 0;

    public Employee getEditableEmployee() {
        return editableEmployee;
    }

    public void setEditableEmployee(Employee editableEmployee) {
        this.editableEmployee = editableEmployee;
        comboSalutationEmployee.getItems().setAll(Salutation.values());
        comboOccupationGroupEmployee.getItems().setAll(OccupationGroup.values());
        comboSalaryLevelEmployee.getItems().setAll(SalaryLevel.values());
        if (editableEmployee != null){
            comboSalutationEmployee.getSelectionModel().select(editableEmployee.getSalutation());
            tfTitleEmployee.setText(editableEmployee.getTitle());
            tfFirstnameEmployee.setText(editableEmployee.getFirstName());
            tfLastnameEmployee.setText(editableEmployee.getLastName());
            if (editableEmployee.getSsnr() != 0) {
                tfSsnrEmployee.setText("" + editableEmployee.getSsnr());
            }
            dpBirthdateEmployee.setValue(editableEmployee.getBirthDate());
            //ADDRESS
            try {
                tfStreetEmployee.setText(editableEmployee.getStreetAndNr().substring(0, editableEmployee.getStreetAndNr().lastIndexOf(' ')));
                tfHousenumberEmployee.setText(editableEmployee.getStreetAndNr().split(" ")[editableEmployee.getStreetAndNr().split(" ").length -1]);
            } catch (Exception ex) {}
            if (editableEmployee.getZipCode() != 0){
                tfZipEmployee.setText(""+editableEmployee.getZipCode());
            }
            tfPlaceEmployee.setText(editableEmployee.getPlace());
            //PRIVACY
            Privacy privacyOfEditableEmployee = new Privacy();
            if (editableEmployee.getPrivacy() != null) {
                privacyOfEditableEmployee = editableEmployee.getPrivacy();
            }
            cbPrivacy1Employee.setSelected(privacyOfEditableEmployee.getPrivacies().get(0));
            cbPrivacy2Employee.setSelected(privacyOfEditableEmployee.getPrivacies().get(1));
            cbPrivacy3Employee.setSelected(privacyOfEditableEmployee.getPrivacies().get(2));
            cbPrivacy4Employee.setSelected(privacyOfEditableEmployee.getPrivacies().get(3));
            //INFO
            tglBtnFulltimeEmployee.setSelected(!editableEmployee.isVolunteering());
            tglBtnVolunteeringEmployee.setSelected(editableEmployee.isVolunteering());
            //tfOccupationGroupEmployee.setText(editableEmployee.getOccupationGroup());
            comboOccupationGroupEmployee.getSelectionModel().select(editableEmployee.getOccupationGroup());
            comboSalaryLevelEmployee.getSelectionModel().select(editableEmployee.getSalaryLevel());
            if (editableEmployee.getHoursPerWeek() != 0){
                tfHoursPerWeekEmployee.setText(""+editableEmployee.getHoursPerWeek());
            }
            dpDateSalaryLevelEmployee.setValue(editableEmployee.getDateSalaryLevel());
            dpDateOfEmploymentEmployee.setValue(editableEmployee.getDateOfEmployment());
            //CONTACT
            tfTelNrEmployee.setText(editableEmployee.getTelNr());
            tfEmailEmployee.setText(editableEmployee.getEmail());
            //BANK
            tfBicEmployee.setText(editableEmployee.getBic());
            tfIbanEmployee.setText(editableEmployee.getIban());
        }
    }

    @FXML
    void btnCancelEmployee_Clicked(ActionEvent event) {
        showScene("EmployeeList");
    }

    @FXML
    void btnDeleteImageEmployee_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkEmployee_Clicked(ActionEvent event) {
        Employee employeeToAdd = new Employee();
        basicErrorCounter = 0;
        addressErrorCounter = 0;
        contactErrorCounter = 0;
        infoErrorCounter = 0;
        bankErrorCounter = 0;
        lbMessage.setText("");
        errors.clear();

        errors.add(tfCheck(tfFirstnameEmployee, "^\\D+$", tPaneBasicData, basicErrorCounter));
        if (errors.get(0) == true){
            basicErrorCounter++;
        }
        errors.add(tfCheck(tfLastnameEmployee, "^\\D+$", tPaneBasicData, basicErrorCounter));
        if (errors.get(1) == true){
            basicErrorCounter++;
        }

        if (!errors.contains(true)){
            employeeToAdd = new Employee(   comboSalutationEmployee.getSelectionModel().getSelectedItem(),
                    tfFirstnameEmployee.getText(),
                    tfLastnameEmployee.getText()
            );}

        if (!tfCheck(tfTitleEmployee, "^(\\D+)?$", tPaneBasicData, basicErrorCounter)) {
            employeeToAdd.setTitle(tfTitleEmployee.getText());
        } else {
            basicErrorCounter++;
            errors.add(true);
        }

        if (!tfCheck(tfSsnrEmployee, "^[1-9][0-9]{9}$", tPaneBasicData, basicErrorCounter)) {
            try {
                employeeToAdd.setSsnr(Integer.parseInt(tfSsnrEmployee.getText()));
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        } else {
            basicErrorCounter++;
            errors.add(true);
        }

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            employeeToAdd.setBirthDate(ldsc.fromString(dpBirthdateEmployee.getEditor().getText()));
            dpBirthdateEmployee.setStyle(null);
            if (basicErrorCounter == 0){
                tPaneBasicData.setStyle(null);
            }
        } catch (Exception e) {
            dpBirthdateEmployee.setStyle("-FX-Border-Color: red");
            tPaneBasicData.setStyle("-FX-Text-Fill: red");
            basicErrorCounter++;
            errors.add(true);
        }

        if (!tfCheck(tfStreetEmployee, "^\\D+$", tPaneAddress, addressErrorCounter)) {
            if (!tfCheck(tfHousenumberEmployee, "^([0-9]+)([^0-9]*)$", tPaneAddress, addressErrorCounter)){
                employeeToAdd.setStreetAndNr(tfStreetEmployee.getText() + " " + tfHousenumberEmployee.getText());
            } else {
                addressErrorCounter++;
                errors.add(true);
            }
        } else {
            addressErrorCounter++;
            errors.add(true);
        }

        if (!tfCheck(tfZipEmployee, "^[1-9][0-9]{3}$", tPaneAddress, addressErrorCounter)) {
            try {
                employeeToAdd.setZipCode(Integer.parseInt(tfZipEmployee.getText()));
            } catch (Exception e) { }

        } else {
            addressErrorCounter++;
            errors.add(true);
        }

        if (!tfCheck(tfPlaceEmployee, "^\\D+$", tPaneAddress, addressErrorCounter)){
            employeeToAdd.setPlace(tfPlaceEmployee.getText());
        } else {
            addressErrorCounter++;
            errors.add(true);
        }

        employeeToAdd.setPrivacy(new Privacy(new ArrayList<Boolean>(){{add(cbPrivacy1Employee.isSelected()); add(cbPrivacy2Employee.isSelected()); add(cbPrivacy3Employee.isSelected()); add(cbPrivacy4Employee.isSelected());}}));

        if (!tfCheck(tfTelNrEmployee, "^[0-9]+$", tPaneContact, contactErrorCounter)) {
            employeeToAdd.setTelNr(tfTelNrEmployee.getText());
        } else {
            contactErrorCounter++;
            errors.add(true);
        }

        if (!tfCheck(tfEmailEmployee, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$", tPaneContact, contactErrorCounter)) {
            employeeToAdd.setEmail(tfEmailEmployee.getText());
        } else {
            contactErrorCounter++;
            errors.add(true);
        }

        employeeToAdd.setOccupationGroup(comboOccupationGroupEmployee.getSelectionModel().getSelectedItem());
        employeeToAdd.setSalaryLevel(comboSalaryLevelEmployee.getSelectionModel().getSelectedItem());

        if (!tfCheck(tfHoursPerWeekEmployee, "^([0-9]*)?$", tPaneInformation, infoErrorCounter)){
            try {
                employeeToAdd.setHoursPerWeek(Integer.parseInt(tfHoursPerWeekEmployee.getText()));
            } catch (Exception e) {}
        } else {
            infoErrorCounter++;
            errors.add(true);
        }

        employeeToAdd.setVolunteering(tglBtnVolunteeringEmployee.isSelected());

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            employeeToAdd.setDateSalaryLevel(ldsc.fromString(dpDateSalaryLevelEmployee.getEditor().getText()));
            dpDateSalaryLevelEmployee.setStyle(null);
        } catch (Exception e) {
            dpDateSalaryLevelEmployee.setStyle("-FX-Border-Color: red");
            infoErrorCounter++;
            errors.add(true);
        }

        if (dpDateOfEmploymentEmployee.getEditor().getText() == ""){
            dpDateOfEmploymentEmployee.setStyle("-FX-Border-Color: red");
            infoErrorCounter++;
            errors.add(true);
        } else {
            try {
                LocalDateStringConverter ldsc = new LocalDateStringConverter();
                employeeToAdd.setDateOfEmployment(ldsc.fromString(dpDateOfEmploymentEmployee.getEditor().getText()));
                if (infoErrorCounter == 0){
                    dpDateOfEmploymentEmployee.setStyle(null);
                }

            } catch (Exception e) {
                dpDateOfEmploymentEmployee.setStyle("-FX-Border-Color: red");
                infoErrorCounter++;
                errors.add(true);
            }
        }
        if (!tfCheck(tfIbanEmployee, "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", tPaneBank, bankErrorCounter)) {
            employeeToAdd.setIban(tfIbanEmployee.getText());
        } else {
            errors.add(true);
            bankErrorCounter++;
        }

        if (!tfCheck(tfBicEmployee, "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?$", tPaneBank, bankErrorCounter)){
            employeeToAdd.setBic(tfBicEmployee.getText());
        } else {
            errors.add(true);
            bankErrorCounter++;
        }

        if(!errors.contains(true) && PersonDAO.getInstance().addPerson(employeeToAdd)) {
            if (editableEmployee != null) { PersonDAO.getInstance().deletePerson(editableEmployee);
            }
            super.showScene("EmployeeList");
        }
    }

    @FXML
    void btnSelectImageEmployee_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

    private boolean tfCheck(TextField tf, String regex, TitledPane tp, int counter){
        boolean error = true;
        if (!tf.getText().matches(regex)) {
            error = true;
            tf.setStyle("-FX-Border-Color: red");
            tp.setStyle("-FX-Text-Fill: red");
            lbMessage.setText(lbMessage.getText() + tf.getId() + ",");
        } else {
            error = false;
            tf.setStyle(null);
            if (counter == 0){
                tp.setStyle(null);
            }
        }
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboSalutationEmployee.getItems().setAll(Salutation.values());
        comboSalutationEmployee.getSelectionModel().select(0);

        comboOccupationGroupEmployee.getItems().setAll(OccupationGroup.values());
        comboOccupationGroupEmployee.getSelectionModel().select(0);

        comboSalaryLevelEmployee.getItems().setAll(SalaryLevel.values());
        comboSalaryLevelEmployee.getSelectionModel().select(0);

        this.accordionEmployees.setExpandedPane(tPaneBasicData);
    }
}
