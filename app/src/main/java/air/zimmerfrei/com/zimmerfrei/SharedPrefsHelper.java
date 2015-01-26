package air.zimmerfrei.com.zimmerfrei;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import air.zimmerfrei.com.zimmerfrei.datamodel.profile.Profile;

/**
 * Created by Andro on 13.1.2015.
 * Helper class used to get values from Shared Preferences
 */
public class SharedPrefsHelper {

    /**
     * Method is called if user is authenticated, this method saves users data to
     * SharedPreferences for future use
     * @param profile is response from server with users details
     */
    public static void saveToSharedPref(Profile profile, Context context) {
        SharedPreferences sp = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);

        sp.edit().putString("token", profile.getRememberToken()).apply();
        sp.edit().putString("username", profile.getResponse().getUsername()).apply();
        sp.edit().putString("name", profile.getResponse().getName()).apply();
        sp.edit().putString("surname", profile.getResponse().getSurname()).apply();
        sp.edit().putString("avatar", profile.getResponse().getAvatar()).apply();
        sp.edit().putString("phone", profile.getResponse().getPhone()).apply();
        sp.edit().putString("email", profile.getResponse().getEmail()).apply();
    }

    /**
     * Popup alert that asks user if he really want to sign out
     * @param context application context
     */
    public static void signOutAlert(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.logout)
                .setMessage(R.string.are_you_sure_logout)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSharedPref(context);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void deleteSharedPref(Context context) {
        SharedPreferences sp = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    public static SharedPreferences getGCMPreferences(Context context) {
        return context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
    }

    /**
     * Get Google Cloud Messaging ID used for push notifications
     * @param context application context
     * @return returns string with GCM ID
     */
    public static String getGCMid(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("air.zimmerfrei.com.zimmerfrei", Context.MODE_PRIVATE);
        return prefs.getString("registration_id", "error");
    }

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
