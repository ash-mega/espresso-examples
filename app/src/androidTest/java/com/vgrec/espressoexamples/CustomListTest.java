/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 9:30 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.CustomListActivity;

import org.junit.Rule;
import org.junit.Test;

import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookAuthor;
import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookId;
import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookTitle;

public class CustomListTest extends BaseTest {
    
    private static final String BOOK_TITLE = "Java Concurrency in Practice";
    
    private static final String BOOK_AUTHOR = "Brian Goetz";
    
    @Rule
    public ActivityTestRule<CustomListActivity> rule = new ActivityTestRule<>(CustomListActivity.class);
    
    @Test
    public void testOpenBookById() {
        // Click on the Book with ID 5
        clickViewWithData(withBookId(5));
        // Check the correct book title is displayed
        checkViewWithIdByText(R.id.book_title,BOOK_TITLE);
        // Check the correct author is displayed
        checkViewWithIdByText(R.id.book_author,BOOK_AUTHOR);
    }
    
    @Test
    public void testOpenBookByTitleAndAuthor() {
        // Match a book with a specific title and author name
        clickViewWithData(matchesAllConditions(withBookId(5),withBookTitle(BOOK_TITLE),withBookAuthor(BOOK_AUTHOR)));
        // Check the correct book title is displayed
        checkViewWithIdByText(R.id.book_title,BOOK_TITLE);
        // Check the correct author is displayed
        checkViewWithIdByText(R.id.book_author,BOOK_AUTHOR);
    }
    
    @Test
    public void testClickOnBookByPosition() {
        clickViewOnPosition(5);
        // Check the correct book title is displayed
        checkViewWithIdByText(R.id.book_title,BOOK_TITLE);
        // Check the correct author is displayed
        checkViewWithIdByText(R.id.book_author,BOOK_AUTHOR);
    }
}
