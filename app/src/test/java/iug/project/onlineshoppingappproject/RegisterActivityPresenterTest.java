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

import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityPresenterTest extends TestCase {

  RegisterActivityPresenter registerActivityPresenter;
  @Mock
  RegisterActivityViewInterface registerActivityView;

  @Mock
  FirebaseAuth firebaseAuth;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);



    Context context = Mockito.mock(Context.class);

    int apps = FirebaseApp.getApps(context).size();

    if (apps == 0) {
      FirebaseOptions options = new FirebaseOptions.Builder()
              .setApplicationId("1:433236011580:android:1e18ba90232c3919393139")
              .setProjectId("onlineshoppingappproject")
              .build();
      FirebaseApp.initializeApp(context, options);
    }


//  firebaseAuth = Mockito.mock(FirebaseAuth.class);
    registerActivityPresenter = new RegisterActivityPresenter(registerActivityView);

  }

  @Test
  public void testPresenterStartHomeActivity(){
    String email = "ahmed@gmail.com";
    String username = "ahmed";
    String password = "123456";
    String confirmPassword = "123456";



    //Mockito.when(firebaseAuth.createUserWithEmailAndPassword(email,password))
    //.thenReturn(true);

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