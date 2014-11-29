package air.zimmerfrei.com.zimmerfrei.test;

/**
 * Created by Andro on 8.11.2014..
 */
public class TestApartment {

    private int drawableId;
    private String distance;
    private float rating;

    public TestApartment(int drawableId, String distance, float rating) {
        this.drawableId = drawableId;
        this.distance = distance;
        this.rating = rating;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
