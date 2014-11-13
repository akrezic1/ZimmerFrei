package com.zimmerfrei.air.zimmerfrei;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Andro on 10.11.2014..
 */
public class ApartmentDetailsFragment extends Fragment {


    List<Integer> apartmentImages;

    public ApartmentDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_apartment_details, container, false);
        getActivity().setTitle("Apartment details");
        return rootView;
    }

    private void getImages() {
        apartmentImages.add(R.drawable.apartment);
        apartmentImages.add(R.drawable.apartment2);
        apartmentImages.add(R.drawable.apartment3);
        apartmentImages.add(R.drawable.apartment4);
        apartmentImages.add(R.drawable.apartment5);
        apartmentImages.add(R.drawable.apartment6);
    }

}
