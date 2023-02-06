package com.example.planland.views.fragments.mainFragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planland.R;
import com.example.planland.entity.User;
import com.example.planland.viewModels.TodoViewModel;
import com.example.planland.viewModels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewToDoFragment extends Fragment
{
    private EditText textFieldTitle;
    private EditText textFieldDescription;
    String[] temp;

    private TodoViewModel todoViewModel;
    private UserViewModel userViewModel;

    private Button buttonCreate;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        temp=new String[50];
        todoViewModel = new ViewModelProvider(requireActivity()).get(TodoViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        final User[] currentUser = new User[1];
        userViewModel.getLoggedUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                currentUser[0] =user;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_new_to_do, container, false);

        //Initialize objects ...
        textFieldTitle = inflatedView.findViewById(R.id.textFieldTitle);
        textFieldDescription = inflatedView.findViewById(R.id.textFieldDescription);
        buttonCreate = inflatedView.findViewById(R.id.buttonCreate);

        /*buttonCreate.setOnClickListener(view -> {
                Todo: create new Todo
            }
        });*/

        return inflatedView;
    }
}