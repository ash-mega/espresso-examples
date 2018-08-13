/*
 * android2
 * mega.privacy.android.app.espresso.core
 *
 * Created by Ash Wu on 13/08/18 11:44 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matcher;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static com.vgrec.espressoexamples.core.Actions.clearText;
import static com.vgrec.espressoexamples.core.Actions.click;
import static com.vgrec.espressoexamples.core.Actions.pressImeActionButton;
import static com.vgrec.espressoexamples.core.Actions.setDate;
import static com.vgrec.espressoexamples.core.Actions.setTime;
import static com.vgrec.espressoexamples.core.Actions.swipeLeft;
import static com.vgrec.espressoexamples.core.Actions.swipeRight;
import static com.vgrec.espressoexamples.core.Matchers.DataMatchers.matchesItemText;
import static com.vgrec.espressoexamples.core.Matchers.DataMatchers.matchesType;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesAssignableFrom;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesChecked;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesDisplayed;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesHintId;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesId;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesItem;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesNotChecked;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesNotDisplayed;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesText;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesTextId;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.notMatches;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.withDecorView;
import static com.vgrec.espressoexamples.core.TargetElements.DataElements.onData;
import static com.vgrec.espressoexamples.core.TargetElements.DataElements.onPosition;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onSearchBar;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onView;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onViewId;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onViewText;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onViewTextId;
import static com.vgrec.espressoexamples.core.TargetElements.ViewElements.onViewType;
import static com.vgrec.espressoexamples.tool.CustomMatchers.withAdaptedData;

public class TestHelper {
    
    protected Context getContext() {
        return InstrumentationRegistry.getContext();
    }
    
    protected void sleep1s() {
        sleep(1000);
    }
    
    protected void sleep2s() {
        sleep(2000);
    }
    
    protected void sleep3s() {
        sleep(3000);
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    protected void clickItemInDisplayedAdapterView(Matcher<Object>... matchers) {
        onData(matchesAllConditions(matchers)).
                inAdapterView(Matchers.ViewMatchers.matchesAllConditions(matchesAssignableFrom(AdapterView.class),matchesDisplayed()))
                .perform(click());
    }
    
    protected void openActionBarOverflowOrOptionsMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(getContext());
    }
    
    protected void closeSoftKeyboard() {
        Espresso.closeSoftKeyboard();
    }
    
    protected void pressBack() {
        Espresso.pressBack();
    }
    
    protected void clickViewWithId(int id) {
        onViewId(id).perform(click());
    }
    
    protected void clickViewWithIdAndDismissKeyboard(int id) {
        clickViewWithId(id);
        closeSoftKeyboard();
    }
    
    protected void clickRecyclerViewOnPosition(int id,int position) {
        onViewId(id).perform(Actions.clickRecyclerViewOnPosition(position));
    }
    
    protected void clickRecyclerViewOnItemWithText(int id,String text) {
        onViewId(id).perform(Actions.clickRecyclerViewOnItemWithText(text));
    }
    
    protected void clickViewWithData(Matcher<Object> dataMatcher) {
        onData(dataMatcher).perform(click());
    }
    
    protected void clickViewWithText(String text) {
        onViewText(text).perform(click());
    }
    
    protected void clickViewWithTextId(int resourceId) {
        onViewTextId(resourceId).perform(click());
    }
    
    protected void clickViewOnPosition(int position) {
        onPosition(position).perform(click());
    }
    
    protected void clickViewOnItemWithText(String text) {
        onData(matchesItemText(text)).perform(click());
    }
    
    protected void datePickerSetDate(int year,int monthOfYear,int dayOfMonth) {
        onViewType(DatePicker.class).perform(setDate(year,monthOfYear,dayOfMonth));
    }
    
    protected void timePickerSetTime(int hours,int minutes) {
        onViewType(TimePicker.class).perform(setTime(hours,minutes));
    }
    
    protected void typeText(int id,String text) {
        onViewId(id).perform(Actions.typeText(text));
    }
    
    protected void resetTextField(int id) {
        onViewId(id).perform(clearText());
    }
    
    protected void doSearch(String textToSearch) {
        onSearchBar().perform(Actions.typeText(textToSearch),pressImeActionButton());
    }
    
    protected void clearSearchBar() {
        onSearchBar().perform(clearText());
    }
    
    protected void inputSearchBar(String input) {
        onSearchBar().perform(Actions.typeText(input));
    }
    
    private void checkViewWithIdByText(int id,String toCheck,boolean isMatch) {
        if (isMatch) {
            onViewId(id).check(matches(matchesText(toCheck)));
        } else {
            onViewId(id).check(matches(notMatches(matchesText(toCheck))));
        }
    }
    
    protected void checkViewWithIdMatchesText(int id,String expectedText) {
        checkViewWithIdByText(id,expectedText,true);
    }
    
    protected void checkViewWithIdNotMatchesText(int id,String expectedText) {
        checkViewWithIdByText(id,expectedText,false);
    }
    
    private void checkViewWithIdByTextId(int id,int expectedTextId,boolean isMatch) {
        if (isMatch) {
            onViewId(id).check(matches(matchesTextId(expectedTextId)));
        } else {
            onViewId(id).check(matches(notMatches(matchesTextId(expectedTextId))));
        }
    }
    
    protected void checkViewWithIdMatchesTextId(int id,int expectedTextId) {
        checkViewWithIdByTextId(id,expectedTextId,true);
    }
    
    protected void checkViewWithIdNotMatchesTextId(int id,int expectedTextId) {
        checkViewWithIdByTextId(id,expectedTextId,false);
    }
    
    private void checkViewWithIdByDisplayed(int id,boolean isDisplayed) {
        if (isDisplayed) {
            onViewId(id).check(matches(matchesDisplayed()));
        } else {
            onViewId(id).check(matches(matchesNotDisplayed()));
        }
    }
    
    protected void checkViewWithIdMatchesDisplayed(int id) {
        checkViewWithIdByDisplayed(id,true);
    }
    
    protected void checkViewWithIdMatchesNotDisplayed(int id) {
        checkViewWithIdByDisplayed(id,false);
    }
    
    private void checkViewWithTextByDisplayed(String text,boolean isDisplayed) {
        if (isDisplayed) {
            onViewText(text).check(matches(matchesDisplayed()));
        } else {
            onViewText(text).check(matches(matchesNotDisplayed()));
        }
    }
    
    private void isCheckBoxChecked(int id,boolean checked) {
        if (checked) {
            onViewId(id).check(matches(matchesChecked()));
        } else {
            onViewId(id).check(matches(matchesNotChecked()));
        }
    }
    
    protected void checkBoxChecked(int id) {
        isCheckBoxChecked(id,true);
    }
    
    protected void checkBoxNotChecked(int id) {
        isCheckBoxChecked(id,false);
    }
    
    protected void checkViewWithTextMatchesDisplayed(String text) {
        checkViewWithTextByDisplayed(text,true);
    }
    
    protected void checkViewWithTextMatchesNotDisplayed(String text) {
        checkViewWithTextByDisplayed(text,false);
    }
    
    private void checkItemDisplayed(DataInteraction item) {
        item.check(matches(matchesDisplayed()));
    }
    
    protected void checkView(ViewInteraction view,ViewAssertion condition) {
        view.check(condition);
    }
    
    protected void checkViewMatches(ViewInteraction view,Matcher<View> condition) {
        view.check(matches(condition));
    }
    
    protected void checkData(DataInteraction data,ViewAssertion condition) {
        data.check(condition);
    }
    
    protected void checkItemWithTextNotInSpinner(int spinnerId,String textToCheck) {
        onViewId(spinnerId).check(matches(notMatches(matchesItem(matchesItemText(textToCheck)))));
    }
    
    protected void checkItemWithTextInSpinner(int spinnerId,String textToCheck) {
        onViewId(spinnerId).check(matches(matchesItem(matchesItemText(textToCheck))));
    }
    
    protected Matcher<Object> matchesAllConditions(Matcher<Object>... matchers) {
        return Matchers.DataMatchers.matchesAllConditions(matchers);
    }
    
    private void checkViewWithTextIdByDisplayed(int resourceId,boolean isDisplayed) {
        if (isDisplayed) {
            onViewTextId(resourceId).check(matches(matchesDisplayed()));
        } else {
            onViewTextId(resourceId).check(matches(matchesNotDisplayed()));
        }
    }
    
    protected void checkViewWithTextIdMatchesDisplayed(int resourceId) {
        checkViewWithTextIdByDisplayed(resourceId,true);
    }
    
    protected void checkViewWithTextIdMatchesNotDisplayed(int resourceId) {
        checkViewWithTextIdByDisplayed(resourceId,false);
    }
    
    protected void checkViewWithIdMatchesHintId(int id,int hintId) {
        onViewId(id).check(matches(matchesHintId(hintId)));
    }
    
    protected void checkItemWithTextDisplayed(String expectedText) {
        checkItemDisplayed(onData(matchesAllConditions(matchesType(String.class),matchesItemText(expectedText))));
    }
    
    private ViewInteraction onViewMatchesAllConditions(Matcher<View>... viewMatchers) {
        return onView(viewMatchers);
    }
    
    protected ViewInteraction onViewIdDisplayed(int id) {
        return onViewMatchesAllConditions(matchesId(id),matchesDisplayed());
    }
    
    private ViewInteraction getSearchSuggestion(String suggestion,ActivityTestRule rule) {
        return onViewText(suggestion).inRoot(withDecorView(notMatches(Matchers.ViewMatchers.is(rule.getActivity().getWindow().getDecorView()))));
    }
    
    protected Matcher<View> onText(String text) {
        return matchesText(text);
    }
    
    protected void checkSearchSuggestion(String suggestion,ActivityTestRule rule) {
        getSearchSuggestion(suggestion,rule).check(matches(matchesDisplayed()));
    }
    
    protected void clickSearchSuggestion(String suggestion,ActivityTestRule rule) {
        getSearchSuggestion(suggestion,rule).perform(click());
    }
    
    protected void viewPagerswipeLeft(int id) {
        onViewId(id).perform(swipeLeft());
    }
    
    protected void viewPagerswipeRight(int id) {
        onViewId(id).perform(swipeRight());
    }
}
