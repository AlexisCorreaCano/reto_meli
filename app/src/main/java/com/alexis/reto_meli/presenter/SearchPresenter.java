package com.alexis.reto_meli.presenter;

import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.reto_meli.view.SearchView;

public class SearchPresenter {
    private final SearchUseCase searchUseCase;
    private final SearchView searchView;

    public SearchPresenter(SearchUseCase searchUseCase, SearchView searchView) {
        this.searchUseCase = searchUseCase;
        this.searchView = searchView;
    }

    public void setView(){

    }

    public void searchItem(String item){
        searchUseCase.searchItem(item,responseModel -> {
            searchView.showData(responseModel.results);
        },error->{
            System.out.println(error.getMessage());
        });
    }

}
