package data;
import model.*;
import java.util.ArrayList;

public class PersonDAO {
    private ArrayList<Client> clients = new ArrayList<Client>();            // ArrayList mit allen Personen
    private ArrayList<Employee> employees = new ArrayList<Employee>();      // ArrayList mit allen Company
    private ArrayList<Sponsor> sponsor = new ArrayList<Sponsor>();      // ArrayList mit allen Company
    private static PersonDAO instance = null;


    private PersonDAO() {

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




// GETTER
    public ArrayList<Sponsor> getSponsor() {
        return sponsor;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
