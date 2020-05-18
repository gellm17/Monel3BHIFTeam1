package controller;

import app.SceneLoader;
import data.EventDAO;
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
import model.Employee;
import model.Event;

import javax.swing.*;
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
    private TableView<?> tableProtocols;

    @FXML
    private ComboBox<?> comboClient;

    @FXML
    private Button btnResetClient;

    @FXML
    private ComboBox<?> comboEmployee;

    @FXML
    private Button btnResetEmployee;

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
                    if (!group) {
                        setText("Einzelaktivität");
                    } else {
                        setText("Gruppenaktivität");
                    }
                }
            }
        });
        tcDate.prefWidthProperty().bind(tableEvents.widthProperty().divide(3)); // w * 1/2
        tcBezeichnung.prefWidthProperty().bind(tableEvents.widthProperty().divide(3));
        tcKategorie.prefWidthProperty().bind(tableEvents.widthProperty().divide(3)); // w * 1/4

    }

    @FXML
    void btnResetClient_Clicked(ActionEvent event) {

    }

    @FXML
    void btnResetEmployee_Clicked(ActionEvent event) {

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
        EventDAO.getInstance().deleteEvent(selectedItem);
    }

    @FXML
    void btnEditEvent_Clicked(ActionEvent event) {
        try {


            if (selectedItem.getIsGroup()) {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditGroupEvent.fxml"));
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
                AddEditGroupEvent_Controller editController = fxml.getController();
                editController.setEditableEvent((Event) selectedItem);
                SceneLoader loader = editController;
                loader.setPrimaryStage(this.getPrimStage());
            } else {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditSingleEvent.fxml"));
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
                AddEditSingleEvent_Controller editController = fxml.getController();
                editController.setEditableEvent((Event) selectedItem, null);
                SceneLoader loader = editController;
                loader.setPrimaryStage(this.getPrimStage());
            }





        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
