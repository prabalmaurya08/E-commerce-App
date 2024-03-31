package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.adapter.MyCart_Adapter;
import com.example.firebase.modal.MyCart_Modal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    Toolbar toolbar;
  RecyclerView recyclerView;
  List<MyCart_Modal> cartModalList;
  MyCart_Adapter adapter;
  TextView finalPrice;
  FirebaseFirestore firebaseFirestore;
  FirebaseAuth auth;
  Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.myCart_recyclerView);
        toolbar=findViewById(R.id.myCart_toolbar);
        finalPrice=findViewById(R.id.myCart_price);
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        placeOrder=findViewById(R.id.myCart_order_btn);
        setSupportActionBar(toolbar);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("myTotalAmount"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartModalList=new ArrayList<>();
        adapter=new MyCart_Adapter(this,cartModalList);
        recyclerView.setAdapter(adapter);




        //Place order Btn

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CartActivity.this, Payment.class);
                String finalPriceValue = finalPrice.getText().toString();
                intent1.putExtra("amount", finalPriceValue);
              startActivity(intent1);

            }
        });
        firebaseFirestore.collection("addToCart").document(auth.getCurrentUser().getUid()).collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        MyCart_Modal myCartModal=documentSnapshot.toObject(MyCart_Modal.class);
                        cartModalList.add(myCartModal);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(CartActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           int  value=intent.getIntExtra("totalAmount",0);
           finalPrice.setText("$"+ value);
        }
    };
}