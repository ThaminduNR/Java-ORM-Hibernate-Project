package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface UserBo extends SuperBO {

    boolean addUser(UserDTO userDTO) throws IOException;

    boolean updateUser(UserDTO userDTO) throws IOException;

    boolean deleteUse(String id) throws IOException;

    ArrayList<UserDTO> getAllUser() throws IOException;
}
