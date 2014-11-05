package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "country")
public class Country extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Country() {
        super();
    }

    public Country(String name, Timestamp created_at, Timestamp updated_at) {
        super();
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
}
