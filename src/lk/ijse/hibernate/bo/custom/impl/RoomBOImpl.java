package lk.ijse.hibernate.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.ROOM);
    @Override
    public boolean addRoom(RoomDTO roomDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = new Room(roomDTO.getRoomId(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getQty());
        try{
            roomDAO.add(room,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = new Room(roomDTO.getRoomId(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getQty());

        try{
            roomDAO.update(room,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<RoomDTO> roomList = new ArrayList<>();
        try {
            ArrayList<Room> all = roomDAO.getAll(session);
            for (Room r:all) {
                roomList.add(new RoomDTO(
                        r.getRoomId(),
                        r.getRoomType(),
                        r.getKeyMoney(),
                        r.getQty()
                ));
            }
            transaction.commit();
            return roomList;

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return roomList;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomDAO.delete(id,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public ObservableList<String> getAllRoomIds() throws IOException {
        return roomDAO.getRoomIds();
    }

    @Override
    public ArrayList<RoomDTO> changeStateRoomId(String id) throws IOException {
        ArrayList<Room> rooms = roomDAO.changeStateRoomId(id);

        ArrayList<RoomDTO> roomDetail = new ArrayList<>();

        for (Room r:rooms) {
            roomDetail.add(new RoomDTO(
                 r.getRoomId(),
                 r.getRoomType(),
                 r.getKeyMoney(),
                 r.getQty()
            ));
        }

        return roomDetail;
    }

    @Override
    public boolean updateRoomQty(String id) throws IOException {
        return roomDAO.updateRoomQty(id);
    }

    @Override
    public RoomDTO getRoom(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room r = roomDAO.searchRoom(id, session);
        transaction.commit();
        session.close();

        return new RoomDTO(r.getRoomId(), r.getRoomType(), r.getKeyMoney(), r.getQty());
    }
}
