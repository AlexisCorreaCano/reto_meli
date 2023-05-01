package com.alexis.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.alexis.domain.model.ResponseModel;
import com.alexis.infrastructure.searchretrofit.SearchApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RunWith(JUnit4.class)
public class SearchApiTest {

    private SearchApi searchApi;

    @Test
    public void searchItem_itemGood_shouldReturnResponseModelWithResult() throws InterruptedException {
        //Arrange
        searchApi = new SearchApi("https://api.mercadolibre.com");
        final ResponseModel[] responseModelExpected = {null};
        final Throwable[] throwableExpected = {null};
        CountDownLatch latch = new CountDownLatch(1);
        Consumer<ResponseModel> responseModelConsumer = new Consumer<ResponseModel>() {
            @Override
            public void accept(ResponseModel responseModel) {
                responseModelExpected[0] = responseModel;
            }
        };
        Consumer<Throwable> throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                throwableExpected[0] = throwable;
            }
        };
        String itemToSearch = "camara";

        //Act
        searchApi.searchItem(itemToSearch,responseModelConsumer,throwableConsumer);
        latch.await(5, TimeUnit.SECONDS);

        //Assert
        assertNotNull(responseModelExpected[0]);
    }

    @Test
    public void searchItem_withNonexistentItem_shouldReturnResponseModelWithOutResult() throws InterruptedException {
        //Arrange
        searchApi = new SearchApi("https://api.mercadolibre.com");
        final ResponseModel[] responseModelExpected = {null};
        final Throwable[] throwableExpected = {null};
        CountDownLatch latch = new CountDownLatch(1);
        Consumer<ResponseModel> responseModelConsumer = new Consumer<ResponseModel>() {
            @Override
            public void accept(ResponseModel responseModel) {
                responseModelExpected[0] = responseModel;
            }
        };
        Consumer<Throwable> throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                throwableExpected[0] = throwable;
            }
        };
        String itemToSearch = "dsgsdfgsdfg";

        //Act
        searchApi.searchItem(itemToSearch,responseModelConsumer,throwableConsumer);
        latch.await(5, TimeUnit.SECONDS);

        //Assert
        assertTrue(responseModelExpected[0].results.isEmpty());
    }

    @Test
    public void searchItem_withBadApi_shouldReturnThrowable() throws InterruptedException {
        //Arrange
        searchApi = new SearchApi("https://api.ercadolibre.com");
        final ResponseModel[] responseModelExpected = {null};
        final Throwable[] throwableExpected = {null};
        CountDownLatch latch = new CountDownLatch(1);
        Consumer<ResponseModel> responseModelConsumer = new Consumer<ResponseModel>() {
            @Override
            public void accept(ResponseModel responseModel) {
                responseModelExpected[0] = responseModel;
            }
        };
        Consumer<Throwable> throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                throwableExpected[0] = throwable;
            }
        };
        String itemToSearch = "dsgsdfgsdfg";

        //Act
        searchApi.searchItem(itemToSearch,responseModelConsumer,throwableConsumer);
        latch.await(5, TimeUnit.SECONDS);

        //Assert
        assertNotNull(throwableExpected[0]);
    }
}
