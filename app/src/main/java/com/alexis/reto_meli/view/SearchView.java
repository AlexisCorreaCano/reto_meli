package com.alexis.reto_meli.view;

import com.alexis.domain.model.ResponseModel;

public interface SearchView {
    void showLoading();
    void hideLoading();
    void showError();
    void showData(ResponseModel responseModel);
}
