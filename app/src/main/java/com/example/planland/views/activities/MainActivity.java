package com.example.planland.views.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.planland.R;
import com.example.planland.views.fragments.mainFragments.CalendarFragment;
import com.example.planland.views.fragments.mainFragments.HomeFragment;
import com.example.planland.views.fragments.mainFragments.RecapFragment;
import com.example.planland.views.fragments.mainFragments.SettingsFragment;
import com.example.planland.views.fragments.mainFragments.ToDoListFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener fireAuthListener;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        /*navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }*/





        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item)
            {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id)
                {
                    case R.id.nav_home:
                        replaceFragment(new HomeFragment()); break;
                    case R.id.nav_to_do_list:
                        replaceFragment(new ToDoListFragment()); break;
                    case R.id.nav_calendar:
                        replaceFragment(new CalendarFragment()); break;
                    case R.id.nav_recap:
                        replaceFragment(new RecapFragment()); break;
                    case R.id.nav_settings:
                        replaceFragment(new SettingsFragment()); break;
                    default:
                        return true;
                }
                return true;
            }
        });*/


        firebaseAuth =FirebaseAuth.getInstance();
        fireAuthListener= firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()==null){
                // TODO: Uncomment before pushing changes!
                //startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };

        navSetup();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        // TODO: Uncomment before pushing changes!
        /*FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            String name = currentUser.getDisplayName();
            String uid = currentUser.getUid();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();
            boolean emailVerified = currentUser.isEmailVerified();

            if(name!=null)
                ShowToast("Hello there "+name);
            else
                ShowToast("Hello robot "+uid);
        }else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }*/
    }

    public void ShowToast(String toastText)
    {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_home);
                //Toast.makeText(MainActivity.this, "Home Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_to_do_list:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_to_do_list);
                //Toast.makeText(MainActivity.this, "Todo List Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_calendar:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_calendar);
                //Toast.makeText(MainActivity.this, "Calendar Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_recap:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_recap);
                //Toast.makeText(MainActivity.this, "Recap Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_settings);
                //Toast.makeText(MainActivity.this, "Settings Test", Toast.LENGTH_SHORT).show();
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }*/

    private void navSetup() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_to_do_list,
                R.id.nav_calendar, R.id.nav_recap, R.id.nav_settings).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}