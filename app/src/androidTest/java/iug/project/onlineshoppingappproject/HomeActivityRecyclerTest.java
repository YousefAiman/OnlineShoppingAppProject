package iug.project.onlineshoppingappproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import iug.project.onlineshoppingappproject.Activities.HomeActivity;
import iug.project.onlineshoppingappproject.Fragments.CartFragment;
import iug.project.onlineshoppingappproject.Models.Product;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityRecyclerTest {

  @Rule
  public ActivityScenarioRule<HomeActivity> homeActivityTestRule
          = new ActivityScenarioRule<>(HomeActivity.class);

  @Test
  public void timeConversionForNewProductsTest() {
    //Checks if newly added product would be labeled as being published 'Just now'

    String timeConversionText = "Just now";

    Espresso.onView(ViewMatchers.withId(R.id.homeProductsRv)).check(
            ViewAssertions.matches(ViewMatchers.
                    hasDescendant(ViewMatchers.withText(timeConversionText)))
            );

  }

  @Test
  public void timeConversionForOlderProductsTest() {

    //Checks if a product that was added an hour ago would be labeled
    // as being published 'An hour ago'

    String timeConversionText = "An hour ago";

    Espresso.onView(ViewMatchers.withId(R.id.homeProductsRv))
            .perform(RecyclerViewActions.scrollToPosition(1))
            .check(ViewAssertions.matches(ViewMatchers.
            hasDescendant(ViewMatchers.withText(timeConversionText))));

  }


}