package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.RegisterActivityViewInterface;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityViewInterface {

    EditText emailEd;
    EditText usernameEd;
    EditText passwordEd;
    EditText passwordConfirmEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();


    }

    void initViews() {
        emailEd = findViewById(R.id.emailEd);
        usernameEd = findViewById(R.id.usernameEd);
        passwordEd = findViewById(R.id.passwordEd);
        passwordConfirmEd = findViewById(R.id.passwordConfirmEd);
    }

    @Override
    public void startHomeActivity() {

    }

    @Override
    public void printErrorMessage(String errorMessage) {

    }
}