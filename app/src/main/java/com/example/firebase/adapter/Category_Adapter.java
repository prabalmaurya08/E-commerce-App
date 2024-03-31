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
import com.example.firebase.R;
import com.example.firebase.ShowAllActivity;
import com.example.firebase.modal.Category_modal;

import org.w3c.dom.Text;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder>{
    Context context;
    List<Category_modal> categoryModalList;

    public Category_Adapter(Context context, List<Category_modal> categoryModalList) {
        this.context = context;
        this.categoryModalList = categoryModalList;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(categoryModalList.get(position).getImg_url()).into(holder.cat_img);
        holder.cat_name.setText(categoryModalList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ShowAllActivity.class);
                i.putExtra("type",categoryModalList.get(position).getType());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cat_img;
        TextView cat_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_img=itemView.findViewById(R.id.category_image);
            cat_name=itemView.findViewById(R.id.category_name);

        }
    }
}
