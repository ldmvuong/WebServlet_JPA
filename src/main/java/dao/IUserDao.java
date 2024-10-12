package dao;

import entity.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
    void insert(User user);
    boolean updatePassword(String username, String password);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean checkUserByUsernameAndEmail(String username, String email);

}
