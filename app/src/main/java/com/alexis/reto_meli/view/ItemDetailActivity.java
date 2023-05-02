package com.alexis.reto_meli.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ItemDetailActivity extends AppCompatActivity {
    private ImageView iv_image;
    private TextView tv_sold_item;
    private TextView tv_original_price;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_free_shipping;
    private TextView tv_seller;
    private TextView tv_sold_seller;
    private TextView tv_available_quantity;
    private TextView tv_offer;
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
        NumberFormat numberFormat = new DecimalFormat("#,###");

        tv_sold_item.setText(String.format("%d Vendidos",result.sold_quantity));
        tv_title.setText(result.title);

        Picasso.get().load(result.thumbnail)
                .placeholder(R.drawable.imagen)
                .error(R.drawable.error)
                .into(iv_image);

        if(result.original_price != null){
            String originalPriceS = String.format("$ %s ",numberFormat.format((Double)result.original_price));
            tv_original_price.setText(originalPriceS);
            tv_original_price.setPaintFlags(tv_original_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            tv_original_price.setVisibility(View.VISIBLE);

            Double originalPriceD = (Double) result.original_price;
            Double price = result.price;
            double offerPercentage = 100-((price * 100)/originalPriceD);
            if(offerPercentage > 0){
                tv_offer.setText(String.format("%d%% OFF",(int)offerPercentage));
                tv_offer.setVisibility(View.VISIBLE);
            }
        }

        String price = String.format("$ %s",numberFormat.format((Double)result.price));
        tv_price.setText(price);
        tv_free_shipping.setVisibility(result.shipping.free_shipping == true? View.VISIBLE:View.GONE);
        tv_seller.setText(result.seller.nickname);
        tv_sold_seller.setText(String.format("+%d Ventas",result.seller.seller_reputation.transactions.completed));
        tv_available_quantity.setText(result.available_quantity);
    }

    private void loadView() {
        iv_image = findViewById(R.id.iv_image);
        tv_sold_item = findViewById(R.id.tv_sold_item);
        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        tv_free_shipping = findViewById(R.id.tv_free_shipping);
        tv_seller = findViewById(R.id.tv_seller);
        tv_sold_seller = findViewById(R.id.tv_sold_seller);
        tv_available_quantity = findViewById(R.id.tv_available_quantity);
        tv_original_price = findViewById(R.id.tv_original_price);
        tv_offer = findViewById(R.id.tv_offer);
    }

    private void showError(String message){
        View alertCustomView = LayoutInflater.from(ItemDetailActivity.this).inflate(R.layout.custom_alert_view,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ItemDetailActivity.this);

        alertDialog.setView(alertCustomView);
        Button btn_close = (Button) alertCustomView.findViewById(R.id.btn_close);

        final AlertDialog dialog = alertDialog.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}