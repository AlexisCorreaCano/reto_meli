package com.alexis.reto_meli.presenter;

import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.reto_meli.view.SearchView;

import javax.inject.Inject;


public class SearchPresenter {
    private final SearchUseCase searchUseCase;
    private SearchView searchView;
    @Inject
    public SearchPresenter(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
        this.searchView = searchView;
    }

    public void setView(SearchView searchView){
        this.searchView = searchView;
    }

    public void searchItem(String item){
        searchUseCase.searchItem(item,responseModel -> {
            searchView.showData(responseModel.results);
        },error->{
            System.out.println(error.getMessage());
        });
    }

}
