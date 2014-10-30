package com.zimmerfrei.air.zimmerfrei;

import android.location.Location;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Andro on 29.10.2014..
 */
public class NearMeFragment extends Fragment {

    private static GoogleMap mMap;
    private static Double latitude, longitude;

    public NearMeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.fragment_near_me, container, false);

        latitude = 46.284029;
        longitude = 16.351776;
        setUpMapIfNeeded();

        return rootView;
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    private static void setUpMap() {
        // For showing a move to my loction button
        mMap.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        //mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
        // For zooming automatically to the Dropped PIN Location
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener(){
            @Override
            public void onMyLocationChange(Location location) {
                CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
            }
        });
    }
}
