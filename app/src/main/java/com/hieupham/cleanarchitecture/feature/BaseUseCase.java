package com.hieupham.cleanarchitecture.feature;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by jollyjoker992 on 20/05/2018.
 */

public abstract class BaseUseCase {

    private Transformer transformer;

    public BaseUseCase(@NonNull Transformer transformer) {
        this.transformer = transformer;
    }

    void dispose() {
        transformer.dispose();
    }

    public <T> LiveData<Resource<T>> transformMaybe(Maybe<T> source) {
        return transformer.maybe(source);
    }

    public <T> LiveData<Resource<T>> transformSingle(Single<T> source) {
        return transformer.single(source);
    }

    public <T> LiveData<Resource<T>> transformObservable(Observable<T> source) {
        return transformer.observable(source);
    }

    public <T> LiveData<Resource<T>> transformFlowable(Flowable<T> source) {
        return transformer.flowable(source);
    }
}
