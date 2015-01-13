package air.zimmerfrei.com.zimmerfrei.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;

/**
 * Created by Andro on 13.1.2015.
 */
public class ApartmentListAdapter extends ArrayAdapter<ApartmentResponse> {

    Context context;
    private List<ApartmentResponse> listApartment;

    public ApartmentListAdapter(Context context, int resource, List<ApartmentResponse> listApartment) {
        super(context, resource, listApartment);
        this.listApartment = listApartment;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_my_places, parent, false);

        ApartmentResponse apartment = listApartment.get(position);

        ImageView image = (ImageView) view.findViewById(R.id.imageApartment);
        Picasso.with(context).load(apartment.getCover_photo()).into(image);

        TextView address = (TextView) view.findViewById(R.id.addressNearMe);
        address.setText(apartment.getAddress());

        RatingBar rating = (RatingBar) view.findViewById(R.id.ratingNearMe);
        rating.setRating(Float.parseFloat(apartment.getRating()));

        return view;
    }

}


