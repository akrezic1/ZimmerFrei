package air.zimmerfrei.com.zimmerfrei.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import air.zimmerfrei.com.zimmerfrei.R;
import air.zimmerfrei.com.zimmerfrei.SharedPrefsHelper;
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
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
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
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        LoginAPI api = adapter.create(LoginAPI.class);

        api.login(user, pass, token, new Callback<Profile>() {
            @Override
            public void success(Profile profile, retrofit.client.Response response) {
                if (profile.getStatus() == 200) {
                    SharedPrefsHelper.saveToSharedPref(profile, getActivity());
                    closeActivity();
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
     * Close LoginActivity after login and get back to MainActivity
     */
    protected void closeActivity() {
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(R.string.login);
    }
}
