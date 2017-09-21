package com.yossisegev.producthuntclient.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Yossi Segev on 08/09/2017.
 */
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
