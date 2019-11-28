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
import br.com.dontpad.controllers.ConfigurationController;

public class ConfigurationActivity extends AppCompatActivity {
    private Switch nightMode;

    private ConstraintLayout layoutConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        initializeVars();

        setConfiguration();

        verifyNightMode();

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

    @Override
    protected void onStart() {
        super.onStart();

        /*setConfiguration();
        verifyNightMode();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeVars();
        setConfiguration();
        verifyNightMode();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void initializeVars(){
        nightMode = findViewById(R.id.modo_noturno);
        layoutConfiguration = findViewById(R.id.frame_layout_configuration);
    }

    public void enableNightMode(){
        ConfigurationController.enableNightMode();
        changeConfiguration();
        ConfigurationController.isChanged = true;
        ConfigurationController.nightMode = true;
    }

    public void disableNightMode(){
       ConfigurationController.disableNightMode();
       changeConfiguration();
       ConfigurationController.isChanged = true;
        ConfigurationController.nightMode = false;
    }

    public void setConfiguration(){
        if(ConfigurationController.isChanged){
            nightMode.setTextColor(getResources().getColor(ConfigurationController.colorText));
            nightMode.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            layoutConfiguration.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            ConfigurationController.isChanged = false;
        }
    }

    public void changeConfiguration(){
        nightMode.setTextColor(getResources().getColor(ConfigurationController.colorText));
        nightMode.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        layoutConfiguration.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
    }

    public void verifyNightMode(){
        if(ConfigurationController.nightMode)
            nightMode.setChecked(true);
        else
            nightMode.setChecked(false);
    }
    // notepad.setTextColor(getResources().getColor(R.color.colorAccent));
}
