package com.example.myapplication.sharedprefer;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.list.baihoc;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {

    public static final String LIST_KEY = "BAG";
    public static final String product= "pro";

    public static void writeListInPref(Context context, List<baihoc> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(product, jsonString);
        editor.apply();
    }

    public static List<baihoc> readListFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(product, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<baihoc>>() {}.getType();
        List<baihoc> list = gson.fromJson(jsonString, type);
        return list;
    }

}