package br.com.dontpad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import br.com.dontpad.R;

public class MainActivity extends AppCompatActivity {
    private EditText notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notepad = findViewById(R.id.note_edit_text);
    }
}
