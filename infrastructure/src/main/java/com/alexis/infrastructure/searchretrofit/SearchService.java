package com.alexis.infrastructure.searchretrofit;

import com.alexis.domain.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {
    @GET("/sites/MLA/search")
    Call<ResponseModel> getItems(@Query("q") String name);
}
