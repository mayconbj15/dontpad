package br.com.dontpad.controllers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.dontpad.models.User;

public class LoginController {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference().child("users");

    public User autorizeLogin(String url){
        User newUser = new User();

        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if(user != null && user.getName().equals(url)){
                        newUser.setName(user.getName());
                        // provavel erro de sobescrever o usuário existente, verificar o valor de user.getPad()
                        newUser.setPad(user.getPad());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return newUser;
    }

    public User createNewUser(String url){
        User newUser = new User(url);
        newUser.setName(url);
        
        // provável erro de sobescrever o usuário existente
        usersReference.child(url).setValue(newUser);

        return newUser;
    }

}
