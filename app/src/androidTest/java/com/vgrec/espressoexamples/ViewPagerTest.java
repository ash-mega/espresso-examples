package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;
import android.widget.AdapterView;

import com.vgrec.espressoexamples.activities.ViewPagerActivity;
import com.vgrec.espressoexamples.core.TestHelper;

import org.junit.Rule;
import org.junit.Test;

import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookAuthor;
import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookTitle;

public class ViewPagerTest extends TestHelper {
    
    private static final String BOOK_TITLE = "Clean Code";
    private static final String BOOK_AUTHOR = "Robert C. Martin";
    
    @Rule
    public ActivityTestRule<ViewPagerActivity> rule = new ActivityTestRule<>(ViewPagerActivity.class);
    
    @Test
    public void testAllTabDisplayedOnSwipe() {
        // Locate the ViewPager and perform a swipe left action
        viewPagerswipeLeft(R.id.pager);
        
        // Check the "ALL BOOKS" text is displayed
        checkViewMatches(onViewIdDisplayed(R.id.header_text),onText("ALL BOOKS"));
    }
    
    @Test
    public void testClickOnBookFromNewTab() {
        // The below commented out line will fail with AmbiguousViewMatcherException because the same ListView is used in both pages of ViewPager.
        // onData(allOf(withBookTitle(BOOK_TITLE), withBookAuthor(BOOK_AUTHOR))).perform(click());
        
        // We have to refine the query specifying that we are looking for an AdapterView that is currently visible.
        clickItemInDisplayedAdapterView(withBookTitle(BOOK_TITLE),withBookAuthor(BOOK_AUTHOR));
        // Check the correct book title is displayed
        checkViewWithIdMatchesText(R.id.book_title,BOOK_TITLE);
        
        // Check the correct author is displayed
        checkViewWithIdMatchesText(R.id.book_author,BOOK_AUTHOR);
    }
}
