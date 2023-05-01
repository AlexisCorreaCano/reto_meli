package com.alexis.reto_meli.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.google.gson.Gson;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        String json = intent.getStringExtra("item");
        Gson gson = new Gson();
        Result result = gson.fromJson(json, Result.class);
    }
}