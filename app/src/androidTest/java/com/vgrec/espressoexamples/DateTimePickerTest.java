/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 9/08/18 1:30 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.DateTimePickerActivity;
import com.vgrec.espressoexamples.core.TestHelper;

import org.junit.Rule;
import org.junit.Test;

public class DateTimePickerTest extends TestHelper {
    
    @Rule
    public ActivityTestRule<DateTimePickerActivity> rule = new ActivityTestRule<>(DateTimePickerActivity.class);
    
    @Test
    public void testSetDate() {
        int year = 2020;
        int month = 11;
        int day = 15;
        
        clickViewWithId(R.id.date_picker_button);
        datePickerSetDate(year,month + 1,day);
        clickViewWithId(android.R.id.button1);
        checkViewWithIdMatchesText(R.id.status,year + "/" + month + "/" + day);
    }
    
    @Test
    public void testSetTime() {
        int hour = 10;
        int minutes = 59;
        
        clickViewWithId(R.id.time_picker_button);
        timePickerSetTime(hour,minutes);
        clickViewWithId(android.R.id.button1);
        checkViewWithIdMatchesText(R.id.status,hour + ":" + minutes);
    }
}
