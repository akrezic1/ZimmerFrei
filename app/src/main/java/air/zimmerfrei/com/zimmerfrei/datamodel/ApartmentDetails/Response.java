package air.zimmerfrei.com.zimmerfrei.datamodel.apartmentdetails;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String capacity;
    @Expose
    private String stars;
    @Expose
    private String address;
    @Expose
    private String email;
    @Expose
    private String phone;
    @SerializedName("phone_2")
    @Expose
    private String phone2;
    @Expose
    private String rating;
    @Expose
    private String price;
    @Expose
    private String lat;
    @Expose
    private String lng;
    @Expose
    private String city;
    @Expose
    private String type;
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;
    @Expose
    private List<Picture> pictures = new ArrayList<Picture>();
    @Expose
    private List<Room> rooms = new ArrayList<Room>();
    @SerializedName("user_nickname")
    @Expose
    private String userNickname;
    @SerializedName("user_avatar")
    @Expose
    private String userAvatar;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;

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
     * The coverPhoto
     */
    public String getCoverPhoto() {
        return coverPhoto;
    }

    /**
     *
     * @param coverPhoto
     * The cover_photo
     */
    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    /**
     *
     * @return
     * The pictures
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     *
     * @param pictures
     * The pictures
     */
    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    /**
     *
     * @return
     * The rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param rooms
     * The rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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
     * The userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     *
     * @param userAvatar
     * The user_avatar
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
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


}