package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

public class LoginActivity extends AppCompatActivity implements RegisterActivityViewInterface {
    EditText emailEd;
    EditText passwordEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

    }

    void initViews() {
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);
    }


    @Override
    public void startHomeActivity() {

    }

    @Override
    public void printErrorMessage(String errorMessage) {

    }
}