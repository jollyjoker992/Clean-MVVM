package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.hieupham.cleanarchitecture.feature.BaseUseCase;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;

/**
 * Created by jollyjoker992 on 20/05/2018.
 */

public abstract class UseCase extends BaseUseCase {

    UseCase(@NonNull Transformer transformer) {
        super(transformer);
    }

    abstract LiveData<Resource<List<TaskModel>>> getTasksByOwner(String uid);

}
