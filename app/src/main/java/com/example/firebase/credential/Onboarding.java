package com.example.firebase.credential;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.firebase.R;
import com.example.firebase.adapter.Slider_Adapter;

public class Onboarding extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button letsGo;
    Animation animation;
    Slider_Adapter sliderAdapter;
    TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        letsGo=findViewById(R.id.letsGo);
        viewPager=findViewById(R.id.page_viewer);
        dotsLayout=findViewById(R.id.dots);
        sliderAdapter=new Slider_Adapter(this);
       addDots(0);
        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(changeListener);
        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Onboarding.this, MainHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addDots(int position){
        dots=new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);


            dotsLayout.addView(dots[i]);
        }
        if(dots.length >0){
            dots[position].setTextColor(getResources().getColor(R.color.pink));
        }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

                if (position < 2) {
                    letsGo.setVisibility(View.INVISIBLE);
                } else {
                    animation = AnimationUtils.loadAnimation(Onboarding.this, R.anim.slide_anim);
                    letsGo.setAnimation(animation);
                    letsGo.setVisibility(View.VISIBLE);
                }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}