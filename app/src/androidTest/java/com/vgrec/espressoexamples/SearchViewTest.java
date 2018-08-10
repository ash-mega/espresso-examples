/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 5:11 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.vgrec.espressoexamples.activities.SearchViewActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;

public class SearchViewTest extends BaseTest {
    
    public static final String HELSINKI = "Helsinki";
    
    @Rule
    public ActivityTestRule<SearchViewActivity> rule = new ActivityTestRule<>(SearchViewActivity.class);
    
    @Test
    public void testItemNotFound() {
        // Click on the search icon
        clickViewWithId(R.id.action_search);
        sleep2s();
        // Type the text in the search field and submit the query
        doSearch("No such item");
        sleep2s();
        // Check the empty view is displayed
        checkViewWithIdByDisplayed(R.id.empty_view);
    }
    
    @Test
    public void testItemFound() {
        clickViewWithId(R.id.action_search);
        doSearch(HELSINKI);
        // Check empty view is not displayed
        checkViewWithIdByNotDisplayed(R.id.empty_view);
        
        // Check the item we are looking for is in the search result list.
        checkItemWithTextDisplayed(HELSINKI);
    }
    
    @Test
    public void testSearchSuggestionDisplayed() {
        clickViewWithId(R.id.action_search);
        doSearch(HELSINKI);
        
        // Go back to previous screen
        pressBack();
        
        // Clear the text in search field
        clearSearchBar();
        
        // Enter the first letter of the previously searched word
        inputSearchBar("He");
        sleep3s();
        // Check the search suggestions appear
        onViewText(HELSINKI)
                .inRoot(withDecorView(not(Matchers.is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
    
    @Test
    public void testClickOnSearchSuggestion() {
        clickViewWithId(R.id.action_search);
        doSearch(HELSINKI);
        
        // Go back to previous screen
        pressBack();
        
        // Clear the text in search field
        clearSearchBar();
        
        // Enter the first letter of the previously searched word
        inputSearchBar("He");
        // Click on the "Java" item from the suggestions list
        //TODO If don't close it then can't perform click.
        closeSoftKeyboard();
        onView(withText(HELSINKI))
                .inRoot(withDecorView(not(Matchers.is(rule.getActivity().getWindow().getDecorView()))))
                .perform(click());
        
        // Check the item appears in search results list.
        checkItemWithTextDisplayed(HELSINKI);
    }
}
