package com.alexis.reto_meli.view.search;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.alexis.reto_meli.presenter.SearchPresenter;
import com.alexis.reto_meli.view.adapter.search.ItemsAdapter;
import com.alexis.reto_meli.view.adapter.search.OnItemClickListener;
import com.alexis.reto_meli.view.builder.ErrorAlertBuild;
import com.alexis.reto_meli.view.itemdetail.ItemDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    @Inject
    SearchPresenter searchPresenter;
    @Inject
    ErrorAlertBuild errorAlertBuild;
    private EditText et_search;
    private RecyclerView rv_list_item;
    private ItemsAdapter itemsAdapter;
    private ProgressBar progressBar;
    private ArrayList<Result> results;
    private LinearLayout containerNoFound;
    private final Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchPresenter.setView(this);
        loadView();
        loadEvent();
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
    public void showError(String error) {
        hideLoading();
        errorAlertBuild.buildAlert(this,error).show();
    }

    @Override
    public void showData(ArrayList<Result> results) {
        this.results = results;

        itemsAdapter = new ItemsAdapter(results, new OnItemClickListener() {
            @Override
            public void onItemClick(Result item) {
                Intent intent = new Intent(SearchActivity.this, ItemDetailActivity.class);

                String json = gson.toJson(item);
                intent.putExtra("item",json);
                startActivity(intent);
            }
        });
        rv_list_item.setAdapter(itemsAdapter);
        rv_list_item.setLayoutManager(new LinearLayoutManager(this));
        if(results.isEmpty()){
            containerNoFound.setVisibility(View.VISIBLE);
        }
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

    private void loadEvent() {
        rv_list_item.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == SCROLL_STATE_DRAGGING){
                    hideKeyboard();
                }
            }

        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == 3 || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER ) ){
                    containerNoFound.setVisibility(View.GONE);
                    searchPresenter.searchItem(v.getText().toString());
                    handled = true;
                    hideKeyboard();
                }
                return handled;
            }
        });
    }
    private void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void loadView() {
        et_search=findViewById(R.id.et_search);
        rv_list_item=findViewById(R.id.rv_list_item);
        progressBar = findViewById(R.id.progress_bar);
        containerNoFound = findViewById(R.id.container_no_found);
        progressBar.setVisibility(View.GONE);
    }
}
