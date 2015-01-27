package air.zimmerfrei.com.zimmerfrei;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import air.zimmerfrei.com.zimmerfrei.fragments.AboutFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.HelpFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.HomeFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.MyPlacesFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.MyProfileFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.NearMeListFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.NearMeMapFragment;


public class MainActivity extends Activity implements
        NavigationDrawerCallbacks,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static FragmentManager fManager;
    private GoogleApiClient client;
    public static double latitude;
    public static double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        fManager = getFragmentManager();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getCurrentLocation();
    }


    /**
     * Getting users current location on start of application
     */
    private void getCurrentLocation() {

        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        client.connect();
    }

    /**
     * Switch between fragments on navigation drawer click
     *
     * @param position represents navigation drawer item position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance(position + 1);
                switchFragment(fragment, false);
                break;
            case 1:
                fragment = NearMeMapFragment.newInstance(position + 1);
                switchFragment(fragment, true);
                break;
            case 2:
                fragment = NearMeListFragment.newInstance(position + 1);
                switchFragment(fragment, true);
                break;
            case 3:
                if (SharedPrefsHelper.getAuthToken(this).equals("error")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    fragment = MyPlacesFragment.newInstance(position + 1);
                    switchFragment(fragment, true);
                }
                break;
            case 4:
                if (SharedPrefsHelper.getAuthToken(this).equals("error")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    fragment = MyProfileFragment.newInstance(position + 1);
                    switchFragment(fragment, true);
                }
                break;
            case 5:
                fragment = HelpFragment.newInstance(position + 1);
                switchFragment(fragment, true);
                break;
            case 6:
                fragment = AboutFragment.newInstance(position + 1);
                switchFragment(fragment, true);
                break;
        }
    }

    /**
     * Switch fragment and choose if you want to put it on backstack
     *
     * @param fragment  fragment that is going to be added
     * @param backstack boolean value - true if you want to put it on backstack
     */
    private void switchFragment(Fragment fragment, boolean backstack) {
        FragmentManager fragmentManager = getFragmentManager();
        if (backstack) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // clear previous fragments from backstack
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, R.animator.enter_top, R.animator.exit_bottom)
                    .addToBackStack(null)
                    .replace(R.id.container, fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            if (SharedPrefsHelper.getAuthToken(this).equals("error")) {
                getMenuInflater().inflate(R.menu.main, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_signed_in, menu);
            }
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.action_sign_out:
                invalidateOptionsMenu();
                SharedPrefsHelper.signOutAlert(this);
                break;

            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(client);
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, R.string.could_not_connect, Toast.LENGTH_SHORT).show();
        latitude = 46.00;
        longitude = 16.00;
    }

    @Override
    protected void onStop() {
        super.onStop();
        client.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
