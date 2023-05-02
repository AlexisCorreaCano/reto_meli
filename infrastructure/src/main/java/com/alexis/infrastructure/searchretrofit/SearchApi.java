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

    public SearchApi(String url) {
        this.retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        this.searchService = retrofit.create(SearchService.class);
    }
    @Override
    public void searchItem(String item, Consumer<ResponseModel> onSuccess, Consumer<Throwable> onError) {
        try{
            searchService.getItems(item).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    switch (response.code()){
                        case 404:
                            onError.accept(new Exception("No se encontró el recurso solicitado"));
                            break;
                        case 200:
                            onSuccess.accept(response.body());
                            break;
                        default:
                            onError.accept(new Exception("Hubo un error al obtener la información\n"));
                            break;
                    }
                }
                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    onError.accept(t);
                }
            });
        }catch (Exception e){
            onError.accept(e);
        }
    }
}
