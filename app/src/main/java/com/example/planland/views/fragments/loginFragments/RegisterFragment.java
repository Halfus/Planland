package com.example.planland.views.fragments.loginFragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planland.R;
import com.example.planland.databinding.FragmentRegisterBinding;
import com.example.planland.databinding.FragmentSettingsBinding;
import com.example.planland.entity.User;
import com.example.planland.viewModels.UserViewModel;
import com.example.planland.views.activities.LoginActivity;
import com.example.planland.views.activities.MainActivity;
import com.example.planland.views.fragments.mainFragments.SettingsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment
{
    private FirebaseAuth firebaseAuth;
    private UserViewModel userViewModel;

    private EditText email;
    private EditText password;

    private Button buttonLogin;
    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Register(View view)
    {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail))
        {
            ShowToast("Email field is empty!");
            return;
        }
        if(TextUtils.isEmpty(userPassword))
        {
            ShowToast("Password field is empty!");
            return;
        }
        if(userPassword.length() < 8)
        {
            ShowToast("Password must be minimum 8 characters!");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        Log.d(TAG, "Registered new user. " + task.isSuccessful());
                        if (!task.isSuccessful())
                        {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            ShowToast("Authentication failed. " + task.getException());
                            Log.d(TAG, "Authentication failed. " + task.getException());
                        }
                        else
                        {
                            userViewModel.AddUser(new User(firebaseAuth.getCurrentUser().getUid() ,firebaseAuth.getCurrentUser().getEmail(),"secret"));
                            launchMainActivity();
                        }
                    }
                });
    }

    public void ShowToast(String toastText)
    {
        Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
    }

    private void launchMainActivity()
    {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}