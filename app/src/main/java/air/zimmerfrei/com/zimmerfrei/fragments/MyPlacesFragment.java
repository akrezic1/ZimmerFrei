package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import air.zimmerfrei.com.zimmerfrei.ApartmentListFragment;
import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.webservice.ProfileAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Andro on 29.10.2014.
 */
public class MyPlacesFragment extends ApartmentListFragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_places, container, false);
        if (!getAuthToken().equals("error")) {
            requestData();
        } else {
            Toast.makeText(getActivity(), R.string.login_or_register, Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, 0, R.animator.exit_right)
                    .addToBackStack(null)
                    .add(R.id.container, LoginOrRegisterFragment.newInstance(1))
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
                updateDisplay(R.layout.list_my_places);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Retrofit log: ", error.getMessage());
            }
        });
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
