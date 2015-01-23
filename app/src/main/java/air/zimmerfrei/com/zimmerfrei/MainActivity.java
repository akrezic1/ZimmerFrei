package air.zimmerfrei.com.zimmerfrei;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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


public class MainActivity extends FragmentActivity implements
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
     * @param position represents navigation drawer item position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new Fragment();

        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance(position + 1);
                break;
            case 1:
                fragment = NearMeMapFragment.newInstance(position + 1);
                break;
            case 2:
                fragment = NearMeListFragment.newInstance(position + 1);
                break;
            case 3:
                fragment = MyPlacesFragment.newInstance(position + 1);
                break;
            case 4:
                if (SharedPrefsHelper.getAuthToken(this).equals("error")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    fragment = MyProfileFragment.newInstance(position + 1);
                }
                break;
            case 5:
                fragment = HelpFragment.newInstance(position + 1);
                break;
            case 6:
                fragment = AboutFragment.newInstance(position + 1);
                break;
        }

        if (position != 0) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, R.animator.exit_bottom)
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, R.animator.exit_bottom)
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(client);
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            Toast.makeText(getApplication(), latitude + " " + longitude, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
        public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, R.string.could_not_connect, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        client.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNavigationDrawerItemSelected(0);
    }
}
