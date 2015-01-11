package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.ResponseStatus;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Retrofit interface for web services involving users preferences
 * Created by Andro on 11.1.2015.
 */
public interface ProfileAPI {

    @FormUrlEncoded
    @POST("/api/v1/setUserFavorites")
    public void setUserFavorite(
        @Field("_token") String token,
        @Field("username") String username,
        @Field("apartment") int id,
        Callback<ResponseStatus> response
    );
}
