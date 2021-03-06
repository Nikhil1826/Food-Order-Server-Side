package com.fasttech.foodorderserver2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fasttech.foodorderserver2.ViewHolder.OrderDetailAdapter;

public class OrderDetailActivity extends AppCompatActivity {

    TextView order_id,order_phone,order_address,order_total,order_comment;
    String order_id_value="";
    RecyclerView lstFoods;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order_id = (TextView)findViewById(R.id.order_id);
        order_phone = (TextView)findViewById(R.id.order_phone);
        order_address = (TextView)findViewById(R.id.order_address);
        order_total = (TextView)findViewById(R.id.order_total);
        order_comment = (TextView)findViewById(R.id.order_comment);

        lstFoods = (RecyclerView)findViewById(R.id.lstFoods);
        lstFoods.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstFoods.setLayoutManager(layoutManager);

        if(getIntent()!=null){
            order_id_value = getIntent().getStringExtra("OrderId");
        }

        order_id.setText(order_id_value);
        order_phone.setText(CurrentUser.cRequest.getPhone());
        order_total.setText(CurrentUser.cRequest.getTotal());
        order_address.setText(CurrentUser.cRequest.getAddress());
        order_comment.setText(CurrentUser.cRequest.getComment());


        OrderDetailAdapter adapter = new OrderDetailAdapter(CurrentUser.cRequest.getFoods());
        adapter.notifyDataSetChanged();
        lstFoods.setAdapter(adapter);

    }
}
