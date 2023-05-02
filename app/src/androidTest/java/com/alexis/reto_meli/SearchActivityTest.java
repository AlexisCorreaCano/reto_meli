package com.alexis.reto_meli;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.alexis.reto_meli.view.search.SearchActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class SearchActivityTest extends BaseTest{
    CountDownLatch latch = new CountDownLatch(1);
    @Rule
    public ActivityScenarioRule<SearchActivity> activityTestRule =
            new ActivityScenarioRule<SearchActivity>(SearchActivity.class);

    @Test
    public void searchItem_itemNameOk_shouldNoDisplayError() throws InterruptedException {

        //Arrange
        String itemToSearch = "cama";

        //Act
        enterText(R.id.et_search,itemToSearch);
        enterClick(R.id.et_search);
        latch.await(5, TimeUnit.SECONDS);

        //Assert
        verifyNoDisplay(R.id.progress_bar);
        verifyNoDisplay(R.id.container_no_found);
    }

    @Test
    public void searchItem_itemNameOk_shouldDisplayProgressBar() throws InterruptedException {
        //Arrange
        String itemToSearch = "cama";

        //Act
        enterText(R.id.et_search,itemToSearch);
        enterClick(R.id.et_search);
        latch.await(1, TimeUnit.SECONDS);

        //Assert
        verifyDisplay(R.id.progress_bar);
    }

    @Test
    public void searchItem_itemNameBad_shouldDisplayNoFound() throws InterruptedException {
        //Arrange
        String itemToSearch = "sdfgsdfgsdfgdsgsdf";

        //Act
        enterText(R.id.et_search,itemToSearch);
        enterClick(R.id.et_search);
        latch.await(5, TimeUnit.SECONDS);

        //Assert
        verifyDisplay(R.id.container_no_found);
    }
    @Test
    public void searchItem_itemNameOk_shouldShowDetail() throws InterruptedException {
        //Arrange
        String itemToSearch = "cama";

        //Act
        enterText(R.id.et_search,"cama");
        enterClick(R.id.et_search);
        latch.await(5, TimeUnit.SECONDS);
        selectItem(R.id.rv_list_item,0);
        latch.await(1, TimeUnit.SECONDS);

        //Assert
        verifyDisplay(R.id.tv_title);
    }

    @Test
    public void searchItem_withoutItemName_shouldShowError() throws InterruptedException {
        //Arrange
        String itemToSearch = "cama";

        //Act
        enterText(R.id.et_search,"");
        enterClick(R.id.et_search);
        latch.await(2, TimeUnit.SECONDS);

        //Assert
        verifyDisplay(R.id.tv_error_message);
    }
}
