package iug.project.onlineshoppingappproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;

public class BitmapDecoder {

  public static Bitmap decodeUri(Context c, Uri uri)
          throws FileNotFoundException {
    BitmapFactory.Options o = new BitmapFactory.Options();
    o.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

    int width_tmp = o.outWidth, height_tmp = o.outHeight;
    int scale = 1;

    while (width_tmp / 2 >= 250 && height_tmp / 2 >= 250) {
      width_tmp /= 2;
      height_tmp /= 2;
      scale *= 2;
    }

    BitmapFactory.Options o2 = new BitmapFactory.Options();
    o2.inSampleSize = scale;
    return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
  }

}
