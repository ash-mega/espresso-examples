package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;
import android.widget.AdapterView;

import com.vgrec.espressoexamples.activities.ViewPagerActivity;

import org.junit.Rule;
import org.junit.Test;

import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookAuthor;
import static com.vgrec.espressoexamples.tool.CustomMatchers.withBookTitle;
import static org.hamcrest.Matchers.allOf;

public class ViewPagerTest extends BaseTest {
    
    private static final String BOOK_TITLE = "Clean Code";
    private static final String BOOK_AUTHOR = "Robert C. Martin";
    
    @Rule
    public ActivityTestRule<ViewPagerActivity> rule = new ActivityTestRule<>(ViewPagerActivity.class);
    
    @Test
    public void testAllTabDisplayedOnSwipe() {
        // Locate the ViewPager and perform a swipe left action
        onViewId(R.id.pager).perform(swipeLeft());
        
        // Check the "ALL BOOKS" text is displayed
        checkView(onView(withId(R.id.header_text),isDisplayed()),matches(withText("ALL BOOKS")));
    }
    
    @Test
    public void testClickOnBookFromNewTab() {
        // The below commented out line will fail with AmbiguousViewMatcherException because the same ListView is used in both pages of ViewPager.
        // onData(allOf(withBookTitle(BOOK_TITLE), withBookAuthor(BOOK_AUTHOR))).perform(click());
        
        // We have to refine the query specifying that we are looking for an AdapterView that is currently visible.
        onData(allOf(withBookTitle(BOOK_TITLE),withBookAuthor(BOOK_AUTHOR)))
                .inAdapterView(allOf(isAssignableFrom(AdapterView.class),isDisplayed()))
                .perform(click());
        // Check the correct book title is displayed
        checkViewWithIdByText(R.id.book_title,BOOK_TITLE);
        
        // Check the correct author is displayed
        checkViewWithIdByText(R.id.book_author,BOOK_AUTHOR);
    }
}
