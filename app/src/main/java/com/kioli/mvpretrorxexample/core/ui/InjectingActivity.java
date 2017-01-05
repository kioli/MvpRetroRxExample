package com.kioli.mvpretrorxexample.core.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.kioli.mvpretrorxexample.core.mvp.MVPView;

import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class InjectingActivity extends AppCompatActivity implements MVPView {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		ButterKnife.bind(this);
		Icepick.restoreInstanceState(this, savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Icepick.saveInstanceState(this, outState);
	}

	/**
	 * Provides the layout resource id
	 *
	 * @return layout resource id
	 */
	@LayoutRes
	protected abstract int getLayoutResId();

	/**
	 * Provides the context
	 *
	 * @return context
	 */
	@Override
	public Context getContext() {
		return this;
	}
}
