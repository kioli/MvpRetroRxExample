package com.kioli.mvpretrorxexample.core.mvp;

import com.kioli.mvpretrorxexample.core.data.DataFragment;

/**
 * Interface shared by presenters doing asynchronous calls with RxJava that require {@link DataFragment}
 */
public interface MVPPresenter<V extends MVPView, F extends DataFragment> {

	/**
	 * returns the DataFragment used by the Presenter (if any)
	 *
	 * @return DataFragment
	 */
	F getDataFragment();

	/**
	 * Attaches the provided view to the presenter
	 *
	 * @param baseView view to attach to the presenter
	 */
	void attachView(V baseView);

	/**
	 * Detaches the view from the presenter
	 */
	void detachView();

}