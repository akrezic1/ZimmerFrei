package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.ResponseStatus;
import air.zimmerfrei.com.zimmerfrei.datamodel.apartment.Apartment;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Retrofit interface for web services involving users preferences
 * Created by Andro on 11.1.2015.
 */
public interface ProfileAPI {

    /**
     * Save apartment to MyPlaces
     * @param token session token
     * @param username users username
     * @param id apartments ID
     * @param response response from server
     */
    @FormUrlEncoded
    @POST("/api/v1/setUserFavorites")
    public void setUserFavorite(
            @Field("_token") String token,
            @Field("username") String username,
            @Field("apartment") int id,
            Callback<ResponseStatus> response
    );

    /**
     * Remove apartment from MyPlaces
     * @param token session token
     * @param username users username
     * @param id apartments ID
     * @param response response from server
     */
    @FormUrlEncoded
    @POST("/api/v1/deleteUserFavorites")
    public void deleteUserFavorite(
            @Field("_token") String token,
            @Field("username") String username,
            @Field("apartment") int id,
            Callback<ResponseStatus> response
    );

    /**
     * Get list with users favourite apartments
     * @param token session token
     * @param username users username
     * @param apartment response from server
     */
    @FormUrlEncoded
    @POST("/api/v1/getUserFavorites")
    public void getUserFavorites(
            @Field("_token") String token,
            @Field("username") String username,
            Callback<Apartment> apartment
    );
}
