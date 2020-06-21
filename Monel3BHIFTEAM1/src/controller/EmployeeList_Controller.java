package controller;

import app.SceneLoader;
import controller.AddEditEmployee_Controller;
import data.PersonDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import model.Client;
import model.Employee;
import model.Person;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeList_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    //Table

    @FXML
    private TableView<Employee> tableEmployees;

    @FXML
    private TableColumn<Employee, String> tcFirstname;

    @FXML
    private TableColumn<Employee, String> tcLastname;

    @FXML
    private TableColumn<Employee, Boolean> tcVolunteering;

    @FXML
    private TableColumn<Employee, Integer> tcZipCode;

    @FXML
    private TableColumn<Employee, String> tcPlace;

    @FXML
    private TableColumn<Employee, String> tcTelNr;


    private Object selectedItem;


    /*Weitere Informationen
    @FXML
    private TableColumn<Client, String> tcDiagnose;

    @FXML
    private TableColumn<Client, String> tcJob;
    @FXML
    private TableColumn<Client, String> tcIban;
    @FXML
    private TableColumn<Client, String> tcBic;
    @FXML
    private TableColumn<Client, String> tcAllergies;


    @FXML
    private TableColumn<Client, Person> tcEsv;

    @FXML
    private TableColumn<Client, Person> tcEmergencyContact1;
    @FXML
    private TableColumn<Client, Person> tcEmergencyContact2;
*/

    @FXML
    private TextField tfSearchEmployees;

    @FXML
    private Button btnSearchEmployees;

    @FXML
    private Button btnFilterEmployees;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnDeleteEmployee;

    @FXML
    private Button btnEditEmployee;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            PersonDAO.getInstance().setEmployees(FXCollections.observableArrayList(DBManager.getAllEmployees().values()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tableEmployees.setItems((ObservableList<Employee>) PersonDAO.getInstance().getEmployees());
        this.CreateColumns();
        this.ConfigureTableView();
        this.btnDeleteEmployee.setDisable(true);
        this.btnEditEmployee.setDisable(true);

        tableEmployees.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        showEmployee();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //this.tabActivities.setGraphic(buildImage("../resources/iconAkt.png");

        tableEmployees.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDeleteEmployee.setDisable(false);
                btnEditEmployee.setDisable(false);
                selectedItem = newSelection;
                System.out.println(selectedItem);


            }
        });
    }

    @SuppressWarnings("unchecked")
    public void CreateColumns() {
        //Picture
        tcFirstname = new TableColumn<Employee, String>("Vorname");
        tcLastname = new TableColumn<Employee, String>("Nachname");
        tcVolunteering = new TableColumn<Employee, Boolean>("Ehrenamt");
        tcZipCode = new TableColumn<Employee, Integer>("Postleitzahl");
        tcPlace = new TableColumn<Employee, String>("Ort");
        tcTelNr = new TableColumn<Employee, String>("Telefon");

        //Weitere Table Options
        //tcDiagnose = new TableColumn<Client, String>("Diagnose");
        //tcJob = new TableColumn<Client, String>("Beschäftigung");
        //tcIban = new TableColumn<Client, String>("IBAN");
        //tcBic = new TableColumn<Client, String>("Diagnose");



        tcVolunteering.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("volunteering"));
        tcFirstname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tcZipCode.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("zipCode"));
        tcPlace.setCellValueFactory(new PropertyValueFactory<Employee, String>("place"));
        tcTelNr.setCellValueFactory(new PropertyValueFactory<Employee, String>("telNr"));
        this.tableEmployees.getColumns().addAll(tcFirstname, tcLastname, tcVolunteering, tcZipCode, tcPlace, tcTelNr);

    }

    public void ConfigureTableView() {
        //Width
        tcVolunteering.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6)); // w * 1/4
        tcFirstname.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6)); // w * 1/2
        tcLastname.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6));
        tcPlace.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6));
        tcZipCode.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6));
        tcTelNr.prefWidthProperty().bind(tableEmployees.widthProperty().divide(6));

    }


    @FXML
    void btnAddEmployee_Clicked(ActionEvent event) {
        showScene("AddEditEmployee");
    }

    @FXML
    void btnDeleteEmployee_Clicked(ActionEvent event) throws SQLException {
            PersonDAO.getInstance().deletePerson((Person)selectedItem);
    }

    @FXML
    void btnEditEmployee_Clicked(ActionEvent event) {
        try {

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditEmployee.fxml"));
            BorderPane root = fxml.load();
            Scene scene = new Scene(root);
            this.getPrimStage().setScene(scene);
            Screen screen = Screen.getPrimary();

            //Maximized
            Rectangle2D bounds = screen.getVisualBounds();
            this.getPrimStage().setX(bounds.getMinX());
            this.getPrimStage().setY(bounds.getMinY());
            this.getPrimStage().setWidth(bounds.getWidth());
            this.getPrimStage().setHeight(bounds.getHeight());
            this.getPrimStage().show();


            AddEditEmployee_Controller editController = fxml.getController();
            editController.setEditableEmployee((Employee) selectedItem);


            SceneLoader loader = editController;
            loader.setPrimaryStage(this.getPrimStage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnFilterEmployees_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {
        openInfo();
    }

    @FXML
    void btnNavActivities_Clicked(ActionEvent event) {
        showScene("EventList");
    }

    @FXML
    void btnNavBills_Clicked(ActionEvent event) {
        showScene("BillList");
    }

    @FXML
    void btnNavBirthdays_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavClients_Clicked(ActionEvent event) {
        showScene("ClientList");
    }

    @FXML
    void btnNavEmployees_Clicked(ActionEvent event) {
        showScene("EmployeeList");
    }

    @FXML
    void btnNavPhotos_Clicked(ActionEvent event) {

    }

    @FXML
    void btnNavSponsors_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSearchEmployees_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {
        openSettings();
    }


    private void showEmployee() throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/EmployeeSummary.fxml"));
        BorderPane root = fxml.load();
        Scene scene = new Scene(root);
        this.getPrimStage().setScene(scene);
        Screen screen = Screen.getPrimary();

        //Maximized
        Rectangle2D bounds = screen.getVisualBounds();
        this.getPrimStage().setX(bounds.getMinX());
        this.getPrimStage().setY(bounds.getMinY());
        this.getPrimStage().setWidth(bounds.getWidth());
        this.getPrimStage().setHeight(bounds.getHeight());
        this.getPrimStage().show();



        EmployeeSummary_Controller showController = fxml.getController();
        //Pass whatever data you want. You can have multiple method calls here
        showController.setshowEmployee((Employee) selectedItem);


        SceneLoader loader = showController;
        loader.setPrimaryStage(this.getPrimStage());

    }


}
