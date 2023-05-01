package com.alexis.domain;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.alexis.domain.model.ResponseModel;
import com.alexis.domain.repository.SearchRepository;
import com.alexis.domain.usecase.SearchUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Consumer;

@RunWith(JUnit4.class)
public class SearchUseCaseTest {

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private ResponseModel responseModel;

    private SearchUseCase searchUseCase;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        searchUseCase = new SearchUseCase(searchRepository);
    }

    @Test
    public void searchItem_itemEmpty_shouldReturnException(){
        //Arrange
        Exception exceptionExpected = null;
        try {
            //Act
            searchUseCase.searchItem("",null,null);
        }catch (Exception e){
            exceptionExpected = e;
        }finally {
            //Assert
            assertNotNull(exceptionExpected);
        }
    }

    @Test
    public void searchItem_onSuccessNull_shouldReturnNullPointerException(){
        //Arrange
        doThrow(new NullPointerException()).when(searchRepository).searchItem("cama",null,null);
        NullPointerException exceptionExpected = null;
        try {
            //Act
            searchUseCase.searchItem("cama",null,null);
        }catch (NullPointerException e){
            exceptionExpected = e;
        }finally {
            //Assert
            assertNotNull(exceptionExpected);
        }
    }

    @Test
    public void searchItem_allDataGood_shouldReturnResponseModelWithResult(){
        //Arrange
        ResponseModel responseModelExpected = mock(ResponseModel.class);
        Consumer<ResponseModel> responseModelConsumer = mock(Consumer.class);
        Consumer<Throwable> throwableConsumer = mock(Consumer.class);
        String itemToSearch = "cama";
        doAnswer((value)->{
            Consumer<ResponseModel> responseModelC = value.getArgument(1);
            responseModelC.accept(responseModelExpected);
            return null;
        }).when(searchRepository).searchItem(itemToSearch,responseModelConsumer,throwableConsumer);

        //Act
        searchUseCase.searchItem(itemToSearch,responseModelConsumer,throwableConsumer);

        //Assert
        verify(responseModelConsumer).accept(responseModelExpected);
    }

    @Test
    public void searchItem_withData_shouldReturnThowablle(){
        //Arrange
        Throwable throwableExpected = mock(Throwable.class);
        Consumer<ResponseModel> responseModelConsumer = mock(Consumer.class);
        Consumer<Throwable> throwableConsumer = mock(Consumer.class);
        String itemToSearch = "cama";
        doAnswer((value)->{
            Consumer<Throwable> responseModelC = value.getArgument(2);
            responseModelC.accept(throwableExpected);
            return null;
        }).when(searchRepository).searchItem(itemToSearch,responseModelConsumer,throwableConsumer);

        //Act
        searchUseCase.searchItem(itemToSearch,responseModelConsumer,throwableConsumer);

        //Assert
        verify(throwableConsumer).accept(throwableExpected);
    }
}
