/*
 * android2
 * mega.privacy.android.app.espresso.core
 *
 * Created by Ash Wu on 13/08/18 9:56 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples.core;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Matcher;

import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesAllConditions;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesAssignableFrom;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesId;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesTextId;
import static com.vgrec.espressoexamples.core.Matchers.ViewMatchers.matchesType;

public class TargetElements {
    
    public static class DataElements {
        
        static DataInteraction onData(Matcher<?> dataMatcher) {
            return Espresso.onData(dataMatcher);
        }
    
        static DataInteraction onText(String text) {
            return onData(Matchers.ViewMatchers.matchesText(text));
        }
        
        static DataInteraction onPosition(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Position must be >= 0");
            }
            return onData(Matchers.DataMatchers.matchesAnything()).atPosition(position);
        }
    }
    
    public static class ViewElements {
        
        static ViewInteraction onView(Matcher<View> viewMatcher) {
            return Espresso.onView(viewMatcher);
        }
        
        static ViewInteraction onView(Matcher<View>... viewMatchers) {
            return onView(matchesAllConditions(viewMatchers));
        }
        
        static ViewInteraction onViewId(int id) {
            return onView(matchesId(id));
        }
        
        static ViewInteraction onViewText(String text) {
            return onView(Matchers.ViewMatchers.matchesText(text));
        }
        
        static ViewInteraction onViewTextId(int resourceId) {
            return onView(matchesTextId(resourceId));
        }
        
        static ViewInteraction onViewType(Class<? extends View> cls) {
            return onView(matchesType(cls));
        }
    
        static ViewInteraction onSearchBar() {
            return onView(matchesAssignableFrom(EditText.class));
        }
    }
}
