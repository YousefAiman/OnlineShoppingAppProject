package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import iug.project.onlineshoppingappproject.Presenters.LoginActivityPresenter;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.RegisterActivityViewInterface;

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