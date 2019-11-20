package com.example.poe3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseUser mUser;
    private Button btnLogin;
    private Button register;
    private EditText emailField;
    private EditText passField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.Lbutton);
        emailField = (EditText) findViewById(R.id.Lemail);
        passField = (EditText) findViewById(R.id.Lpass);

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();

                if(mUser != null){
                    //takes to another view if the validation was sucsessful
                    startActivity(new Intent(MainActivity.this, nav.class));
                    Toast.makeText(MainActivity.this,"Welcome to my app",Toast.LENGTH_LONG).show();
                }else {Toast.makeText(MainActivity.this,"Login failed please try again",Toast.LENGTH_LONG).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(emailField.getText().toString())
                        && !TextUtils.isEmpty(passField.getText().toString())){

                    String email = emailField.getText().toString();
                    String pwd = passField.getText().toString();
                    login(email,pwd);


                }else{

                }
            }
        });
    }

    private void login(String email, String pwd) {

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"Welcome to my app",Toast.LENGTH_LONG).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
        // Check if user is signed in (non-null) and update UI accordingly.

    }
}
