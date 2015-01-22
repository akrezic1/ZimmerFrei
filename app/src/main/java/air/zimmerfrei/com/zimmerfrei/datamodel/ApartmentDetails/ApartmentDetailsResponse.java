package air.zimmerfrei.com.zimmerfrei.datamodel.apartmentdetails;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDetailsResponse {

    @Expose
    private List<Response> response = new ArrayList<Response>();

    /**
     *
     * @return
     * The response
     */
    public List<Response> getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(List<Response> response) {
        this.response = response;
    }

}