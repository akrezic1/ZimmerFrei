package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartmentdetails.ApartmentDetailsResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface with methods used to get apartments from web services using Retrofit
 * Created by Andro on 29.11.2014..
 */
public interface ApartmentAPI {

    /**
     * Retrofit method that returns json with all apartments in given range
     * @param lat is latitude as in geographic coordinate
     * @param lng is longitude as in geographic coordinate
     * @param range sets the range on map where pins will spawn
     * @param apartment is response from server
     */
    @GET("/api/v1/locations")
    public void getApartments(
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("range") String range,
            Callback<Apartment> apartment);

    /**
     * Retrofit method that returns json with details for given apartment
     * @param id is apartments ID, which is unique for every apartment
     * @param response is servers response
     */
    @GET("/api/v1/apartmentDetails")
    public void getApartmentDetails(
            @Query("apartment_id") int id,
            @Query("lat") String lat,
            @Query("lng") String lng,
            Callback<ApartmentDetailsResponse> response);

    /**
     * Retrofit method used to get apartments best offers
     * @param lat is latitude as in geographic coordinate
     * @param lng is longitude as in geographic coordinate
     * @param apartment is response from server
     */
    @GET("/api/v1/apartmentSpecialOffers")
    public void getBestOffers(
            @Query("lat") String lat,
            @Query("lng") String lng,
            Callback<Apartment> apartment
    );

}
