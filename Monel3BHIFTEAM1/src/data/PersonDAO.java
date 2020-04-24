package data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonDAO {
    private ObservableList<Client> clients = FXCollections.observableList(new ArrayList<Client>());           // ArrayList mit allen Personen
    private ArrayList<Employee> employees = new ArrayList<Employee>();      // ArrayList mit allen Employees
    private ArrayList<Sponsor> sponsor = new ArrayList<Sponsor>();      // ArrayList mit allen Company
    private static PersonDAO instance = null;


    private PersonDAO() {
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 11111));
       // clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 22222));
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 23452));
        clients.add(new Client(Salutation.Herr, "Clemens", "Burgmann", "Arnbach", "99", 9920, "Sillian", LocalDate.now(), 4919, "RZTIAT", "AT23 2342"));
    }
    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }

    public boolean addPerson(Person p) {
        boolean success = false;
        if(p instanceof Client) {
            Client c = (Client)p;
            success = clients.add(c);
        } else if (p instanceof Employee){
            Employee emp = (Employee) p;
            success = employees.add(emp);
        } else if (p instanceof Sponsor){
            Sponsor sp = (Sponsor) p;
            success = sponsor.add(sp);
        }
        return success;
    }

    public boolean deletePerson(Person p) {
        boolean success = false;
        if(p instanceof Client) {
            Client c = (Client)p;
            success = clients.remove(c);
        } else if (p instanceof Employee){
            Employee emp = (Employee) p;
            success = employees.remove(emp);
        } else if (p instanceof Sponsor){
            Sponsor sp = (Sponsor) p;
            success = sponsor.remove(sp);
        }
        return success;
    }




// GETTER
    public ArrayList<Sponsor> getSponsor() {
        return sponsor;
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
