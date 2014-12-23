package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.adapters.CircleTransform;
import air.zimmerfrei.com.zimmerfrei.datamodel.profile.Profile;
import air.zimmerfrei.com.zimmerfrei.webservice.LoginAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by Andro on 29.10.2014..
 */
public class MyProfileFragment extends Fragment implements View.OnClickListener {

    private String name, surname, email, phone, avatar;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MyProfileFragment newInstance(int sectionNumber) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MyProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        updateDisplay(view);
        return view;
    }

    protected void updateDisplay(View view) {
        getUserData();

        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
        Picasso.with(getActivity()).load(avatar).transform(new CircleTransform()).into(imageView);

        TextView fullname = (TextView) view.findViewById(R.id.profile_name_lastname);
        String fullName = name + " " + surname;
        fullname.setText(fullName);

        Button btnLogout = (Button) view.findViewById(R.id.profile_logout);
        btnLogout.setOnClickListener(this);
    }

    protected void getUserData() {
        SharedPreferences sp = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);

        name = sp.getString("name", "error");
        surname = sp.getString("surname", "error");
        email = sp.getString("email", "error");
        phone = sp.getString("phone", "error");
        avatar = sp.getString("avatar", "error");
    }

    @Override
    public void onClick(View v) {
        alertDialog();
    }

    private void alertDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.logout)
                .setMessage(R.string.are_you_sure_logout)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSharedPref();
                        openHome();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteSharedPref() {
        SharedPreferences sp = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    private void openHome() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance(1))
                .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                .commit();
    }
}
