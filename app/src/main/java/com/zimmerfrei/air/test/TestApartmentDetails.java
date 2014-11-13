package com.zimmerfrei.air.test;

/**
 * Created by Andro on 11.11.2014..
 */
public class TestApartmentDetails {

    private int[] drawableId;
    private String description;

    public TestApartmentDetails(int[] drawableId, String description) {
        super();
        this.drawableId = drawableId;
        this.description = description;
    }

    public int[] getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int[] drawableId) {
        this.drawableId = drawableId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
