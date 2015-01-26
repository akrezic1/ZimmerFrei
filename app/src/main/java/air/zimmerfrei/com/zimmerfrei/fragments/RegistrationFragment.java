package air.zimmerfrei.com.zimmerfrei.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Fragment containing registration form
 * Created by Andro on 23.12.2014.
 */
public class RegistrationFragment extends SwypeFragment implements View.OnClickListener {

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
    public static RegistrationFragment newInstance(int sectionNumber) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        rootView.setOnTouchListener(this);
        getLaravelToken();

        Button btnRegister = (Button) rootView.findViewById(R.id.button_register);
        btnRegister.setOnClickListener(this);

        return rootView;
    }

    /**
     * Returns RestAdapter that is needed to call web service
     * @return LoginAPI RestAdapter
     */
    private LoginAPI getRestAdapter() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.ENDPOINT))
                .build();

        return adapter.create(LoginAPI.class);
    }

    @Override
    public void onClick(View v) {
        LoginAPI api = getRestAdapter();

        EditText txtUser = (EditText) getView().findViewById(R.id.editUsername);
        EditText txtPass = (EditText) getView().findViewById(R.id.editPassword);
        EditText txtEmail = (EditText) getView().findViewById(R.id.editEmail);

        String username = txtUser.getText().toString();
        String password = txtPass.getText().toString();
        String email = txtEmail.getText().toString();
        String gcm = SharedPrefsHelper.getGCMid(getActivity());

        api.register(laravelToken, email, username, password, gcm, new Callback<Profile>() {
            @Override
            public void success(Profile profile, Response response) {
                SharedPrefsHelper.saveToSharedPref(profile, getActivity());
                closeActivity();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), R.string.registration_problem, Toast.LENGTH_SHORT).show();
                Log.d("Registration", error.getMessage());
            }
        });
    }

    /**
     * Close LoginActivity after login and get back to MainActivity
     */
    protected void closeActivity() {
        getActivity().finish();
    }

    private void getLaravelToken() {
        LoginAPI api = getRestAdapter();

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

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(R.string.registration);
    }
}
