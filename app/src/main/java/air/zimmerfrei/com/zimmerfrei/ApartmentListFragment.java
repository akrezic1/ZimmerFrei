package air.zimmerfrei.com.zimmerfrei;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import air.zimmerfrei.com.zimmerfrei.adapters.ApartmentListAdapter;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;
import air.zimmerfrei.com.zimmerfrei.fragments.ApartmentDetailsFragment;

/**
 * Abstract class implementation for fragments that use lists with apartments, it's only required
 * to populate listApartment list with apartments and call updateDisplay(int LayoutID) method
 * with ID from layout that displays list
 * layout
 * Created by Andro on 13.1.2015.
 */
public abstract class ApartmentListFragment extends ListFragment{

    /**
     * The fragment argument representing the section number for this fragment.
     */
    protected static final String ARG_SECTION_NUMBER = "section_number";

    protected List<ApartmentResponse> listApartment;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int apartmentId;
        if (listApartment.get(position).getApartment_id() == null) {
            apartmentId = Integer.parseInt(listApartment.get(position).getIdMember());
        } else {
            apartmentId = Integer.parseInt(listApartment.get(position).getApartment_id());
        }

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
        if (listApartment.isEmpty() || listApartment.get(0).getDistanceTo() != null) {
            Collections.sort(listApartment, new Comparator<ApartmentResponse>() {
                @Override
                public int compare(ApartmentResponse lhs, ApartmentResponse rhs) {
                    return (int) (Float.parseFloat(lhs.getDistanceTo()) - Float.parseFloat(rhs.getDistanceTo()));
                }
            });
        } else {
            //Toast.makeText(getActivity(), R.string.no_favorites, Toast.LENGTH_LONG).show();
        }
        ApartmentListAdapter adapter = new ApartmentListAdapter(getActivity(), layoutID, listApartment);
        setListAdapter(adapter);
    }
}
