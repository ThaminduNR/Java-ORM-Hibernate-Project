package lk.ijse.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomDTO {

    private String sId;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;

    private String roomId;
    private String  roomType;
    private double keyMoney;
    private int qty;


}
