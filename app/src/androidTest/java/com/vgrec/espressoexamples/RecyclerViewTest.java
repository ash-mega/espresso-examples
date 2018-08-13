/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 3:54 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.RecyclerViewActivity;
import com.vgrec.espressoexamples.core.TestHelper;

import org.junit.Rule;
import org.junit.Test;

public class RecyclerViewTest extends TestHelper {
    
    private static final String BOOK_TITLE = "Effective Java";
    private static final String BOOK_AUTHOR = "Joshua Bloch";
    
    @Rule
    public ActivityTestRule<RecyclerViewActivity> rule = new ActivityTestRule<>(RecyclerViewActivity.class);
    
    @Test
    public void testClickAtPosition() {
        // Perform a click on first element in the RecyclerView
        clickRecyclerViewOnPosition(R.id.recyclerView,3);
        checkViewWithIdMatchesText(R.id.book_title,BOOK_TITLE);
        checkViewWithIdMatchesText(R.id.book_author,BOOK_AUTHOR);
    }
    
    @Test
    public void testClickOnViewInRow() {
        // Perform click on an element with a specific text
        clickRecyclerViewOnItemWithText(R.id.recyclerView,BOOK_TITLE);
        checkViewWithIdMatchesText(R.id.book_title,BOOK_TITLE);
    }
}
