package com.example.firebase.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        firebaseAuth=FirebaseAuth.getInstance();





    }
    public void login(View view){
        String userEmail=email.getText().toString().trim();
        String userPassword=pass.getText().toString().trim();
        if(userEmail.isEmpty()){
            Toast.makeText(this, "Enter Your Email", Toast.LENGTH_SHORT).show();


        }
        else{
            if(userPassword.isEmpty()){
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            }

        }
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean("flag",true);
                    editor.apply();



                    startActivity(new Intent(MainActivity.this, Onboarding.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter valid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void signup(View view){
        startActivity(new Intent(MainActivity.this,SignUp.class));
    }
}