package com.coatedmoose.daggerizedandroid.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Annotated classes are designated as being for an Android {@link android.app.Activity}, or some
 * variant (fragment activity, list activity etc)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {
}
