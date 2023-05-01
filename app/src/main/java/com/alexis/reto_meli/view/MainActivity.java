package com.alexis.reto_meli.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.alexis.reto_meli.presenter.SearchPresenter;
import com.alexis.reto_meli.view.adapter.ItemsAdapter;
import com.alexis.reto_meli.view.adapter.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    private Button btn_search;
    private EditText et_search;
    private RecyclerView rv_list_item;
    private ItemsAdapter itemsAdapter;
    private ProgressBar progressBar;

    private ArrayList<Result> results;
    Gson gson = new Gson();

    @Inject
    SearchPresenter searchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchPresenter.setView(this);
        loadElements();
        loadEvent();
    }

    private void loadEvent() {
        btn_search.setOnClickListener(this);

    }

    private void loadElements() {
        btn_search = findViewById(R.id.btn_search);
        et_search=findViewById(R.id.et_search);
        rv_list_item=findViewById(R.id.rv_list_item);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showData(ArrayList<Result> results) {
        this.results = results;
        itemsAdapter = new ItemsAdapter(results, new OnItemClickListener() {
            @Override
            public void onItemClick(Result item) {
                Intent intent = new Intent(MainActivity.this,ItemDetailActivity.class);

                String json = gson.toJson(item);
                intent.putExtra("item",json);
                startActivity(intent);
            }
        });
        rv_list_item.setAdapter(itemsAdapter);
        rv_list_item.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        searchPresenter.searchItem(et_search.getText().toString());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("search",et_search.getText().toString());
        String jsonResult = gson.toJson(results);
        outState.putString("result",jsonResult);
        outState.putParcelable("list",rv_list_item.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        et_search.setText(savedInstanceState.getString("search"));
        String jsonResult = savedInstanceState.getString("result");
        results =  gson.fromJson(jsonResult, new TypeToken<ArrayList<Result>>(){}.getType());
        showData(results);
        rv_list_item.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable("list"));
    }
}
