package com.zimmerfrei.air.zimmerfrei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zimmerfrei.air.test.TestApartment;

import java.util.List;


/**
 * Created by Andro on 7.11.2014..
 * Adapter used for MyPlaces fragment
 */

public class MyPlacesAdapter extends BaseAdapter {

    Context context;
    protected List<TestApartment> listApartment;
    LayoutInflater inflater;

    public MyPlacesAdapter(Context context, List<TestApartment> listApartment) {
        this.listApartment = listApartment;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listApartment.size();
    }

    @Override
    public Object getItem(int position) {
        return listApartment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listApartment.get(position).getDrawableId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.near_me_list_layout, parent, false);

            holder.distance = (TextView) convertView.findViewById(R.id.distance);
            holder.imgApartment = (ImageView) convertView.findViewById(R.id.apartment);
            holder.rating = (RatingBar) convertView.findViewById(R.id.ratingNearMe);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TestApartment apartment = listApartment.get(position);
        holder.distance.setText(apartment.getDistance());
        holder.imgApartment.setImageResource(apartment.getDrawableId());
        holder.rating.setRating(apartment.getRating());

        return convertView;
    }

    private class ViewHolder {
        TextView distance;
        ImageView imgApartment;
        RatingBar rating;
    }
}
