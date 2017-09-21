package com.yossisegev.domain.authentication;

import android.support.annotation.Nullable;

/**
 * Created by Yossi Segev on 29/07/2017.
 */

public interface AuthStorage {
    void saveToken(String token);
    @Nullable String getToken();
}
