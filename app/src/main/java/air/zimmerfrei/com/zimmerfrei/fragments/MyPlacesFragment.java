package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.adapters.MyPlacesAdapter;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import air.zimmerfrei.com.zimmerfrei.webservice.ProfileAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Andro on 29.10.2014.
 */
public class MyPlacesFragment extends ListFragment {

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
    public static MyPlacesFragment newInstance(int sectionNumber) {
        MyPlacesFragment fragment = new MyPlacesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    List<ApartmentResponse> listApartment;

    public MyPlacesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_places, container, false);
        if (!getAuthToken().equals("error")) {
            requestData();
        } else {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, 0, R.animator.exit_right)
                    .addToBackStack(null)
                    .add(R.id.container, LoginFragment.newInstance(1))
                    .commit();
        }
        return rootView;
    }

    /**
     * Request full details of selected apartment
     */
    private void requestData() {
        listApartment = new ArrayList<>();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        ProfileAPI api = adapter.create(ProfileAPI.class);

        api.getUserFavorites(getAuthToken(), getUsername(), new Callback<Apartment>() {
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

    /**
     * If data request was successful, update display with data from response
     */
    protected void updateDisplay() {
        MyPlacesAdapter adapter = new MyPlacesAdapter(getActivity(), R.layout.list_my_places, listApartment);
        setListAdapter(adapter);
    }

    private String getAuthToken() {
        SharedPreferences prefs = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("token", "error");
    }

    private String getUsername() {
        SharedPreferences prefs = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("username", "error");
    }

}
