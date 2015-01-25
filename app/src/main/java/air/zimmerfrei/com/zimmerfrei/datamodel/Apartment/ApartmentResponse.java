package air.zimmerfrei.com.zimmerfrei.datamodel.apartment;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "ApartmentResponses")
public class ApartmentResponse extends Model {

    @Column(name = "apartment_id")
    private String apartment_id;
    @Column(name = "idMember")
    @SerializedName(value = "id")
    @Expose
    private String idMember;
    @Column(name = "Name")
    @Expose
    private String name;
    @Column(name = "Description")
    @Expose
    private String description;
    @Column(name = "Capacity")
    @Expose
    private String capacity;
    @Column(name = "Stars")
    @Expose
    private String stars;
    @Column(name = "Address")
    @Expose
    private String address;
    @Column(name = "Email")
    @Expose
    private String email;
    @Column(name = "Phone")
    @Expose
    private String phone;
    @SerializedName("phone_2")
    @Column(name = "Phone2")
    @Expose
    private String phone2;
    @Column(name = "Rating")
    @Expose
    private String rating;
    @Column(name = "Lat")
    @Expose
    private String lat;
    @Column(name = "Lng")
    @Expose
    private String lng;
    @Column(name = "Price")
    @Expose
    private String price;
    @Column(name = "CoverPhoto")
    @Expose
    private String cover_photo;
    @Column(name = "City")
    @Expose
    private String city;
    @Column(name = "Type")
    @Expose
    private String type;
    @Column(name = "UserNickname")
    @SerializedName("user_nickname")
    @Expose
    private String userNickname;
    @Column(name = "UserEmail")
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @Column(name = "UserPhone")
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("distance_to")
    @Expose
    private String distanceTo;


    public String getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(String apartment_id) {
        this.apartment_id = apartment_id;
    }

    /**
     *
     * @return
     * The id
     */
    public String getIdMember() {
        return idMember;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setIdMember(String id) {
        this.idMember = id;
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
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     *
     * @param phone2
     * The phone_2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     *
     * @return
     * The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     *
     * @param lng
     * The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
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
     * The cover_photo
     */
    public String getCover_photo() {
        return cover_photo;
    }

    /**
     *
     * @param cover_photo
     * The cover_photo
     */
    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     *
     * @param userNickname
     * The user_nickname
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     *
     * @return
     * The userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     *
     * @param userEmail
     * The user_email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     *
     * @return
     * The userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     *
     * @param userPhone
     * The user_phone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     *
     * @return
     * The distanceTo
     */
    public String getDistanceTo() {
        return distanceTo;
    }

    /**
     *
     * @param distanceTo
     * The distance_to
     */
    public void setDistanceTo(String distanceTo) {
        this.distanceTo = distanceTo;
    }

    public ApartmentResponse(String idMember, String name, String description, String capacity,
                             String stars, String address, String email, String phone,
                             String phone2, String rating, String lat, String lng, String price,
                             String cover_photo, String city, String type, String userNickname,
                             String userEmail, String userPhone) {
        super();
        this.idMember = idMember;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.stars = stars;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.phone2 = phone2;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
        this.price = price;
        this.cover_photo = cover_photo;
        this.city = city;
        this.type = type;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public ApartmentResponse() {
        super();
    }
}