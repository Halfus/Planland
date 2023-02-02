package com.example.planland.contracts;

import com.example.planland.entity.User;
import com.example.planland.repositories.UserRepository;

public interface UserService {
    User LoginCredentials(String username, String password);
    void GetUserById(String ID, UserRepository.UserCallBack callBack); //TBC
    User GetUserByUsername(String username);
    void AddUser(User user);
    void DeleteUserById(String ID);
    void DeleteUserByReference(User user);
    void UpdateUser(User user);
}
