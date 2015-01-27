package air.zimmerfrei.com.zimmerfrei;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import air.zimmerfrei.com.zimmerfrei.fragments.NearMeMapFragment;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public MainActivityTest() {
        super(MainActivity.class);
    }

    NearMeMapFragment mapFragment;
    private MainActivity mainActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = (MainActivity) getActivity();
    }



    public void testFragment() {
        MainActivityTest fragment = new MainActivityTest() {
            //Override methods and add assertations here.
        };
    }
}