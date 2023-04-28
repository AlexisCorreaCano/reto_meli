package com.alexis.infrastructure.searchretrofit;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.repository.SearchRepository;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchApi implements SearchRepository {
    private final Retrofit retrofit;
    private final SearchService searchService;

    public SearchApi() {
        this.retrofit = new Retrofit.Builder().baseUrl("https://api.mercadolibre.com").addConverterFactory(GsonConverterFactory.create()).build();
        this.searchService = retrofit.create(SearchService.class);
    }

    @Override
    public void searchItem(String item, Consumer<ResponseModel> onSuccess, Consumer<Throwable> onError) {
        try{
            searchService.getItems(item).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    onSuccess.accept(response.body());
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    onError.accept(t);
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
