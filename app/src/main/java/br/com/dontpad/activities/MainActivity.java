package br.com.dontpad.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.dontpad.R;
import br.com.dontpad.controllers.ConfigurationController;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;

public class MainActivity extends AppCompatActivity {
    private EditText notepad;
    private User user;
    private Button buttonConfiguration;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersReference = databaseReference.child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVars();
        setConfiguration();
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
        if(ConfigurationController.isChanged){
            notepad.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            notepad.setTextColor(getResources().getColor(ConfigurationController.colorText));
        }

    }

    public void initializeVars(){
        user = UserController.getUser();
        notepad = findViewById(R.id.note_edit_text);

        notepad.setText(user.getPad().getPad());

        buttonConfiguration = findViewById(R.id.button_configuration);
        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }
}
