package br.com.dontpad.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import br.com.dontpad.R;
import br.com.dontpad.controllers.ConfigurationController;

public class ConfigurationActivity extends AppCompatActivity {
    private Switch nightMode;
    private Spinner spinnerTextColor;
    private Spinner spinnerBackgroundColor;

    private TextView textColor;
    private TextView backgroundColor;

    private ConstraintLayout layoutConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        initializeVars();
        setConfiguration();
        verifyNightMode();
        makeSpinners();

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
        makeSpinners();
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
        spinnerTextColor = findViewById(R.id.spinner_text_color);
        spinnerBackgroundColor = findViewById(R.id.spinner_background_color);
        textColor = findViewById(R.id.text_color_view);
        backgroundColor = findViewById(R.id.background_color_view);
    }

    public void enableNightMode(){
        ConfigurationController.enableNightMode();
        changeConfiguration();

        ConfigurationController.nightMode = true;
    }

    public void disableNightMode(){
       ConfigurationController.disableNightMode();
       changeConfiguration();
       ConfigurationController.nightMode = false;
    }

    public void setConfiguration(){
        if(ConfigurationController.isChanged){
            nightMode.setTextColor(getResources().getColor(ConfigurationController.colorText));
            nightMode.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            layoutConfiguration.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            spinnerBackgroundColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorText));
            spinnerTextColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorText));

            textColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
            backgroundColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
            textColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
            backgroundColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));

            ConfigurationController.isChanged = false;
        }
    }

    public void changeConfiguration(){
        nightMode.setTextColor(getResources().getColor(ConfigurationController.colorText));
        nightMode.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        layoutConfiguration.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        spinnerBackgroundColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorText));
        spinnerTextColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorText));

        textColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
        backgroundColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
        textColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        backgroundColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));

        ConfigurationController.isChanged = true;
    }

    public void verifyNightMode(){
        if(ConfigurationController.nightMode)
            nightMode.setChecked(true);
        else
            nightMode.setChecked(false);
    }

    public void makeSpinners(){
        spinnerTextColor = (Spinner) findViewById(R.id.spinner_text_color);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTextColor.setAdapter(adapter);

        spinnerBackgroundColor = (Spinner) findViewById(R.id.spinner_background_color);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBackgroundColor.setAdapter(adapter2);

    }
    // notepad.setTextColor(getResources().getColor(R.color.colorAccent));
}
