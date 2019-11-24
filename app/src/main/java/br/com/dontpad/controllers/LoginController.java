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

    public String autorizeLogin(String url){
        final String[] urlLogin = new String[1];

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if(user.getName().equals(url))
                        urlLogin[0] = user.getName();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return urlLogin[0];
    }

    public void createNewUrl(String url){
        usersReference.setValue(url);
    }

}
