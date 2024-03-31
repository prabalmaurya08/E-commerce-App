package com.example.firebase.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firebase.R;
import com.example.firebase.modal.Address_Modal;

import java.util.List;

public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.ViewHolder> {
    Context context;
    List<Address_Modal> addressModalList;
    private OnItemClickListener clickListener;
    private int selectedItemPosition = RecyclerView.NO_POSITION;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
        notifyDataSetChanged(); // Notify the adapter to redraw the RecyclerView
    }




    public Address_Adapter(Context context, List<Address_Modal> addressModalList) {
        this.context = context;
        this.addressModalList = addressModalList;
    }

    @NonNull
    @Override
    public Address_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));
    }
//    public void setSelectedPosition(int position) {
//        int previousSelectedPosition = selectedPosition;
//        selectedPosition = position;
//        notifyItemChanged(previousSelectedPosition);
//        notifyItemChanged(selectedPosition);
//    }


    @Override
    public void onBindViewHolder(@NonNull Address_Adapter.ViewHolder holder, int position) {
        holder.address.setText(addressModalList.get(holder.getAdapterPosition()).getAddress());
        holder.phone_no.setText(addressModalList.get(holder.getAdapterPosition()).getPhone_no());
        holder.name.setText(addressModalList.get(holder.getAdapterPosition()).getName());
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.bg));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // or set to your default background color
        }
//        Address_Modal addressModal = addressModalList.get(position);
//        holder.bindData(addressModal, selectedPosition == position);

    }



    @Override
    public int getItemCount() {
        return addressModalList.size();
    }

//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        this.listener= (OnItemClickListener) onItemClickListener;
//    }
//
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address,name,phone_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.show_address);
            name=itemView.findViewById(R.id.show_name);
            phone_no=itemView.findViewById(R.id.show_phone_no);
            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(position);
                    }
                }
            });
        }
//        public void bindData(Address_Modal addressModal, boolean isSelected) {
//            address.setText(addressModal.getAddress());
//            name.setText(addressModal.getName());
//            phone_no.setText(addressModal.getPhone_no());
//            itemView.setActivated(isSelected);
//        }

    }
}


//    Context context;
//    List<Address_Modal> addressModalList;
//    SelectAddress selectAddress;
//
//    private RadioButton selectRadioButton;
//
//    public Address_Adapter(Context context, List<Address_Modal> addressModalList, SelectAddress selectAddress) {
//        this.context = context;
//        this.addressModalList = addressModalList;
//        this.selectAddress = selectAddress;
//    }
//
//    @NonNull
//    @Override
//    public Address_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.add_address_item,parent,false));
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull Address_Adapter.ViewHolder holder,
//                                 int position) {
//        holder.address.setText(addressModalList.get(holder.getAdapterPosition()).getUserAddress());
//        holder.radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for(Address_Modal modal:addressModalList){
//                    modal.setSelected(false);
//                }
//
//                addressModalList.get(position).setSelected(true);
//                if(selectRadioButton!=null){
//                    selectRadioButton.setChecked(false);
//                }
//                selectRadioButton=(RadioButton) v;
//                selectRadioButton.setChecked(true);
//                selectAddress.setAddress(addressModalList.get(holder.getAdapterPosition()).getUserAddress());
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return addressModalList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView address;
//        RadioButton radioButton;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            address=itemView.findViewById(R.id.fullAdd);
//            radioButton=itemView.findViewById(R.id.radioButton);
//        }
//    }
//    public  interface SelectAddress{
//        void setAddress(String address);
//    }

