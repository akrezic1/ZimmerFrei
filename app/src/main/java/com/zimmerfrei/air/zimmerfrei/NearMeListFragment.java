package com.zimmerfrei.air.zimmerfrei;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zimmerfrei.air.test.TestApartment;

import java.util.ArrayList;

/**
 * Created by Andro on 10.11.2014..
 */
public class NearMeListFragment extends Fragment {

    ArrayList<TestApartment> arrayApartment;
    ListView lvNearMe;

    public NearMeListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_near_me_list, container, false);
        arrayApartment = new ArrayList<TestApartment>();


        TestApartment apartment = new TestApartment(R.drawable.apartment6,  "6km", 5);
        TestApartment apartment2 = new TestApartment(R.drawable.apartment5, "10km", 4.7f);
        TestApartment apartment3 = new TestApartment(R.drawable.apartment4, "17km", 4.5f);
        TestApartment apartment4 = new TestApartment(R.drawable.apartment3, "18km", 4f);
        TestApartment apartment5 = new TestApartment(R.drawable.apartment2, "19km", 4.2f);
        TestApartment apartment6 = new TestApartment(R.drawable.apartment, "25km", 4.1f);

        arrayApartment.add(apartment);
        arrayApartment.add(apartment2);
        arrayApartment.add(apartment3);
        arrayApartment.add(apartment4);
        arrayApartment.add(apartment5);
        arrayApartment.add(apartment6);

        lvNearMe = (ListView) rootView.findViewById(R.id.lvNearMe);
        NearMeListAdapter adapter = new NearMeListAdapter(getActivity(), arrayApartment);
        lvNearMe.setAdapter(adapter);

        // When user clicks on list item
        lvNearMe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Open apartment details", Toast.LENGTH_SHORT).show();
                Fragment newFragment = new ApartmentDetailsFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();

                transaction.replace(R.id.fragment_near_me_list, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return rootView;


    }

}
