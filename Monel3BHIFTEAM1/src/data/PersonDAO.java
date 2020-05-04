package data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonDAO {
    private ObservableList<Person> persons = FXCollections.observableArrayList(new ArrayList<Person>());      // ArrayList mit allen Personen (ESV und Notfallkontakte)
    private ObservableList<Client> clients = FXCollections.observableList(new ArrayList<Client>());           // ArrayList mit allen Personen
    private ObservableList<Employee> employees = FXCollections.observableList(new ArrayList<Employee>());     // ArrayList mit allen Employees
    private ObservableList<Sponsor> sponsor = FXCollections.observableList(new ArrayList<>());                // ArrayList mit allen Company
    private static PersonDAO instance = null;


    private PersonDAO() {
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 11111));
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 22222));
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 23452));
        clients.add(new Client(Salutation.Herr, "Clemens", "Burgmann"));
        employees.add(new Employee(Salutation.Herr, "Mike", "Gell"));
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
        } else {
            success = persons.add(p);
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

    public void loadPersons(ResultSet rs) throws SQLException {
        String persontyp = "";
        while(rs.next()) {
            persontyp = rs.getString("personentyp");
            if(persontyp == "KLIENT") {
                //addPerson(new Client(Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer").split(" ")[0], rs.getString("strasse_hausnummer"), rs.getInt("svnr"), rs.getString("diagnose"), rs.getString("beschaeftigung"), "iban", "bic", rs.getString("allergien"), esv, nofall1, notfall2); //parameter noch nicht alle richtig
            } else if(persontyp == "MITARBEITER") {
                //addPerson(new Employee(Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer").split(" ")[0], rs.getString("strasse_hausnummer"), rs.getInt("svnr")); //fehlen noch parameter
            } else if(persontyp == "SPONSOR") {
                //addPerson(new Sponsor(Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer").split(" ")[0], rs.getString("strasse_hausnummer")); // fehlen noch parameter
            } else {
                addPerson(new Person(Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer").split(" ")[0], rs.getString("strasse_hausnummer").split(" ")[1], Integer.parseInt(rs.getString("plz")), rs.getString("ort"), rs.getString("telefonnummer"), rs.getString("email"), LocalDate.parse(rs.getString("geburtsdatum"))));
            }
        }
    }



    // GETTER
    public ObservableList<Sponsor> getSponsor() {
        return sponsor;
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }
}
