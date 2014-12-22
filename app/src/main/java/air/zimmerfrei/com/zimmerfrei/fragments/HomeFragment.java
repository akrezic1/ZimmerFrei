package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import air.zimmerfrei.com.zimmerfrei.MainActivity;
import air.zimmerfrei.com.zimmerfrei.R;

/**
 * Created by Andro on 27.11.2014..
 */
public class HomeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public HomeFragment() {

    }

    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
