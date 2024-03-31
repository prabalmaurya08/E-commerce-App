package com.example.firebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.firebase.adapter.Address_Adapter;
import com.example.firebase.modal.Address_Modal;
import com.example.firebase.modal.NewProduct_modal;
import com.example.firebase.modal.Popular;
import com.example.firebase.modal.ShowAll_Modal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SelectAddress extends AppCompatActivity  {
    Button addAddress,payment;
    Toolbar toolbar;

    RecyclerView recyclerView;
    List<Address_Modal> addressModalList;
    Address_Adapter adapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.select_recyclerView);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Object obj=getIntent().getSerializableExtra("item");

        auth=FirebaseAuth.getInstance();
        addAddress=findViewById(R.id.btn_add_address);
        payment=findViewById(R.id.btn_payment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressModalList=new ArrayList<>();
        adapter=new Address_Adapter(getApplicationContext(),addressModalList);
        recyclerView.setAdapter(adapter);
       // recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(this,recyclerView,new RecyclerView.SimpleOnItemTouchListener())

        adapter.setOnItemClickListener(position -> {
            adapter.setSelectedItemPosition(position);

            // Rest of your code...
        });



        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount=calculateTotalAmount(obj);
                String val=String.valueOf(amount);

                Intent int1=new Intent(SelectAddress.this, Payment.class);
                int1.putExtra("amount",val);

                startActivity(int1);


            }
        });
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectAddress.this, AddAddressActivity.class));
            }
        });



        firebaseFirestore.collection("currentUser").document(auth.getCurrentUser().getUid()).collection("Address").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                Address_Modal modal=documentSnapshot.toObject(Address_Modal.class);
                                addressModalList.add(modal);
                                adapter.notifyDataSetChanged();
                            }


                        }
                    }
                });

    }
    private int calculateTotalAmount(Object obj) {
        int amount = 0;

        if (obj instanceof NewProduct_modal) {
            NewProduct_modal newProductModal = (NewProduct_modal) obj;
            amount += Integer.parseInt(newProductModal.getPrice());
        }

        if (obj instanceof Popular) {
            Popular popular = (Popular) obj;
            amount += Integer.parseInt(popular.getPrice());
        }

        if (obj instanceof ShowAll_Modal) {
            ShowAll_Modal showAllModal = (ShowAll_Modal) obj;
            amount += Integer.parseInt(showAllModal.getPrice());
        }

        return amount;
    }
//    @Override
//    public boolean onOptionsItemSelected(Menu
//                                                     Item item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // Navigate back to the previous activity
//                finish();
//                return true;
//            // Add more cases for other menu items if needed
//        }
//        return super.onOptionsItemSelected(item);
//    }


}