package com.example.firebase.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.CartActivity;
import com.example.firebase.fragment.HomeFragment;
import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainHome extends AppCompatActivity {
    EditText email,password;
    Fragment homeFragment;
    FirebaseAuth auth;
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        homeFragment=new HomeFragment();
        loadHomeFragment(homeFragment);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        FirebaseMessaging.getInstance().subscribeToTopic("ecommerce").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg="DONE";
                if(!task.isSuccessful()){
                    msg="FAILED";
                }

            }
        });
    }

    private void loadHomeFragment(Fragment homeFragment) {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fl,homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.cart){
            startActivity(new Intent(MainHome.this, CartActivity.class));
            finish();
        }
        if(id==R.id.logout){
            auth.signOut();
            startActivity(new Intent(MainHome.this, MainActivity.class));
            finish();
            SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            editor.putBoolean("flag",false);
            editor.apply();
        }
        return true;
    }
}