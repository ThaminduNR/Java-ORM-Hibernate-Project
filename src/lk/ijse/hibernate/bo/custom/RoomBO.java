package lk.ijse.hibernate.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.RoomDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    boolean addRoom(RoomDTO roomDTO) throws IOException;

    boolean updateRoom(RoomDTO roomDTO) throws IOException;

    ArrayList<RoomDTO> getAllRooms() throws IOException;

    boolean deleteRoom(String id) throws IOException;

    ObservableList<String> getAllRoomIds() throws IOException;

    ArrayList<RoomDTO> changeStateRoomId(String id) throws IOException;

    boolean updateRoomQty(String id) throws IOException;

    RoomDTO getRoom(String id) throws IOException;

}
