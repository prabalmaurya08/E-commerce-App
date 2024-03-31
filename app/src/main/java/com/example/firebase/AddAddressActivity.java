package com.example.firebase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
public class AddAddressActivity extends AppCompatActivity {


    EditText name, address, city, postal, phone;


    Button save;


    Toolbar toolbar;

    FirebaseAuth auth;


    FirebaseFirestore firebaseFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_address);
        toolbar = findViewById(R.id.toolbar);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        save = findViewById(R.id.save_address);
        name = findViewById(R.id.address_name);
        address = findViewById(R.id.address_AddressLine);
        city = findViewById(R.id.address_city);
        postal = findViewById(R.id.address_postal);
        phone = findViewById(R.id.address_phone);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String useradd = address.getText().toString();
                String userCity = city.getText().toString();
                String userCode = postal.getText().toString();
                String userPhone = phone.getText().toString();
                String final_address ="";
                if (username.isEmpty()) {
                    Toast.makeText(AddAddressActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                if (!useradd.isEmpty()) {
                    final_address += useradd;
                }
                if (!userCity.isEmpty()) {
                    final_address += userCity;
                }
                if (!userCode.isEmpty()) {
                    final_address += userCode;
                }
                if (userPhone.isEmpty()) {
                    Toast.makeText(AddAddressActivity.this, "Provide Phone Number", Toast.LENGTH_SHORT).show();
                }
                if (!username.isEmpty() && !useradd.isEmpty() && !userCity.isEmpty() && !userCode.isEmpty() && !userPhone.isEmpty()) {
                    Map<String,String> hm = new HashMap<>();
                    hm.put("Address", final_address);
                    hm.put("name",username);
                    hm.put("phone_no",userPhone);

                    firebaseFirestore.collection("currentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(hm).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AddAddressActivity.this, SelectAddress.class));
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(AddAddressActivity.this, "Kindly Fill the Address", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}