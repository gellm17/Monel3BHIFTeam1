package data;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bill;
import model.Bill;
import model.EventProtocol;

import java.util.ArrayList;
import java.util.Iterator;

public class BillDAO {

    private ObservableList<Bill> bills = FXCollections.observableList(new ArrayList<Bill>());
    private static BillDAO instance = null;

    private BillDAO() {
        /*Bill e = new Bill(2, LocalDate.now(), "Fußball", false);
        this.events.add(new Bill(1, LocalDate.now(), "Kino", true));
        this.events.add(e);*/
        //this.eventProtocols.add(new EventProtocol(1, LocalTime.now(), LocalTime.now(), LocalDate.now(), 10.03, new Employee(Salutation.Herr,"Herbert", "Gell"), new Client(Salutation.Herr, "Michael", "Gell"), e, 100.01));
    }

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }


    public void setBills(ObservableList<Bill> bills) {
        this.bills = bills;
    }

    public ObservableList<Bill> getBills() {
        return bills;
    }

}


