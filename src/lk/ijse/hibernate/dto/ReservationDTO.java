package lk.ijse.hibernate.dto;

import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private String resId;
    private String type;
    private double keyMoney;
    private double payAmount;
    private Student student;
    private String date;
    private Room room;
    private String status;

    public ReservationDTO(String resId, String date, Student student, Room room, String status){
        this.resId=resId;
        this.date=date;
        this.student=student;
        this.room=room;
        this.status=status;
    }

}
