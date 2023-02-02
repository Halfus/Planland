package com.example.planland.views.activities;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planland.entity.User;
import com.example.planland.viewModels.UserViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import com.example.planland.R;

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth firebaseAuth;
    private UserViewModel userViewModel;

    private EditText email;
    private EditText password;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result ->
            {
                if (result.getResultCode() == RESULT_OK)
                    launchMainActivity();
                else
                    Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            });

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
        {
            launchMainActivity();
        }
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.textFieldEmail);
        password = findViewById(R.id.textFieldPassword);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void launchMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void Login(View v)
    {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher_round)
                .setIsSmartLockEnabled(false)
                .build();

        activityResultLauncher.launch(signInIntent);
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
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    Log.d(TAG, "Registered new user. " + task.isSuccessful());
                    if (!task.isSuccessful())
                    {
                        ShowToast("Authentication failed. " + task.getException());
                    }
                    else
                    {
                        userViewModel.AddUser(new User(firebaseAuth.getCurrentUser().getUid() ,firebaseAuth.getCurrentUser().getEmail()));
                        launchMainActivity();
                    }
                }
            });
    }

    public void ShowToast(String toastText)
    {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}