package air.zimmerfrei.com.zimmerfrei;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import air.zimmerfrei.com.zimmerfrei.fragments.AboutFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.HelpFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.HomeFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.LoginFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.MyPlacesFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.MyProfileFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.NearMeListFragment;
import air.zimmerfrei.com.zimmerfrei.fragments.NearMeMapFragment;


public class MainActivity extends FragmentActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private boolean DEVELOPER_MODE = true;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static FragmentManager fManager;
    private GoogleApiClient client;
    public static double latitude;
    public static double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEVELOPER_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()// or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new Fragment();

        if (position == 0) {
            fragment = HomeFragment.newInstance(position + 1);
        }
        else if (position == 1) {
            fragment = NearMeMapFragment.newInstance(position + 1);
        }
        else if (position == 2) {
            fragment = NearMeListFragment.newInstance(position + 1);
        }
        else if (position == 3) {
            fragment = MyPlacesFragment.newInstance(position + 1);
        }
        else if (position == 4) {
            if (loadToken() == 1) {
                fragment = MyProfileFragment.newInstance(position + 1);
            }
            else {
                fragment = LoginFragment.newInstance(position + 1);
            }
        }
        else if (position == 5) {
            fragment = HelpFragment.newInstance(position + 1);
        }
        else if (position == 6) {
            fragment = AboutFragment.newInstance(position + 1);
        }

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.enter_bottom, R.animator.exit_top, 0, 0)
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_home);
                break;
            case 2:
                mTitle = getString(R.string.title_section1);
                break;
            case 3:
                mTitle = getString(R.string.title_section2);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
            case 5:
                mTitle = getString(R.string.title_section4);
                break;
            case 6:
                mTitle = getString(R.string.title_section5);
                break;
            case 7:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
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

    private int loadToken () {
        SharedPreferences sp = this.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        String token = sp.getString("token", "Error obtaining token");
        if (token.equals("Error obtaining token"))
            return 0;
        else
            return 1;
    }
}
