package com.jeant.game;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AndroidInterfaceClass implements FireBaseInterface{

    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidInterfaceClass() {
        this.database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference("movimientos");
    }

    @Override
    public void SomeFunction() {
        System.out.println("Just some function");
    }

    @Override
    public void FirstFireBaseTest() {
        if(myRef!=null){
            myRef.setValue("Hello, World");
        }
        else {
            System.out.println("DataBaseReference was not set");
        }
    }

    @Override
    public void SetOnValueChangedListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Log.d("Result","Value is" + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Result: ", "Failed to value " + error.toException());
            }
        });
    }

    @Override
    public void SetValueInDB(String target, HashMap value) {
        myRef = database.getReference(target);
        myRef.setValue(value);
    }
}
