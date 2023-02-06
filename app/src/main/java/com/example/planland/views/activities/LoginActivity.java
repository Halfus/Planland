package com.example.planland.views.activities;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planland.databinding.ActivityLoginBinding;
import com.example.planland.databinding.FragmentSettingsBinding;
import com.example.planland.entity.User;
import com.example.planland.viewModels.UserViewModel;
import com.example.planland.views.fragments.loginFragments.RegisterFragment;
import com.example.planland.views.fragments.mainFragments.HomeFragment;
import com.example.planland.views.fragments.mainFragments.SettingsFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import com.example.planland.R;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private FirebaseAuth firebaseAuth;
    private UserViewModel userViewModel;

    private EditText email;
    private EditText password;

    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonFacebook;
    private Button buttonGoogle;

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

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);

        buttonFacebook = (Button) findViewById(R.id.buttonFacebook);
        buttonFacebook.setOnClickListener(this);

        buttonGoogle = (Button) findViewById(R.id.buttonGoogle);
        buttonGoogle.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    }

    private void launchMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void Login(View v)
    {

        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            launchMainActivity();
                        } else {
                            ShowToast("Authentication failed.");
                        }
                    }
                });
    }

    public void Register(View view)
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

    public void ShowToast(String toastText)
    {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.buttonLogin)
        {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            //Deal wit missing user input
            if(userEmail!=null && !userEmail.trim().isEmpty())
                if(userPassword!=null&& !userPassword.trim().isEmpty())
                    Login(view);
                else
                    ShowToast("Please enter a password");
            else
                ShowToast("Pleas enter an email");
        }
        else if(view.getId() == R.id.buttonRegister)
        {
            Register(view);
        }
        else if(view.getId() == R.id.buttonFacebook)
        {
            Snackbar.make(view,"Facebook authentication has been disabled by developer",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }
        else if(view.getId() == R.id.buttonGoogle)
        {
            Snackbar.make(view,"Google authentication has been disabled by developer",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }
    }
}