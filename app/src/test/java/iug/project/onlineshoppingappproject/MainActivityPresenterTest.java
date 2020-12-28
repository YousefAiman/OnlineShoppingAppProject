package iug.project.onlineshoppingappproject;

import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import iug.project.onlineshoppingappproject.Views.MainActivityViewInterface;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest extends TestCase {

  MainActivityPresenter mainActivityPresenter;

  @Mock
  MainActivityViewInterface mainActivityViewInterface;

  @Mock
  SharedPreferences sharedPreferences;


  @Before
  public void setUp() {

    sharedPreferences = Mockito.mock(SharedPreferences.class);

    mainActivityPresenter = new MainActivityPresenter(mainActivityViewInterface,sharedPreferences);

  }

  @Test
  public void testPresenterFirstTimeVisitor(){

    Mockito.when(sharedPreferences.getBoolean("firstTime",true)).thenReturn(true);

    //when
    mainActivityPresenter.checkFirstTimeVisitor();

    //then
    Mockito.verify(mainActivityViewInterface).startWelcomeScreen();

  }

  @Test
  public void testPresenterSecondTimeVisitor(){

    Mockito.when(sharedPreferences.getBoolean("firstTime",true)).thenReturn(false);

    //when
    mainActivityPresenter.checkFirstTimeVisitor();

    //then
    Mockito.verify(mainActivityViewInterface).startLoginScreen();

  }


}