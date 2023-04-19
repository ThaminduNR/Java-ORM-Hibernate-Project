package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.ReservationDetailDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.ReservationDetail;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {

    @Override
    public void add(ReservationDetail reservationDetail, Session session) {
        session.save(reservationDetail);
    }

    @Override
    public void update(ReservationDetail reservationDetail, Session session) {
        session.update(reservationDetail);

    }

    @Override
    public void delete(String s, Session session) {

    }

    @Override
    public ArrayList<ReservationDetail> getAll(Session session) throws IOException {
        String hql = "FROM ReservationDetail";
        List list = session.createQuery(hql).list();
        return (ArrayList<ReservationDetail>) list;
    }
}
