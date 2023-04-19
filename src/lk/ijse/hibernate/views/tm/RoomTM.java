package lk.ijse.hibernate.views.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomTM {

    private String roomId;
    private String  roomType;
    private double keyMoney;
    private int qty;
}
