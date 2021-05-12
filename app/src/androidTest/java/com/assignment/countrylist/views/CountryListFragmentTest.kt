package com.assignment.countrylist.views
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.countrylist.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class CountryListFragmentTest {
    private lateinit var scenario: FragmentScenario<CountryListFragment>
    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_CountryList_NoActionBar)
        scenario.moveToState(Lifecycle.State.STARTED)
    }
    @Test
    fun testSwipeViewClick() {
        onView(withId(R.id.swipe_refresh)).perform(click())
    }
    @Test
    fun checkTextValue() {
        onView(withId(R.id.tv_nodata)).check(matches(withText("No data found")))
    }
    @Test
    fun testCountryListFragment() {
        scenario.moveToState(Lifecycle.State.CREATED)
    }
    @Test
    fun testLifeCycle() {
        val scenario = launchFragmentInContainer<CountryListFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
        // EventFragment moves from RESUMED -> STARTED -> CREATED
    }
    @Test fun testreCreateFragment() {
        val scenario = launchFragmentInContainer<CountryListFragment>()
        scenario.recreate()
    }
}
