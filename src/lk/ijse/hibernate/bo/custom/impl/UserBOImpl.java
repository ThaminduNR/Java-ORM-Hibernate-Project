package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.UserBo;
import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.dao.custom.impl.UserDAOImpl;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class UserBOImpl implements UserBo {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(UserDTO userDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(userDTO.getUserId(), userDTO.getUName(), userDTO.getPws());

        try {
            userDAO.add(user,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(userDTO.getUserId(), userDTO.getUName(), userDTO.getPws());

        try {
            userDAO.update(user,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteUse(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.delete(id,session);
            transaction.commit();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;

        }
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<UserDTO> allUsers = new ArrayList<>();

        try {
            ArrayList<User> all = userDAO.getAll(session);
            for (User u:all) {
                allUsers.add( new UserDTO(
                        u.getUserId(),
                        u.getUName(),
                        u.getPws()
                ));
            }
            transaction.commit();
            return allUsers;

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return allUsers;
        }finally {
            session.close();
        }

    }
}
