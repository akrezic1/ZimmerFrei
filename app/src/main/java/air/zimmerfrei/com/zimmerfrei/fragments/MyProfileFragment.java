package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class MyProfileFragment extends Fragment {

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
    }

    protected void getUserData() {
        SharedPreferences sp = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);

        name = sp.getString("name", "error");
        surname = sp.getString("surname", "error");
        email = sp.getString("email", "error");
        phone = sp.getString("phone", "error");
        avatar = sp.getString("avatar", "error");
    }

}
