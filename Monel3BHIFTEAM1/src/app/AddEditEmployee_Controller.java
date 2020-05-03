package app;

import data.PersonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import model.Client;
import model.Employee;
import model.Privacy;
import model.Salutation;

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
    private ToggleButton tglBtnVolunteeringEmployee;

    @FXML
    private TextField tfOccupationGroupEmployee;

    @FXML
    private TextField tfSalaryLevelEmployee;

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

    public Employee getEditableEmployee() {
        return editableEmployee;
    }

    public void setEditableEmployee(Employee editableEmployee) {
        this.editableEmployee = editableEmployee;
        comboSalutationEmployee.getItems().setAll(Salutation.values());
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
                tfStreetEmployee.setText(editableEmployee.getStreetAndNr().split(" ")[0]);
                tfHousenumberEmployee.setText(editableEmployee.getStreetAndNr().split(" ")[1]);
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
            tfOccupationGroupEmployee.setText(editableEmployee.getOccupationGroup());
            if (editableEmployee.getHoursPerWeek() != 0){
                tfHoursPerWeekEmployee.setText(""+editableEmployee.getHoursPerWeek());
            }
            dpDateSalaryLevelEmployee.setValue(editableEmployee.getDateSalaryLevel());
            dpDateOfEmploymentEmployee.setValue(editableEmployee.getDateOfEmployment());
            //CONTACT
            tfTelNrEmployee.setText(editableEmployee.getTelNr());
            tfEmailEmployee.setText(editableEmployee.getEmail());
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

        if (!tfCheck(tfTitleEmployee, "^(\\D+)?$")) {
            employeeToAdd.setTitle(tfTitleEmployee.getText());
        } else {
            errors.add(true);
        }

        errors.add(tfCheck(tfFirstnameEmployee, "^\\D+$"));
        errors.add(tfCheck(tfLastnameEmployee, "^\\D+$"));

        if (!errors.contains(true)){
            employeeToAdd = new Employee(   comboSalutationEmployee.getSelectionModel().getSelectedItem(),
                    tfFirstnameEmployee.getText(),
                    tfLastnameEmployee.getText()
            );}


        if (!tfCheck(tfSsnrEmployee, "^([1-9][0-9]{3})?$")) {
            try {
                employeeToAdd.setSsnr(Integer.parseInt(tfSsnrEmployee.getText()));
            } catch (Exception ex){

            }
        } else {
            errors.add(true);
        }

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            employeeToAdd.setBirthDate(ldsc.fromString(dpBirthdateEmployee.getEditor().getText()));
            dpBirthdateEmployee.setStyle(null);
        } catch (Exception e) {
            dpBirthdateEmployee.setStyle("-FX-Border-Color: red");
            errors.add(true);
        }

        if (!tfCheck(tfStreetEmployee, "^(\\D+)?$") && !tfCheck(tfHousenumberEmployee, "^([1-9][0-9]*)?$")) {
            employeeToAdd.setStreetAndNr(tfStreetEmployee.getText() + " " + tfHousenumberEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfZipEmployee, "^([1-9][0-9]{3})?$")) {
            try {
                employeeToAdd.setZipCode(Integer.parseInt(tfZipEmployee.getText()));
            } catch (Exception e) { }

        } else {
            errors.add(true);
        }

        if (!tfCheck(tfPlaceEmployee, "^(\\D+)?$")){
            employeeToAdd.setPlace(tfPlaceEmployee.getText());
        } else {
            errors.add(true);
        }

        employeeToAdd.setPrivacy(new Privacy(new ArrayList<Boolean>(){{add(cbPrivacy1Employee.isSelected()); add(cbPrivacy2Employee.isSelected()); add(cbPrivacy3Employee.isSelected()); add(cbPrivacy4Employee.isSelected());}}));

        if (!tfCheck(tfTelNrEmployee, "^([0-9]*)?$")) {
            employeeToAdd.setTelNr(tfTelNrEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfEmailEmployee, "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)?$")) {
            employeeToAdd.setEmail(tfEmailEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfOccupationGroupEmployee, "^(\\D+)?$")){
            employeeToAdd.setOccupationGroup(tfOccupationGroupEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfSalaryLevelEmployee, "^(\\D+)?$")){
            employeeToAdd.setSalaryLevel(tfSalaryLevelEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfHoursPerWeekEmployee, "^([0-9]*)?$")){
            try {
                employeeToAdd.setHoursPerWeek(Integer.parseInt(tfHoursPerWeekEmployee.getText()));
            } catch (Exception e) {}
        } else {
            errors.add(true);
        }

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            employeeToAdd.setDateSalaryLevel(ldsc.fromString(dpDateSalaryLevelEmployee.getEditor().getText()));
            dpDateSalaryLevelEmployee.setStyle(null);
        } catch (Exception e) {
            dpDateSalaryLevelEmployee.setStyle("-FX-Border-Color: red");
            errors.add(true);
        }

        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            employeeToAdd.setDateOfEmployment(ldsc.fromString(dpDateOfEmploymentEmployee.getEditor().getText()));
            dpDateOfEmploymentEmployee.setStyle(null);
        } catch (Exception e) {
            dpDateOfEmploymentEmployee.setStyle("-FX-Border-Color: red");
            errors.add(true);
        }

        if (!tfCheck(tfIbanEmployee, "^([A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?)?$")) {
            employeeToAdd.setIban(tfIbanEmployee.getText());
        } else {
            errors.add(true);
        }

        if (!tfCheck(tfBicEmployee, "^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)?$")){
            employeeToAdd.setBic(tfBicEmployee.getText());
        } else {
            errors.add(true);
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

    private boolean tfCheck(TextField tf, String regex){
        boolean error = true;
        if (!tf.getText().matches(regex)) {
            error = true;
            tf.setStyle("-FX-Border-Color: red");
        } else {
            error = false;
            tf.setStyle(null);
        }
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboSalutationEmployee.getItems().setAll(Salutation.values());
        comboSalutationEmployee.getSelectionModel().select(0);

        this.accordionEmployees.setExpandedPane(tPaneBasicData);
    }
}
