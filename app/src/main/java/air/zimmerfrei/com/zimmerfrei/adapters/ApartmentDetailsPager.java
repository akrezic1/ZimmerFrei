package air.zimmerfrei.com.zimmerfrei.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import air.zimmerfrei.com.zimmerfrei.R;

/**
 * Created by Andro on 23.12.2014.
 */
public class ApartmentDetailsPager extends PagerAdapter {

    Context context;
    String[] pictures;

    public ApartmentDetailsPager(Context context, String[] pictures) {
        this.context = context;
        this.pictures = pictures;
    }


    @Override
    public int getCount() {
        return pictures.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(pictures[position]).into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
