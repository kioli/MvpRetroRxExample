package com.kioli.mvpretrorxexample.core.mvp;

public abstract class BasePresenter<V extends MVPView> implements MVPPresenter<V> {

	private V _view;

	@Override
	public void attachView(V mvpView) {
		_view = mvpView;
	}

	@Override
	public void detachView() {
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
}
