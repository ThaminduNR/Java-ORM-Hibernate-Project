package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.entity.User;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void add(User user, Session session) {
        session.save(user);

    }

    @Override
    public void update(User user, Session session) {
        session.update(user);
    }

    @Override
    public void delete(String s, Session session) {
        User user = session.get(User.class, s);
        session.delete(user);

    }

    @Override
    public ArrayList<User> getAll(Session session) throws IOException {
        List usList = session.createQuery("FROM User").list();
        return (ArrayList<User>) usList;

    }
}
