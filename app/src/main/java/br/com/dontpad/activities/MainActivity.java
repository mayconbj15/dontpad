package br.com.dontpad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import br.com.dontpad.R;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;

public class MainActivity extends AppCompatActivity {
    private EditText notepad;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notepad = findViewById(R.id.note_edit_text);

        user = UserController.getUser();
    }
}
