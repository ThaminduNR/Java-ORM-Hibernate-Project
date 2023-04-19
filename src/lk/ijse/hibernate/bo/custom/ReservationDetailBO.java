package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ReservationDetailDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDetailBO extends SuperBO {

   boolean addTOReservationDetailData(ReservationDetailDTO rdd) throws IOException;

   boolean updateReservationDetailData(ReservationDetailDTO rdd) throws IOException;

   ArrayList<ReservationDetailDTO> getAllData() throws IOException;

}
