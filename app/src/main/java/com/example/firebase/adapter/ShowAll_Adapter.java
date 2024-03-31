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
import com.example.firebase.modal.ShowAll_Modal;

import java.util.List;

public class ShowAll_Adapter extends RecyclerView.Adapter<ShowAll_Adapter.ViewHolder> {
    Context context;
    List<ShowAll_Modal> showAll_modalList;

    public ShowAll_Adapter(Context context, List<ShowAll_Modal> showAll_modalList) {
        this.context = context;
        this.showAll_modalList = showAll_modalList;
    }

    @NonNull
    @Override
    public ShowAll_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.show_all,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAll_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(showAll_modalList.get(position).getImg_url()).into(holder.showALlImage);
        holder.s_name.setText(showAll_modalList.get(position).getName());
        holder.s_price.setText(showAll_modalList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(context, Detailed_Activity.class);
                i1.putExtra("detail",showAll_modalList.get(holder.getAdapterPosition()));
                context.startActivity(i1);
            }
        });



    }

    @Override
    public int getItemCount() {
        return showAll_modalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView showALlImage;
        TextView s_name,s_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showALlImage=itemView.findViewById(R.id.showAll_image);
            s_name=itemView.findViewById(R.id.showAll_name);
            s_price=itemView.findViewById(R.id.showALl_price);
        }
    }
}
