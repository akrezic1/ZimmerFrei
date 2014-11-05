package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "city")
public class City extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "country_id")
    private Country country;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public City() {
        super();
    }

    public City(String name, Float lat, Float lng, Country country, Timestamp created_at, Timestamp updated_at) {
        super();
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public Country getCountry() {
        return country;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}
