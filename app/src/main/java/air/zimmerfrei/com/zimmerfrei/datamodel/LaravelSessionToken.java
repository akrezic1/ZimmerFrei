package air.zimmerfrei.com.zimmerfrei.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaravelSessionToken {

    @SerializedName("session_token")
    @Expose
    private String sessionToken;

    /**
     *
     * @return
     * The sessionToken
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     *
     * @param sessionToken
     * The session_token
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

}