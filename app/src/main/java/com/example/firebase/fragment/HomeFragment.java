package com.example.firebase.fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.firebase.R;
import com.example.firebase.ShowAllActivity;
import com.example.firebase.adapter.Category_Adapter;
import com.example.firebase.adapter.NewProduct_Adapter;

import com.example.firebase.adapter.Pop_adapter;
import com.example.firebase.modal.Category_modal;
import com.example.firebase.modal.NewProduct_modal;
import com.example.firebase.modal.Popular;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import org.w3c.dom.Text;

import java.util.ArrayList;

import java.util.List;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }
    TextView popSeeAll,newPopSeeAll;
    //Category Item
    RecyclerView catRecyclerView,newProductRecyclerView,popular_recyclerView;
    Category_Adapter categoryAdapter;
    List<Category_modal> category_modalList;
    FirebaseFirestore db;
    LinearLayout linearLayout;


    //New Product

    NewProduct_Adapter newProductAdapter;
    List<NewProduct_modal> newProductModalList;


    //popular
    Pop_adapter popularAdapter;

    List<Popular> popular_modalList;

    ProgressDialog progressDialog;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=   inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog=new ProgressDialog(getActivity());
        linearLayout=root.findViewById(R.id.linearLayout);
        //Giving Recycler View id
        catRecyclerView=root.findViewById(R.id.category_recyclerView);

        newProductRecyclerView=root.findViewById(R.id.new_product_recyclerView);

        popSeeAll=root.findViewById(R.id.popular_seeAll);
        newPopSeeAll=root.findViewById(R.id.new_pop_seeAll);

        popular_recyclerView=root.findViewById(R.id.popular_product_recyclerView);
        db=FirebaseFirestore.getInstance();
        ImageSlider imageSlider=root.findViewById(R.id.image_slider);
        List<SlideModel> slideModals=new ArrayList<>();
        slideModals.add(new SlideModel(R.drawable.slide_pic,"Discount 70%", ScaleTypes.CENTER_CROP));
        slideModals.add(new SlideModel(R.drawable.sundaysale,"Sale on Sunday", ScaleTypes.CENTER_CROP));
        slideModals.add(new SlideModel(R.drawable.new_sale,"Newly Launched product", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModals);
        progressDialog.setTitle("Welcome to E-commerce App");
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //Set on Click Listener on See ALl

        popSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getContext(), ShowAllActivity.class);
                startActivity(i2);

            }
        });
        newPopSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getContext(), ShowAllActivity.class);
                startActivity(i3);

            }
        });

        //Category

        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        category_modalList=new ArrayList<>();
        categoryAdapter=new Category_Adapter(getContext(),category_modalList);
        catRecyclerView.setAdapter(categoryAdapter);


        //To Read From FireBase FireSTore

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category_modal categoryModal=document.toObject(Category_modal.class);
                                category_modalList.add(categoryModal);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //New Product

        newProductRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        newProductModalList=new ArrayList<>();
        newProductAdapter=new NewProduct_Adapter(getContext(),newProductModalList);
        newProductRecyclerView.setAdapter(newProductAdapter);

        db.collection("NewProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProduct_modal newProductModal=document.toObject(NewProduct_modal.class);
                                newProductModalList.add(newProductModal);
                                newProductAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //Popular Product
        popular_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popular_modalList=new ArrayList<>();
        popularAdapter=new Pop_adapter(getContext(),popular_modalList);
        popular_recyclerView.setAdapter(popularAdapter);
        db.collection("popular")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Popular popular=document.toObject(Popular.class);
                                popular_modalList.add(popular);
                                popularAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;



    }


}