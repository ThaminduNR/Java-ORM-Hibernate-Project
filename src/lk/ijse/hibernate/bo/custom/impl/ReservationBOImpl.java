package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationBO;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;



public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = new ReservationDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();
    RoomBO roomBO = new RoomBOImpl();

    @Override
    public boolean addReservation(ReservationDTO reservationDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = new Reservation(
                reservationDTO.getResId(),
                reservationDTO.getDate(),
                reservationDTO.getStudent(),
                reservationDTO.getRoom(),
                reservationDTO.getStatus()
        );
        try{
            Room room = reservationDTO.getRoom();
            String roomId = room.getRoomId();
            Room room1 = roomDAO.searchRoom(roomId, session);
            room1.setQty(room1.getQty()-1);
            roomDAO.update(room1,session);
            reservationDAO.add(reservation,session);

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
    public String getLastReservationId() throws IOException {
        return reservationDAO.getLastReservationId();
    }


}
