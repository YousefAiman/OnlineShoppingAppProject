package iug.project.onlineshoppingappproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import iug.project.onlineshoppingappproject.ProductActivityPresenter;
import iug.project.onlineshoppingappproject.ProductsViewPager;
import iug.project.onlineshoppingappproject.R;
import iug.project.onlineshoppingappproject.Views.ProductActivityViewInterface;

public class ProductActivity extends AppCompatActivity implements ProductActivityViewInterface {
    ArrayList<String> promoImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        promoImages = new ArrayList<>();
        promoImages.add("https://firebasestorage.googleapis.com/v0/b/onlineshoppingappproject.appspot.com/o/profileImages%2F09e888a8-5a69-4d21-9f49-bf537a09ce87?alt=media&token=0e5c7b1c-3a26-450d-9a1f-09a9b6450cf7");
        promoImages.add("https://firebasestorage.googleapis.com/v0/b/onlineshoppingappproject.appspot.com/o/profileImages%2F09e888a8-5a69-4d21-9f49-bf537a09ce87?alt=media&token=0e5c7b1c-3a26-450d-9a1f-09a9b6450cf7");


        ProductActivityPresenter productActivityPresenter = new ProductActivityPresenter(this);
        productActivityPresenter.checkImages(promoImages);

    }

    void createImagesPager(ArrayList<String> promoImages){


        ViewPager productsViewPager = findViewById(R.id.productsViewPager);
        LinearLayout productsSlider = findViewById(R.id.productsSlider);

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

    @Override
    public void productScreen() {

    }

    @Override
    public void createImagesViewPager() {
        createImagesPager(promoImages);
    }

    @Override
    public void printNoImagesError() {
        Toast.makeText(this, "No Images", Toast.LENGTH_SHORT).show();
    }
}