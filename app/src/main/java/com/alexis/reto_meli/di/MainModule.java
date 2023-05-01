package com.alexis.reto_meli.di;

import com.alexis.domain.repository.SearchRepository;
import com.alexis.domain.usecase.SearchUseCase;
import com.alexis.infrastructure.searchretrofit.SearchApi;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class MainModule {

    @Provides
    SearchRepository provideSearchRepository(){
        return new SearchApi("https://api.mercadolibre.com");
    }

    @Provides
    SearchUseCase provideSearchUseCase(SearchRepository searchRepository){
        return new SearchUseCase(searchRepository);
    }

}
