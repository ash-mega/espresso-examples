package com.vgrec.espressoexamples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ActionBarExampleTest.class,
        CustomListTest.class,
        DateTimePickerTest.class,
        DialogTests.class,
        EnterNameTest.class,
        RecyclerViewTest.class,
        SearchViewTest.class,
        SpinnerSelectionTest.class,
        ViewPagerTest.class})
public class TestSuite {
}
