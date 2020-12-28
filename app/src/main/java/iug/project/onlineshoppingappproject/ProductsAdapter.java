package iug.project.onlineshoppingappproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import iug.project.onlineshoppingappproject.Models.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

  final int itemLayout;
  final ArrayList<Product> products;


  public ProductsAdapter(ArrayList<Product> products,int itemLayout){
    this.itemLayout = itemLayout;
    this.products = products;
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

  static class ProductsViewHolder extends RecyclerView.ViewHolder {

    final Picasso picasso = Picasso.get();
    ImageView productIv,cartIv;
    TextView productNameTv,productPriceTv,productPublishTv;

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


    }
  }
}
