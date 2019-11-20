package com.example.poe3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.View;

import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {

    private static final String TAG = "profile";

    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";

    private EditText name;
    private EditText gender;
    private EditText age;
    private EditText height;
    private EditText weight;
    private Button upload;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (EditText) findViewById(R.id.Username);
        gender = (EditText) findViewById(R.id.Usergender);
        age = (EditText) findViewById(R.id.UserAge);
        height = (EditText) findViewById(R.id.UserHeight);
        weight = (EditText) findViewById(R.id.UserWeight);
        upload = (Button) findViewById(R.id.btnUpload);

        upload.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                String Name = name.getText().toString();
                String Gender = gender.getText().toString();
                String Age = age.getText().toString();
                String Height = height.getText().toString();
                String Weight = weight.getText().toString();


                Map<String, Object> note = new HashMap<>();
                note.put(KEY_NAME, Name);
                note.put(KEY_GENDER, Gender);
                note.put(KEY_AGE, Age);
                note.put(KEY_HEIGHT, Height);
                note.put(KEY_WEIGHT, Weight);



                db.collection("User Info").document("My First Note").set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(profile.this, nav.class));
                                Toast.makeText(profile.this, "Profile data saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startActivity(new Intent(profile.this, nav.class));
                                Toast.makeText(profile.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });

            }
        });

    }



}
