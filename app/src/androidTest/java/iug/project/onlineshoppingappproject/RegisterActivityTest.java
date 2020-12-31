package iug.project.onlineshoppingappproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import iug.project.onlineshoppingappproject.Activities.HomeActivity;
import iug.project.onlineshoppingappproject.Activities.LoginActivity;
import iug.project.onlineshoppingappproject.Activities.RegisterActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {

  @Rule
  public ActivityScenarioRule<RegisterActivity> registerActivityTestRule
          = new ActivityScenarioRule<>(RegisterActivity.class);

  @Before
  public void initIntent(){
    Intents.init();
  }

  @Test
  public void registerTest() throws InterruptedException {

    //entering email in email field
    Espresso.onView(ViewMatchers.withId(R.id.emailEd))
            .perform(ViewActions.typeText("newuser@gmail.com"),
                    ViewActions.closeSoftKeyboard());


    //entering username in username field
    Espresso.onView(ViewMatchers.withId(R.id.usernameEd))
            .perform(ViewActions.typeText("new test user"),
                    ViewActions.closeSoftKeyboard());


    //entering password in password field
    Espresso.onView(ViewMatchers.withId(R.id.passwordEd))
            .perform(ViewActions.typeText("123456"),
                    ViewActions.closeSoftKeyboard());

    //entering same password in confirm password field
    Espresso.onView(ViewMatchers.withId(R.id.passwordConfirmEd))
            .perform(ViewActions.typeText("123456"),
                    ViewActions.closeSoftKeyboard());

    //clicking the Register button
    Espresso.onView(ViewMatchers.withId(R.id.registerBtn)).perform(ViewActions.click());

    //waiting for firebase auth register response and creating user in firestore database
    Thread.sleep(4000);

    //checking that Home activity is started after a successful login
    Intents.intended(IntentMatchers.hasComponent(HomeActivity.class.getName()));
  }

  @Test
  public void startLoginActivityTest(){

    //clicking the toSigninLayout layout
    Espresso.onView(ViewMatchers.withId(R.id.toSigninLayout)).perform(ViewActions.click());

    //checking that Login activity is started
    Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
  }


    @After
  public void releaseIntent(){
    Intents.release();
  }


}