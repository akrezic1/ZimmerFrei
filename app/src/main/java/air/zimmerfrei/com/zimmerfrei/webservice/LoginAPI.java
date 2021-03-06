package air.zimmerfrei.com.zimmerfrei.webservice;

import air.zimmerfrei.com.zimmerfrei.datamodel.LaravelSessionToken;
import air.zimmerfrei.com.zimmerfrei.datamodel.profile.Profile;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Interface with methods that registered users can use, or to register new account
 * Created by Andro on 21.12.2014.
 */
public interface LoginAPI {

    /**
     * Retrofit method used to authenticate user, if it is successful it returns users profile data,
     * including name, surname, username, email, phone, avatar and authentication token
     * @param username users username
     * @param password users password
     * @param callback response from server
     */
    @FormUrlEncoded
    @POST("/api/v1/login")
    public void login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("_token") String token,
            Callback<Profile> callback
    );

    /**
     * Getting Laravel session token from web, it is used to communicate with server
     * @param session_token returns token to use for communication
     */
    @GET("/auth/token")
    public void getLaravelSessionToken(
        Callback<LaravelSessionToken> session_token
    );

    /**
     * Register new user
     * @param token Laravel session token that is needed to communicate with web service
     * @param email users email
     * @param username users username
     * @param password users password
     * @param gcm Google Cloud Messaging ID that is used for push notifications
     * @param callback response from server
     */
    @FormUrlEncoded
    @POST("/api/v1/signup")
    public void register(
            @Field("_token") String token,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("gcm_phone_id") String gcm,
            Callback<Profile> callback
    );

}
