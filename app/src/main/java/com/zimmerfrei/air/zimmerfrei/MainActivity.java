package com.zimmerfrei.air.zimmerfrei;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.zimmerfrei.air.db.Apartment;
import com.zimmerfrei.air.db.User;
import com.zimmerfrei.air.nav.DrawerItemCustomAdapter;
import com.zimmerfrei.air.nav.ObjectDrawerItem;


public class MainActivity extends Activity {

    /*
    * Handling navigation drawer
    */
    private String[] mNavigationDrawerItemTitles;   // List of titles for navigation drawer
    private DrawerLayout mDrawerLayout;             // Layout for navigation drawer
    private ListView mDrawerList;                   // List view for navigation drawer
    private ActionBarDrawerToggle mDrawerToggle;    // Used for listener to open/close drawer
    private CharSequence mDrawerTitle;              // Title to display in ActionBar when drawer is open
    private CharSequence mTitle;                    // Title to display in ActionBar when drawer is closed

    public static FragmentManager fragmentManager;  // Instance of FragmentManager used to switch between fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle(); // Set ActionBar title to app title

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array); // Getting navigation titles from strings.xml
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Filling the ListView with items
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[6];

        drawerItem[0] = new ObjectDrawerItem("Near me - map");
        drawerItem[1] = new ObjectDrawerItem("Near me - list");
        drawerItem[2] = new ObjectDrawerItem("My places");
        drawerItem[3] = new ObjectDrawerItem("Language");
        drawerItem[4] = new ObjectDrawerItem("Help");
        drawerItem[5] = new ObjectDrawerItem("About");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            public void onDrawerClosed (View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened (View view) {
                super.onDrawerOpened(view);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        fragmentManager = getFragmentManager();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    // Open a new fragment with click on drawer item
    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new NearMeFragment();
                    break;
                case 1:
                    fragment = new NearMeListFragment();
                    break;
                case 2:
                    fragment = new MyPlacesFragment();
                    break;
                case 3:
                    break;
                case 4:
                    fragment = new HelpFragment();
                    break;
                case 5:
                    fragment = new AboutFragment();
                    break;
                default:
                    break;
            }

            if (fragment != null) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }

        }
    }

}
