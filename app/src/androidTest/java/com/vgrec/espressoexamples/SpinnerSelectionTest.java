/*
 * EspressoExamples
 * com.vgrec.espressoexamples
 *
 * Created by Ash Wu on 10/08/18 11:14 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples;

import android.support.test.rule.ActivityTestRule;

import com.vgrec.espressoexamples.activities.SpinnerSelectionActivity;
import com.vgrec.espressoexamples.tool.RecyclerViewActions;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

public class SpinnerSelectionTest extends BaseTest {
    
    public static final String INVALID_COUNTRY_NAME = "NoSuchCountry";
    public static final String VALID_COUNTRY_NAME = "Moldova";
    public static final String FIRST_ITEM_TEXT = "Select your country";
    
    @Rule
    public ActivityTestRule<SpinnerSelectionActivity> rule = new ActivityTestRule<>(SpinnerSelectionActivity.class);
    
    @Test
    public void testCountryNotInList() {
        checkItemWithTextNotInAdapterView(R.id.countries_spinner,INVALID_COUNTRY_NAME);
    }
    
    @Test
    public void testLabelDoesNotChangeIfFirstItemSelected() {
        // Click on the Spinner
        clickViewWithId(R.id.countries_spinner);
        
        // Click on the first item from the list, which is a marker string: "Select your country"
//        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        clickViewOnPosition(0);
        
        // Check that the country label is not updated.
        checkViewWithIdByText(R.id.country_label,FIRST_ITEM_TEXT,false);
    }
    
    @Test
    public void testLabelUpdatesIfValidCountrySelected() {
        // Click on the Spinner
        clickViewWithId(R.id.countries_spinner);
        
        // Select a country from the list
        clickViewOnItemWithText(VALID_COUNTRY_NAME);
        
        // Check that the country label is updated with selected country
        checkViewWithIdByText(R.id.country_label,VALID_COUNTRY_NAME,true);
    }
}
