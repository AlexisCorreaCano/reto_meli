package com.alexis.reto_meli.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.infrastructure.searchretrofit.SearchApi;
import com.alexis.reto_meli.R;
import com.alexis.reto_meli.presenter.SearchPresenter;


public class MainActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    private Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        loadEvent();
    }

    private void loadEvent() {
        btn_search.setOnClickListener(this);
    }

    private void loadData() {
        btn_search = findViewById(R.id.btn_search);
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
    public void showData(ResponseModel responseModel) {

    }

    @Override
    public void onClick(View v) {
        SearchPresenter presenter = new SearchPresenter(new SearchUseCase(new SearchApi()));
        presenter.searchItem("camara");
    }
}