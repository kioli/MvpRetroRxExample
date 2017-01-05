package com.kioli.mvpretrorxexample.core.data;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract UI-less fragment used to hold observables throught device rotations
 */
public abstract class DataFragment extends Fragment {

	@Override
	public void onAttach(Context ctx) {
		super.onAttach(ctx);
		setRetainInstance(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return null;
	}
}