package air.zimmerfrei.com.zimmerfrei.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.ApartmentListFragment;
import air.zimmerfrei.com.zimmerfrei.DBHelper;
import air.zimmerfrei.com.zimmerfrei.LoginActivity;
import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.SharedPrefsHelper;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
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

        if (isNetworkAvailable()) {
            if (!SharedPrefsHelper.getAuthToken(getActivity()).equals("error")) {
                requestData();
            } else {
                Toast.makeText(getActivity(), R.string.login_or_register, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        } else {
            listApartment = loadFromDB();
            updateDisplay(R.layout.list_my_places);
        }

        return rootView;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private List<ApartmentResponse> loadFromDB() {
        return new Select().from(ApartmentResponse.class).execute();
    }

    /**
     * Request full details of selected apartment
     */
    private void requestData() {
        listApartment = new ArrayList<>();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        ProfileAPI api = adapter.create(ProfileAPI.class);

        api.getUserFavorites(
                SharedPrefsHelper.getAuthToken(getActivity()),
                SharedPrefsHelper.getUsername(getActivity()),
                new Callback<Apartment>() {
            @Override
            public void success(Apartment apartments, Response response) {
                listApartment = apartments.getResponse();
                DBHelper.saveListApartment(listApartment);
                updateDisplay(R.layout.list_my_places);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Retrofit log: ", error.getMessage());
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(R.string.title_section_my_places);
    }
}
