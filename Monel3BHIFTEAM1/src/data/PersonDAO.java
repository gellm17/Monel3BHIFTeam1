package data;

import model.Client;
import model.Person;

public class PersonDAO {
    private ArrayList<Client> clients = new ArrayList<Client>();            // ArrayList mit allen Personen
    //private ArrayList<Employee> employees = new ArrayList<Employee>();      // ArrayList mit allen Company
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
        if(j instanceof Client) {
            Client c = (Client)p;
            success = clients.add(c);
        } else if {
            Employee c = (Company) j;
            success = companies.add(c);
        }
        return success;
    }
}
