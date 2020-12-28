package iug.project.onlineshoppingappproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;

import iug.project.onlineshoppingappproject.Fragments.CartFragment;
import iug.project.onlineshoppingappproject.Fragments.ProfileFragment;
import iug.project.onlineshoppingappproject.Models.Product;
import iug.project.onlineshoppingappproject.ProductsAdapter;
import iug.project.onlineshoppingappproject.R;

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

  RecyclerView homeProductsRv;
  ArrayList<Product> products;
  ProductsAdapter productsAdapter;
  SwipeRefreshLayout homeSwipeRefreshLayout;
  CollectionReference promotionsRef;
  BottomNavigationView homeBottomNavigationView;
  FrameLayout homeFrameLayout;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    promotionsRef = FirebaseFirestore.getInstance().collection("Products");

    initViews();

    products = new ArrayList<>();
    productsAdapter =
            new ProductsAdapter(products,R.layout.product_grid_item_design);

    homeProductsRv.setAdapter(productsAdapter);

    getAllProducts();

    homeSwipeRefreshLayout.setOnRefreshListener(this);

    homeBottomNavigationView.setSelectedItemId(R.id.homeNavigationItem);

    homeBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      if(item.getItemId() == R.id.homeNavigationItem){

        if(homeBottomNavigationView.getSelectedItemId() == R.id.homeNavigationItem){

          onRefresh();

        }else{
          homeFrameLayout.setVisibility(View.INVISIBLE);
          getSupportFragmentManager().beginTransaction().remove(
                  getSupportFragmentManager().getFragments().get(0)
          ).commit();
        }
      }else if(item.getItemId() == R.id.cartNavigationItem){
          replaceFragment(new CartFragment());
      }else{
        replaceFragment(new ProfileFragment());
      }
      return true;
    });
  }

  void replaceFragment(Fragment fragment){

    if(homeFrameLayout.getVisibility() == View.INVISIBLE){
      homeFrameLayout.setVisibility(View.VISIBLE);
    }
    getSupportFragmentManager().beginTransaction().
            replace(homeFrameLayout.getId(),fragment)
            .commit();
  }
  void initViews(){

    homeProductsRv = findViewById(R.id.homeProductsRv);
    homeSwipeRefreshLayout = findViewById(R.id.homeSwipeRefreshLayout);
    homeBottomNavigationView = findViewById(R.id.homeBottomNavigationView);
    homeFrameLayout = findViewById(R.id.homeFrameLayout);

  }

  void getAllProducts(){

    homeSwipeRefreshLayout.setRefreshing(true);

    promotionsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
      @Override
      public void onSuccess(QuerySnapshot snapshots) {

        for(int i=0;i<10;i++){
          products.addAll(snapshots.toObjects(Product.class));
        }

      }
    }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {

        homeSwipeRefreshLayout.setRefreshing(false);
        productsAdapter.notifyDataSetChanged();

      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Log.d("ttt","failed to get products: "+e.getMessage());
      }
    });

  }


  @Override
  public void onRefresh() {

    products.clear();
    productsAdapter.notifyDataSetChanged();
    getAllProducts();

  }

  @Override
  public void onBackPressed() {

    if(homeFrameLayout.getVisibility() == View.VISIBLE){
      homeBottomNavigationView.setSelectedItemId(R.id.homeNavigationItem);
    }else{
      super.onBackPressed();
    }

  }

}