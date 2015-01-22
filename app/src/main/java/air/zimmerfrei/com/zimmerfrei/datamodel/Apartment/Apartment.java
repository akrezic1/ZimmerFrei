package air.zimmerfrei.com.zimmerfrei.datamodel.apartment;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Apartment {

    @Expose
    private List<ApartmentResponse> response = new ArrayList<ApartmentResponse>();

    /**
     *
     * @return
     * The response
     */
    public List<ApartmentResponse> getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(List<ApartmentResponse> response) {
        this.response = response;
    }

}