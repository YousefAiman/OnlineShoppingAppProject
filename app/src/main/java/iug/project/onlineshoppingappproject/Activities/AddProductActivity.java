package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.Views.AddProductViewInterface;

public class AddProductActivity extends AppCompatActivity implements AddProductViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    @Override
    public void startProductActivity() {

    }

    @Override
    public void printErrorMessage(String errorMessage) {

    }

    @Override
    public void printSuccessMessage(String successMessage) {



    }
}