package lk.ijse.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    private String resId;
    private  String Date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "sId")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "roomId")
    private Room room;

    private String status;


}
