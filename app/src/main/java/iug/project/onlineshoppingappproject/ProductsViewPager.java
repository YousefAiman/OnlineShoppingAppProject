package iug.project.onlineshoppingappproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsViewPager extends PagerAdapter {

  private final Context context;
  private final ArrayList<String> images;
  private final Picasso picasso = Picasso.get();

  public ProductsViewPager(Context context, ArrayList<String> images) {
    this.context = context;
    this.images = images;
  }

  @Override
  public int getCount() {
    return images.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, final int position) {

    View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
            .inflate(R.layout.product_pager_layout, null);
    final ImageView imageView = view.findViewById(R.id.pagerImageView);
    picasso.load(images.get(position)).fit().centerInside().into(imageView);
//    view.setOnClickListener(v -> ((HomeActivity) context).showImageFullScreen(images.get(position)));
    container.addView(view);
    return view;

  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
  }
}
