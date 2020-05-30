package controller;

import app.SceneLoader;
import data.EventDAO;
import data.PersonDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private TableView<EventProtocol> tableProtocols;
/*
    @FXML
    private ComboBox<Client> comboClient;
*/
    @FXML
    private Button btnResetClient;
/*
    @FXML
    private ComboBox<Employee> comboEmployee;
*/

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

    //Col for Event
    @FXML
    private TableColumn<Event, LocalDate> tcDate;
    @FXML
    private TableColumn<Event, String> tcBezeichnung;
    @FXML
    private TableColumn<Event, Boolean> tcKategorie;

    // Col for EventProtocol
    //Col for Event
    @FXML
    private TableColumn<EventProtocol, Client> tcClient;
    @FXML
    private TableColumn<EventProtocol, Employee> tcEmployee;
    @FXML
    private TableColumn<EventProtocol, LocalTime> tcStart;
    @FXML
    private TableColumn<EventProtocol, LocalTime> tcEnd;
    @FXML
    private TableColumn<EventProtocol, LocalDate> tcDateProtocol;
    @FXML
    private TableColumn<EventProtocol, Double> tcRideCosts;
    @FXML
    private TableColumn<EventProtocol, Double> tcHourlyRate;
    @FXML
    private TableColumn<EventProtocol, Event> tcEvent;
    @FXML
    private TextField tfSearchClient;
    @FXML
    private TextField tfSearchEmployee;


    private Event selectedItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DBManager.open();
            EventDAO.getInstance().setEvents(FXCollections.observableArrayList(DBManager.getAllEvents().values()));
            EventDAO.getInstance().setEventProtocols(FXCollections.observableArrayList(DBManager.getAllEventProtocols()));
            PersonDAO.getInstance().setEmployees(FXCollections.observableArrayList(DBManager.getAllEmployees().values()));
            PersonDAO.getInstance().setClients(FXCollections.observableArrayList(DBManager.getAllClients()));
            DBManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





        // 5. Add sorted (and filtered) data to the table.
        tableProtocols.setItems(iniSearchEventProtocol());


        //ObservableList<Client> clients = FXCollections.observableArrayList(PersonDAO.getInstance().getClients());
        this.tableEvents.setItems(iniSearchEvents());

        this.CreateColumns();
        this.ConfigureTableView();
        this.btnDeleteEvent.setDisable(true);
        this.btnEditEvent.setDisable(true);

        //this.tabActivities.setGraphic(buildImage("../resources/iconAkt.png");

        tableEvents.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    goToEdit();
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

        tableProtocols.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    goToEdit();
                }
            }
        });

        tableProtocols.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedItem = newSelection.getEvent();
                System.out.println(selectedItem);


            }
        });

        //this.comboClient.getItems().setAll(PersonDAO.getInstance().getClients());
        //this.comboEmployee.getItems().setAll(PersonDAO.getInstance().getEmployees());
    }

    private SortedList<Event> iniSearchEvents() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Event> filteredData = new FilteredList<>(EventDAO.getInstance().getEvents(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfSearchEvents.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableEvents.comparatorProperty());
        return sortedData;
    }

    private SortedList<EventProtocol> iniSearchEventProtocol() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<EventProtocol> filteredData = new FilteredList<>(EventDAO.getInstance().getEventProtocols(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfSearchClient.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eventProtocol -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (eventProtocol.getClient().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearchEmployee.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eventProtocol -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (eventProtocol.getEmployee().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<EventProtocol> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableProtocols.comparatorProperty());
        return sortedData;
    }

    @SuppressWarnings("unchecked")
    public void CreateColumns() {
        //Picture
        tcDate = new TableColumn<Event, LocalDate>("Datum");
        tcBezeichnung = new TableColumn<Event, String>("Bezeichnung");
        tcKategorie = new TableColumn<Event, Boolean>("Kategorie");

        tcDateProtocol = new TableColumn<EventProtocol, LocalDate>("Datum");
        tcEvent = new TableColumn<EventProtocol, Event>("Aktivität");
        tcClient = new TableColumn<EventProtocol, Client>("Klient");
        tcEmployee = new TableColumn<EventProtocol, Employee>("Mitarbeiter");
        tcHourlyRate = new TableColumn<EventProtocol, Double>("Stundensatz");
        tcRideCosts = new TableColumn<EventProtocol, Double>("Fahrtkosten");
        tcStart = new TableColumn<EventProtocol, LocalTime>("Start");
        tcEnd = new TableColumn<EventProtocol, LocalTime>("Ende");
        //Weitere Table Options
        //tcDiagnose = new TableColumn<Client, String>("Diagnose");
        //tcJob = new TableColumn<Client, String>("Beschäftigung");
        //tcIban = new TableColumn<Client, String>("IBAN");
        //tcBic = new TableColumn<Client, String>("Diagnose");




        tcDate.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
        tcBezeichnung.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        tcKategorie.setCellValueFactory(new PropertyValueFactory<Event, Boolean>("isGroup"));


        tcDateProtocol.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalDate>("year_month"));
        tcEvent.setCellValueFactory(new PropertyValueFactory<EventProtocol, Event>("event"));
        tcClient.setCellValueFactory(new PropertyValueFactory<EventProtocol, Client>("client"));
        tcEmployee.setCellValueFactory(new PropertyValueFactory<EventProtocol, Employee>("employee"));
        tcHourlyRate.setCellValueFactory(new PropertyValueFactory<EventProtocol, Double>("hourlyRate"));
        tcRideCosts.setCellValueFactory(new PropertyValueFactory<EventProtocol, Double>("rideCosts"));
        tcStart.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalTime>("startTime"));
        tcEnd.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalTime>("endTime"));


        this.tableEvents.getColumns().addAll(tcDate, tcBezeichnung, tcKategorie);

        this.tableProtocols.getColumns().addAll(tcDateProtocol, tcEvent, tcClient, tcEmployee, tcHourlyRate, tcRideCosts, tcStart, tcEnd);

    }

    public void ConfigureTableView() {
        //Width
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        //Event
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

        //EventProtocol
tcDateProtocol.setCellFactory(column -> new TableCell<EventProtocol, LocalDate>() {
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
        tcHourlyRate.setCellFactory(column -> new TableCell<EventProtocol, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(value+" €");
                }
            }
        });

        tcRideCosts.setCellFactory(column -> new TableCell<EventProtocol, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(value+" €");
                }
            }
        });



        tcDateProtocol.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcEvent.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcClient.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcEmployee.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcHourlyRate.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcRideCosts.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcStart.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4
        tcEnd.prefWidthProperty().bind(tableEvents.widthProperty().divide(8)); // w * 1/4


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
        goToEdit();
    }

    @FXML
    void btnFilterEvents_Clicked(ActionEvent event) {

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
    void btnSearchEvents_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

    private void goToEdit() {
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
                editController.setEditableEvent((Event) selectedItem, EventDAO.getInstance().getEventProtocolByEvent((Event) selectedItem));
                SceneLoader loader = editController;
                loader.setPrimaryStage(this.getPrimStage());
            }



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
