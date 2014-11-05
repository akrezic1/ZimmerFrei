package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "pictures")
public class Picture extends Model {
    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "apartment_id")
    private Apartment apartment_id;

    public Picture() {
        super();
    }

    public Picture(String title, String url, Apartment apartment_id) {
        this.title = title;
        this.url = url;
        this.apartment_id = apartment_id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Apartment getApartment_id() {
        return apartment_id;
    }
}
