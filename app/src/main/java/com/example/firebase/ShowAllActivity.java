package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Toast;

import com.example.firebase.adapter.ShowAll_Adapter;
import com.example.firebase.modal.ShowAll_Modal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAll_Adapter showAllAdapter;
    List<ShowAll_Modal> showAll_modalList;
    FirebaseFirestore firestore;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        recyclerView=findViewById(R.id.showAllRecylerview);

        String type= getIntent().getStringExtra("type");

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAll_modalList=new ArrayList<>();
        showAllAdapter=new ShowAll_Adapter(this,showAll_modalList);
        recyclerView.setAdapter(showAllAdapter);
        firestore=FirebaseFirestore.getInstance();
//        firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
//                        ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
//                        showAll_modalList.add(showAllModal);
//                        showAllAdapter.notifyDataSetChanged();
//
//                    }
//
//                }
//                else{
//                    Toast.makeText(ShowAllActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        if (type != null && !type.isEmpty()) {
//            // Load data based on the selected type
//
//            firestore.collection("ShowAll").whereEqualTo("type", type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        showAll_modalList.clear(); // Clear the existing data
//                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                            ShowAll_Modal showAllModal = documentSnapshot.toObject(ShowAll_Modal.class);
//                            showAll_modalList.add(showAllModal);
//                        }
//                        showAllAdapter.notifyDataSetChanged();
//                    } else {
//                        Toast.makeText(ShowAllActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }


        if(type==null) {
            firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            ShowAll_Modal showAllModal = documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    } else {
                        Toast.makeText(ShowAllActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

            if(type!=null&&type.equalsIgnoreCase("men")){
                firestore.collection("ShowAll").whereEqualTo("type","men").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                                showAll_modalList.add(showAllModal);


                            }
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }
                });
            }
            if(type!=null&&type.equalsIgnoreCase("women")){
            firestore.collection("ShowAll").whereEqualTo("type","women").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }

                }
            });
        }

        if(type!=null&&type.equalsIgnoreCase("camera")){
            firestore.collection("ShowAll").whereEqualTo("type","camera").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }

                }
            });
        }

        if(type!=null && type.equalsIgnoreCase("watch")){
            firestore.collection("ShowAll").whereEqualTo("type","watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }

                }
            });
        }
        if(type!=null && type.equalsIgnoreCase("kids")){
            firestore.collection("ShowAll").whereEqualTo("type","kids").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }

                }
            });
        }
        if(type!=null && type.equalsIgnoreCase("shoes")){
            firestore.collection("ShowAll").whereEqualTo("type","shoes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            ShowAll_Modal showAllModal=documentSnapshot.toObject(ShowAll_Modal.class);
                            showAll_modalList.add(showAllModal);
                            showAllAdapter.notifyDataSetChanged();

                        }

                    }

                }
            });
        }




    }


    }

//    private void loadDataBasedOnType(String type) {
//        firestore.collection("ShowAll").whereEqualTo("type", type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    showAll_modalList.clear(); // Clear the existing data
//                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                        ShowAll_Modal showAllModal = documentSnapshot.toObject(ShowAll_Modal.class);
//                        showAll_modalList.add(showAllModal);
//                    }
//                    showAllAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(ShowAllActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

