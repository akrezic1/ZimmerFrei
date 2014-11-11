package com.zimmerfrei.air.test;

/**
 * Created by Andro on 8.11.2014..
 */
public class TestApartment {

    private int drawableId;
    private String description;
    private String distance;

    public TestApartment(int drawableId, String description, String distance) {
        super();
        this.drawableId = drawableId;
        this.description = description;
        this.distance = distance;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
