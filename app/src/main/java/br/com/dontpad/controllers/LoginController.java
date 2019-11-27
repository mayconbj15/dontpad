package br.com.dontpad.controllers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.dontpad.activities.LoginActivity;
import br.com.dontpad.activities.MainActivity;
import br.com.dontpad.models.User;

public class LoginController {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersReference = databaseReference.child("users");


    public void autorizeLogin(String url){

        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userLogin = new User();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if(user != null && user.getName().equals(url)){
                        userLogin.setName(user.getName());
                        userLogin.setPad(user.getPad());

                        if(!userExist(userLogin)){
                            System.out.println("NÃO EXISTE");
                            createNewUser(url);
                        }

                        //changeActivity();
                        UserController.setUser(userLogin);
                        LoginActivity.changeActivity = true;

                        //changeActivity();
                        break;
                    }


                }

                if(!userExist(userLogin)){
                    createNewUser(url);
                    UserController.setUser(userLogin);
                    LoginActivity.changeActivity = true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean userExist(User user){
        return user.getPad() != null && !user.getName().equals("");
    }

    public User createNewUser(String url){
        User newUser = new User(url);
        newUser.setName(url);
        
        // provável erro de sobescrever o usuário existente
        usersReference.child(url).setValue(newUser);

        return newUser;
    }

    public void changeActivity(){
        /*Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("userName", user.getName());
        startActivity(intent);*/

        LoginActivity loginActivity = new LoginActivity();
        loginActivity.changeActivity();
    }
}
