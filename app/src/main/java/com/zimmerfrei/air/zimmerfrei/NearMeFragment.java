package com.zimmerfrei.air.zimmerfrei;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andro on 29.10.2014..
 */
public class NearMeFragment extends Fragment {

    public NearMeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_near_me, container, false);
        return rootView;
    }
}
