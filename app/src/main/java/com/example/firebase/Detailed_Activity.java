package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.firebase.modal.NewProduct_modal;
import com.example.firebase.modal.Popular;

import com.example.firebase.modal.ShowAll_Modal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Detailed_Activity extends AppCompatActivity {
    ImageView detail_img,plus,minus;
    TextView count,price,description,rating_no,name;
    Button addToCart,buyNow;
    int totalQuantity=1;
    int totalPrice=0;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    NewProduct_modal newProductModal=null;
    Popular popularModal=null;

    ShowAll_Modal showAllModal=null;
    RatingBar ratingBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        final Object obj=getIntent().getSerializableExtra("detail");
        if(obj instanceof NewProduct_modal){
            newProductModal=(NewProduct_modal) obj;
        } else if (obj instanceof Popular) {
            popularModal=(Popular) obj;
            
        } else if (obj instanceof ShowAll_Modal) {
            showAllModal=(ShowAll_Modal) obj;

        }
        setContentView(R.layout.activity_detailed);
        detail_img=findViewById(R.id.detail_image);
        count=findViewById(R.id.count);
        price=findViewById(R.id.price);
        name=findViewById(R.id.product_name);
        description=findViewById(R.id.description);
        rating_no=findViewById(R.id.rating_no);
        ratingBar=findViewById(R.id.ratingBar);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        addToCart=findViewById(R.id.addTocart);
        buyNow=findViewById(R.id.buyNow);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity <10){
                    totalQuantity++;
                    count.setText(String.valueOf(totalQuantity));
                }
                if(newProductModal!=null) {
                    totalPrice=Integer.parseInt(newProductModal.getPrice()) *totalQuantity;
                }
                if(popularModal!=null){
                    totalPrice=Integer.parseInt(popularModal.getPrice()) *totalQuantity;
                }
                if(showAllModal!=null){
                    totalPrice=Integer.parseInt(showAllModal.getPrice()) *totalQuantity;
                }

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity >1){
                    totalQuantity--;
                    count.setText(String.valueOf(totalQuantity));
                }

            }
        });
        //New Product
        if(newProductModal!=null){
            Glide.with(getApplicationContext()).load(newProductModal.getImg_url()).into(detail_img);
            name.setText(newProductModal.getName());
            description.setText(newProductModal.getDescription());
            rating_no.setText(newProductModal.getRating());
            price.setText(newProductModal.getPrice());
            totalPrice=Integer.parseInt(newProductModal.getPrice()) *totalQuantity;


        }

        //Popular Product

        if(popularModal!=null){
            Glide.with(getApplicationContext()).load(popularModal.getImg_url()).into(detail_img);
            name.setText(popularModal.getName());
            description.setText(popularModal.getDescription());
            rating_no.setText(popularModal.getRating());
            price.setText(popularModal.getPrice());
            totalPrice=Integer.parseInt(popularModal.getPrice()) *totalQuantity;
        }

        if(showAllModal!=null){
            Glide.with(getApplicationContext()).load(showAllModal.getImg_url()).into(detail_img);
            name.setText(showAllModal.getName());
            description.setText(showAllModal.getDescription());
            rating_no.setText(showAllModal.getRating());
            price.setText(showAllModal.getPrice());
            totalPrice=Integer.parseInt(showAllModal.getPrice()) *totalQuantity;
        }


        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detailed_Activity.this,SelectAddress.class);


                if(newProductModal!=null){
                    intent.putExtra("item",newProductModal);

                }
                if(popularModal!=null){
                    intent.putExtra("item",popularModal);

                }
                if(showAllModal!=null){
                    intent.putExtra("item",showAllModal);

                }
                startActivity(intent);

            }
        });

        //Add to cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart();
            }
        });



    }

    private void addCart() {
        String currentDate,currentTime;
        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat date=new SimpleDateFormat("dd-MM-yyyy ");
        currentDate=date.format(calendar.getTime());

        SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss a");
        currentTime=time.format(calendar.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();
        String uid=firebaseFirestore.collection("Users").document().getId();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("productDate",currentDate);
        cartMap.put("productTime",currentTime);
        cartMap.put("TotalQuantity",count.getText().toString());
        cartMap.put("TotalPrice",totalPrice);
        cartMap.put("docId",uid);

        firebaseFirestore.collection("addToCart").document(auth.getCurrentUser().getUid())
                .collection("Users").document(uid).set(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Detailed_Activity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}