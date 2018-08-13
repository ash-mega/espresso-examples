/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 3:17 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.EnterNameActivity;
import com.vgrec.espressoexamples.core.TestHelper;

import org.junit.Rule;
import org.junit.Test;

public class EnterNameTest extends TestHelper {
    
    private static final String USER_NAME = "John";
    private static final String GREETING_MESSAGE = "Hello " + USER_NAME + "!";
    
    @Rule
    public ActivityTestRule<EnterNameActivity> rule = new ActivityTestRule<>(EnterNameActivity.class);
    
    @Test
    public void testHintDisplayed() {
        checkViewWithIdMatchesHintId(R.id.name_edittext,R.string.enter_name);
    }
    
    @Test
    public void testErrorMessageDisplayed() {
        // Making sure the error message is not displayed by default
        checkViewWithIdMatchesNotDisplayed(R.id.error_text);
        // Click on "Next" button
        clickViewWithId(R.id.next_button);
        // Now check the error message is displayed
        checkViewWithIdMatchesDisplayed(R.id.error_text);
    }
    
    @Test
    public void testGreetingMessageWithNameDisplayed() {
        typeText(R.id.name_edittext,USER_NAME);
        clickViewWithId(R.id.next_button);
        checkViewWithIdMatchesText(R.id.greeting_message,GREETING_MESSAGE);
    }
}
