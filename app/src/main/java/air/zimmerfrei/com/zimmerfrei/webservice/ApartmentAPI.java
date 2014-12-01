package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment.Apartment;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Andro on 29.11.2014..
 */
public interface ApartmentAPI {

    @GET("/zimmer-frei/public/api/v1/locations?lat=46.00&lng=16.00&range=1")
    public void getApartments(Callback<Apartment> apartman);

}
