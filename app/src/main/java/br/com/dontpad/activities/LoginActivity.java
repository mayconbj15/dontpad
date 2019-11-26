package br.com.dontpad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dontpad.R;
import br.com.dontpad.controllers.LoginController;
import br.com.dontpad.models.User;


public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText urlEditText;
    private String url;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVars();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlEditText.getText().toString();
                if(urlIsValid(url)){
                    url = verifyLogin(url);
                    changeActivity();
                }
            }
        });
    }

    public void initializeVars(){
        this.loginButton = findViewById(R.id.button_login);
        this.urlEditText = findViewById(R.id.url_edit_text);
        this.user = new User();
    }

    public String verifyLogin(String url){
        //method to verify if the url exist in database
        LoginController loginController = new LoginController();

        String loginUrl = loginController.autorizeLogin(url);

        if(!urlIsValid(loginUrl)){
            loginController.createNewUrl(url);
            loginUrl = url;
        }

        return loginUrl;
    }

    public boolean urlIsValid(String url){
        return url != null && !url.equals("");
    }

    public void changeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userName", user.getName());
        startActivity(intent);
    }
}
