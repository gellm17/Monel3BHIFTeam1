package data;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bill;
import model.Costs;
import model.EventProtocol;

import java.sql.SQLException;
import java.util.ArrayList;

public class CostDAO {
    private ObservableList<Costs> costs = FXCollections.observableList(new ArrayList<Costs>());
    private static CostDAO instance = null;

    private CostDAO() {

    }

    public static CostDAO getInstance() {
        if (instance == null) {
            instance = new CostDAO();
        }
        return instance;
    }

    public boolean addCost(Costs c) {
        if (c.getId() == 0) {
            try {
                c.setid(DBManager.insertCosts(c));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return costs.add(c);
    }

    public boolean deleteCost(Costs c) throws SQLException {
            DBManager.deleteCosts(c);
        return costs.remove(c);
    }

    public ObservableList<Costs> getCosts() {
        return costs;
    }

    public void setCosts(ObservableList<Costs> costs) {
        this.costs = costs;
    }

    public ObservableList<Costs> getCostsByEventProtocol(EventProtocol ep){
        ObservableList<Costs> res = FXCollections.observableList(new ArrayList<Costs>());
        for (Costs c: costs) {
            if (ep == c.getEventprotocol()){
                res.add(c);
            }
        }
        return res;
    }
}
