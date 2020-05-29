
   package controller;

import app.SceneLoader;
import data.BillDAO;
import data.EventDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.util.converter.LocalDateStringConverter;
import model.Bill;
import model.Client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

   public class BillsPerClientList_Controller extends SceneLoader {

        @FXML
        private Button btnPrevMonth;

        @FXML
        private Label lbYearMonth;

        @FXML
        private Button btnNextMonth;

        @FXML
        private Button btnGenerateBill;

        @FXML
        private TableView<Bill> tableBills;

        @FXML
        private TableColumn<Bill, LocalDate> tcDate;
        @FXML
        private TableColumn<Bill, String> tcUse;

        @FXML
        private Button btnPrintBill;

        @FXML
        private Button btnInfo;

        @FXML
        private Button btnSettings;

        @FXML
        private Label lbTitle;

        @FXML
        private Button btnBack;

        private Client client = null;
        private Bill selectedBill = null;


        public void afterSet () {
             try {

                  DBManager.open();
                  BillDAO.getInstance().setBills(FXCollections.observableArrayList(DBManager.getAllBills(client)));
                  DBManager.close();
             } catch (Exception e) {
                  e.printStackTrace();
             }

             this.tableBills.setItems(BillDAO.getInstance().getBills());
             this.CreateColumns();
             this.ConfigureTableView(BillDAO.getInstance().getBills());

             tableBills.setOnMousePressed(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {
                       if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                            try {
                                 showPrintBill();
                            } catch (IOException e) {
                                 e.printStackTrace();
                            }
                       }
                  }
             });

             tableBills.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                  if (newSelection != null) {
                       selectedBill = newSelection;
                       System.out.println(selectedBill);



                  }
             });


        }

        private void showPrintBill() throws IOException {
             FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/ViewBill.fxml"));
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



             ViewBill_Controller billController = fxml.getController();
             //Pass whatever data you want. You can have multiple method calls here
             billController.setBill(selectedBill); //TODO


             SceneLoader loader = billController;
             loader.setPrimaryStage(this.getPrimStage());
        }

        @SuppressWarnings("unchecked")
        public void CreateColumns() {
             //Picture
             tcDate = new TableColumn<Bill, LocalDate>("Austellungsdatum");
             tcUse = new TableColumn<Bill, String>("use");


             //Weitere Table Options
             //tcDiagnose = new TableColumn<Client, String>("Diagnose");
             //tcJob = new TableColumn<Client, String>("Beschäftigung");
             //tcIban = new TableColumn<Client, String>("IBAN");
             //tcBic = new TableColumn<Client, String>("Diagnose");




             tcDate.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("dateOfIssue"));
             tcUse.setCellValueFactory(new PropertyValueFactory<Bill, String>("use"));


             this.tableBills.getColumns().addAll(tcDate, tcUse);

        }

        public void ConfigureTableView(ObservableList<Bill> bills) {
             //Width

             tcDate.prefWidthProperty().bind(tableBills.widthProperty().divide(2)); // w * 1/2
             tcUse.prefWidthProperty().bind(tableBills.widthProperty().divide(3));


        }

        public void setClient(Client client) {
            this.client = client;
            afterSet();
             System.out.println("Hallo"+client.getId());
        }

        @FXML
        void btnGenerateBill_Clicked(ActionEvent event) {
             Bill generatedBill = new Bill(0, client, LocalDate.now(), "Monatsrechnung " + lbYearMonth.getText() + " für " + client.getFirstName() + " " + client.getLastName());            //TODO NOT sure if date of today or yearMonth
             generatedBill.setEventProtocols(FXCollections.observableArrayList(EventDAO.getInstance().getEventProtocolsByClient(client)));
             BillDAO.getInstance().addBill(generatedBill);
        }

        @FXML
        void btnInfo_Clicked(ActionEvent event) {

        }

        @FXML
        void btnNextMonth_Clicked(ActionEvent event) {
             int currentMonth = Integer.parseInt(lbYearMonth.getText().split("/")[0]);
             int currentYear = Integer.parseInt(lbYearMonth.getText().split("/")[1]);

             if (currentMonth == 12){
                  lbYearMonth.setText("01/" + (currentYear+1));
             } else {
                  lbYearMonth.setText(String.format("%02d", currentMonth + 1) + "/" + currentYear);
             }
        }

        @FXML
        void btnPrevMonth_Clicked(ActionEvent event) {
             int currentMonth = Integer.parseInt(lbYearMonth.getText().split("/")[0]);
             int currentYear = Integer.parseInt(lbYearMonth.getText().split("/")[1]);

             if (currentMonth == 1){
                  lbYearMonth.setText("12/" + (currentYear-1));
             } else {
                  lbYearMonth.setText(String.format("%02d", currentMonth - 1) + "/" + currentYear);
             }
        }

        @FXML
        void btnPrintBill_Clicked(ActionEvent event) {
             try {
                  showPrintBill();
             } catch (IOException e){
                  e.printStackTrace();
             }
        }

        @FXML
        void btnSettings_Clicked(ActionEvent event) {

        }

    }
