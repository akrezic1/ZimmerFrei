package air.zimmerfrei.com.zimmerfrei;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.FrameLayout;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public ApplicationTest() {
        super(MainActivity.class);
    }

    FrameLayout layout;
    private MainActivity mainActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);

        mainActivity = getActivity();
        layout = (FrameLayout) mainActivity.findViewById(R.id.container);

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(mainActivity, R.id.action_login, 0);

        Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
        assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
        a.finish();
    }

    public void testPreconditions () throws Exception {
        //assertNotNull("BLABLALBA", layout);
    }
}