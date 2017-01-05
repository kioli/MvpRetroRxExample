package com.kioli.mvpretrorxexample.my;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kioli.mvpretrorxexample.R;
import com.kioli.mvpretrorxexample.my.mvp.MyContract;
import com.kioli.mvpretrorxexample.my.mvp.MyContract.MVPMyView;
import com.kioli.mvpretrorxexample.my.mvp.MyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	static class ViewHolderData extends RecyclerView.ViewHolder {
		@BindView(R.id.item_list_text)
		AppCompatTextView itemText;

		ViewHolderData(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

	private List<MyModel> _data;
	private MVPMyView _view;

	public MyAdapter(final List<MyModel> dataset, final MyContract.MVPMyView view) {
		_data = dataset;
		_view = view;
	}

	@Override
	public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
		final ViewHolderData holder = new ViewHolderData(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
		holder.itemText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				Toast.makeText(_view.getContext(), holder.itemText.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final MyModel model = _data.get(position);
		final String content = model.name()
				.concat(" - ").concat(model.gender())
				.concat(" - ").concat(model.region());
		((ViewHolderData) holder).itemText.setText(content);
	}

	@Override
	public int getItemCount() {
		return _data.size();
	}
}
