package lk.ijse.hibernate.views.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartTM {
    private String resNo;
    private String sId;
    private String rId;
    private String roomType;
    private double keyMoney;
    private double payAmount;
    private String date;
    private String status;
    private Button btn;

}
