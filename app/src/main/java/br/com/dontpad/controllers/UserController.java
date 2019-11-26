package br.com.dontpad.controllers;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.dontpad.models.User;

public class UserController {

    DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference().child("users");

    public UserController(){

    }

    public User findUserByName(String name){
        User newUser = new User();

        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if(user != null && user.getName().equals(name)){
                        newUser.setName(user.getName());
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
}
