package air.zimmerfrei.com.zimmerfrei;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.gms.internal.in;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationLanguageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public ApplicationLanguageTest() {
        super(MainActivity.class);
    }

    FrameLayout layout;
    private MainActivity mainActivity;
    private SettingsActivity settingsActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);

        mainActivity = getActivity();
        layout = (FrameLayout) mainActivity.findViewById(R.id.container);

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(mainActivity, R.id.action_settings, 1);


        //getInstrumentation().invokeMenuActionSync(mainActivity, R.id.spLanguageType, 2);
        //final Button btn = (Button) mainActivity.findViewById(R.id.button_save_settings);
        //btn.performClick();

        Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 5000);
        assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
        a.finish();
    }

    public void testPreconditions () throws Exception {
    }
}