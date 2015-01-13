package air.zimmerfrei.com.zimmerfrei;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.adapters.ApartmentListAdapter;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.fragments.ApartmentDetailsFragment;

/**
 * Abstract class implementation for fragments that use lists with apartments
 * Created by Andro on 13.1.2015.
 */
public abstract class ApartmentListFragment extends ListFragment{

    /**
     * The fragment argument representing the section number for this fragment.
     */
    protected static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * ENDPOINT is base location of web services
     */
    public static final String ENDPOINT = "http://188.226.150.65";

    protected List<ApartmentResponse> listApartment;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int apartmentId = Integer.parseInt(listApartment.get(position).getId());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.enter_right, R.animator.exit_left, 0, R.animator.exit_right)
                .addToBackStack(null)
                .add(R.id.container, ApartmentDetailsFragment.newInstance(1, apartmentId))
                .commit();
    }

    /**
     * If data request was successful, update display with data from response
     */
    protected void updateDisplay(int layoutID) {
        ApartmentListAdapter adapter = new ApartmentListAdapter(getActivity(), layoutID, listApartment);
        setListAdapter(adapter);
    }
}
