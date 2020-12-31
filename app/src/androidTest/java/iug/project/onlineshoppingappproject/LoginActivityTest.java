package iug.project.onlineshoppingappproject;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.os.Handler;
import android.service.autofill.Validators;
import android.support.test.InstrumentationRegistry;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

import iug.project.onlineshoppingappproject.Activities.HomeActivity;
import iug.project.onlineshoppingappproject.Activities.LoginActivity;
import iug.project.onlineshoppingappproject.Activities.RegisterActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

  @Rule
  public ActivityScenarioRule<LoginActivity> loginActivityTestRule
          = new ActivityScenarioRule<>(LoginActivity.class);

  @Before
  public void initIntent(){
    Intents.init();
  }

  @Test
  public void loginTest() throws InterruptedException {

    //entering email in email field
    Espresso.onView(ViewMatchers.withId(R.id.emailEd))
            .perform(ViewActions.typeText("y@gmail.com"),
                    ViewActions.closeSoftKeyboard());

    //entering password in password field
    Espresso.onView(ViewMatchers.withId(R.id.passwordEd))
            .perform(ViewActions.typeText("123456"),
                    ViewActions.closeSoftKeyboard());


    //clicking the login button
    Espresso.onView(ViewMatchers.withId(R.id.loginBtn)).perform(ViewActions.click());

    //waiting for firebase auth login response
    Thread.sleep(3000);

    //checking that Home activity is started after a successful login
    Intents.intended(IntentMatchers.hasComponent(HomeActivity.class.getName()));
  }


  @Test
  public void startRegisterActivityTest(){

    //clicking the toSignUpLayout layout
    Espresso.onView(ViewMatchers.withId(R.id.toSignUpLayout)).perform(ViewActions.click());

    //checking that Register activity is started
    Intents.intended(IntentMatchers.hasComponent(RegisterActivity.class.getName()));
  }

  @After
  public void releaseIntent(){
    Intents.release();
  }


}