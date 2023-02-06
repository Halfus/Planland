package com.example.planland.repositories;

import androidx.annotation.NonNull;

import com.example.planland.contracts.UserService;
import com.example.planland.entity.User;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.locks.ReentrantLock;

public class UserRepository implements UserService {

    private static volatile UserRepository instance;
    private final static ReentrantLock lock=new ReentrantLock();

    private final DatabaseReference usersReference;

    private UserRepository(){
        usersReference = FirebaseDatabase.getInstance("https://reand-e2c65-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users"); //TODO Different change according to your firebase
    }

    //Singleton
    public static UserRepository getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    //Maybe remove?
    @Override
    public User LoginCredentials(String username, String password) {
        return null;
    }
    //TODO: Test
    @Override
    public void GetUserById(String ID, UserCallBack callback) {

        usersReference.child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                System.out.print("User: "+u.toString() );
                callback.callback(u);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //TODO: Implement
    @Override
    public User GetUserByUsername(String username) {
        return null;
    }

    @Override
    public void AddUser(User user) {

        usersReference.child(user.getId()).setValue(user);
    }
    @Override
    public void UpdateUser(User user) {
        usersReference.child(user.getId()).setValue(user);
    }
    @Override
    public void DeleteUserById(String ID) {
        usersReference.child(ID).setValue(null);
    }

    @Override
    public void DeleteUserByReference(User user) {
        usersReference.child(user.getId()).setValue(null);
    }

    public interface UserCallBack {
        void callback(User user);
    }
}
