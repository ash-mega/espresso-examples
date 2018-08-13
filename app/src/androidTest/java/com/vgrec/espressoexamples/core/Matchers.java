/*
 * android2
 * mega.privacy.android.app.espresso.core
 *
 * Created by Ash Wu on 13/08/18 9:57 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples.core;

import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class Matchers {
    
    static class DataMatchers {

        static org.hamcrest.Matcher<Object> is(Object v) {
            return org.hamcrest.Matchers.is(v);
        }
        
        static Matcher<Object> matchesAllConditions(Matcher<Object>... matchers) {
            return allOf(matchers);
        }
        
        static Matcher<Object> matchesAnything() {
            return anything();
        }
        
        static Matcher<Object> matchesType(Class<?> type) {
            return org.hamcrest.core.Is.is(instanceOf(type));
        }
        
        static Matcher<Object> matchesItemText(String expectedText) {
            final Matcher<String> itemTextMatcher = equalTo(expectedText);
            
            return new BoundedMatcher<Object, String>(String.class) {
                @Override
                public boolean matchesSafely(String text) {
                    return itemTextMatcher.matches(text);
                }
                
                @Override
                public void describeTo(Description description) {
                    description.appendText("with item content: ");
                    itemTextMatcher.describeTo(description);
                }
            };
        }
    }
    
    static class ViewMatchers {
    
        static Matcher<Root> withDecorView(Matcher<View> decorViewMatcher) {
            return RootMatchers.withDecorView(decorViewMatcher);
        }
    
        static org.hamcrest.Matcher<View> is(View v) {
            return org.hamcrest.Matchers.is(v);
        }
        
        static Matcher<View> notMatches(Matcher<View> matcher) {
            return not(matcher);
        }
        
        static Matcher<View> matchesAllConditions(Matcher<View>... matchers) {
            return allOf(matchers);
        }
        
        public static Matcher<View> matchesId(int viewId) {
            return withId(viewId);
        }
        
        public static Matcher<View> matchesText(String text) {
            return withText(text);
        }
        
        static Matcher<View> matchesTextId(int resourceId) {
            return withText(resourceId);
        }
        
        static Matcher<View> matchesType(Class<? extends View> cls) {
            return withClassName(org.hamcrest.Matchers.equalTo(cls.getName()));
        }
        
        public static Matcher<View> matchesDisplayed() {
            return isDisplayed();
        }
        
        static Matcher<View> matchesNotDisplayed() {
            return not(matchesDisplayed());
        }
        
        static Matcher<View> matchesChecked() {
            return isChecked();
        }
        
        static Matcher<View> matchesNotChecked() {
            return not(matchesChecked());
        }
        
        static Matcher<View> matchesHintId(int resourceId) {
            return withHint(resourceId);
        }
        
        static Matcher<View> matchesHintText(String hint) {
            return withHint(hint);
        }
        
        static Matcher<View> matchesAssignableFrom(Class<? extends View> cls) {
            return isAssignableFrom(cls);
        }
    
        static Matcher<View> hasDescendant(Matcher<View> descendantMatcher) {
            return android.support.test.espresso.matcher.ViewMatchers.hasDescendant(descendantMatcher);
        }
        
        static Matcher<View> matchesItem(final Matcher<Object> dataMatcher) {
            return new TypeSafeMatcher<View>() {
                
                @Override
                public void describeTo(Description description) {
                    description.appendText("with class name: ");
                    dataMatcher.describeTo(description);
                }
                
                @Override
                public boolean matchesSafely(View view) {
                    if (!(view instanceof AdapterView)) {
                        return false;
                    }
                    Adapter adapter = ((AdapterView)view).getAdapter();
                    for (int i = 0;i < adapter.getCount();i++) {
                        if (dataMatcher.matches(adapter.getItem(i))) {
                            return true;
                        }
                    }
                    return false;
                }
            };
        }
    }
}
