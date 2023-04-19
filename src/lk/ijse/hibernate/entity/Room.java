package lk.ijse.hibernate.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Room {
    @Id
    private String roomId;
    private String  roomType;
    private double keyMoney;
    private int qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL )
    private List<Reservation> roomDetail;

    public Room(String roomId, String roomType, double keyMoney, int qty) {
        this.roomId=roomId;
        this.roomType=roomType;
        this.keyMoney=keyMoney;
        this.qty=qty;
    }
    public Room(String roomId){
        this.roomId=roomId;
    }
}
