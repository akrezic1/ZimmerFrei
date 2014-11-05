package com.zimmerfrei.air.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
* Entity class representing roles that users can be
*/
@Table(name = "roles")
public class Role extends Model {
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "edited_at")
    private Timestamp edited_at;

    public Role() {
        super();
    }

    public Role(String name, Timestamp created_at, Timestamp edited_at) {
        super();
        this.name = name;
        this.created_at = created_at;
        this.edited_at = edited_at;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getEdited_at() {
        return edited_at;
    }
}
