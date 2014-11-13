package com.zimmerfrei.air.zimmerfrei;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zimmerfrei.air.test.TestApartmentDetails;

import java.util.List;


/**
 * Created by Andro on 11.11.2014..
 */
public class ApartmentDetailsAdapter extends BaseAdapter {

    Context context;
    protected List<TestApartmentDetails> listDetails;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return listDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return listDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.apartment_details_layout, parent, false);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TestApartmentDetails apartmentDetails = listDetails.get(position);

        return null;
    }

    private class ViewHolder {

    }
}
