package br.com.dontpad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dontpad.R;
import br.com.dontpad.controllers.LoginController;
import br.com.dontpad.controllers.UserController;
import br.com.dontpad.models.User;


public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText urlEditText;
    private String url;

    // private User user;

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
                    verifyLogin(url);
                    changeActivity();
                }
            }
        });
    }

    public void initializeVars(){
        this.loginButton = findViewById(R.id.button_login);
        this.urlEditText = findViewById(R.id.url_edit_text);
        // this.user = new User();
    }

    public void verifyLogin(String url){
        //method to verify if the url exist in database
        LoginController loginController = new LoginController();

        User newUser = loginController.autorizeLogin(url); //bug aqui

        if(!userExist(newUser)){
            Toast.makeText(getApplicationContext(), "aqui", Toast.LENGTH_SHORT).show();
            newUser = loginController.createNewUser(url);
        }

        UserController.setUser(newUser);
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
}
