package controller;

import app.SceneLoader;
import data.PersonDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Client;
import model.Event;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BillList_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private TableView<?> tableProtocols;

    @FXML
    private TextField tfSearchClient;

    @FXML
    private TextField tfSearchEmployee;

    @FXML
    private TableView<Client> tableClients;

    @FXML
    private TableColumn<Client, String> tcFirstname;

    @FXML
    private TableColumn<Client, String> tcLastname;

    @FXML
    private TableColumn<Client, Integer> tcZipCode;

    @FXML
    private TableColumn<Client, String> tcPlace;

    private Object selectedItem;

    @FXML
    private Button btnViewBills;

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
        DBManager.open();
        PersonDAO.getInstance().setClients(FXCollections.observableArrayList(DBManager.getAllClients()));
        DBManager.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    //ObservableList<Client> clients = FXCollections.observableArrayList(PersonDAO.getInstance().getClients());
        this.tableClients.setItems(PersonDAO.getInstance().getClients());
        this.CreateColumns();
        this.ConfigureTableView(PersonDAO.getInstance().getClients());
       

    //this.tabActivities.setGraphic(buildImage("../resources/iconAkt.png");

        tableClients.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                try {
                    showReceipeForClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

        tableClients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            selectedItem = newSelection;

            System.out.println(selectedItem);
        }
    });


}

    private void showReceipeForClient() throws IOException {

        //TODO
    }

    @SuppressWarnings("unchecked")
    public void CreateColumns() {
        //Picture
        tcFirstname = new TableColumn<Client, String>("Vorname");
        tcLastname = new TableColumn<Client, String>("Nachname");
        tcZipCode = new TableColumn<Client, Integer>("Postleitzahl");
        tcPlace = new TableColumn<Client, String>("Ort");

        //Weitere Table Options
        //tcDiagnose = new TableColumn<Client, String>("Diagnose");
        //tcJob = new TableColumn<Client, String>("Beschäftigung");
        //tcIban = new TableColumn<Client, String>("IBAN");
        //tcBic = new TableColumn<Client, String>("Diagnose");




        tcFirstname.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        tcZipCode.setCellValueFactory(new PropertyValueFactory<Client, Integer>("zipCode"));
        tcPlace.setCellValueFactory(new PropertyValueFactory<Client, String>("place"));

        this.tableClients.getColumns().addAll(tcFirstname, tcLastname, tcZipCode, tcPlace);

    }

    public void ConfigureTableView(ObservableList<Client> clients) {
        //Width

        tcFirstname.prefWidthProperty().bind(tableClients.widthProperty().divide(4)); // w * 1/2
        tcLastname.prefWidthProperty().bind(tableClients.widthProperty().divide(4));
        tcZipCode.prefWidthProperty().bind(tableClients.widthProperty().divide(4)); // w * 1/4
        tcPlace.prefWidthProperty().bind(tableClients.widthProperty().divide(4));

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

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
    void btnSettings_Clicked(ActionEvent event) {

    }

    @FXML
    void btnViewBills_Clicked(ActionEvent event) {
        showReceipeForClient();
    }

}
