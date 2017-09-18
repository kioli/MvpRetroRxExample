package com.kioli.mvpretrorxexample.my.mvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.kioli.mvpretrorxexample.R;
import com.kioli.mvpretrorxexample.core.adapter.SimpleDivider;
import com.kioli.mvpretrorxexample.core.mvp.PresenterHolder;
import com.kioli.mvpretrorxexample.core.ui.InjectingActivity;
import com.kioli.mvpretrorxexample.my.MyAdapter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import icepick.State;

public class MyView extends InjectingActivity implements MVPMyView {

	@BindView(R.id.loading)
	RelativeLayout spinner;
	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.swipe_refresh_layout)
	SwipeRefreshLayout refreshView;

	@State
	ArrayList<Person> data;

	private MVPMyPresenter _presenter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setupPresenter();

		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new SimpleDivider(this));

		refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				requestData(true);
				refreshView.setRefreshing(false);
			}
		});

		requestData(false);
	}

	private void setupPresenter() {
		_presenter = (MVPMyPresenter) PresenterHolder.getInstance().getPresenter(MyPresenter.class);
		if (_presenter == null) {
			_presenter = new MyPresenter();
		}
		_presenter.attachView(this);
	}

	@Override
	public void showLoading() {
		spinner.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		spinner.setVisibility(View.GONE);
	}

	@Override
	public void requestData(final boolean forceRefresh) {
		_presenter.getListPeople(forceRefresh);
	}

	@Override
	public void sendDataToView(List<Person> showList) {
		data = new ArrayList<>(showList);
		setItems();
	}

	private void setItems() {
		recyclerView.setAdapter(new MyAdapter(data, this));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		_presenter.detachView();
	}

	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		PresenterHolder.getInstance().putPresenter(MyPresenter.class, _presenter);
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.view_main;
	}
}
