package hr.kraljic.hydroponicmonitoringapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String TOKEN = " hr.kraljic.hydroponicmonitoringapp.token";

    public static void setToken(Context context, String token) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);

        sharedpreferences.edit()
                .putString("token", token)
                .apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);

        String token = sharedpreferences.getString("token", null);

        return token;
    }

    public static void removeToken(Context context) {
        context.deleteSharedPreferences(TOKEN);
    }

}
