package air.zimmerfrei.com.zimmerfrei.datamodel.apartmentdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String capacity;
    @Expose
    private String stars;
    @Expose
    private String description;
    @Expose
    private String price;
    @SerializedName("apartment_id")
    @Expose
    private String apartmentId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     *
     * @param capacity
     * The capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return
     * The stars
     */
    public String getStars() {
        return stars;
    }

    /**
     *
     * @param stars
     * The stars
     */
    public void setStars(String stars) {
        this.stars = stars;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The apartmentId
     */
    public String getApartmentId() {
        return apartmentId;
    }

    /**
     *
     * @param apartmentId
     * The apartment_id
     */
    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}