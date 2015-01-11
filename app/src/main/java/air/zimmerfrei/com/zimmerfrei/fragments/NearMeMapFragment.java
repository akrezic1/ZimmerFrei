package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.MainActivity;
import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 29.10.2014.
 * Fragment shows Google Map with markers around users location that represent apartments,
 * it's also possible to long click on map and get new markers on that location
 */
public class NearMeMapFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private MapFragment mFragment;
    private HashMap<Marker, MarkerInfo> markerExtra;

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
        listApartment = new ArrayList<>();
        markerExtra = new HashMap<>();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();
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
     * @param lat latitude
     * @param lng longitude
     * @param range range of markers on map, around given location
     */
    private void requestData(String lat, String lng, String range) {
        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        ApartmentAPI api = adapter.create(ApartmentAPI.class);

        if (lat == null || lng == null) {
            Toast.makeText(getActivity(), R.string.location_error, Toast.LENGTH_SHORT).show();
        } else {
            api.getApartments(lat, lng, range, new Callback<Apartment>() {
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
    }

    /**
     * If obtaining the data from server was successful, show markers (pins) on map
     */
    private void updatePins() {
        for (int i = 0; i < listApartment.size(); i++) {
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(
                    Double.parseDouble(listApartment.get(i).getLat()),
                    Double.parseDouble(listApartment.get(i).getLng())))
                    .title(listApartment.get(i).getName())
                    .snippet("Rating: " + listApartment.get(i).getRating()));

            MarkerInfo markerInfo = new MarkerInfo(listApartment.get(i).getId(), listApartment.get(i).getName());
            markerExtra.put(marker, markerInfo);
        }
        mMap.setOnInfoWindowClickListener(this);
    }

    /**
     * Enable MyLocations and center the map on users current location
     * @param lat latitude
     * @param lng longitude
     */
    private void setUpMap(double lat, double lng) {
        mMap.setMyLocationEnabled(true);
        LatLng myLocation = new LatLng(lat, lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10));

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMap == null) {
            mMap = mFragment.getMap();
        }

        double lat = MainActivity.latitude;
        double lng = MainActivity.longitude;
        setUpMap(lat, lng);
        requestData(String.valueOf(lat), String.valueOf(lng), "1");

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                alertDialog(latLng);
            }
        });
    }

    /**
     * Alert dialog that appears when user long presses on map. If dialog is confirmed (clicked on "OK"),
     * map clears all markers, and adds new ones close to location pressed
     * @param latLng is used for latitude and longitude of pressed location
     */
    private void alertDialog(final LatLng latLng) {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.look_here)
                .setMessage(R.string.are_you_sure_map)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mMap.clear();
                        markerExtra.clear();
                        requestData(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), "1");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        MarkerInfo info = markerExtra.get(marker);
        int apartmentId = Integer.parseInt(info.getId());
        // TODO get apartment ID somehow..
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, 0, R.animator.exit_right)
                .addToBackStack(null)
                .add(R.id.container, ApartmentDetailsFragment.newInstance(1, apartmentId))
                .commit();
    }
}

/**
 * MarkerInfo class is used to add extra details to map markers, because it natively doesn't support
 * it. HashMap is using Marker as a key, and MarkerInfo class for other values.
 */
class MarkerInfo {
    String id;
    String title;

    MarkerInfo(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }
}