package com.goozix.githubdisplay.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.goozix.domain.entity.DomainModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<R extends BaseRouter, T extends DomainModel> extends ViewModel {

    private CompositeDisposable compositeDisposable;
    public ObservableBoolean progressBar = new ObservableBoolean(true);
    public ObservableBoolean emailContainer = new ObservableBoolean(true);
    public ObservableBoolean organizationContainer = new ObservableBoolean(true);

    protected R router;

    protected abstract void runInject();

    public BaseViewModel() {
        runInject();
    }

    public void addRouter(R router) {
        this.router = router;
    }

    public void removeRouter() {
        router = null;
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null
                && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void showProgressBar() {
        progressBar.set(true);
    }

    public void hideProgressBar() {
        progressBar.set(false);
    }
    public void hideEmailContainer() {
        emailContainer.set(false);
    }
    public void hideOrganizationContainer() {
        organizationContainer.set(false);
    }
}
