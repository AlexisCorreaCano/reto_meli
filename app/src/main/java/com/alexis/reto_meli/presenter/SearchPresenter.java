package com.alexis.reto_meli.presenter;

import com.alexis.domain.usecase.SearchUseCase;

public class SearchPresenter {
    private final SearchUseCase searchUseCase;

    public SearchPresenter(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    public void setView(){

    }

    public void searchItem(String item){
        searchUseCase.searchItem(item,responseModel -> {
            System.out.println(responseModel.toString());
        },error->{
            System.out.println(error.getMessage());
        });
    }

}
