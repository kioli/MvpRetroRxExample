package com.kioli.mvpretrorxexample.core.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.core.data.DataFragment;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends MVPView, F extends DataFragment> implements MVPPresenter<V, F> {

	public static final Scheduler DEFAULT_SUBSCRIBE_ON_SCHEDULER = Schedulers.newThread();
	public static final Scheduler DEFAULT_OBSERVE_ON_SCHEDULER = AndroidSchedulers.mainThread();

	private CompositeSubscription _compositeSubscription = new CompositeSubscription();
	private V _view;

	protected DataFragment _dataFragment;

	@Override
	public void attachView(V mvpView) {
		_view = mvpView;
	}

	@Override
	public void detachView() {
		_compositeSubscription.clear();
		_view = null;
	}

	public V getView() {
		return _view;
	}

	protected void checkViewAttached() {
		if (_view == null) {
			throw new MvpViewNotAttachedException();
		}
	}

	protected void addSubscription(@NonNull final Subscription subscription) {
		_compositeSubscription.add(subscription);
	}
}
