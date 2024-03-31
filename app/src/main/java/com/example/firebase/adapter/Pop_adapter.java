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
import com.example.firebase.modal.Popular;



import java.util.List;

public class Pop_adapter extends RecyclerView.Adapter<Pop_adapter.ViewHolder> {
    Context context;
    List<Popular> popularList;

    public Pop_adapter(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public Pop_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.popular,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Pop_adapter.ViewHolder holder, int position) {
        Glide.with(context).load(popularList.get(position).getImg_url()).into(holder.pop_img);
        holder.price.setText(popularList.get(position).getPrice());
        holder.name.setText(popularList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Detailed_Activity.class);
                intent.putExtra("detail", popularList.get(holder.getAdapterPosition()));
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pop_img;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pop_img=itemView.findViewById(R.id.popular_image);
            name=itemView.findViewById(R.id.popular_name);
            price=itemView.findViewById(R.id.pop_price);
        }
    }
}
