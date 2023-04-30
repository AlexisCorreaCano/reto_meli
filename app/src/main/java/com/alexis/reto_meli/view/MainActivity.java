package com.alexis.reto_meli.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alexis.domain.model.Result;
import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.infrastructure.searchretrofit.SearchApi;
import com.alexis.reto_meli.R;
import com.alexis.reto_meli.presenter.SearchPresenter;
import com.alexis.reto_meli.view.adapter.ItemsAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    private Button btn_search;
    private EditText et_search;
    private RecyclerView rv_list_item;
    private ItemsAdapter itemsAdapter;
    @Inject
    SearchPresenter searchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchPresenter.setView(this);
        loadData();
        loadEvent();
    }

    private void loadEvent() {
        btn_search.setOnClickListener(this);
    }

    private void loadData() {
        btn_search = findViewById(R.id.btn_search);
        et_search=findViewById(R.id.et_search);
        rv_list_item=findViewById(R.id.rv_list_item);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showData(ArrayList<Result> results) {
        itemsAdapter = new ItemsAdapter(results);
        rv_list_item.setLayoutManager(new LinearLayoutManager(this));
        rv_list_item.setAdapter(itemsAdapter);

    }

    @Override
    public void onClick(View v) {
        searchPresenter.searchItem(et_search.getText().toString());
    }
}