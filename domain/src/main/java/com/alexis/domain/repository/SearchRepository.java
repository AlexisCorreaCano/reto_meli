package com.alexis.domain.repository;


import com.alexis.domain.model.ResponseModel;

import java.util.function.Consumer;

public interface SearchRepository {
     void searchItem(String item, Consumer<ResponseModel> onSuccess,Consumer<Throwable> onError);
}
