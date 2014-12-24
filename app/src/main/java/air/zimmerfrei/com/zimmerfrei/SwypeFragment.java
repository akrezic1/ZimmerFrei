package air.zimmerfrei.com.zimmerfrei;

import android.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Andro on 24.12.2014.
 * SwypeFragment is Fragment implementation with methods for popping back stack that returns
 * user to last fragment. It uses OnTouchListener that listens to swype from right to left.
 */
public abstract class SwypeFragment extends Fragment implements View.OnTouchListener{

    float x1 = 0, x2 = 0;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = event.getX();
                if (x1 < x2) {
                    getFragmentManager().popBackStack();
                }
            }
        }
        return true;
    }
}
