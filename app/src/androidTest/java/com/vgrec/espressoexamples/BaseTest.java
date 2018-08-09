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
import android.view.ViewManager;

import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.anything;

class BaseTest {
    
    org.hamcrest.Matcher<Object> matchesAllConditions(org.hamcrest.Matcher<Object>... matchers) {
        return org.hamcrest.core.AllOf.allOf(matchers);
    }
    
    DataInteraction onData(Matcher<?> dataMatcher) {
        return Espresso.onData(dataMatcher);
    }
    
    DataInteraction onPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be >= 0");
        }
        return Espresso.onData(anything()).atPosition(position);
    }
    
    ViewInteraction onView(final Matcher<View> viewMatcher) {
        return Espresso.onView(viewMatcher);
    }
    
    Context getContext() {
        return InstrumentationRegistry.getContext();
    }
    
    void openActionBarOverflowOrOptionsMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(getContext());
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
    
    ViewAssertion matches(final Matcher<View> viewMatcher) {
        return ViewAssertions.matches(viewMatcher);
    }
    
    ViewAssertion doesNotExist() {
        return ViewAssertions.doesNotExist();
    }
    
    void clickViewWithId(int id) {
        onView(withId(id)).perform(click());
    }
    
    void clickViewWithData(Matcher<Object> dataMatcher) {
        onData(dataMatcher).perform(click());
    }
    
    void clickViewWithText(String text) {
        onView(withText(text)).perform(click());
    }
    
    void clickViewOnPosition(int position) {
        onPosition(position).perform(click());
    }
    
    void checkViewWithIdByText(int id,String expectedText) {
        onView(withId(id)).check(matches(withText(expectedText)));
    }
    
    void checkViewWithTextByText(String text,String expectedText) {
        onView(withText(text)).check(matches(withText(expectedText)));
    }
    
    void checkView(ViewInteraction view,ViewAssertion condition) {
        view.check(condition);
    }
    
    void checkView(DataInteraction data,ViewAssertion condition) {
        data.check(condition);
    }
    
    void sleep1s() {
        sleep(1000);
    }
    
    void slee2s() {
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
