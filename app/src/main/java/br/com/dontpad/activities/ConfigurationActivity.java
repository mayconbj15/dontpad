package br.com.dontpad.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import br.com.dontpad.R;

public class ConfigurationActivity extends AppCompatActivity {
    private Switch nightMode;

    private EditText editTextPad;
    private EditText editTextLogin;

    private TextView welcomeTextView;

    private LinearLayout linearLayoutLogin;
    private ConstraintLayout layoutConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        getContext();

        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    // ativa modo noturno
                    enableNightMode();
                }
                else{
                    disableNightMode();
                }

            }
        });

    }


    public void getContext(){
        nightMode = findViewById(R.id.modo_noturno);
        editTextPad = findViewById(R.id.note_edit_text);
        linearLayoutLogin = findViewById(R.id.linear_layout_login);
        welcomeTextView = findViewById(R.id.welcome_text_view);
        layoutConfiguration = findViewById(R.id.frame_layout_configuration);
        editTextLogin = findViewById(R.id.url_edit_text);
    }

    public void enableNightMode(){
        nightMode.setTextColor(getResources().getColor(R.color.white));
        nightMode.setBackgroundColor(getResources().getColor(R.color.black));
        layoutConfiguration.setBackgroundColor(getResources().getColor(R.color.black));

        /*linearLayoutLogin.setBackgroundColor(getResources().getColor(R.color.black));
        welcomeTextView.setBackgroundColor(getResources().getColor(R.color.black));
        welcomeTextView.setTextColor(getResources().getColor(R.color.white));
        editTextLogin.setTextColor(getResources().getColor(R.color.white));

        editTextPad.setBackgroundColor(getResources().getColor(R.color.black));
        editTextPad.setTextColor(getResources().getColor(R.color.white));*/


    }

    public void disableNightMode(){
        nightMode.setTextColor(getResources().getColor(R.color.black));
        nightMode.setBackgroundColor(getResources().getColor(R.color.white));
        layoutConfiguration.setBackgroundColor(getResources().getColor(R.color.white));
    }
    // notepad.setTextColor(getResources().getColor(R.color.colorAccent));
}
