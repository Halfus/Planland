package com.example.planland.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.planland.entity.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UserViewModel extends AndroidViewModel
{
    /*private final UserService userService;*/
    private MutableLiveData<User> loggedUser;

    public UserViewModel(@NonNull Application application)
    {
        super(application);
        /*userService = UserFirebaseRepository.getInstance();*/
        loggedUser = new MutableLiveData<>();
    }

    public LiveData<User> getLoggedUser()
    {
        if (loggedUser.getValue() == null)
        {
            if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            {
                /*userService.getUserById(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid(), user -> {
                    loggedUser.setValue(user);
                });*/
            }
        }
        return loggedUser;
    }

    public void AddUser(User user)
    {
        /*userService.addUser(user);*/
    }

    public void UpdateUser()
    {
        /*userService.updateUser(loggedUser.getValue());*/
    }

    public void DeleteUser(User user)
    {
        loggedUser = null;
        /*userService.deleteUser(user.getUserId());*/
    }

    public void LogOut()
    {
        loggedUser=null;
    }
}
