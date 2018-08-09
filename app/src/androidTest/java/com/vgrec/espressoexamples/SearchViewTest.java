/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 5:11 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.SearchViewActivity;

import org.junit.Rule;
import org.junit.Test;

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
}
