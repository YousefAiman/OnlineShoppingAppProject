package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import iug.project.onlineshoppingappproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(() ->
                        startActivity(
                                new Intent(MainActivity.this, RegisterActivity.class)),
                100);

    }
}