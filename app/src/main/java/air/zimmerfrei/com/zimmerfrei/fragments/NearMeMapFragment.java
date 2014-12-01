package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 29.10.2014..
 */
public class NearMeMapFragment extends Fragment {

    private GoogleMap mMap;
    private MapFragment mFragment;
    private LocationClient client;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * ENDPOINT is base location of web services
     */
    public static final String ENDPOINT = "http://188.226.150.65";
    List<ApartmentResponse> listApartment;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NearMeMapFragment newInstance(int sectionNumber) {
        try {
            NearMeMapFragment fragment = new NearMeMapFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NearMeMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_near_me, container, false);
        listApartment = new ArrayList<ApartmentResponse>();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();

        try {
            client = new LocationClient(getActivity(), new GooglePlayServicesClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {
                    Toast.makeText(getActivity(),
                            "onConnected", Toast.LENGTH_LONG).show();
                    Location currentLocation = client.getLastLocation();
                    if (currentLocation != null) {
                        CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
                        CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);
                        mMap.moveCamera(center);
                        mMap.animateCamera(zoom);
                    }
                }

                @Override
                public void onDisconnected() {
                    Toast.makeText(getActivity(),
                            "onDisc", Toast.LENGTH_LONG).show();
                }
            }, new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(ConnectionResult connectionResult) {
                    Toast.makeText(getActivity(),
                            "fail!", Toast.LENGTH_LONG).show();
                }
            });
            client.connect();
        } catch (Exception e) {

        }

    }

    private void setUpMapIfNeeded() {
        FragmentManager fm = getChildFragmentManager();
        mFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mFragment == null) {
            mFragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mFragment).commit();
        }
    }

    /**
     * Requests the data from server to update map with markers (pins) using Retrofit
     */
    private void requestData() {
        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        ApartmentAPI api = adapter.create(ApartmentAPI.class);

        api.getApartments(new Callback<Apartment>() {
            @Override
            public void success(Apartment apartments, Response response) {
                listApartment = apartments.getResponse();
                updatePins();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Retrofit log: ", error.getMessage());
            }
        });
    }

    /**
     * If obtaining the data from server was successful, show markers (pins) on map
     */
    private void updatePins() {
        for (int i = 0; i < listApartment.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(
                    Double.parseDouble(listApartment.get(i).getLat()),
                    Double.parseDouble(listApartment.get(i).getLng())))
                    .title(listApartment.get(i).getName()));
        }
    }

    /**
     * Set up features to map:
     * Enable my location
     * Detect loaction and set up view
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
/*
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener(){
            @Override
            public void onMyLocationChange(Location location) {
                CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
                // remove listener after it gets needed data
                mMap.setOnMyLocationChangeListener(null);
            }
        });
*/

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMap == null) {
            mMap = mFragment.getMap();
        }
        setUpMap();
        requestData();
    }
}
