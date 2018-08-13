/*
 * android2
 * mega.privacy.android.app.espresso.core
 *
 * Created by Ash Wu on 13/08/18 11:24 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.vgrec.espressoexamples.core;

import android.content.Context;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;

import com.vgrec.espressoexamples.tool.PickerActions;
import com.vgrec.espressoexamples.tool.RecyclerViewActions;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Actions {
    
    static void openActionBarOverflowOrOptionsMenu(Context context) {
        Espresso.openActionBarOverflowOrOptionsMenu(context);
    }
    
    static void closeSoftKeyboard() {
        Espresso.closeSoftKeyboard();
    }
    
    static void pressBack() {
        Espresso.pressBack();
    }
    
    static ViewAction click() {
        return ViewActions.click();
    }
    
    static ViewAction clickRecyclerViewOnPosition(int position) {
        return RecyclerViewActions.actionOnItemAtPosition(position,click());
    }
    
    static ViewAction longClickRecyclerViewOnPosition(int position) {
        return RecyclerViewActions.actionOnItemAtPosition(position,longClick());
    }
    
    static ViewAction clickRecyclerViewOnItemWithText(String text) {
        return RecyclerViewActions.actionOnItem(hasDescendant(withText(text)),click());
    }
    
    static ViewAction longClickRecyclerViewOnItemWithText(String text) {
        return RecyclerViewActions.actionOnItem(hasDescendant(withText(text)),longClick());
    }
    
    static ViewAction longClick() {
        return ViewActions.longClick();
    }
    
    static ViewAction typeText(String text) {
        return ViewActions.typeText(text);
    }
    
    static ViewAction clearText() {
        return ViewActions.clearText();
    }
    
    static ViewAction scrollTo() {
        return ViewActions.scrollTo();
    }
    
    static ViewAction swipeDown() {
        return ViewActions.swipeDown();
    }
    
    static ViewAction swipeLeft() {
        return ViewActions.swipeLeft();
    }
    
    static ViewAction swipeRight() {
        return ViewActions.swipeRight();
    }
    
    static ViewAction swipeUp() {
        return ViewActions.swipeUp();
    }
    
    static ViewAction setDate(int year,int monthOfYear,int dayOfMonth) {
        return PickerActions.setDate(year,monthOfYear,dayOfMonth);
    }
    
    static ViewAction setTime(int hours,int minutes) {
        return PickerActions.setTime(hours,minutes);
    }
    
    static ViewAction pressImeActionButton() {
        return ViewActions.pressImeActionButton();
    }
}
