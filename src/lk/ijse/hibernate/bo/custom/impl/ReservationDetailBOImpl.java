package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationDetailBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.ReservationDetailDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.impl.ReservationDetailDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dto.ReservationDetailDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.ReservationDetail;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailBOImpl implements ReservationDetailBO {

    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.RESERVATIONDETAIL);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.ROOM);


    @Override
    public boolean addTOReservationDetailData(ReservationDetailDTO rdd) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ReservationDetail reservationDetail = new ReservationDetail(rdd.getResId(), rdd.getSId(), rdd.getSName(), rdd.getRoomId(), rdd.getRoomType(), rdd.getKeyMoney(),
                rdd.getPayAmount(), rdd.getStatus());

        try{
            reservationDetailDAO.add(reservationDetail,session);
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
    public boolean updateReservationDetailData(ReservationDetailDTO rdd) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ReservationDetail reservationDetail = new ReservationDetail(
                rdd.getResId(),
                rdd.getSId(),
                rdd.getSName(),
                rdd.getRoomId(),
                rdd.getRoomType(),
                rdd.getKeyMoney(),
                rdd.getPayAmount(),
                rdd.getStatus()
        );
        try{
            reservationDetailDAO.update(reservationDetail,session);
            Room room = roomDAO.searchRoom(rdd.getRoomId(), session);
            room.setQty(room.getQty()+1);
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
    public ArrayList<ReservationDetailDTO> getAllData() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<ReservationDetailDTO> list = new ArrayList<>();

        try{
            ArrayList<ReservationDetail> list1 = reservationDetailDAO.getAll(session);
            for (ReservationDetail rd:list1) {
                list.add(new ReservationDetailDTO(
                        rd.getResId(),
                        rd.getSId(),
                        rd.getSName(),
                        rd.getRoomId(),
                        rd.getRoomType(),
                        rd.getKeyMoney(),
                        rd.getPayAmount(),
                        rd.getStatus()
                ));
            }
            transaction.commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return list;
        }finally {
            session.close();
        }

    }
}
