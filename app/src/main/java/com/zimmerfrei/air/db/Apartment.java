package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "apartments")
public class Apartment extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_2")
    private String phone_2;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "owner_id")
    private User user;

    @Column(name = "type_id")
    private ApartmentType type_id;

    @Column(name = "city_id")
    private City city;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Apartment() {
        super();
    }

    public Apartment(String name, String description, Integer capacity, String address,
                     String email, String phone, String phone_2, Integer rating, Float lat,
                     Float lng, User user, ApartmentType type_id, City city, Timestamp created_at,
                     Timestamp updated_at) {
        super();
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.phone_2 = phone_2;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
        this.user = user;
        this.type_id = type_id;
        this.city = city;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public Integer getRating() {
        return rating;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public User getUser() {
        return user;
    }

    public ApartmentType getType_id() {
        return type_id;
    }

    public City getCity() {
        return city;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}
