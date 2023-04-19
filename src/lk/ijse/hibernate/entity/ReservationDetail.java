package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDetail {
    @Id
    private String resId;
    private String sId;
    private String sName;
    private String roomId;
    private String roomType;
    private double keyMoney;
    private double payAmount;
    private String status;

}
