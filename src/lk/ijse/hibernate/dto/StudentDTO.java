package lk.ijse.hibernate.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String sId;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;
}
