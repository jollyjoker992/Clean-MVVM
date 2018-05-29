package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import com.hieupham.cleanarchitecture.data.DataProvider;
import com.hieupham.cleanarchitecture.feature.ViewModelTest;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hieupham on 5/22/18.
 */

public class TaskListViewModelTest extends ViewModelTest {

    @Mock
    TaskListUseCase useCase;

    @InjectMocks
    TaskListViewModel viewModel;

    @Mock
    Observer<Resource<List<TaskModel>>> observerTasks;

    @Test
    public void verifyGetTaskByOwnerSuccess() {
        final String uid = "uid";
        final List<TaskModel> taskModels = DataProvider.taskModelsByUser();
        final Resource<List<TaskModel>> expectedResult = Resource.success(taskModels);
        final MutableLiveData<Resource<List<TaskModel>>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedResult);

        when(useCase.getTasksByOwner(uid)).thenReturn(liveData);
        viewModel.tasks().observeForever(observerTasks);
        viewModel.init(uid);

        verify(observerTasks).onChanged(expectedResult);
        assertTrue("Get tasks is success but not return success status",
                viewModel.tasks().getValue().isSuccessful());
        assertEquals(expectedResult, viewModel.tasks().getValue());
    }

    @Test
    public void verifyGetTaskByOwnerError() {
        final String uid = "uid";
        final TimeoutException expectedException = DataProvider.timeoutException();
        final Resource<List<TaskModel>> expectedResult = Resource.error(expectedException, null);
        final MutableLiveData<Resource<List<TaskModel>>> liveData = new MutableLiveData<>();
        liveData.setValue(expectedResult);

        when(useCase.getTasksByOwner(uid)).thenReturn(liveData);
        viewModel.tasks().observeForever(observerTasks);
        viewModel.init(uid);

        verify(observerTasks).onChanged(expectedResult);
        assertTrue("Get tasks is error but not return an error",
                viewModel.tasks().getValue().isError());
        assertEquals(expectedResult, viewModel.tasks().getValue());
        assertEquals(expectedException, viewModel.tasks().getValue().throwable());
    }
}
