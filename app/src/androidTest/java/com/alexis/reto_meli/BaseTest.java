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

import android.view.KeyEvent;

public class BaseTest {
    protected static void clickButton(int resourceId){
        onView(withId(resourceId))
                .perform(click());
    }

    protected static void clickButton(String text){
        onView(withText(text))
                .perform(click());
    }

    protected static void enterText(int resourceId,String text){
        onView(withId(resourceId))
                .perform(replaceText(text));
    }

    protected static void verifyTextDisplay(int resourceId,String text){
        onView(allOf(withId(resourceId),withText(text)))
                .check(matches(isDisplayed()));
    }

    protected static void verifyTextDisplay(String resourceName){
        onView(allOf(withResourceName(resourceName)))
                .check(matches(isDisplayed()));
    }
    protected static void verifyDisplay(int resourceId){
        onView(withId(resourceId))
                .check(matches(isDisplayed()));
    }

    protected static void enterClick(int resourceId){
        onView(withId(resourceId)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
    }
}
