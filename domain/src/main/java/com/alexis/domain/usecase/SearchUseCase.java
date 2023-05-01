package com.alexis.domain.usecase;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.repository.SearchRepository;
import com.alexis.domain.shared.BusinessException;

import java.util.function.Consumer;

public class SearchUseCase {
    private final SearchRepository searchRepository;

    public SearchUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
    public void searchItem(String item, Consumer<ResponseModel> onSuccess, Consumer<Throwable> onError)  {

        if(item.isEmpty()){
            throw new BusinessException(BusinessException.MESSAGE_ITEM_EMPTY_OR_NULL);
        }

        searchRepository.searchItem(item,onSuccess,onError);
    }
}
