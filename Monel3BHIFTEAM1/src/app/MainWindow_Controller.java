package app;

import data.PersonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Client;
import model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class MainWindow_Controller extends SceneLoader implements Initializable {
    @FXML private javafx.scene.control.Button closeButton;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label lbTitle;

    @FXML
    private TabPane tabPaneClients;

    @FXML
    private Tab tabActivities;

    @FXML
    private Tab tabBills;

    @FXML
    private Tab tabClients;

    @FXML
    private TableView<Client> tableClients;

    @FXML
    private TableColumn<Client, String> tcFirstname;

    @FXML
    private TableColumn<Client, String> tcLastname;

    @FXML
    private TableColumn<Client, Integer> tcSsnr;

    @FXML
    private TableColumn<Client, String> tcTelnr;


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
    private TextField tfSearchClients;

    @FXML
    private Button btnSearchClients;

    @FXML
    private Button btnFilterClients;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnDeleteClient;

    @FXML
    private Button btnEditClient;

    @FXML
    private Tab tabEmployees;

    @FXML
    private Tab tabSponsors;

    @FXML
    private Tab tabPhotos;

    @FXML
    private Tab tabBirthdays;

    private Object selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ObservableList<Client> clients = FXCollections.observableArrayList(PersonDAO.getInstance().getClients());
        this.tableClients.setItems(PersonDAO.getInstance().getClients());
        this.CreateColumns();
        this.ConfigureTableView(PersonDAO.getInstance().getClients());
        this.btnDeleteClient.setDisable(true);
        this.btnEditClient.setDisable(true);

        tableClients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDeleteClient.setDisable(false);
                btnEditClient.setDisable(false);
                selectedItem = newSelection;
                System.out.println(selectedItem);


            }
        });


    }

    @SuppressWarnings("unchecked")
    public void CreateColumns() {
        //Picture
        tcFirstname = new TableColumn<Client, String>("Vorname");
        tcLastname = new TableColumn<Client, String>("Nachname");
        tcSsnr = new TableColumn<Client, Integer>("Ssnr");
        tcTelnr = new TableColumn<Client, String>("Telefon");

        //Weitere Table Options
        //tcDiagnose = new TableColumn<Client, String>("Diagnose");
        //tcJob = new TableColumn<Client, String>("Beschäftigung");
        //tcIban = new TableColumn<Client, String>("IBAN");
        //tcBic = new TableColumn<Client, String>("Diagnose");



        tcSsnr.setCellValueFactory(new PropertyValueFactory<Client, Integer>("ssnr"));
        tcFirstname.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        tcLastname.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        tcTelnr.setCellValueFactory(new PropertyValueFactory<Client, String>("telNr"));
        this.tableClients.getColumns().addAll(tcSsnr, tcFirstname, tcLastname, tcTelnr);

    }

    public void ConfigureTableView(ObservableList<Client> clients) {
        //Width
        tcSsnr.prefWidthProperty().bind(tableClients.widthProperty().divide(5)); // w * 1/4
        tcFirstname.prefWidthProperty().bind(tableClients.widthProperty().divide(5)); // w * 1/2
        tcLastname.prefWidthProperty().bind(tableClients.widthProperty().divide(4));
        tcTelnr.prefWidthProperty().bind(tableClients.widthProperty().divide(3));
        /*
        this.tvVerPersonCompany.setEditable(true);
        this.tvCompany.setEditable(true);

        //Width
        tcVerSsnr.prefWidthProperty().bind(tvVerPersonCompany.widthProperty().divide(5)); // w * 1/4
        tcDiagnose.prefWidthProperty().bind(tvVerPersonCompany.widthProperty().divide(5)); // w * 1/2
        tcWorkplace.prefWidthProperty().bind(tvVerPersonCompany.widthProperty().divide(4));
        tcVerLic.prefWidthProperty().bind(tvVerPersonCompany.widthProperty().divide(3));

        tcCompName.prefWidthProperty().bind(tvCompany.widthProperty().divide(3)); // w * 1/4
        tcRegDate.prefWidthProperty().bind(tvCompany.widthProperty().divide(3)); // w * 1/2
        tcVerResponsible.prefWidthProperty().bind(tvCompany.widthProperty().divide(3));


        tcVerSsnr.setCellFactory(TextFieldTableCell.<Person, Integer>forTableColumn(new IntegerStringConverter()));
        tcVerSsnr.setOnEditCommit(new EventHandler<CellEditEvent<Person, Integer>>() {
            @Override
            public void handle(CellEditEvent<Person, Integer> t) {
                ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSsnr(t.getNewValue());
            }
        });

        tcDiagnose.setCellFactory(TextFieldTableCell.forTableColumn());
        tcDiagnose.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {
                ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });


        tcWorkplace.setCellFactory(ComboBoxTableCell.forTableColumn(companies));
        tcWorkplace.setOnEditCommit(new EventHandler<CellEditEvent<Person, Company>>() {
            @Override
            public void handle(CellEditEvent<Person, Company> t) {
                ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWorkplace(t.getNewValue());
            }
        });


        tcCompName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcCompName.setOnEditCommit(new EventHandler<CellEditEvent<Company, String>>() {
            @Override
            public void handle(CellEditEvent<Company, String> t) {
                ((Company) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });

        tcVerResponsible.setCellFactory(ComboBoxTableCell.forTableColumn(persons));
        tcVerResponsible.setOnEditCommit(new EventHandler<CellEditEvent<Company, Person>>() {
            @Override
            public void handle(CellEditEvent<Company, Person> t) {
                ((Company) t.getTableView().getItems().get(t.getTablePosition().getRow())).setResponsible(t.getNewValue());
            }
        });

         */
		/*
		tcRegDate.setCellFactory(DatePicker.forTableColumn());
		tcRegDate.setOnEditCommit(new EventHandler<CellEditEvent<Company, LocalDate>>() {
			@Override
			public void handle(CellEditEvent<Company, LocalDate> t) {
				((Company) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRegDate(t.getNewValue());
			}
		});
		*/
        /*
         * ObservableList<Person> company=FXCollections.observableArrayList(new
         * Person("Mike", 324543), new Person("Clemens", 294533));
         * //ObservableList<String> company = FXCollections.observableArrayList("Rapid",
         * "Salzburg", "Bayern München","Augsburg");
         * tcVerResponsible.setCellFactory(ComboBoxTableCell.forTableColumn(company));
         * tcVerResponsible.setOnEditCommit(new
         * EventHandler<CellEditEvent<JuristicPerson, Person>>() {
         *
         * @Override public void handle(CellEditEvent<JuristicPerson, Person> t) {
         * ((Company)t.getTableView().getItems().get(t.getTablePosition().getRow())).
         * setResponsible((t.getNewValue())); } });
         */
        /*
         * //Geht nicht wie Checkboxen in Cell darstellen
         * tcVerLic.setCellFactory(TextFieldTableCell.forTableColumn());
         * tcVerLic.setOnEditCommit(new EventHandler<CellEditEvent<JuristicPerson,
         * String>>() {
         *
         * @Override public void handle(CellEditEvent<JuristicPerson, String> t) {
         * ((Person)
         * t.getTableView().getItems().get(t.getTablePosition().getRow())).setLic((
         * ObservableSet<License>) FXCollections.observableArrayList(License.A)); } });
         */

    }




    @FXML
    void btnAddClient_Clicked(ActionEvent event) {
        showScene("AddEditClient");
    }

    @FXML
    void btnDeleteClient_Clicked(ActionEvent event) {
        PersonDAO.getInstance().deletePerson((Person)selectedItem);
    }

    @FXML
    void btnEditClient_Clicked(ActionEvent event) {
        //AddEditClient_Controller editcontroller = new AddEditClient_Controller();
        //editcontroller.setEditableClient((Client) selectedItem);
       // showScene("AddEditClient");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditClient.fxml"));
            BorderPane root = loader.load();

            Scene scene = new Scene(root);
            Stage primaryStage = this.getPrimStage();
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Monel Pro");
            primaryStage.setScene(scene);
            Screen screen = Screen.getPrimary();
            //Get controller of scene2
            AddEditClient_Controller editController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            editController.setEditableClient((Client) selectedItem);




            //Maximized
            Rectangle2D bounds = screen.getVisualBounds();
            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
            primaryStage.show();

            /*
            Stage primStage = null;
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddEditClient.fxml"));
            BorderPane root = (BorderPane)fxml.load();
            Scene scene = new Scene(root);
            primStage.setScene(scene);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            AddEditClient_Controller editcontroller = fxml.getController();
            editcontroller.setEditableClient((Client) selectedItem);

            primStage.setX(bounds.getMinX());
            primStage.setY(bounds.getMinY());
            primStage.setWidth(bounds.getWidth());
            primStage.setHeight(bounds.getHeight());
            primStage.show();

            SceneLoader loader = editcontroller;
            loader.setPrimaryStage(primStage);
            */

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void btnFilterClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnInfo_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSearchClients_Clicked(ActionEvent event) {

    }

    @FXML
    void btnSettings_Clicked(ActionEvent event) {

    }

}

