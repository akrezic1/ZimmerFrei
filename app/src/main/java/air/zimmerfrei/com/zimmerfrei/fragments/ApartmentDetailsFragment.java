package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.R;


/**
 * Created by Andro on 10.11.2014..
 */
public class ApartmentDetailsFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

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

    List<Integer> apartmentImages;

    public ApartmentDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_apartment_details, container, false);
        getActivity().setTitle("Apartment details");
        return rootView;
    }

    private void getImages() {
        apartmentImages.add(R.drawable.apartment);
        apartmentImages.add(R.drawable.apartment2);
        apartmentImages.add(R.drawable.apartment3);
        apartmentImages.add(R.drawable.apartment4);
        apartmentImages.add(R.drawable.apartment5);
        apartmentImages.add(R.drawable.apartment6);
    }

}
