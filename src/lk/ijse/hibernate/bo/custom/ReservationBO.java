package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ReservationDTO;

import java.io.IOException;


public interface ReservationBO extends SuperBO{


    boolean addReservation(ReservationDTO reservationDTO) throws IOException;

    String getLastReservationId() throws IOException;

}
