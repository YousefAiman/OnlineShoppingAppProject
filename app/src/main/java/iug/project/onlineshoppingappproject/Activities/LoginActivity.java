package iug.project.onlineshoppingappproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import iug.project.onlineshoppingappproject.LoginActivityPresenter;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

public class LoginActivity extends AppCompatActivity implements LoginActivityViewInterface, View.OnClickListener {
    EditText emailEd, passwordEd;
    Button loginBtn;
    FirebaseAuth firebaseAuth;
    LoginActivityPresenter loginActivityPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivityPresenter = new LoginActivityPresenter(this);

        initViews();

        firebaseAuth = FirebaseAuth.getInstance();
        findViewById(R.id.toSignUpLayout).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);


    }

    void initViews() {
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);
    }


    @Override
    public void startHomeActivity() {
        String email = emailEd.getText().toString();
        String password = passwordEd.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void printErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printSuccessMessage(String successMessage) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn){
            loginActivityPresenter.checkLoginInfo(emailEd.getText().toString(),
                    passwordEd.getText().toString());
        } else if (view.getId() == R.id.toSignUpLayout){
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        }
    }
}