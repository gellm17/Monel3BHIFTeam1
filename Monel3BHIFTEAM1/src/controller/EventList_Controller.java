package controller;

import app.SceneLoader;
import data.EventDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Event;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventList_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private TableView<Event> tableEvents;

    @FXML
    private TextField tfSearchEvents;

    @FXML
    private Button btnSearchEvents;

    @FXML
    private Button btnFilterEvents;

    @FXML
    private Button btnAddSingleEvent;

    @FXML
    private Button btnAddGroupEvent;

    @FXML
    private Button btnDeleteEvent;

    @FXML
    private Button btnEditEvent;

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
    private TableColumn<Event, LocalDate> tcDate;
    @FXML
    private TableColumn<Event, String> tcBezeichnung;

    @FXML
    private TableColumn<Event, Boolean> tcKategorie;

    private Event selectedItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ObservableList<Client> clients = FXCollections.observableArrayList(PersonDAO.getInstance().getClients());
        this.tableEvents.setItems(EventDAO.getInstance().getEvents());
        this.CreateColumns();
        this.ConfigureTableView();
        this.btnDeleteEvent.setDisable(true);
        this.btnEditEvent.setDisable(true);

        //this.tabActivities.setGraphic(buildImage("../resources/iconAkt.png");

        tableEvents.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        throw new IOException("Hallo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tableEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDeleteEvent.setDisable(false);
                btnEditEvent.setDisable(false);
                selectedItem = newSelection;
                System.out.println(selectedItem);


            }
        });


    }

    @SuppressWarnings("unchecked")
    public void CreateColumns() {
        //Picture
        tcDate = new TableColumn<Event, LocalDate>("Datum");
        tcBezeichnung = new TableColumn<Event, String>("Bezeichnung");
        tcKategorie = new TableColumn<Event, Boolean>("Kategorie");

        //Weitere Table Options
        //tcDiagnose = new TableColumn<Client, String>("Diagnose");
        //tcJob = new TableColumn<Client, String>("Beschäftigung");
        //tcIban = new TableColumn<Client, String>("IBAN");
        //tcBic = new TableColumn<Client, String>("Diagnose");




        tcDate.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
        tcBezeichnung.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        tcKategorie.setCellValueFactory(new PropertyValueFactory<Event, Boolean>("isGroup"));


        this.tableEvents.getColumns().addAll(tcDate, tcBezeichnung, tcKategorie);

    }

    public void ConfigureTableView() {
        //Width
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tcDate.setCellFactory(column -> new TableCell<Event, LocalDate>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(formatter.format(date));
                }
            }
        });

        tcKategorie.setCellFactory(column -> new TableCell<Event, Boolean>() {
            @Override
            protected void updateItem(Boolean group, boolean empty) {
                super.updateItem(group, empty);
                if (empty) {
                    setText("");
                } else {
                    if (group) {
                        setText("Einzel-Aktivität");
                    } else {
                        setText("Gruppen-Aktivität");
                    }
                }
            }
        });
        tcDate.prefWidthProperty().bind(tableEvents.widthProperty().divide(3)); // w * 1/2
        tcBezeichnung.prefWidthProperty().bind(tableEvents.widthProperty().divide(3));
        tcKategorie.prefWidthProperty().bind(tableEvents.widthProperty().divide(3)); // w * 1/4

    }

    @FXML
    void btnAddGroupEvent_Clicked(ActionEvent event) {
        showScene("AddEditGroupEvent");
    }

    @FXML
    void btnAddSingleEvent_Clicked(ActionEvent event) {
        showScene("AddEditSingleEvent");
    }

    @FXML
    void btnDeleteEvent_Clicked(ActionEvent event) {

    }

    @FXML
    void btnEditEvent_Clicked(ActionEvent event) {

    }

    @FXML
    void btnFilterEvents_Clicked(ActionEvent event) {

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
    void btnSearchEvents_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}
