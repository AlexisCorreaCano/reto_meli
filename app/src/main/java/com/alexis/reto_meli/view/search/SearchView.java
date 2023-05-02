package com.alexis.reto_meli.view.search;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.model.Result;

import java.util.ArrayList;

public interface SearchView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    void showData(ArrayList<Result> result);
}
