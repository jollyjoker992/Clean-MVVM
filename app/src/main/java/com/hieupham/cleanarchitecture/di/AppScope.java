package com.hieupham.cleanarchitecture.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by hieupham on 5/14/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
