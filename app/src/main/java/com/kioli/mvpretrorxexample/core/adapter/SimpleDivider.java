package com.kioli.mvpretrorxexample.core.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kioli.mvpretrorxexample.R;

public class SimpleDivider extends RecyclerView.ItemDecoration {

	private Drawable _divider;

	public SimpleDivider(Context context) {
		_divider = ContextCompat.getDrawable(context, R.color.dim);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + _divider.getIntrinsicHeight();

			_divider.setBounds(left, top, right, bottom);
			_divider.draw(c);
		}
	}
}