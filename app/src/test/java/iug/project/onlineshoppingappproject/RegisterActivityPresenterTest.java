package iug.project.onlineshoppingappproject;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

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
  public void testPresenterSuccessTest(){

    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printSuccessMessage("Registration Success");

  }

  @Test
  public void testPresenterEmailEmptyError(){

    String email = "";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Email field is empty");

  }

  @Test
  public void testPresenterUsernameEmptyError(){

    String email = "ahmed@gmail.com";
    String username = "";
    String password = "123456";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Username field is empty");

  }

  @Test
  public void testPresenterPasswordEmptyError(){

    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Password field is empty");

  }

  @Test
  public void testPresenterConfirmPasswordEmptyError(){

    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Confirm Password field is empty");

  }

  @Test
  public void testPresenterPasswordLengthError(){

    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "12345";
    String confirmPassword = "123456";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Password is less than six characters");

  }

  @Test
  public void testPresenterPasswordConfirmationError(){

    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123457";

    //when
    registerActivityPresenter.checkUserRegistrationInput(email,username,password,confirmPassword);

    //then
    Mockito.verify(registerActivityView).printErrorMessage("Password doesn't match");

  }

}