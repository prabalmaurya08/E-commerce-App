package com.example.firebase.credential;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firebase.R;


public class Splash_Screen extends AppCompatActivity {
    ImageView imageView;
    Animation animation;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView=findViewById(R.id.splash_img);

        animation = AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.slide_anim);
        imageView.setAnimation(animation);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                boolean next=pref.getBoolean("flag",false);
                if(next){
                    Intent intent=new Intent(Splash_Screen.this, MainHome.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent i=new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        },3000);
    }
}