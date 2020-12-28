package iug.project.onlineshoppingappproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import iug.project.onlineshoppingappproject.Models.Product;
import iug.project.onlineshoppingappproject.ProductsViewPager;
import iug.project.onlineshoppingappproject.R;

public class ProductActivity extends AppCompatActivity {

    ViewPager productsViewPager;
    LinearLayout productsSlider;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product = (Product) getIntent().getSerializableExtra("product");

        ((TextView)findViewById(R.id.priceTv)).setText(String.format(Locale.getDefault(),
                "%,d", product.getPrice())+"$");

        ((TextView)findViewById(R.id.toolbarTitleTv)).setText(product.getName());
        ((TextView)findViewById(R.id.descrTv)).setText(product.getDescription());

        CollectionReference usersRef = FirebaseFirestore.getInstance().collection("Users");
        String currentUserId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        findViewById(R.id.addToCartBtn).setOnClickListener(view -> usersRef.document(currentUserId)
                .update("cart", FieldValue.arrayUnion(product.getProductId()))
                .addOnSuccessListener(aVoid -> Toast.makeText(ProductActivity.this,
                        "Product was added to cart"
                        , Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(ProductActivity.this, "Adding to cart failed"
                                , Toast.LENGTH_SHORT).show()));

        productsViewPager = findViewById(R.id.productsViewPager);
        productsSlider = findViewById(R.id.productsSlider);

        createImagesPager();

    }

    void createImagesPager(){

        ArrayList<String> promoImages = product.getImageUrls();

        ProductsViewPager viewPagerAdapter = new ProductsViewPager(this, promoImages);
        productsViewPager.setAdapter(viewPagerAdapter);
        if (promoImages.size() > 1) {
            productsViewPager.setOffscreenPageLimit(viewPagerAdapter.getCount() - 1);

            ImageView[] dots = new ImageView[viewPagerAdapter.getCount()];
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            for (int i = 0; i < promoImages.size(); i++) {
                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_icon));
                params.setMargins((int) (4*getResources().getDisplayMetrics().density),
                        0, (int) (4*getResources().getDisplayMetrics().density), 0);
                productsSlider.addView(dots[i], params);
            }

            if(promoImages.size() == 3){
                DrawableCompat.setTint(DrawableCompat.wrap(dots[1].
                        getDrawable()),getResources().getColor(R.color.blue));

                productsViewPager.setCurrentItem(1);
            }else{
                DrawableCompat.setTint(DrawableCompat.wrap(dots[0].
                        getDrawable()),getResources().getColor(R.color.blue));
            }


            productsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {


//                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(ProductActivity.this
//                            , R.drawable.indicator_icon);
//                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//                    DrawableCompat.setTint(wrappedDrawable, Color.RED);

                    for (ImageView imageView : dots) {

                        imageView.setColorFilter(ContextCompat.getColor(ProductActivity.this
                                , R.color.white));

                    }

                    dots[position].setColorFilter(ContextCompat.getColor(ProductActivity.this
                            , R.color.blue));

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

    }
}