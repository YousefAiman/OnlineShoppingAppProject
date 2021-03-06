package iug.project.onlineshoppingappproject;

import com.google.firebase.auth.FirebaseAuth;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityPresenterTest extends TestCase {

    LoginActivityPresenter loginActivityPresenter;

    @Mock
    LoginActivityViewInterface loginActivityView;

    @Mock
    FirebaseAuth firebaseAuth;

    @Before
    public void setUp() {
        loginActivityPresenter = new LoginActivityPresenter(loginActivityView);
    }

    @Test
    public void testPresenterStartHomeActivity() {
        String email = "ahmed@gmail.com";
        String password = "123456";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).startHomeActivity();

    }

    @Test
    public void emptyEmail() {
        String email = "";
        String password = "123456";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Email field is empty");
    }

/*    @Test
    public void wrongEmail() {
        String email = "ahmed@gmail";
        String password = "123456";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Email field is empty");
    }*/

    @Test
    public void emptyPassword() {
        String email = "ahmed@gmail.com";
        String password = "";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Password field is empty");

    }

    @Test
    public void wrongPassword(){
        String email = "ahmed@gmail.com";
        String password = "12345";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Password is less than six characters");
    }


}
