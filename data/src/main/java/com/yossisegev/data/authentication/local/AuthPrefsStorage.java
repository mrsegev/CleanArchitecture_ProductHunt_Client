package com.yossisegev.data.authentication.local;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.yossisegev.domain.authentication.AuthStorage;

/**
 * Created by Yossi Segev on 29/07/2017.
 */

public class AuthPrefsStorage implements AuthStorage {
    private static final String PREF_KEY_TOKEN = "pref_key_token";
    private SharedPreferences sharedPreferences;

    public AuthPrefsStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveToken(String token) {
        sharedPreferences.edit().putString(PREF_KEY_TOKEN, token).apply();
    }

    @Override
    @Nullable
    public String getToken() {
        return sharedPreferences.getString(PREF_KEY_TOKEN, null);
    }
}
