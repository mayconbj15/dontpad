package br.com.dontpad.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
    private Button saveButton;

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
        textColor = findViewById(R.id.background_text_view);
        backgroundColor = findViewById(R.id.background_text_view);
        saveButton = findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveConfiguration();

            }
        });
    }

    public void enableNightMode(){
        ConfigurationController.enableNightMode();
        setConfiguration();

        ConfigurationController.nightMode = true;
    }

    public void disableNightMode(){
       ConfigurationController.disableNightMode();
       setConfiguration();
       ConfigurationController.nightMode = false;
    }

    public void setConfiguration(){
        nightMode.setTextColor(getResources().getColor(ConfigurationController.colorText));
        nightMode.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        layoutConfiguration.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        spinnerBackgroundColor.setBackgroundColor(getResources().getColor(R.color.gray));
        spinnerTextColor.setBackgroundColor(getResources().getColor(R.color.gray));

        textColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
        backgroundColor.setTextColor(getResources().getColor(ConfigurationController.colorText));
        textColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        backgroundColor.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));

        ConfigurationController.isChanged = false;

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

    public void saveConfiguration(){
        Object textColor = spinnerTextColor.getSelectedItem();
        Object backgroundColor = spinnerBackgroundColor.getSelectedItem();

        ConfigurationController.changeTextColor(textColor);
        ConfigurationController.changeBackgroundColor(backgroundColor);

        setConfiguration();
    }

    // notepad.setTextColor(getResources().getColor(R.color.colorAccent));
}
