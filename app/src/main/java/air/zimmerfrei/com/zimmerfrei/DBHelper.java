package air.zimmerfrei.com.zimmerfrei;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.ApartmentResponse;

/**
 * Helper class used for ActiveAndroid database queries
 * Created by Andro on 25.1.2015.
 */
public class DBHelper {

    /**
     * Save full list of apartments in local database
     * @param list ApartmentResponse list
     */
    public static void saveListApartment(List<ApartmentResponse> list) {
        new Delete().from(ApartmentResponse.class).execute();

        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ApartmentResponse apartment = new ApartmentResponse();

                apartment.setApartment_id(list.get(i).getApartment_id());
                apartment.setIdMember(list.get(i).getIdMember());
                apartment.setAddress(list.get(i).getAddress());
                apartment.setCapacity(list.get(i).getCapacity());
                apartment.setCity(list.get(i).getCity());
                apartment.setCover_photo(list.get(i).getCover_photo());
                apartment.setDescription(list.get(i).getDescription());
                apartment.setEmail(list.get(i).getEmail());
                apartment.setLat(list.get(i).getLat());
                apartment.setLng(list.get(i).getLng());
                apartment.setName(list.get(i).getName());
                apartment.setPhone(list.get(i).getPhone());
                apartment.setPhone2(list.get(i).getPhone2());
                apartment.setPrice(list.get(i).getPrice());
                apartment.setRating(list.get(i).getRating());
                apartment.setStars(list.get(i).getStars());
                apartment.setType(list.get(i).getType());
                apartment.setUserEmail(list.get(i).getUserEmail());
                apartment.setUserNickname(list.get(i).getUserNickname());
                apartment.setUserPhone(list.get(i).getUserPhone());

                apartment.save();
            }
            ActiveAndroid.setTransactionSuccessful();

        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    /**
     * Check if user already saved apartment to favorites
     * @param id apartments id
     * @return returns true if saved, false if not
     */
    public static boolean isApartmentSaved(String id) {
        List<Model> response =  new Select().from(ApartmentResponse.class).where("apartment_id = ?", id).execute();
        return !response.isEmpty();
    }

}
