package iug.project.onlineshoppingappproject;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityPresenterTest extends TestCase {

  RegisterActivityPresenter registerActivityPresenter;
  @Mock
  RegisterActivityViewInterface registerActivityView;

  @Before
  public void setUp() {

    registerActivityPresenter = new RegisterActivityPresenter(registerActivityView);

  }

  @Test
  public void testPresenterStartHomeActivity(){
    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.registerUser(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).startHomeActivity();

  }

  @Test
  public void testPresenterDisplayErrorMessage(){
    String email = "ahmed@gmail";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.registerUser(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Registration Error");

  }

}