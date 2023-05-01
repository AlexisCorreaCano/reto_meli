package com.alexis.reto_meli.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ItemDetailActivity extends AppCompatActivity {
    private ImageView iv_image;
    private TextView tv_sold_item,tv_title,tv_price,tv_free_shipping,tv_seller,tv_sold_seller,tv_avalible_quantity;
    private Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        String json = intent.getStringExtra("item");
        Gson gson = new Gson();
        result = gson.fromJson(json, Result.class);

        try {
            loadView();
            loadData();
        }catch (Exception e){
            showError("En el momento nuestra aplicación está teniendo problemas, por favor intenta más tarde");
        }

    }

    private void loadData() {
        Picasso.get().load(result.thumbnail)
                .placeholder(R.drawable.imagen)
                .error(R.drawable.error)
                .into(iv_image);
        tv_sold_item.setText(String.format("%d Vendidos",result.sold_quantity));
        tv_title.setText(result.title);
        tv_price.setText(String.format("$ %f",result.price));
        tv_free_shipping.setVisibility(result.shipping.free_shipping == true? View.VISIBLE:View.GONE);
        tv_seller.setText(result.seller.nickname);
        tv_sold_seller.setText(String.format("+%d Ventas",result.seller.seller_reputation.transactions.completed));
        tv_avalible_quantity.setText(String.format("%d disponible",result.available_quantity) );
    }

    private void loadView() {
        iv_image = findViewById(R.id.iv_image);
        tv_sold_item = findViewById(R.id.tv_sold_item);
        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        tv_free_shipping = findViewById(R.id.tv_free_shipping);
        tv_seller = findViewById(R.id.tv_seller);
        tv_sold_seller = findViewById(R.id.tv_sold_seller);
        tv_avalible_quantity = findViewById(R.id.tv_available_quantity);
    }

    private void showError(String message){

    }
}