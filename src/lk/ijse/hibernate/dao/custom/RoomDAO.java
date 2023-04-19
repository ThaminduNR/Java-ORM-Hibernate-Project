package lk.ijse.hibernate.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Room;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room,String> {
    ObservableList<String> getRoomIds() throws IOException;

    ArrayList<Room> changeStateRoomId(String id) throws IOException;

    Room searchRoom(String id, Session session) throws IOException;

    boolean updateRoomQty(String id) throws IOException;


}
