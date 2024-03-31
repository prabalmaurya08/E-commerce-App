package com.example.firebase.adapter;

import static java.nio.file.Files.delete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.modal.MyCart_Modal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class MyCart_Adapter  extends RecyclerView.Adapter<MyCart_Adapter.ViewHolder> {
    Context context;
    List<MyCart_Modal> myCartModalList;
    int totalAmount;
    int dlt_price;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyCart_Adapter(Context context, List<MyCart_Modal> myCartModalList) {
        this.context = context;
        this.myCartModalList = myCartModalList;
    }

    @NonNull
    @Override
    public MyCart_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyCart_Adapter.ViewHolder holder, int position) {

        holder.name.setText(myCartModalList.get(holder.getAdapterPosition()).getProductName());
        holder.price.setText(myCartModalList.get(holder.getAdapterPosition()).getProductPrice());
        holder.time.setText(myCartModalList.get(holder.getAdapterPosition()).getProductTime());
        holder.date.setText(myCartModalList.get(holder.getAdapterPosition()).getProductDate());
        holder.quan.setText(myCartModalList.get(holder.getAdapterPosition()).getTotalQuantity());
        totalAmount=totalAmount+myCartModalList.get(position).getTotalPrice();

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DocId=myCartModalList.get(holder.getAdapterPosition()).getDocId();
                int deletedItemPrice = myCartModalList.get(holder.getAdapterPosition()).getTotalPrice();
                if(DocId!=null){
                    firestore.collection("addToCart").document(auth.getCurrentUser().getUid()).collection("Users")
                            .document(DocId).delete().

                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    myCartModalList.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                    totalAmount -= deletedItemPrice;
                                    Intent intent = new Intent("myTotalAmount");
                                    intent.putExtra("totalAmount", totalAmount);
                                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                                    Toast.makeText(context, "SuccessFul deleted", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                else {
                    Toast.makeText(context, "Null Id", Toast.LENGTH_SHORT).show();
                }

            }
        });




        Intent intent=new Intent("myTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return myCartModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quan;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quan=itemView.findViewById(R.id.myCart_item_quan);
            name=itemView.findViewById(R.id.myCart_item_name);
            price=itemView.findViewById(R.id.myCart_item_price);
            date=itemView.findViewById(R.id.myCart_item_date);
            time=itemView.findViewById(R.id.myCart_item_time);
            delete=itemView.findViewById(R.id.delete_cart);
        }
    }
}
