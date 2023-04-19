package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {


    /*@Override
    public boolean add(Reservation reservation) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Reservation> getAll() throws IOException {
        return null;
    }

    @Override
    public boolean update(Reservation reservation) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }
*/

    @Override
    public String getLastReservationId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = "RID-001";

        String sql="FROM Reservation ORDER BY resId DESC";
        Query query = session.createQuery(sql);
        query.setMaxResults(1);
        Reservation lastRes = (Reservation) query.uniqueResult();
        id = lastRes!= null?lastRes.getResId() : id;

        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public ArrayList<Reservation> getAll(Session session) throws IOException {
        return null;
    }

    @Override
    public void add(Reservation reservation, Session session) {
        session.save(reservation);
    }

    @Override
    public void update(Reservation reservation, Session session) {

    }

    @Override
    public void delete(String s, Session session) {

    }
}
