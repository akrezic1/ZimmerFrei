package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.MainActivity;
import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.adapters.NearMeListAdapter;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 10.11.2014.
 * Fragment shows list of apartments close to users location.
 * Clicking on any apartment from list opens apartment details.
 */
public class NearMeListFragment extends ListFragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * ENDPOINT is base location of web services
     */
    public static final String ENDPOINT = "http://188.226.150.65";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NearMeListFragment newInstance(int sectionNumber) {
        NearMeListFragment fragment = new NearMeListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    List<ApartmentResponse> listApartment;

    public NearMeListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_near_me_list, container, false);

        double lat = MainActivity.latitude;
        double lng = MainActivity.longitude;
        requestData(String.valueOf(lat), String.valueOf(lng), "1");

        return rootView;
    }

    /**
     * Requests the data from server to update map with markers (pins) using Retrofit
     * @param lat latitude
     * @param lng longitude
     * @param range range of markers on map, around given location
     */
    private void requestData(String lat, String lng, String range) {
        listApartment = new ArrayList<>();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        if (lat == null || lng == null) {
            Toast.makeText(getActivity(), R.string.location_error, Toast.LENGTH_SHORT).show();
        } else {
            ApartmentAPI api = adapter.create(ApartmentAPI.class);

            api.getApartments(lat, lng, range, new Callback<Apartment>() {
                @Override
                public void success(Apartment apartments, Response response) {
                    listApartment = apartments.getResponse();
                    updateDisplay();
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
     * If data request was successful, update display with data from response
     */
    protected void updateDisplay() {
        NearMeListAdapter adapter = new NearMeListAdapter(getActivity(), R.layout.list_near_me, listApartment);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int apartmentId = Integer.parseInt(listApartment.get(position).getId());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, 0, R.animator.exit_right)
                .addToBackStack(null)
                .add(R.id.container, ApartmentDetailsFragment.newInstance(1, apartmentId))
                .commit();
    }
}
