package com.zimmerfrei.air.zimmerfrei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zimmerfrei.air.test.TestApartment;

import java.util.List;

/**
 * Created by Andro on 10.11.2014..
 */
public class NearMeListAdapter extends BaseAdapter {

    Context context;
    protected List<TestApartment> listApartment;
    LayoutInflater inflater;

    public NearMeListAdapter(Context context, List<TestApartment> listApartment) {
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

            holder.description = (TextView) convertView.findViewById(R.id.details);
            holder.distance = (TextView) convertView.findViewById(R.id.distance);
            holder.imgApartment = (ImageView) convertView.findViewById(R.id.apartment);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TestApartment apartment = listApartment.get(position);
        holder.description.setText(apartment.getDescription());
        holder.distance.setText(apartment.getDistance());
        holder.imgApartment.setImageResource(apartment.getDrawableId());

        return convertView;
    }

    private class ViewHolder {
        TextView description;
        TextView distance;
        ImageView imgApartment;
    }

}
