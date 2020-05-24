package controller;

import app.SceneLoader;
import data.PersonDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SponsorList_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private TableView<?> tableSponsors;

    @FXML
    private TextField tfSearchSponsors;

    @FXML
    private Button btnSearchSponsors;

    @FXML
    private Button btnFilterSponsors;

    @FXML
    private Button btnAddSponsor;

    @FXML
    private Button btnDeleteSponsor;

    @FXML
    private Button btnEditSponsor;

    @FXML
    private Button btnNavActivities;

    @FXML
    private Button btnNavBills;

    @FXML
    private Button btnNavClients;

    @FXML
    private Button btnNavEmployees;

    @FXML
    private Button btnNavSponsors;

    @FXML
    private Button btnNavPhotos;

    @FXML
    private Button btnNavBirthdays;

    @FXML
    void btnAddSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnEditSponsor_Clicked(ActionEvent event) {

    }

    @FXML
    void btnFilterSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavActivities_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavBills_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavBirthdays_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavEmployees_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavPhotos_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSearchSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DBManager.open();
            PersonDAO.getInstance().setSponsor(FXCollections.observableArrayList(DBManager.getAllSponsors().values()));
            DBManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
