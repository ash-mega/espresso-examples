/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 8:56 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.vgrec.espressoexamples.tool.PickerActions;
import com.vgrec.espressoexamples.tool.RecyclerViewActions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class BaseTest {
    
    //********************************** General ****************************************/
    Context getContext() {
        return InstrumentationRegistry.getContext();
    }
    
    void openActionBarOverflowOrOptionsMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(getContext());
    }
    
    //********************************** Interaction ****************************************/
    DataInteraction onData(Matcher<?> dataMatcher) {
        return Espresso.onData(dataMatcher);
    }
    
    DataInteraction onPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be >= 0");
        }
        return Espresso.onData(anything()).atPosition(position);
    }
    
    ViewInteraction onView(Matcher<View> viewMatcher) {
        return Espresso.onView(viewMatcher);
    }
    
    ViewInteraction onView(Matcher<View>... viewMatchers) {
        return Espresso.onView(allOf(viewMatchers));
    }
    
    ViewInteraction onViewId(int id) {
        return Espresso.onView(withId(id));
    }
    
    ViewInteraction onViewText(String text) {
        return Espresso.onView(withText(text));
    }
    
    ViewInteraction getSingleSearchBar() {
        return onView(isAssignableFrom(EditText.class));
    }
    
    //********************************** Matcher ****************************************/
    org.hamcrest.Matcher<Object> matchesAllConditions(org.hamcrest.Matcher<Object>... matchers) {
        return org.hamcrest.Matchers.allOf(matchers);
    }
    
    org.hamcrest.Matcher<View> matchesAllConditionsView(org.hamcrest.Matcher<View>... matchers) {
        return org.hamcrest.Matchers.allOf(matchers);
    }
    
    org.hamcrest.Matcher<Object> isType(Class<?> type) {
        return is(instanceOf(type));
    }
    
    Matcher<Object> withItemText(String expectedText) {
        final Matcher<String> itemTextMatcher = equalTo(expectedText);
        return new BoundedMatcher<Object, String>(String.class) {
            @Override
            public boolean matchesSafely(String text) {
                return itemTextMatcher.matches(text);
            }
            
            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }
    
    /**
     * Finds the AdapterView and let another Matcher interrogate the data within it.
     */
    Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {
            
            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }
            
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                Adapter adapter = ((AdapterView)view).getAdapter();
                for (int i = 0;i < adapter.getCount();i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
    
    Matcher<View> withId(int id) {
        return ViewMatchers.withId(id);
    }
    
    Matcher<View> withText(String text) {
        return ViewMatchers.withText(text);
    }
    
    Matcher<View> withTextId(int id) {
        return ViewMatchers.withText(id);
    }
    
    Matcher<View> withClass(Class<?> cls) {
        return ViewMatchers.withClassName(Matchers.equalTo(cls.getName()));
    }
    
    Matcher<View> isDisplayed() {
        return ViewMatchers.isDisplayed();
    }
    
    Matcher<View> isNotDisplayed() {
        return not(ViewMatchers.isDisplayed());
    }
    
    Matcher<View> withHint(int resourceId) {
        return ViewMatchers.withHint(resourceId);
    }
    
    Matcher<View> isAssignableFrom(Class<? extends View> cls) {
        return ViewMatchers.isAssignableFrom(cls);
    }
    
    Matcher<View> withHint(String hint) {
        return ViewMatchers.withHint(hint);
    }
    
    //********************************** Action ****************************************/
    ViewAction click() {
        return ViewActions.click();
    }
    
    ViewAction longClick() {
        return ViewActions.longClick();
    }
    
    ViewAction typeText(String text) {
        return ViewActions.typeText(text);
    }
    
    ViewAction clearText() {
        return ViewActions.clearText();
    }
    
    void pressBack() {
        Espresso.pressBack();
    }
    
    ViewAction scrollTo() {
        return ViewActions.scrollTo();
    }
    
    void closeSoftKeyboard() {
        Espresso.closeSoftKeyboard();
    }
    
    ViewAction swipeDown() {
        return ViewActions.swipeDown();
    }
    
    ViewAction swipeLeft() {
        return ViewActions.swipeLeft();
    }
    
    ViewAction swipeRight() {
        return ViewActions.swipeRight();
    }
    
    ViewAction swipeUp() {
        return ViewActions.swipeUp();
    }
    
    ViewAction setDate(int year,int monthOfYear,int dayOfMonth) {
        return PickerActions.setDate(year,monthOfYear,dayOfMonth);
    }
    
    ViewAction setTime(int hours,int minutes) {
        return PickerActions.setTime(hours,minutes);
    }
    
    ViewAction pressImeActionButton() {
        return ViewActions.pressImeActionButton();
    }
    
    //********************************** Assertion ****************************************/
    ViewAssertion matches(Matcher<View> viewMatcher) {
        return ViewAssertions.matches(viewMatcher);
    }
    
    ViewAssertion doesNotExist() {
        return ViewAssertions.doesNotExist();
    }
    
    //********************************** Perform ****************************************/
    void clickRecyclerViewOnPosition(int id,int position) {
        onViewId(id).perform(RecyclerViewActions.actionOnItemAtPosition(position,click()));
    }
    
    void clickRecyclerViewOnItemWithText(int id,String text) {
        onViewId(id).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(text)),click()));
    }
    
    void clickViewWithId(int id) {
        onViewId(id).perform(click());
    }
    
    void clickViewWithData(Matcher<Object> dataMatcher) {
        onData(dataMatcher).perform(click());
    }
    
    void clickViewWithText(String text) {
        onViewText(text).perform(click());
    }
    
    void clickViewOnPosition(int position) {
        onPosition(position).perform(click());
    }
    
    void clickViewOnItemWithText(String text) {
        onData(allOf(is(instanceOf(String.class)), is(text))).perform(click());
    }
    
    void datePickerSetDate(int year,int monthOfYear,int dayOfMonth) {
        onView(withClass(DatePicker.class)).perform(setDate(year,monthOfYear,dayOfMonth));
    }
    
    void timePickerSetTime(int hours,int minutes) {
        onView(withClass(TimePicker.class)).perform(setTime(hours,minutes));
    }
    
    void typeText(int id,String text) {
        onViewId(id).perform(typeText(text));
    }
    
    void doSearch(String textToSearch) {
        onView(isAssignableFrom(EditText.class)).perform(typeText(textToSearch),pressImeActionButton());
    }
    
    void clearSearchBar() {
        getSingleSearchBar().perform(clearText());
    }
    
    void inputSearchBar(String input) {
        getSingleSearchBar().perform(typeText(input));
    }
    
    //********************************** Check ****************************************/
    void checkViewWithIdByText(int id,String expectedText) {
        checkViewWithIdByText(id,expectedText,true);
    }
    
    void checkViewWithIdByTextId(int id,int expectedTextId) {
        onView(withId(id)).check(matches(withTextId(expectedTextId)));
    }
    
    void checkViewWithIdByDisplayed(int id) {
        checkViewWithIdByDisplayed(id,true);
    }
    
    void checkViewWithIdByNotDisplayed(int id) {
        checkViewWithIdByDisplayed(id,false);
    }
    
    void checkViewWithIdByDisplayed(int id,boolean checkDisplayed) {
        if (checkDisplayed) {
            onView(withId(id)).check(matches(isDisplayed()));
        } else {
            onView(withId(id)).check(matches(isNotDisplayed()));
        }
    }
    
    void checkViewWithTextByText(String text,String expectedText) {
        onView(withText(text)).check(matches(withText(expectedText)));
    }
    
    void checkViewWithTextByText(int textId,String expectedText) {
        onView(withTextId(textId)).check(matches(withText(expectedText)));
    }
    
    void checkViewWithTextByDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }
    
    void checkViewWithTextByDisplayed(int textId) {
        onView(withTextId(textId)).check(matches(isDisplayed()));
    }
    
    void checkViewWithIdByText(int id,String toCheck,boolean isMatch) {
        if (isMatch) {
            onView(withId(id)).check(matches(withText(toCheck)));
        } else {
            onView(withId(id)).check(matches(Matchers.not(withText(toCheck))));
        }
    }
    
    void checkItemDisplayed(DataInteraction item) {
        item.check(matches(isDisplayed()));
    }
    
    void checkItemDisplayed(org.hamcrest.Matcher<Object>... matchers) {
        checkItemDisplayed(onData(allOf(matchers)));
    }
    
    void checkItemWithTextDisplayed(String expectedText) {
        checkItemDisplayed(isType(String.class),withItemText(expectedText));
    }
    
    void checkView(ViewInteraction view,ViewAssertion condition) {
        view.check(condition);
    }
    
    void checkViewMatches(ViewInteraction view,Matcher<View> condition) {
        view.check(matches(condition));
    }
    
    void checkView(DataInteraction data,ViewAssertion condition) {
        data.check(condition);
    }
    
    void checkItemWithTextNotInAdapterView(int adapterViewId,String textToCheck) {
        onView(withId(adapterViewId)).check(matches(Matchers.not(withAdaptedData(withItemText(textToCheck)))));
    }
    
    void checkItemWithTextInAdapterView(int adapterViewId,String textToCheck) {
        onView(withId(adapterViewId)).check(matches(withAdaptedData(withItemText(textToCheck))));
    }
    
    //********************************** General ****************************************/
    void sleep1s() {
        sleep(1000);
    }
    
    void sleep2s() {
        sleep(2000);
    }
    
    void sleep3s() {
        sleep(3000);
    }
    
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
