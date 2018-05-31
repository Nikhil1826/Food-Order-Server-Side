package com.fasttech.foodorderserver2.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasttech.foodorderserver2.CurrentUser;
import com.fasttech.foodorderserver2.Interface.ItemClickListener;
import com.fasttech.foodorderserver2.R;

/**
 * Created by dell on 4/4/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    public Button btnEdit,btnRemove,btnDetail,btnDirection;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);

        btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
        btnRemove = (Button)itemView.findViewById(R.id.btnRemove);
        btnDetail = (Button)itemView.findViewById(R.id.btnDetail);
        btnDirection = (Button)itemView.findViewById(R.id.btnDirection);


    }



   /* @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }*/
}

