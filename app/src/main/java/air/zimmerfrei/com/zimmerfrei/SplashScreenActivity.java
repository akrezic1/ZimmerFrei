package air.zimmerfrei.com.zimmerfrei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Activity that is first started with only ZimmerFrei logo, opens MainActivity after 2 seconds
 * Created by Andro on 26.1.2015.
 */
public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        }, 2000);
    }
}
