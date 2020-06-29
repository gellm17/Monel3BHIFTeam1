package controller;

import app.SceneLoader;
import data.EventDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.util.converter.LocalDateStringConverter;
import model.Client;
import model.Employee;
import model.Event;
import model.EventProtocol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEditGroupEvent_Controller extends SceneLoader implements Initializable {

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private DatePicker dpDateEvent;

    @FXML
    private TextField tfNameEvent;

    @FXML
    private TableView<EventProtocol> tableProtocols;

    @FXML
    private Button btnAddProtocol;

    @FXML
    private Button btnDeleteProtocol;

    @FXML
    private Button btnEditProtocol;

    @FXML
    private TextArea taDescription;

    @FXML
    private Label lbMessage;

    private EventProtocol selectedItem;

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
    private Button btnCancelEvent;

    @FXML
    private Button btnOkEvent;

    private Event editableEvent = null;
    private int errorCounter = 0;
    private boolean wantToAddAPerson = false;

    public Event getEditableEvent() {
        return editableEvent;
    }

    public void setEditableEvent(Event editableEvent) {
        this.editableEvent = editableEvent;
        if (editableEvent != null) {
            dpDateEvent.setValue(editableEvent.getDate());
            taDescription.setText(editableEvent.getNote());
            tfNameEvent.setText(editableEvent.getName());
            tableProtocols.setItems(EventDAO.getInstance().getEventProtocolsByEvent(editableEvent));
        }
    }

    public void configureTableView() {
        //Width
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        /*//EventProtocol
        tcDateProtocol.setCellFactory(column -> new TableCell<EventProtocol, String>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(formatter.format(date));
                }
            }
        });*/
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



    }

    @SuppressWarnings("unchecked")
    public void createColumns() {

        tcDateProtocol = new TableColumn<EventProtocol, LocalDate>("Datum");
        tcEvent = new TableColumn<EventProtocol, Event>("Aktivität");
        tcClient = new TableColumn<EventProtocol, Client>("Klient");
        tcEmployee = new TableColumn<EventProtocol, Employee>("Mitarbeiter");
        tcHourlyRate = new TableColumn<EventProtocol, Double>("Stundensatz");
        tcRideCosts = new TableColumn<EventProtocol, Double>("Fahrtkosten");
        tcStart = new TableColumn<EventProtocol, LocalTime>("Start");
        tcEnd = new TableColumn<EventProtocol, LocalTime>("Ende");


        tcDateProtocol.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalDate>("year_month"));
        tcEvent.setCellValueFactory(new PropertyValueFactory<EventProtocol, Event>("event"));
        tcClient.setCellValueFactory(new PropertyValueFactory<EventProtocol, Client>("client"));
        tcEmployee.setCellValueFactory(new PropertyValueFactory<EventProtocol, Employee>("employee"));
        tcHourlyRate.setCellValueFactory(new PropertyValueFactory<EventProtocol, Double>("hourlyRate"));
        //TODO tcRideCosts.setCellValueFactory(new PropertyValueFactory<EventProtocol, Double>("rideCosts"));
        tcStart.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalTime>("startTime"));
        tcEnd.setCellValueFactory(new PropertyValueFactory<EventProtocol, LocalTime>("endTime"));

        this.tableProtocols.getColumns().addAll(tcDateProtocol, tcEvent, tcClient, tcEmployee, tcHourlyRate, tcRideCosts, tcStart, tcEnd);

    }

    @FXML
    void btnAddProtocol_Clicked(ActionEvent event) throws SQLException {
        wantToAddAPerson = true;
        btnOkEvent_Clicked(event);
    }

    @FXML
    void btnCancelEvent_Clicked(ActionEvent event) {
        showScene("EventList");
    }

    @FXML
    void btnDeleteProtocol_Clicked(ActionEvent event) throws SQLException {
        EventDAO.getInstance().deleteEventProtcol(selectedItem);
        tableProtocols.setItems(EventDAO.getInstance().getEventProtocolsByEvent(editableEvent));
        //tableProtocols.getItems().setAll(EventDAO.getInstance().getEventProtocolByEvent(editableEvent));
    }

    @FXML
    void btnEditProtocol_Clicked(ActionEvent event) {
        goToEdit();
    }

    private void goToEdit() {
        try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditEventProtocol.fxml"));
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
                AddEditEventProtocol_Controller editController = fxml.getController();
                editController.setEditableEvent((EventProtocol) selectedItem);
                editController.setAssignedEvent(editableEvent);
                SceneLoader loader = editController;
                loader.setPrimaryStage(this.getPrimStage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnOkEvent_Clicked(ActionEvent event) throws SQLException {
        errorCounter = 0;
        Event thisEvent;
        if (editableEvent != null){
            thisEvent = editableEvent;
        } else {
            thisEvent = new Event();
        }
        try {
            LocalDateStringConverter ldsc = new LocalDateStringConverter();
            thisEvent.setDate(ldsc.fromString(dpDateEvent.getEditor().getText()));
            dpDateEvent.setStyle(null);
        } catch (Exception e) {
            dpDateEvent.setStyle("-FX-Border-Color: red");
            errorCounter++;
            lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
        }

        if (!tfCheck(tfNameEvent, "^.+$")) {
            thisEvent.setName(tfNameEvent.getText());
        }

        if (!taCheck(taDescription, "^.+$")) {
            thisEvent.setNote(taDescription.getText());
        }

        thisEvent.setIsGroup(true);

        if (editableEvent != null){
            thisEvent.setId(editableEvent.getId());
        }

        if (errorCounter == 0 && EventDAO.getInstance().addEvent(thisEvent)) {
            /*if (editableEvent != null) {
                for(EventProtocol ep:  EventDAO.getInstance().getEventProtocolsByEvent(editableEvent)){
                    ep.setEvent(thisEvent);
                }
                //EventDAO.getInstance().deleteEvent(editableEvent);
            }*/
            if (wantToAddAPerson) {
                try {
                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/AddEditEventProtocol.fxml"));
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


                    AddEditEventProtocol_Controller editController = fxml.getController();
                    editController.setAssignedEvent(thisEvent);


                    SceneLoader loader = editController;
                    loader.setPrimaryStage(this.getPrimStage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                super.showScene("EventList");
            }
        }
    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {
        openSettings();
    }

    private boolean tfCheck(TextField tf, String regex) {
        boolean error = true;
        if (!tf.getText().matches(regex)) {
            error = true;
            errorCounter++;
            tf.setStyle("-FX-Border-Color: red");
            lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
        } else {
            error = false;
            tf.setStyle(null);
        }
        return error;
    }

    private boolean taCheck(TextArea ta, String regex) {
        boolean error = true;
        if (!ta.getText().matches(regex)) {
            error = true;
            errorCounter++;
            ta.setStyle("-FX-Border-Color: red");
            lbMessage.setText("Es sind " + errorCounter + " Fehler aufgetreten!");
        } else {
            error = false;
            ta.setStyle(null);
        }
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        if (editableEvent != null) {
            tableProtocols.setItems(EventDAO.getInstance().getEventProtocolsByEvent(editableEvent));
        }

        createColumns();
        configureTableView();
        this.btnDeleteProtocol.setDisable(true);
        this.btnEditProtocol.setDisable(true);

        tableProtocols.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDeleteProtocol.setDisable(false);
                btnEditProtocol.setDisable(false);
                selectedItem = newSelection;
                System.out.println(selectedItem);
            }
        });
    }
}
