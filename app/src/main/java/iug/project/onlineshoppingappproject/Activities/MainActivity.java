package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import iug.project.onlineshoppingappproject.MainActivityPresenter;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.RegisterActivityPresenter;
import iug.project.onlineshoppingappproject.Views.MainActivityViewInterface;

public class MainActivity extends AppCompatActivity implements MainActivityViewInterface {

    MainActivityPresenter mainActivityPresenter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      if(FirebaseAuth.getInstance().getCurrentUser() != null){

        new Handler().postDelayed(() ->{
          startActivity(
                  new Intent(MainActivity.this, HomeActivity.class));
          finish();
        }, 600);

      }else{

        sharedPreferences = getSharedPreferences("OnlineShoppingShared",MODE_PRIVATE);
        mainActivityPresenter = new MainActivityPresenter(this,
                sharedPreferences);

        mainActivityPresenter.checkFirstTimeVisitor();
      }

    }

  @Override
  public void startWelcomeScreen() {

    sharedPreferences.edit().putBoolean("firstTime",false).apply();

    new Handler().postDelayed(() ->{
      startActivity(
              new Intent(MainActivity.this, WelcomeActivity.class));
      finish();
    }, 1000);


  }

  @Override
  public void startLoginScreen() {

    new Handler().postDelayed(() ->{
      startActivity(
              new Intent(MainActivity.this, LoginActivity.class));
      finish();
    }, 800);


  }
}