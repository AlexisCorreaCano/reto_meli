package com.alexis.reto_meli;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.alexis.reto_meli.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class MainActivityTest extends BaseTest{

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void searchItem_itemNameOk_shouldListItems() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        enterText(R.id.et_search,"cama");
        enterClick(R.id.et_search);

        latch.await(5, TimeUnit.SECONDS);

        verifyDisplay(R.id.rv_list_item);
    }
}
