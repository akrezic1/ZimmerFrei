package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.ApartmentDetails.ApartmentDetailsResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Andro on 1.12.2014..
 */
public interface ApartmentDetailsAPI {

    @GET("/zimmer-frei/public/api/v1/apartmentDetails")
    public void getApartmentDetails(@Query("apartment_id") int id, Callback<ApartmentDetailsResponse> response);

}
