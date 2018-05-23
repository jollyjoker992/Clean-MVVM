package com.hieupham.cleanarchitecture.feature;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.hieupham.cleanarchitecture.util.RxImmediateSchedulerRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by hieupham on 5/22/18.
 */

@RunWith(JUnit4.class)
public abstract class UseCaseTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final TestRule globalTimeoutRule = Timeout.seconds(20);

    @Rule
    public final ExpectedException thrownRule = ExpectedException.none();

    @Rule
    public final TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Rule
    public final RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();

    @Before
    public void before() {
    }

    @After
    public void after() {
    }
}
