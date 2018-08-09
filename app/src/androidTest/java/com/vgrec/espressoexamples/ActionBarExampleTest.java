/*
 * EspressoExamples
 * com.vgrec.espressoexamples.espresso
 *
 * Created by Ash Wu on 8/08/18 4:50 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.ActionBarExampleActivity;

import org.junit.Rule;
import org.junit.Test;

public class ActionBarExampleTest extends BaseTest {
    
    @Rule
    public ActivityTestRule<ActionBarExampleActivity> rule = new ActivityTestRule<>(ActionBarExampleActivity.class);
    
    
    @Test
    public void testClickOnMenuItem() {
        // Click on an item from ActionBar
        clickViewWithId(R.id.action_settings);
        // Verify the correct item was clicked by checking the content of the status TextView
        checkViewWithIdByText(R.id.status,"Settings");
    }
    
    @Test
    public void testOverflowMenuOrOptionsMenu() {
        // Open the action bar overflow or options menu (depending if the device has or not a hardware menu button.)
        openActionBarOverflowOrOptionsMenu();
        // Find the menu item with text "About" and click on it
        clickViewWithText("About");
        // Verify the correct item was clicked by checking the content of the status TextView
        checkViewWithIdByText(R.id.status,"About");
    }
    
    @Test
    public void testActionMode() {
        // Show the contextual ActionBar
        clickViewWithId(R.id.toggle_action_mode);
        // Click on a context item
        clickViewWithId(R.id.action_one);
        // Verify the correct item was clicked by checking the content of the status TextView
        checkViewWithIdByText(R.id.status,"ActionMode1");
    }
}