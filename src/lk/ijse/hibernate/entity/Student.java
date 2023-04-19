package lk.ijse.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id

    private String sId;

    private String name;
    private String address;
    private String contact;

    private String dob;
    private String gender;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Student(String sId, String name, String address, String contact, String dob, String gender) {
        this.sId=sId;
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.dob=dob;
        this.gender=gender;

    }
    public Student(String sId){
        this.sId=sId;
    }
}
