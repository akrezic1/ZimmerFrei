package air.zimmerfrei.com.zimmerfrei.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import air.zimmerfrei.com.zimmerfrei.MainActivity;
import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.SharedPrefsHelper;
import air.zimmerfrei.com.zimmerfrei.SwypeFragment;
import air.zimmerfrei.com.zimmerfrei.adapters.ApartmentDetailsPager;
import air.zimmerfrei.com.zimmerfrei.datamodel.ResponseStatus;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartmentdetails.ApartmentDetailsResponse;
import air.zimmerfrei.com.zimmerfrei.webservice.ApartmentAPI;
import air.zimmerfrei.com.zimmerfrei.webservice.ProfileAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 10.11.2014..
 */
public class ApartmentDetailsFragment extends SwypeFragment {

    ApartmentDetailsResponse listResponse;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ApartmentDetailsFragment newInstance(int sectionNumber, int apartment_id) {
        ApartmentDetailsFragment fragment = new ApartmentDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt("ID", apartment_id);
        fragment.setArguments(args);
        return fragment;
    }

    public ApartmentDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apartment_details, container, false);
        getActivity().setTitle(R.string.title_apartment_details);

        setHasOptionsMenu(true);
        requestData();
        rootView.setOnTouchListener(this);

        return rootView;
    }

    /**
     * Request data from server with all apartment details
     */
    private void requestData() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        ApartmentAPI api = adapter.create(ApartmentAPI.class);

        Bundle bundle = getArguments();
        double lat = MainActivity.latitude;
        double lng = MainActivity.longitude;

        api.getApartmentDetails(bundle.getInt("ID"), String.valueOf(lat), String.valueOf(lng), new Callback<ApartmentDetailsResponse>() {
            @Override
            public void success(ApartmentDetailsResponse apartmentResponse, Response response) {
                listResponse = apartmentResponse;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("RETROFIT: ", error.getMessage());
            }
        });
    }

    protected void updateDisplay() {
        TextView name = (TextView) getView().findViewById(R.id.text_apartment_name);
        name.setText(listResponse.getResponse().get(0).getName());

        getActivity().getActionBar().setTitle(listResponse.getResponse().get(0).getName());

        RatingBar rating = (RatingBar) getView().findViewById(R.id.rating_apartment);
        rating.setRating(Float.parseFloat(listResponse.getResponse().get(0).getRating()));

        TextView details = (TextView) getView().findViewById(R.id.text_apartment_details);
        details.setText(listResponse.getResponse().get(0).getDescription());

        if (listResponse.getResponse().get(0).getDistanceTo() != null) {
            TextView distance = (TextView) getView().findViewById(R.id.apartmentDetailsDistance);
            distance.setText(listResponse.getResponse().get(0).getDistanceTo() + "km " + getActivity().getString(R.string.distance));
        }

        int size = listResponse.getResponse().get(0).getPictures().size();
        String[] pictures = new String[size + 1];
        if (size == 0) {
            pictures[0] = listResponse.getResponse().get(0).getCoverPhoto();
        } else {
            for (int i = 0; i < size; i++) {
                pictures[i] = listResponse.getResponse().get(0).getPictures().get(i).getUrl();
            }
        }
        ViewPager pager = (ViewPager) getView().findViewById(R.id.pager_apartment_details);
        ApartmentDetailsPager adapter = new ApartmentDetailsPager(getActivity(), pictures);
        pager.setAdapter(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator) getView().findViewById(R.id.titles);
        indicator.setViewPager(pager);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_apartment_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark) {
            bookmarkApartment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bookmarkApartment() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        ProfileAPI api = adapter.create(ProfileAPI.class);
        api.setUserFavorite(
                SharedPrefsHelper.getAuthToken(getActivity()),
                SharedPrefsHelper.getUsername(getActivity()),
                Integer.parseInt(listResponse.getResponse().get(0).getId()),
                new Callback<ResponseStatus>() {
            @Override
            public void success(ResponseStatus responseStatus, Response response) {
                if (responseStatus.getStatus() == 200) {
                    Toast.makeText(getActivity(), "Saved to MyPlaces", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), R.string.connection_fail, Toast.LENGTH_SHORT).show();
                Log.d("BOOKMARK", error.getMessage());
            }
        });
    }
}
