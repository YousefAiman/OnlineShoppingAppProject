package iug.project.onlineshoppingappproject;

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

  @Before
  public void setUp() {
    mainActivityPresenter = new MainActivityPresenter(mainActivityViewInterface);
  }

  @Test
  public void testPresenterFirstTimeVisitor(){

    //when
    mainActivityPresenter.checkFirstTimeVisitor();

    //then
    Mockito.verify(mainActivityViewInterface).startWelcomeScreen();

  }

  @Test
  public void testPresenterSecondTimeVisitor(){

    //when
    mainActivityPresenter.checkFirstTimeVisitor();

    //then
    Mockito.verify(mainActivityViewInterface).startHomeScreen();

  }


}