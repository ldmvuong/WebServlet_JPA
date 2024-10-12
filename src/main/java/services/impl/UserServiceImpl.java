package services.impl;

import dao.IRoleDao;
import dao.IUserDao;
import dao.impl.RoleDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Role;
import entity.User;
import services.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void register(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);

        IRoleDao roleDao = new RoleDaoImpl();
        user.setRole(roleDao.findById(3));
        user.setCreateDate(date);

        userDao.insert(user);
        return true;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean updatePassword(String username, String password) {
        return userDao.updatePassword(username, password);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public boolean checkUserByUsernameAndEmail(String username, String email) {
        return userDao.checkUserByUsernameAndEmail(username, email);
    }
}
