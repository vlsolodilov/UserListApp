package com.solodilov.userlistapp.presentation.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.UUID;

public class IdentifierDevice {

    //проблема получения IMEI на устройствах на Андроиде 10 и выше
    //https://developer.android.com/training/articles/user-data-ids
    public static String getImei(Context context) {
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String uuid = sPrefs.getString("uuid",null);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sPrefs.edit();
            editor.putString("uuid",uuid);
            editor.apply();
        }
        return uuid;
    }
}
