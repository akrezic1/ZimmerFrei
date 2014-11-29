package air.zimmerfrei.com.zimmerfrei.webservice;

import java.util.List;

import air.zimmerfrei.com.zimmerfrei.datamodel.Apartment;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Andro on 29.11.2014..
 */
public interface ApartmentAPI {

    @GET("/~akrezic/apartments.json")
    public void getApartments(Callback<List<Apartment>> response);

}
