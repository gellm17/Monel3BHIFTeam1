package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import model.Client;

import java.util.ArrayList;

public class AddEditEmployee_Controller extends SceneLoader {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private ComboBox<?> comboSalutationEmployee;

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
    private TextField tfTelNrClient;

    @FXML
    private TextField tfEmailClient;

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
    private TextField tfIbanClient;

    @FXML
    private TextField tfBicClient;

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

    private Client editableClient = null;

    public Client getEditableClient() {
        return editableClient;
    }

    public void setEditableClient(Client editableClient) {
        this.editableClient = editableClient;
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

    }

    @FXML
    void btnSelectImageEmployee_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}
