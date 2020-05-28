
   package controller;

import app.SceneLoader;
import data.BillDAO;
import data.PersonDAO;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
             //TODO gleich wie bei Client List zu ADDEDITController mitgeben
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




             tcDate.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("firstName"));
             tcUse.setCellValueFactory(new PropertyValueFactory<Bill, String>("lastName"));


             this.tableBills.getColumns().addAll(tcDate, tcUse);

        }

        public void ConfigureTableView(ObservableList<Bill> bills) {
             //Width

             tcDate.prefWidthProperty().bind(tableBills.widthProperty().divide(2)); // w * 1/2
             tcUse.prefWidthProperty().bind(tableBills.widthProperty().divide(2));


        }

        public void setClient(Client client) {
            this.client = client;
            afterSet();
             System.out.println("Hallo"+client.getId());
        }

        @FXML
        void btnGenerateBill_Clicked(ActionEvent event) {

        }

        @FXML
        void btnInfo_Clicked(ActionEvent event) {

        }

        @FXML
        void btnNextMonth_Clicked(ActionEvent event) {

        }

        @FXML
        void btnPrevMonth_Clicked(ActionEvent event) {

        }

        @FXML
        void btnPrintBill_Clicked(ActionEvent event) {

        }

        @FXML
        void btnSettings_Clicked(ActionEvent event) {

        }

    }
