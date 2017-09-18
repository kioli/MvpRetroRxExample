package com.kioli.mvpretrorxexample.core.mvp;

/**
 * Interface shared by presenters
 */
public interface MVPPresenter<V extends MVPView> {

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