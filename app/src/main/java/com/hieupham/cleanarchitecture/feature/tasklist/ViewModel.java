package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;
import com.hieupham.cleanarchitecture.feature.BaseViewModel;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class ViewModel extends BaseViewModel<UseCase> {

    ViewModel(@Nullable UseCase useCase) {
        super(useCase);
    }

    abstract void init(String uid);

    abstract LiveData<Resource<List<TaskModel>>> tasks();
}
