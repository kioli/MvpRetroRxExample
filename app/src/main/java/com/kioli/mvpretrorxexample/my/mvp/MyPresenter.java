package com.kioli.mvpretrorxexample.my.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.core.mvp.BasePresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyView;

import java.util.ArrayList;
import java.util.List;

class MyPresenter extends BasePresenter<MVPMyView> implements MVPMyPresenter {

	private MyModel model = new MyModel();
	private ArrayList<Person> people = new ArrayList<>();

	@Override
	public void getListPeople(final boolean forceRefresh) {
		if (!forceRefresh && !people.isEmpty() && getView() != null) {
			getView().sendDataToView(people);
			return;
		}

		checkViewAttached();
		getView().showLoading();
		model.getPeople(this);
	}

	@Override
	public void callbackForData(@NonNull final List<Person> people) {
		this.people.clear();
		this.people.addAll(people);
		getView().hideLoading();
		getView().sendDataToView(this.people);
	}
}
