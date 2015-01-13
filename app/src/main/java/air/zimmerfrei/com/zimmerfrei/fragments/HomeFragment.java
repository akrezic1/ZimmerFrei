package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import air.zimmerfrei.com.zimmerfrei.R;

/**
 * Created by Andro on 27.11.2014..
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnNearMe = (Button) view.findViewById(R.id.button_near_me);
        Button btnBestOffers = (Button) view.findViewById(R.id.button_best_offers);
        btnNearMe.setOnClickListener(this);
        btnBestOffers.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.button_near_me:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                        .addToBackStack(HomeFragment.class.getName())
                        .replace(R.id.container, NearMeMapFragment.newInstance(1))
                        .commit();
                break;
            case R.id.button_best_offers:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                        .addToBackStack(HomeFragment.class.getName())
                        .replace(R.id.container, BestOffersFragment.newInstance(1))
                        .commit();
                break;
        }
    }
}
