package iug.project.onlineshoppingappproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import iug.project.onlineshoppingappproject.Activities.ProductActivity;
import iug.project.onlineshoppingappproject.Models.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

  final int itemLayout;
  final ArrayList<Product> products;
  boolean isFromCart;

  public ProductsAdapter(ArrayList<Product> products,int itemLayout){
    this.itemLayout = itemLayout;
    this.products = products;
  }

  public ProductsAdapter(ArrayList<Product> products,int itemLayout,boolean isFromCart){
    this.itemLayout = itemLayout;
    this.products = products;
    this.isFromCart = isFromCart;
  }



  @NonNull
  @Override
  public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout
    ,parent,false));

  }

  @Override
  public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

    holder.bind(products.get(position));

  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  void deleteProduct(Product product){

    int index = products.indexOf(product);
    products.remove(index);
    notifyItemRemoved(index);

  }

  class ProductsViewHolder extends RecyclerView.ViewHolder {

    final Picasso picasso = Picasso.get();
    ImageView productIv,cartIv;
    TextView productNameTv,productPriceTv,productPublishTv;
    CollectionReference usersRef = FirebaseFirestore.getInstance().collection("Users");
    String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public ProductsViewHolder(@NonNull View itemView) {
      super(itemView);
      productIv = itemView.findViewById(R.id.productIv);
      cartIv = itemView.findViewById(R.id.cartIv);
      productNameTv = itemView.findViewById(R.id.productNameTv);
      productPriceTv = itemView.findViewById(R.id.productPriceTv);
      productPublishTv = itemView.findViewById(R.id.productPublishTv);
    }


    void bind(Product product){

      picasso.load(product.getImageUrls().get(0)).fit().centerCrop().into(productIv);
      productNameTv.setText(product.getName());
      productPriceTv.setText(String.format(Locale.getDefault(),
              "%,d", product.getPrice())+"$");
      productPublishTv.setText(TimeConvertor.getTimeAgo(product.getPublishTime(),itemView.getContext()));


      cartIv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          if(isFromCart){
            usersRef.document(currentUserId)
                    .update("cart", FieldValue.arrayRemove(product.getProductId()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                        Toast.makeText(itemView.getContext(), "Product was removed from your " +
                                        "cart"
                                , Toast.LENGTH_SHORT).show();


                        deleteProduct(product);

                      }
                    }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                Toast.makeText(itemView.getContext(), "Adding to cart failed"
                        , Toast.LENGTH_SHORT).show();
              }
            });

          }else{
            usersRef.document(currentUserId)
                    .update("cart", FieldValue.arrayUnion(product.getProductId()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                        Toast.makeText(itemView.getContext(), "Product was added to cart"
                                , Toast.LENGTH_SHORT).show();
                      }
                    }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                Toast.makeText(itemView.getContext(), "Adding to cart failed"
                        , Toast.LENGTH_SHORT).show();
              }
            });

          }


        }
      });

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          itemView.getContext().startActivity(new Intent(itemView.getContext(),
                  ProductActivity.class).putExtra("product",product));

        }
      });
    }
  }
}
