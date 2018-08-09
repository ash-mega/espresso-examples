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
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.vgrec.espressoexamples.tool.PickerActions;
import com.vgrec.espressoexamples.tool.RecyclerViewActions;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

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
    
    ViewInteraction onViewId(int id) {
        return Espresso.onView(withId(id));
    }
    
    ViewInteraction onViewText(String text) {
        return Espresso.onView(withText(text));
    }
    
    //********************************** Matcher ****************************************/
    org.hamcrest.Matcher<Object> matchesAllConditions(org.hamcrest.Matcher<Object>... matchers) {
        return org.hamcrest.core.AllOf.allOf(matchers);
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
    
    ViewAction pressBack() {
        return ViewActions.pressBack();
    }
    
    ViewAction scrollTo() {
        return ViewActions.scrollTo();
    }
    
    ViewAction closeSoftKeyboard() {
        return ViewActions.closeSoftKeyboard();
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
        onViewId(id).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
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
        onView(isAssignableFrom(EditText.class)).perform(typeText(textToSearch), pressImeActionButton());
    }
    
    //********************************** Check ****************************************/
    void checkViewWithIdByText(int id,String expectedText) {
        onView(withId(id)).check(matches(withText(expectedText)));
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
    
    void checkView(ViewInteraction view,ViewAssertion condition) {
        view.check(condition);
    }
    
    void checkViewMatches(ViewInteraction view,Matcher<View> condition) {
        view.check(matches(condition));
    }
    
    void checkView(DataInteraction data,ViewAssertion condition) {
        data.check(condition);
    }
    
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
