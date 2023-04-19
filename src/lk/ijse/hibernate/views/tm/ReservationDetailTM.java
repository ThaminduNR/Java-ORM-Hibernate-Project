package lk.ijse.hibernate.views.tm;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDetailTM {

    private String resId;
    private String sId;
    private String sName;
    private String roomId;
    private String roomType;
    private double keyMoney;
    private double payAmount;
    private String status;
}
