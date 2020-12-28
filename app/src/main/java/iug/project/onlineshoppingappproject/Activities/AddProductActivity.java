package iug.project.onlineshoppingappproject.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import iug.project.onlineshoppingappproject.AddProductPresenter;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.Views.AddProductViewInterface;

public class AddProductActivity extends AppCompatActivity implements AddProductViewInterface {

    private static final int
            PICK_IMAGE = 1;

    private ArrayList<String> imageUrisString;
    private ImageView[] promoImages;
    private ProgressBar[] progressBars;
    private int imageNumber = 0;

    private final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    EditText et_product_name,et_product_price,editTextTextMultiLine;

    Button addProductBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        AddProductPresenter addProductPresenter = new AddProductPresenter(this);

        requestPermissions();

        addProductBtn = findViewById(R.id.button);
        et_product_name = findViewById(R.id.et_product_name);
        et_product_price = findViewById(R.id.et_product_price);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);

        progressBars = new ProgressBar[]{
                findViewById(R.id.progress_bar1),
                findViewById(R.id.progress_bar2),
                findViewById(R.id.progress_bar3)
        };

        promoImages = new ImageView[]{
                findViewById(R.id.promoimage1),
                findViewById(R.id.promoimage2),
                findViewById(R.id.promoimage3)
        };


        for (int i = 0; i < 3; i++) {
            int finalI = i;
            promoImages[i].setOnClickListener(view -> {
                imageNumber = finalI;
                getImage();
            });
        }
        imageUrisString = new ArrayList<>();

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductPresenter.checkNameAndDiscriptionInput(et_product_name.getText().toString(),
                        editTextTextMultiLine.getText().toString());
            }
        });
    }

    void getImage() {
        startActivityForResult(Intent.createChooser(new
                Intent(Intent.ACTION_GET_CONTENT).
                setType("image/*"), "Select Image"), PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null && data.getData() != null) {
                selectedImage(imageNumber, data.getData());
        }
    }

    public void requestPermissions() {
        final String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 1);
            }
        }
    }



    Bitmap decodeUri(Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (width_tmp / 2 >= requiredSize && height_tmp / 2 >= requiredSize) {
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(uri), null, o2);
    }

    void selectedImage(int index, Uri promoImageUri) {

        progressBars[index].setVisibility(View.VISIBLE);

        try {
            promoImages[index].setScaleType(ImageView.ScaleType.CENTER_CROP);
            promoImages[index].setImageBitmap(decodeUri(promoImageUri, 80));
            promoImages[index].setClickable(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (promoImageUri != null) {
            addProductBtn.setClickable(false);
            progressBars[index].setVisibility(View.VISIBLE);
            StorageReference reference = firebaseStorage.getReference().child("productImages" +
                    "/" + UUID.randomUUID().toString());

            reference.putFile(promoImageUri).addOnSuccessListener(taskSnapshot -> {

                reference.getDownloadUrl().addOnSuccessListener(uri -> {
                    imageUrisString.add(uri.toString());
                }).addOnCompleteListener(task -> {
                    progressBars[index].setVisibility(View.INVISIBLE);
                    addProductBtn.setClickable(true);
                });
            });
        }
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


        addProductBtn.setClickable(false);
        Map<String,Object> product = new HashMap<>();

        product.put("productId",UUID.randomUUID().toString());
        product.put("name",et_product_name.getText().toString());
        product.put("imageUrls",imageUrisString);
        product.put("price", Long.parseLong(et_product_price.getText().toString()));
        product.put("publishTime", System.currentTimeMillis() / 1000);
        product.put("description", editTextTextMultiLine.getText().toString());
        product.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseFirestore.getInstance().collection("Products")
                .document().set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddProductActivity.this, "Product Added Successfully!"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddProductActivity.this, "An Error Occurred" +
                        " Please try again!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}