package air.zimmerfrei.com.zimmerfrei.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.viewpagerindicator.CirclePageIndicator;

import air.zimmerfrei.com.zimmerfrei.DBHelper;
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
 * Fragment that is used to display details for apartment
 * Created by Andro on 10.11.2014..
 */
public class ApartmentDetailsFragment extends SwypeFragment implements
        CompoundButton.OnCheckedChangeListener, View.OnTouchListener {

    ApartmentDetailsResponse listResponse;
    ToggleButton bookmark;
    TextView name, details, distance;
    ViewPager pager;
    RatingBar rating;
    CirclePageIndicator indicator;
    ApartmentDetailsPager adapter;

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

        name = (TextView) rootView.findViewById(R.id.text_apartment_name);
        details = (TextView) rootView.findViewById(R.id.text_apartment_details);
        distance = (TextView) rootView.findViewById(R.id.apartmentDetailsDistance);
        rating = (RatingBar) rootView.findViewById(R.id.rating_apartment);
        pager = (ViewPager) rootView.findViewById(R.id.pager_apartment_details);
        indicator = (CirclePageIndicator) rootView.findViewById(R.id.titles);
        bookmark = (ToggleButton) rootView.findViewById(R.id.toggle_bookmark);

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

    /**
     * Update display with obtained data
     */
    protected void updateDisplay() {
        getActivity().getActionBar().setTitle(listResponse.getResponse().get(0).getName());

        name.setText(listResponse.getResponse().get(0).getName());
        rating.setRating(Float.parseFloat(listResponse.getResponse().get(0).getRating()));
        details.setText(listResponse.getResponse().get(0).getDescription());

        if (listResponse.getResponse().get(0).getDistanceTo() != null) {
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

        adapter = new ApartmentDetailsPager(getActivity(), pictures);
        pager.setAdapter(adapter);
        pager.setOnTouchListener(this); // If user touches ViewPager, remove auto scroll
        indicator.setViewPager(pager);

        if (DBHelper.isApartmentSaved(listResponse.getResponse().get(0).getId()))
            bookmark.setChecked(true);
        else
            bookmark.setChecked(false);
        bookmark.setOnCheckedChangeListener(this);

        runnable.run(); // Run auto scroll of images inside ViewPager
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);
        if (handler!= null) {
            handler.removeCallbacks(runnable); // Disable auto scroll
            pager.setOnTouchListener(null);
        }
        return true;
    }

    private int position = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            if( position >= adapter.getCount() - 1){
                position = 0;
            }else{
                position++;
            }
            pager.setCurrentItem(position, true);
            handler.postDelayed(runnable, 4000);
        }
    };

    /**
     * Method used to add apartment to bookmarks/MyPlaces
     */
    private void bookmarkApartment() {
        String authToken = SharedPrefsHelper.getAuthToken(getActivity());

        //If this is true then user is not logged in
        if (authToken.equals("error") || authToken.isEmpty()) {
            Toast.makeText(getActivity(), R.string.unauthorized_please, Toast.LENGTH_SHORT).show();
            bookmark.setChecked(false);
        }
        else{
            bookmark.setChecked(true);
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
                                Toast.makeText(getActivity(), R.string.saved_myplaces, Toast.LENGTH_SHORT).show();
                                bookmark.setChecked(true);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getActivity(), R.string.connection_fail, Toast.LENGTH_SHORT).show();
                            bookmark.setChecked(false);
                            Log.d("BOOKMARK", error.getMessage());
                        }
                    });
        }

    }

    /**
     * Method used to remove apartment from bookmarks/MyPlaces
     */
    private void removeBookmark() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        ProfileAPI api = adapter.create(ProfileAPI.class);
        api.deleteUserFavorite(
                SharedPrefsHelper.getAuthToken(getActivity()),
                SharedPrefsHelper.getUsername(getActivity()),
                Integer.parseInt(listResponse.getResponse().get(0).getId()),
                new Callback<ResponseStatus>() {
                    @Override
                    public void success(ResponseStatus responseStatus, Response response) {
                        Log.d("REMOVE", response.getReason());
                        if (responseStatus.getStatus() == 200) {
                            Toast.makeText(getActivity(), R.string.removed_myplaces, Toast.LENGTH_SHORT).show();
                            bookmark.setChecked(false);
                        } else {
                            Toast.makeText(getActivity(), R.string.unauthorized, Toast.LENGTH_SHORT).show();
                            bookmark.setChecked(true);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("REMOVE FAIL", error.getMessage());
                        bookmark.setChecked(true);
                        Toast.makeText(getActivity(), R.string.connection_fail, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            bookmarkApartment();
        } else {
            removeBookmark();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler!= null) {
            handler.removeCallbacks(runnable);
        }
    }
}
