package lk.ijse.hibernate.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    /*@Override
    public boolean add(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(room);


        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public ArrayList<Room> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Room> roomList = new ArrayList<>();
        String hql ="FROM Room";
        List<Room> list = session.createQuery(hql).list();

        for (Room r:list) {
            roomList.add(new Room(
                   r.getRoomId(),
                   r.getRoomType(),
                   r.getKeyMoney(),
                   r.getQty()
            ));
        }


        transaction.commit();
        session.close();
        return roomList;
    }

    @Override
    public boolean update(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, id);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }*/

    @Override
    public ArrayList<Room> getAll(Session session) throws IOException {
        List room = session.createQuery("FROM Room").list();
        return (ArrayList<Room>) room;
    }

    @Override
    public void add(Room room, Session session) {
        session.save(room);
    }

    @Override
    public void update(Room room, Session session) {
        session.update(room);
    }

    @Override
    public void delete(String id, Session session) {
        Room room = session.get(Room.class, id);
        session.delete(room);
    }

    @Override
    public ObservableList<String> getRoomIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<String> roomIdList = FXCollections.observableArrayList();
        String hql="SELECT roomId FROM Room";
        List<String> list = session.createQuery(hql).list();

        roomIdList.addAll(list);
        transaction.commit();
        session.close();

        return roomIdList;
    }

    @Override
    public ArrayList<Room> changeStateRoomId(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Room WHERE roomId=:roomId";
        Query query = session.createQuery(hql);
        query.setParameter("roomId",id);
        List<Room> list = query.list();

        ArrayList<Room> roomDetail = new ArrayList<>();

        for (Room r:list) {
            roomDetail.add(new Room(
                    r.getRoomId(),
                    r.getRoomType(),
                    r.getKeyMoney(),
                    r.getQty()
            ));
        }

        transaction.commit();
        session.close();
        return roomDetail;
    }

    @Override
    public Room searchRoom(String id, Session session) throws IOException {
        Room room = session.get(Room.class, id);
        return room;
    }

    @Override
    public boolean updateRoomQty(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Room SET qty=qty-1 WHERE roomId=:roomId";
        Query query = session.createQuery(hql);
        query.setParameter("roomId",id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }


}
