package com.example.firebase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.firebase.R;

@SuppressLint("MissingInflatedId")
public class Slider_Adapter extends PagerAdapter {
    Context context;

    public Slider_Adapter(Context context) {
        this.context = context;
    }

    LayoutInflater layoutInflater;
    int[] imageArray ={
            R.drawable.slide_pic,
            R.drawable.del,
            R.drawable.standard,
    };
    int[] headingArray ={
            R.string.firstSlide,
            R.string.secondSlide,
            R.string.thirdSlide,

    };
    int[] desArray ={
            R.string.des1,
            R.string.des2,
            R.string.des3,

    };
    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider,container,false);

        ImageView imageView=view.findViewById(R.id.slider_image);
         TextView heading=view.findViewById(R.id.slider_head);
         TextView des=view.findViewById(R.id.slider_description);

         imageView.setImageResource(imageArray[position]);
         heading.setText(headingArray[position]);
         des.setText(desArray[position]);


        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
