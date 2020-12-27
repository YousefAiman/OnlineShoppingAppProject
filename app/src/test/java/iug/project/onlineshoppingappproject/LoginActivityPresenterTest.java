package iug.project.onlineshoppingappproject;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

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
    public void wrongEmail() {
        String email = "ahmed@gmail";
        String password = "123456";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Invalid Email");

    }

    @Test
    public void wrongPassword(){
        String email = "ahmed@gmail.com";
        String password = "12345";

        //when
        loginActivityPresenter.checkLoginInfo(email, password);

        //then
        Mockito.verify(loginActivityView).printErrorMessage("Invalid Password");
    }

}
