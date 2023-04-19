package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;

import java.io.IOException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

     String getLastReservationId() throws IOException;



}
