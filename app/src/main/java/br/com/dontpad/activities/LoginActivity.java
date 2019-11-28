package br.com.dontpad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import br.com.dontpad.R;
import br.com.dontpad.controllers.ConfigurationController;
import br.com.dontpad.controllers.LoginController;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;


public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText urlEditText;
    private String url;

    private LinearLayout linearLayoutLogin;
    private TextView welcomeTextView;

    private LoginController loginController;

    // private User user;
    public  static boolean changeActivity = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVars();
        configureConfigurationController();
        setConfiguration();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlEditText.getText().toString();

                if(urlIsValid(url)){
                    verifyLogin(url);
                }

                if(changeActivity)
                    changeActivity();

            }
        });
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
        this.loginButton = findViewById(R.id.button_login);
        this.urlEditText = findViewById(R.id.url_edit_text);
        this.linearLayoutLogin = findViewById(R.id.linear_layout_login);
        this.welcomeTextView = findViewById(R.id.welcome_text_view);
        this.loginController = new LoginController();
    }

    public void verifyLogin(String url){
        //method to verify if the url exist in database
        loginController.autorizeLogin(url); //bug aqui
    }

    public boolean urlIsValid(String url){
        return url != null && !url.equals("");
    }

    public boolean userExist(User user){
        return user.getPad() != null && !user.getName().equals("");
    }

    public void changeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("userName", user.getName());
        startActivity(intent);
    }

    public void setConfiguration(){
        linearLayoutLogin.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        welcomeTextView.setBackgroundColor(getResources().getColor(ConfigurationController.colorBackground));
        welcomeTextView.setTextColor(getResources().getColor(ConfigurationController.colorText));
        urlEditText.setTextColor(getResources().getColor(ConfigurationController.colorText));
    }

    public void configureConfigurationController(){
        ConfigurationController.setConfiguration(R.color.black, R.color.white);
    }
}
