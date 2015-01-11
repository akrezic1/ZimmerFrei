package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.SwypeFragment;
import air.zimmerfrei.com.zimmerfrei.datamodel.LaravelSessionToken;
import air.zimmerfrei.com.zimmerfrei.datamodel.profile.Profile;
import air.zimmerfrei.com.zimmerfrei.webservice.LoginAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andro on 22.12.2014.
 */
public class LoginFragment extends SwypeFragment implements View.OnClickListener {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * ENDPOINT is base location of web services
     */
    public static final String ENDPOINT = "http://188.226.150.65";

    public static String laravelToken;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LoginFragment newInstance(int sectionNumber) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnLogin = (Button) view.findViewById(R.id.login_button);
        getLaravelToken();
        btnLogin.setOnClickListener(this);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        TextView username = (TextView) getView().findViewById(R.id.username);
        TextView password = (TextView) getView().findViewById(R.id.password);

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user .equals("") || pass.equals("")) {
            Toast.makeText(getActivity(), R.string.no_user_pass, Toast.LENGTH_SHORT).show();
        } else {
            requestData(user, pass, laravelToken);
        }
    }

    private void getLaravelToken() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        LoginAPI api = adapter.create(LoginAPI.class);

        api.getLaravelSessionToken(new Callback<LaravelSessionToken>() {
            @Override
            public void success(LaravelSessionToken laravelSessionToken, Response response) {
                laravelToken = laravelSessionToken.getSessionToken();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), R.string.connection_fail, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * This method is used for users authentication, it sends username and password to server
     * and if that user exists, it returns users data
     * @param user username
     * @param pass password
     */
    private void requestData(String user, String pass, String token) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        LoginAPI api = adapter.create(LoginAPI.class);

        api.login(user, pass, token, new Callback<Profile>() {
            @Override
            public void success(Profile profile, retrofit.client.Response response) {
                if (profile.getStatus() == 200) {
                    saveToSharedPref(profile);
                    openProfile();
                } else {
                    Toast.makeText(getActivity(), R.string.wrong_user_pass, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), R.string.connection_fail, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method is called if user is authenticated, this method saves users data to
     * SharedPreferences for future use
     * @param profile is response from server with users details
     */
    protected void saveToSharedPref(Profile profile) {
        SharedPreferences sp = getActivity().getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);

        sp.edit().putString("token", profile.getRememberToken()).apply();
        sp.edit().putString("name", profile.getResponse().getName()).apply();
        sp.edit().putString("surname", profile.getResponse().getSurname()).apply();
        sp.edit().putString("avatar", profile.getResponse().getAvatar()).apply();
        sp.edit().putString("phone", profile.getResponse().getPhone()).apply();
        sp.edit().putString("email", profile.getResponse().getEmail()).apply();
    }

    /**
     * Open MyProfileFragment after saving users data
     */
    protected void openProfile() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                .replace(R.id.container, MyProfileFragment.newInstance(1))
                .commit();
    }
}
