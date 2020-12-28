package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import iug.project.onlineshoppingappproject.R;

public class WelcomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);



//    SharedPreferences.Editor editor =
//            ;
//    getSharedPreferences("OnlineShoppingShared",MODE_PRIVATE).edit()
//
    findViewById(R.id.toRegisterBtn).setOnClickListener(v -> {
      startActivity(new Intent(WelcomeActivity.this,RegisterActivity.class));
      finish();
    });

  }
}