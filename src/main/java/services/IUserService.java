package services;

import entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User login(String username, String password);
    void register(User user);
    boolean register(String email, String password, String username, String
            fullname, String phone);
    User findById(int id);
    User findByUsername(String username);
    boolean updatePassword(String username, String password);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean checkUserByUsernameAndEmail(String username, String email);
}
