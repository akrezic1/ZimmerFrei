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
 * Created by Andro on 23.12.2014.
 */
public class LoginOrRegisterFragment extends Fragment implements View.OnClickListener{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LoginOrRegisterFragment newInstance(int sectionNumber) {
        LoginOrRegisterFragment fragment = new LoginOrRegisterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginOrRegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login_register, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View view) {
        Button btnCreateAcc = (Button) view.findViewById(R.id.btn_create_acc);
        Button btnLogin = (Button) view.findViewById(R.id.btn_login);

        btnCreateAcc.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.btn_create_acc:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, R.animator.enter_left, R.animator.exit_right)
                        .addToBackStack(null)
                        .replace(R.id.container, RegistrationFragment.newInstance(1))
                        .commit();
                break;
            case R.id.btn_login:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, R.animator.enter_left, R.animator.exit_right)
                        .addToBackStack(null)
                        .replace(R.id.container, LoginFragment.newInstance(1))
                        .commit();
                break;
        }
    }
}
