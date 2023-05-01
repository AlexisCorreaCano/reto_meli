package com.alexis.reto_meli.presenter;

import com.alexis.domain.shared.BusinessException;
import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.reto_meli.view.SearchView;

import javax.inject.Inject;


public class SearchPresenter {
    private final SearchUseCase searchUseCase;
    private SearchView searchView;
    @Inject
    public SearchPresenter(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    public void setView(SearchView searchView){
        this.searchView = searchView;
    }

    public void searchItem(String item){
        try{
            searchView.showLoading();
            searchUseCase.searchItem(item,responseModel -> {
                searchView.showData(responseModel.results);
                searchView.hideLoading();
            },error->{
                searchView.showError("Ocurrió un error al buscar el elemento, por favor intenta más tarde\n");
                searchView.hideLoading();
            });
        }catch (BusinessException e){
            searchView.showError("Debes diligenciar el campo con el nombre del objeto a buscar");
        }catch (Exception e){
            searchView.showError("En el momento nuestra aplicación está teniendo problemas, por favor intenta más tarde");
        }
    }
}
