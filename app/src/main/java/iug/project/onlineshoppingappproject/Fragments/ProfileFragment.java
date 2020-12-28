package iug.project.onlineshoppingappproject.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import iug.project.onlineshoppingappproject.Activities.LoginActivity;
import iug.project.onlineshoppingappproject.Activities.RegisterActivity;
import iug.project.onlineshoppingappproject.Models.Product;
import iug.project.onlineshoppingappproject.Models.User;
import iug.project.onlineshoppingappproject.ProductsAdapter;
import iug.project.onlineshoppingappproject.R;


public class ProfileFragment extends Fragment {

  CircleImageView circleImageView;
  ImageView signOutIv;
  TextView usernameTv,emailTv;
  RecyclerView myProductsRv;
  User user;

  public ProfileFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =  inflater.inflate(R.layout.fragment_profile, container, false);
    circleImageView = view.findViewById(R.id.circleImageView);
    usernameTv = view.findViewById(R.id.usernameTv);
    emailTv = view.findViewById(R.id.emailTv);
    myProductsRv = view.findViewById(R.id.myProductsRv);
    signOutIv = view.findViewById(R.id.signOutIv);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


    signOutIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getContext(), LoginActivity.class));

        if(getActivity()!=null){
          getActivity().finish();
        }

      }
    });

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseFirestore.getInstance().collection("Users")
            .document(currentUser.getUid())
            .get()
            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
              @Override
              public void onSuccess(DocumentSnapshot documentSnapshot) {
                 user = documentSnapshot.toObject(User.class);
              }
            }).
            addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
      @Override
      public void onComplete(@NonNull Task<DocumentSnapshot> task) {

        if(user.getImageUrl() != null){

          Picasso.get().load(user.getImageUrl()).fit().centerCrop().into(circleImageView);

        }

        usernameTv.setText(user.getUsername());
        emailTv.setText(currentUser.getEmail());

//                List<String> userPromos = user.getMyPromos();

        ArrayList<Product> products = new ArrayList<>();
        ProductsAdapter productsAdapter =
                new ProductsAdapter(products,R.layout.product_row_item_design);

        myProductsRv.setAdapter(productsAdapter);


        FirebaseFirestore.getInstance().collection("Products")
                .whereEqualTo("userId",currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                  @Override
                  public void onSuccess(QuerySnapshot snapshots) {

                    Log.d("ttt","result size: "+snapshots.size());

                    products.addAll(snapshots.toObjects(Product.class));

                  }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {

            productsAdapter.notifyDataSetChanged();

          }
        });
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Log.d("ttt","user error: "+e.getMessage());
      }
    });

  }
}