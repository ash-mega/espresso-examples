/*
 * EspressoExamples
 * com.vgrec.espressoexamples.espresso
 *
 * Created by Ash Wu on 8/08/18 4:50 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vgrec.espressoexamples.R;
import com.vgrec.espressoexamples.activities.ActionBarExampleActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActionBarExampleTest {
    
    @Rule
    public ActivityTestRule<ActionBarExampleActivity> mActivityRule = new ActivityTestRule<>(ActionBarExampleActivity.class);
    
    @Test
    public void testClickOnMenuItem() {
        // Click on an item from ActionBar
        onView(withId(R.id.action_settings)).perform(click());
        // Verify the correct item was clicked by checking the content of the status TextView
        onView(withId(R.id.status)).check(matches(withText("Settings")));
        
    }
    
    @Test
    public void testOverflowMenuOrOptionsMenu() {
        sleep();
        // Open the action bar overflow or options menu (depending if the device has or not a hardware menu button.)
        openActionBarOverflowOrOptionsMenu(getContext());
        sleep();
        // Find the menu item with text "About" and click on it
        onView(withText("About")).perform(click());
        sleep();
        // Verify the correct item was clicked by checking the content of the status TextView
        onView(withId(R.id.status)).check(matches(withText("About")));
    }
    
    private void sleep() {
        sleep(1000);
    }
    
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

