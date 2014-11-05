package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by Andro on 5.11.2014..
 */

@Table(name = "apartment_types")
public class ApartmentType extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public ApartmentType() {
        super();
    }

    public ApartmentType(Timestamp created_at, String name, Timestamp updated_at) {
        super();
        this.created_at = created_at;
        this.name = name;
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
