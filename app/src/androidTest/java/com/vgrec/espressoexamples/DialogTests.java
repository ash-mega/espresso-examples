/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 2:52 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.DialogExampleActivity;
import com.vgrec.espressoexamples.core.TestHelper;

import org.junit.Rule;
import org.junit.Test;

public class DialogTests extends TestHelper {
    
    @Rule
    public ActivityTestRule<DialogExampleActivity> rule = new ActivityTestRule<>(DialogExampleActivity.class);
    
    @Test
    public void testCheckDialogDisplayed() {
        // Click on the button that shows the dialog
        clickViewWithId(R.id.confirm_dialog_button);
        // Check the dialog title text is displayed
        checkViewWithTextIdMatchesDisplayed(R.string.dialog_title);
    }
    
    @Test
    public void testClickOkButton() {
        clickViewWithId(R.id.confirm_dialog_button);
        
        // android.R.id.button1 = positive button
        clickViewWithId(android.R.id.button1);
        checkViewWithIdMatchesTextId(R.id.status_text,R.string.ok);
    }
    
    @Test
    public void testClickCancelButton() {
        clickViewWithId(R.id.confirm_dialog_button);
        
        // android.R.id.button2 = negative button
        clickViewWithId(android.R.id.button2);
        checkViewWithIdMatchesTextId(R.id.status_text,R.string.cancel);
    }
}
