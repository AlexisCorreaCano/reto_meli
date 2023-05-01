package com.alexis.domain.usecase;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.repository.SearchRepository;

import java.util.function.Consumer;

public class SearchUseCase {
    private final SearchRepository searchRepository;

    public SearchUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
    public void searchItem(String item, Consumer<ResponseModel> onSuccess, Consumer<Throwable> onError)  {

        if(item.isEmpty()){
            throw new RuntimeException("el artículo a buscar no debe de ser vacío");
        }

        searchRepository.searchItem(item,onSuccess,onError);
    }
}
