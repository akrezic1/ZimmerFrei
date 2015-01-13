package air.zimmerfrei.com.zimmerfrei;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andro on 13.1.2015.
 * Helper class used to get values from Shared Preferences
 */
public class SharedPrefsHelper {

    public static String getAuthToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("token", "error");
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("username", "error");
    }

    public static String getName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("name", "error");
    }

    public static String getSurname(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("surname", "error");
    }

    public static String getAvatar(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("avatar", "error");
    }

    public static String getPhone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("phone", "error");
    }

    public static String getEmail(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("email", "error");
    }

}
