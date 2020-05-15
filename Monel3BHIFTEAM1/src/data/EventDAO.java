
package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EventDAO {

    private ObservableList<Event> events = FXCollections.observableList(new ArrayList<Event>());
    private ObservableList<EventProtocol> eventProtocols = FXCollections.observableList(new ArrayList<EventProtocol>());
    private static EventDAO instance = null;

    private EventDAO() {
    }
    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    public boolean addEvent(Event e) {
        return events.add(e);
    }
    public boolean addEventProtcol(EventProtocol ep) {
        return eventProtocols.add(ep);
    }

    public boolean deleteEvent(Event e) {
        return events.remove(e);
    }
    public boolean deleteEventProtcol(EventProtocol ep) {
        return eventProtocols.remove(ep);
    }


    public void loadPersons(ResultSet rs) throws SQLException {
       // JEG's part
    }

    public ObservableList<Event> getEvents() {
        return events;
    }

    public ObservableList<EventProtocol> getEventProtocols() {
        return eventProtocols;
    }
}
