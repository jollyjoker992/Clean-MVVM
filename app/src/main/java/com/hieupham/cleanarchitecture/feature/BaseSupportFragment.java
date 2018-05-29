package com.hieupham.cleanarchitecture.feature;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hieupham.cleanarchitecture.di.DaggerSupportFragment;

/**
 * Created by hieupham on 5/16/18.
 */

public abstract class BaseSupportFragment extends DaggerSupportFragment {

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getLifecycle().addObserver(viewModel());
        observe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Define the layout res id can be used to inflate {@link View}
     *
     * @return the layout res id
     */
    @LayoutRes
    protected abstract int layoutRes();

    /**
     * Define the {@link BaseViewModel} instance
     *
     * @return the {@link BaseViewModel} instance
     */
    protected abstract BaseViewModel viewModel();

    /**
     * Init {@link View} components here. Such as set adapter for {@link RecyclerView}, set listener
     * or anything else
     */
    protected void initComponents() {
    }

    /**
     * Observe data change from ViewModel
     */
    protected void observe() {
    }
}
