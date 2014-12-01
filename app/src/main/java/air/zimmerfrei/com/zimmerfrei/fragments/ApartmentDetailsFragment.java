package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.datamodel.ApartmentDetails.ApartmentDetailsResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentDetailsAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Andro on 10.11.2014..
 */
public class ApartmentDetailsFragment extends Fragment {

    ApartmentDetailsResponse listResponse;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * ENDPOINT is base location of web services
     */
    public static final String ENDPOINT = "http://188.226.150.65/";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ApartmentDetailsFragment newInstance(int sectionNumber) {
        ApartmentDetailsFragment fragment = new ApartmentDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ApartmentDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apartment_details, container, false);
        getActivity().setTitle("Apartment details");

        requestData();

        return rootView;
    }

    private void requestData() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        ApartmentDetailsAPI api = adapter.create(ApartmentDetailsAPI.class);

        api.getApartmentDetails(2, new Callback<ApartmentDetailsResponse>() {
            @Override
            public void success(ApartmentDetailsResponse apartmentResponse, Response response) {
                Log.d("RETROFIT: ", "SUCCESS!!!");
                listResponse = apartmentResponse;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("RETROFIT: ", "FAIL!!!");
            }
        });
    }

    protected void updateDisplay() {
        TextView name = (TextView) getView().findViewById(R.id.text_apartment_name);
        name.setText(listResponse.getResponse().get(0).getName());

        RatingBar rating = (RatingBar) getView().findViewById(R.id.rating_apartment);
        rating.setRating(Float.parseFloat(listResponse.getResponse().get(0).getRating()));

        TextView details = (TextView) getView().findViewById(R.id.text_apartment_details);
        details.setText(listResponse.getResponse().get(0).getDescription());
    }

}
