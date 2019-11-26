package br.com.dontpad.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.dontpad.R;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;

public class MainActivity extends AppCompatActivity {
    private EditText notepad;
    private User user;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersReference = databaseReference.child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = UserController.getUser();
        notepad = findViewById(R.id.note_edit_text);

        notepad.setText(user.getPad().getPad());
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
