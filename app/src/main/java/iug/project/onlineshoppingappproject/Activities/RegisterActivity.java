package iug.project.onlineshoppingappproject.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import iug.project.onlineshoppingappproject.BitmapDecoder;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.RegisterActivityPresenter;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityViewInterface
    ,View.OnClickListener {

    private static final int PICK_IMAGE = 1,REQUEST_STORAGE = 2;

    EditText emailEd,usernameEd,passwordEd,passwordConfirmEd;
    FirebaseAuth auth;
    CollectionReference usersRef;
    RegisterActivityPresenter registerActivityPresenter;
    ProgressBar progressBar,imageProgressBar;
    ImageView imageIv;
    String currentImageUrl;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerActivityPresenter = new RegisterActivityPresenter(this);

        initViews();

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseFirestore.getInstance().collection("Users");

        findViewById(R.id.registerBtn).setOnClickListener(this);
        findViewById(R.id.toSigninLayout).setOnClickListener(this);
        findViewById(R.id.imageIv).setOnClickListener(this);

    }

    void initViews() {
        emailEd = findViewById(R.id.emailEd);
        usernameEd = findViewById(R.id.usernameEd);
        passwordEd = findViewById(R.id.passwordEd);
        passwordConfirmEd = findViewById(R.id.passwordConfirmEd);
        progressBar = findViewById(R.id.progressBar);
        imageIv = findViewById(R.id.imageIv);
        imageProgressBar = findViewById(R.id.imageProgressBar);
    }

    @Override
    public void startHomeActivity() {

    }

    @Override
    public void printErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    void enableScreenTouch(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void printSuccessMessage(String successMessage) {

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        progressBar.setVisibility(View.VISIBLE);


        auth.createUserWithEmailAndPassword(
                emailEd.getText().toString(),
                passwordEd.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {


                        Map<String,String> user = new HashMap<>();
                        user.put("username",usernameEd.getText().toString());
                        if(currentImageUrl!=null){
                            user.put("imageUrl",currentImageUrl);
                        }else{
                            user.put("imageUrl","");
                        }


                        usersRef.document(authResult.getUser().getUid())
                                .set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        progressBar.setVisibility(View.GONE);

                                        Toast.makeText(RegisterActivity.this,
                                                "Account created successfully"
                                                , Toast.LENGTH_SHORT).show();

                                        startActivity(
                                                new Intent(
                                                        RegisterActivity.this
                                                        ,HomeActivity.class));

                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                auth.getCurrentUser()
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        enableScreenTouch();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(RegisterActivity.this,
                                                "Account creation failed!" +
                                                        "please try again",
                                                Toast.LENGTH_SHORT).show();


                                    }
                                });
                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                enableScreenTouch();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this,
                        "Error occurred please try again!", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.registerBtn){
            if(imageProgressBar.getVisibility() == View.VISIBLE){
                Toast.makeText(this,
                        "Please wait until your profile pic is uploaded!",
                        Toast.LENGTH_SHORT).show();
            }else{

                registerActivityPresenter.checkUserRegistrationInput(
                        emailEd.getText().toString(),
                        usernameEd.getText().toString(),
                        passwordEd.getText().toString(),
                        passwordConfirmEd.getText().toString());
            }
        }else if(view.getId() == R.id.toSigninLayout){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else if(view.getId() == R.id.imageIv){
            if(permissionsGranted()){
                pickImage();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_STORAGE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        }
    }


    void pickImage(){

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Select Image"), PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            imageIv.setClickable(false);

            imageProgressBar.setVisibility(View.VISIBLE);

            if(firebaseStorage == null)
                firebaseStorage = FirebaseStorage.getInstance();

            StorageReference ref = firebaseStorage.getReference()
                    .child("profileImages/" + UUID.randomUUID().toString());

            ref.putFile(data.getData())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    imageProgressBar.setVisibility(View.INVISIBLE);
                                    currentImageUrl = uri.toString();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    imageIv.setClickable(true);
                                    imageIv.setImageBitmap(null);
                                    imageProgressBar.setVisibility(View.INVISIBLE);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(RegisterActivity.this,
                            "An error occurred! please try again!", Toast.LENGTH_SHORT).show();
                    imageIv.setClickable(true);
                    imageIv.setImageBitmap(null);
                    imageProgressBar.setVisibility(View.INVISIBLE);

                }
            });

            try {
                imageIv.setImageBitmap(BitmapDecoder.decodeUri(this,data.getData()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    boolean permissionsGranted() {
        final String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (ContextCompat.checkSelfPermission(getApplicationContext(),permissions[0])
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, REQUEST_STORAGE);
                return false;
            }
        }
        return true;
    }

//    @Override
//    protected void onDestroy() {
//
//
//        if(currentImageUrl!=null){
//            Log.d("ttt","not null");
//
//            firebaseStorage.getReferenceFromUrl(currentImageUrl)
//                    .getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    Log.d("ttt","got url");
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.d("ttt",e.getMessage());
//                }
//            });
////
////            firebaseStorage.getReferenceFromUrl(currentImageUrl)
////            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
////                @Override
////                public void onSuccess(Void v) {
////                    Log.d("ttt","deleted");
////                    onDestroy();
////                }
////            }).addOnCompleteListener(new OnCompleteListener<Void>() {
////                @Override
////                public void onComplete(@NonNull Task<Void> task) {
////                    Log.d("ttt","delete completed");
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////                    Log.d("ttt",e.getMessage());
////                }
////            });
//
//        }else{
//            super.onDestroy();
//        }
//
//    }
}