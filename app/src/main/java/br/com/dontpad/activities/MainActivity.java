package br.com.dontpad.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.dontpad.R;
import br.com.dontpad.controllers.ConfigurationController;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText notepad;
    private User user;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersReference = databaseReference.child("users");

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        initializeVars();
        setConfiguration();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav);

        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeVars();
        setConfiguration();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        String note = notepad.getText().toString();

        usersReference.child(user.getName()).child("pad").child("pad").setValue(note);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void changeActivity(){
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }

    public void setConfiguration(){
            notepad.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            notepad.setTextColor(getResources().getColor(ConfigurationController.colorText));
    }

    public void initializeVars(){
        user = UserController.getUser();
        notepad = findViewById(R.id.note_edit_text);

        notepad.setText(user.getPad().getPad());

        userName = findViewById(R.id.user_name_text_view);
        // userName.setText("Url: " + user.getName());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (id == R.id.configuration_item) {
            changeActivity();
            return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
