package com.zimmerfrei.air.zimmerfrei;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zimmerfrei.air.test.TestApartment;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Andro on 29.10.2014..
 */
public class MyPlacesFragment extends Fragment {

    ArrayList<TestApartment> arrayApartment;
    ListView lvMyPlaces;

    public MyPlacesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_places, container, false);
        arrayApartment = new ArrayList<TestApartment>();


        TestApartment apartment = new TestApartment(R.drawable.apartment, "Opis 1. apartmana", "6km");
        TestApartment apartment2 = new TestApartment(R.drawable.apartment2, "Opis 2. apartmana", "10km");
        TestApartment apartment3 = new TestApartment(R.drawable.apartment3, "Opis 3. apartmana", "17km");
        TestApartment apartment4 = new TestApartment(R.drawable.apartment4, "Opis 4. apartmana", "18km");
        TestApartment apartment5 = new TestApartment(R.drawable.apartment5, "Opis 5. apartmana", "19km");
        TestApartment apartment6 = new TestApartment(R.drawable.apartment6, "Opis 6. apartmana", "25km");

        arrayApartment.add(apartment);
        arrayApartment.add(apartment2);
        arrayApartment.add(apartment3);
        arrayApartment.add(apartment4);
        arrayApartment.add(apartment5);
        arrayApartment.add(apartment6);

        lvMyPlaces = (ListView) rootView.findViewById(R.id.lvMyPlaces);
        MyPlacesAdapter adapter = new MyPlacesAdapter(getActivity(), arrayApartment);
        lvMyPlaces.setAdapter(adapter);

        // When user clicks on list item
        lvMyPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Open apartment details", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;


    }

}
