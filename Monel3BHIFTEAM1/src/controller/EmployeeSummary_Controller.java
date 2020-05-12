package controller;

import app.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Client;
import model.Employee;
import model.Privacy;

public class EmployeeSummary_Controller extends SceneLoader {

    @FXML
    private ImageView imgEmployee;

    @FXML
    private Button btnDeleteEmployee;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Label lbNameEmployee;

    @FXML
    private Label lbSsnrEmployee;

    @FXML
    private Label lbBirthdateEmployee;

    @FXML
    private Label lbStreetEmployee;

    @FXML
    private Label lbPlaceEmployee;

    @FXML
    private Label lbPhoneEmployee;

    @FXML
    private Label lbEmailEmployee;

    @FXML
    private Label lbIbanEmployee;

    @FXML
    private Label lbBicEmployee;

    @FXML
    private Label lbVolunteeringEmployee;

    @FXML
    private Label lbOccupationGroupEmployee;

    @FXML
    private Label lbStreetClient1;

    @FXML
    private Label lbSalaryLevelEmployee;

    @FXML
    private Label lbDateSalaryLevelEmployee;

    @FXML
    private Label lbDateOfEmploymentEmployee;

    @FXML
    private CheckBox cbPrivacy1Employee;

    @FXML
    private CheckBox cbPrivacy4Employee;

    @FXML
    private CheckBox cbPrivacy3Employee;

    @FXML
    private CheckBox cbPrivacy2Employee;

    private Employee showEmployee = null;

    public Employee getshowEmployee() {
        return showEmployee;
    }

    public void setshowEmployee(Employee showEmployee) {
        this.showEmployee = showEmployee;
        if (showEmployee != null) {
            if (showEmployee.getSsnr() != 0){
                lbSsnrEmployee.setText(""+showEmployee.getSsnr());
            }
            lbNameEmployee.setText(showEmployee.getSalutation() + " " + showEmployee.getTitle() + " " + showEmployee.getFirstName() + " " + showEmployee.getLastName());
            lbBirthdateEmployee.setText(showEmployee.getBirthDate() != null? showEmployee.getBirthDate().toString() : "");
            lbStreetEmployee.setText(showEmployee.getStreetAndNr());
            lbPlaceEmployee.setText(showEmployee.getPlace());
            lbPhoneEmployee.setText(showEmployee.getTelNr());
            lbEmailEmployee.setText(showEmployee.getEmail());
            lbIbanEmployee.setText(showEmployee.getIban());
            lbBicEmployee.setText(showEmployee.getBic());
            lbDateOfEmploymentEmployee.setText(showEmployee.getDateOfEmployment() != null? showEmployee.getDateOfEmployment().toString() : "");
            lbDateSalaryLevelEmployee.setText(showEmployee.getDateSalaryLevel() != null? showEmployee.getDateSalaryLevel().toString() : "");
            lbOccupationGroupEmployee.setText(showEmployee.getOccupationGroup().toString());
            lbVolunteeringEmployee.setText(showEmployee.isVolunteering() ? "Ehrenamt" : "Hauptamt");
            lbSalaryLevelEmployee.setText(showEmployee.getSalaryLevel().toString());
            //PRIVACY
            Privacy privacyOfshowEmployee = new Privacy();
            if (showEmployee.getPrivacy() != null) {
                privacyOfshowEmployee = showEmployee.getPrivacy();
            }
            cbPrivacy1Employee.setSelected(privacyOfshowEmployee.getPrivacies().get(0));
            cbPrivacy1Employee.setSelected(privacyOfshowEmployee.getPrivacies().get(1));
            cbPrivacy1Employee.setSelected(privacyOfshowEmployee.getPrivacies().get(2));
            cbPrivacy1Employee.setSelected(privacyOfshowEmployee.getPrivacies().get(3));
        }
    }

    @FXML
    void btnDeleteEmployee_Clicked(ActionEvent event) {

    }

    @FXML
    void btnEditEmployee_Clicked(ActionEvent event) {

    }

}
