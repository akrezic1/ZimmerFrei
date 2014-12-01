package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Activity;
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
import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 10.11.2014..
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
        requestData();
        return rootView;
    }

    private void requestData() {
        listApartment = new ArrayList<ApartmentResponse>();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        ApartmentAPI api = adapter.create(ApartmentAPI.class);

        api.getApartments(new Callback<Apartment>() {
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

    protected void updateDisplay() {
        NearMeListAdapter adapter = new NearMeListAdapter(getActivity(), R.layout.list_near_me, listApartment);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ApartmentDetailsFragment.newInstance(1))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}