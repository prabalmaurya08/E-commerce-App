package com.example.firebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.Detailed_Activity;
import com.example.firebase.R;
import com.example.firebase.modal.NewProduct_modal;

import java.util.List;

public class NewProduct_Adapter extends RecyclerView.Adapter<NewProduct_Adapter.ViewHolder> {
    Context context;
    List<NewProduct_modal> newProductModalList;
    int totalAmount;

    public NewProduct_Adapter(Context context, List<NewProduct_modal> newProductModalList) {
        this.context = context;
        this.newProductModalList = newProductModalList;
    }

    @NonNull
    @Override
    public NewProduct_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.newproduct,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewProduct_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(newProductModalList.get(position).getImg_url()).into(holder.newProductImg);
        holder.price.setText(newProductModalList.get(position).getPrice());
        holder.name.setText(newProductModalList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Detailed_Activity.class);
                intent.putExtra("detail",newProductModalList.get(holder.getAdapterPosition()));
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return newProductModalList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newProductImg;
        TextView price,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newProductImg=itemView.findViewById(R.id.new_product_image);
            price=itemView.findViewById(R.id.new_product_price);
            name=itemView.findViewById(R.id.new_product_name);
        }
    }
}
