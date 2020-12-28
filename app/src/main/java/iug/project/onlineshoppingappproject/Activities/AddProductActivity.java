package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printSuccessMessage(String successMessage) {


        Map<String,String> product = new HashMap<>();

        product.put("name","name");
        product.put("imageUrls","list");


        FirebaseFirestore.getInstance().collection("Products")
                .document().set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

    }
}