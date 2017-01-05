package com.kioli.mvpretrorxexample.my.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.core.mvp.BasePresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter.MyModelFragment;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

class MyPresenter extends BasePresenter<MVPMyView, MyModelFragment> implements MVPMyPresenter {

	MyPresenter(@NonNull final MyModelFragment dataFragment) {
		_dataFragment = dataFragment;
	}

	@Override
	public MyModelFragment getDataFragment() {
		return (MyModelFragment) _dataFragment;
	}

	@Override
	public void subscribeToData(@NonNull final Observable<List<MyModel>> observable) {
		checkViewAttached();
		getView().showLoading();
		addSubscription(observable.subscribe(new Observer<List<MyModel>>() {
			private ArrayList<MyModel> shows = new ArrayList<>();

			@Override
			public void onCompleted() {
				((MyModelFragment) _dataFragment).observableData = null;
				getView().hideLoading();
				getView().sendDataToView(shows);
			}

			@Override
			public void onError(Throwable e) {
				((MyModelFragment) _dataFragment).observableData = null;
				getView().hideLoading();
				getView().sendDataToView(new ArrayList<MyModel>());
			}

			@Override
			public void onNext(List<MyModel> data) {
				shows.addAll(data);
			}
		}));
	}
}
