package com.alexis.reto_meli;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import android.view.KeyEvent;

import androidx.test.espresso.contrib.RecyclerViewActions;

public class BaseTest {
    protected static void clickButton(int resourceId){
        onView(withId(resourceId))
                .perform(click());
    }

    protected static void enterText(int resourceId,String text){
        onView(withId(resourceId))
                .perform(replaceText(text));
    }
    protected static void verifyDisplay(int resourceId){
        onView(withId(resourceId))
                .check(matches(isDisplayed()));
    }

    protected static void verifyNoDisplay(int resourceId){
        onView(withId(resourceId))
                .check(matches(not(isDisplayed())));
    }
    protected static void enterClick(int resourceId){
        onView(withId(resourceId)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
    }

    protected static void selectItem(int resourceId, int itemPosition){
        onView(withId(resourceId)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
