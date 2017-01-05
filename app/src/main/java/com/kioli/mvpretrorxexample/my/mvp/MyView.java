package com.kioli.mvpretrorxexample.my.mvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.kioli.mvpretrorxexample.R;
import com.kioli.mvpretrorxexample.core.adapter.SimpleDivider;
import com.kioli.mvpretrorxexample.core.ui.InjectingActivity;
import com.kioli.mvpretrorxexample.my.MyAdapter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyPresenter.MyModelFragment;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import icepick.State;

public class MyView extends InjectingActivity implements MVPMyView {

	@BindView(R.id.loading)
	RelativeLayout spinner;
	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.swipe_refresh_layout)
	SwipeRefreshLayout swipeToRefreshLayout;
	@BindView(R.id.button)
	AppCompatButton button;

	@State
	ArrayList<MyModel> data;

	private MVPMyPresenter _presenter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setupPresenter();

		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new SimpleDivider(this));

		swipeToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				_presenter.subscribeToData(_presenter.getDataFragment().getObservableForData());
				swipeToRefreshLayout.setRefreshing(false);
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				requestData();
			}
		});

		reestablishSubscriptions();
	}

	private void setupPresenter() {
		MyModelFragment dataFragment = (MyModelFragment) getSupportFragmentManager().findFragmentByTag(MyModelFragment.TAG);
		if (dataFragment == null) {
			dataFragment = new MyModelFragment();
			getSupportFragmentManager().beginTransaction().add(dataFragment, MyModelFragment.TAG).commit();
		}

		_presenter = new MyPresenter(dataFragment);
		_presenter.attachView(this);
	}

	private void reestablishSubscriptions() {
		if (data == null) {
			if (_presenter.getDataFragment().observableData != null) {
				requestData();
			}
		} else {
			setItems();
		}
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
	public void requestData() {
		_presenter.subscribeToData(_presenter.getDataFragment().getObservableForData());
	}

	@Override
	public void sendDataToView(List<MyModel> showList) {
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
	protected int getLayoutResId() {
		return R.layout.view_main;
	}
}
