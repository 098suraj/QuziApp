package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.Duration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText password, email, logemail, logpass;
    Button register, log;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View view;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        setSupportActionBar(toolbar);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        logemail = findViewById(R.id.emaillogin);
        logpass = findViewById(R.id.passwordlogin);
        log = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email = email.getText().toString();
                String text_password = password.getText().toString();
                LoginUser(text_email, text_password);
            }
        });

        navigationdrawer();
    }
    private void register() {
        String logem = logemail.getText().toString();
        String logp = logpass.getText().toString();
        auth.createUserWithEmailAndPassword(logem, logp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, loginSuccess.class);
                    startActivity(intent);
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                } else
                    Toast.makeText(getApplicationContext(), "na hua", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void LoginUser(String text_email, String text_password) {
        auth.signInWithEmailAndPassword(text_email, text_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, loginSuccess.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "hogya", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "na Hopayega", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void navigationdrawer() {
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigatin_open, R.string.navigatin_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        View v=findViewById(android.R.id.content);
        switch (item.getItemId()) {
            case R.id.home:
                break;
            case R.id.nav_plane:
               // Toast.makeText(this, "plane", Toast.LENGTH_SHORT).show();
               Snackbar.make(v, "plane", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.nav_cycle:
                Toast.makeText(this, "cycle", Toast.LENGTH_SHORT).show();
              //  Snackbar.make(view, "cycle", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.nav_bus:
                Toast.makeText(this, "bus", Toast.LENGTH_SHORT).show();
              //  Snackbar.make(view, "bus", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.loginsucc:
              Intent intent=new Intent(this,loginSuccess.class);
              startActivity(intent);
              break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}