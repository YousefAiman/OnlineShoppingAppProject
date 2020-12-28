package iug.project.onlineshoppingappproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import iug.project.onlineshoppingappproject.Models.Product;
import iug.project.onlineshoppingappproject.ProductsAdapter;
import iug.project.onlineshoppingappproject.R;

public class CartFragment extends Fragment {

  RecyclerView cartProductsRv;
  public CartFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =  inflater.inflate(R.layout.fragment_cart, container, false);
    cartProductsRv = view.findViewById(R.id.cartProductsRv);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ArrayList<Product> products = new ArrayList<>();
    ProductsAdapter productsAdapter =
            new ProductsAdapter(products,R.layout.product_row_cart_item_design,true);

    cartProductsRv.setAdapter(productsAdapter);

    FirebaseFirestore.getInstance().collection("Users")
            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
      @Override
      public void onSuccess(DocumentSnapshot documentSnapshot) {

        List<String> cartProductsList = (List<String>) documentSnapshot.get("cart");

        if(cartProductsList == null)
          return;

        if(cartProductsList.size() >= 10){
          FirebaseFirestore.getInstance().
                  collection("Products")
                  .whereIn("productId",cartProductsList)
                  .get()
                  .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot snapshots) {

                      products.addAll(snapshots.toObjects(Product.class));

                    }
                  }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

              productsAdapter.notifyDataSetChanged();

            }
          });
        }else{

          CollectionReference productsRef = FirebaseFirestore.getInstance().
                  collection("Products");


          for(String id:cartProductsList){
            productsRef.whereEqualTo("productId",id)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                      @Override
                      public void onSuccess(QuerySnapshot snapshots) {
                        products.add(snapshots.getDocuments().get(0).toObject(Product.class));
                        productsAdapter.notifyItemInserted(products.size() - 1);
                      }
                    });
          }

        }
      }
    });




  }
}