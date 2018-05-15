package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.LiveData;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.feature.BaseViewModel;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import java.util.List;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class ViewModel extends BaseViewModel {

    abstract void init(String uid);

    abstract LiveData<Resource<List<Task>>> tasks();
}
