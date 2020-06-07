
package data;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EventDAO {

    private ObservableList<Event> events = FXCollections.observableList(new ArrayList<Event>());
    private ObservableList<EventProtocol> eventProtocols = FXCollections.observableList(new ArrayList<EventProtocol>());
    private static EventDAO instance = null;

    private EventDAO() {
        /*Event e = new Event(2, LocalDate.now(), "Fußball", false);
        this.events.add(new Event(1, LocalDate.now(), "Kino", true));
        this.events.add(e);*/
        //this.eventProtocols.add(new EventProtocol(1, LocalTime.now(), LocalTime.now(), LocalDate.now(), 10.03, new Employee(Salutation.Herr,"Herbert", "Gell"), new Client(Salutation.Herr, "Michael", "Gell"), e, 100.01));
    }
    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    public boolean addEvent(Event e) {
        if (e.getId() == 0) {
            try {
                e.setId(DBManager.insertEvent(e));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return events.add(e);
    }
    public boolean addEventProtcol(EventProtocol ep) {
        if (ep.getId() == 0) {
            try {
                ep.setId(DBManager.insertEventprotocol(ep));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return eventProtocols.add(ep);
    }

    public boolean deleteEvent(Event e) throws SQLException {
        DBManager.deleteEvent(e);
        return events.remove(e);
    }
    public boolean deleteEventProtcol(EventProtocol ep) throws SQLException {
        DBManager.deleteEventprotocol(ep);
        return eventProtocols.remove(ep);
    }

    public EventProtocol getEventProtocolByEvent(Event event) {
        Iterator<EventProtocol> it = eventProtocols.iterator();
        EventProtocol res = null;
        while (it.hasNext() && res == null){
            res = it.next();
            if (res.getEvent().getId() != event.getId()){
                res = null;
            }
        }
        return res;
    }

    public ObservableList<EventProtocol> getEventProtocolsByEvent(Event event){
        Iterator<EventProtocol> it = eventProtocols.iterator();
        ObservableList<EventProtocol> res = FXCollections.observableList(new ArrayList<EventProtocol>());
        while (it.hasNext()){
            EventProtocol current = it.next();
            if (current.getEvent().getId() == event.getId()){
                res.add(current);
            }
        }
        return res;
    }

    public ObservableList<Event> getEvents() {
        return events;
    }

    public ObservableList<EventProtocol> getEventProtocols() {
        return eventProtocols;
    }

    public void setEvents(ObservableList<Event> events) { this.events = events; }

    public void setEventProtocols(ObservableList<EventProtocol> eventProtocols) { this.eventProtocols = eventProtocols; }

    public ObservableList<EventProtocol> getEventProtocolsByClientMonth(Client client, String yearMonth) {
        Iterator<EventProtocol> it = eventProtocols.iterator();
        ObservableList<EventProtocol> res = FXCollections.observableList(new ArrayList<EventProtocol>());
        while (it.hasNext()){
            EventProtocol current = it.next();
            if (current.getClient().getId() == client.getId() && yearMonth.equals(current.getYear_month())/*Integer.parseInt(current.getYear_month().split("/")[0]) == Integer.parseInt(yearMonth.split("/")[0]) && Integer.parseInt(current.getYear_month().split("/")[1]) == Integer.parseInt(yearMonth.split("/")[1])*/){
                res.add(current);
            }
        }
        return res;
    }
}
