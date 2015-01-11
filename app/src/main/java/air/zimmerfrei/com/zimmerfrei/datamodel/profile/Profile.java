package air.zimmerfrei.com.zimmerfrei.datamodel.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @Expose
    private Integer status;
    @Expose
    private Response response;
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     *
     * @return
     * The rememberToken
     */
    public String getRememberToken() {
        return rememberToken;
    }

    /**
     *
     * @param rememberToken
     * The remember_token
     */
    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

}