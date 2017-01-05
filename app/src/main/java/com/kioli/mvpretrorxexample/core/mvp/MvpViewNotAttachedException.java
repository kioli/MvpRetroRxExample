package com.kioli.mvpretrorxexample.core.mvp;

class MvpViewNotAttachedException extends RuntimeException {

	MvpViewNotAttachedException() {
		super("Call Presenter.attachView(MVPView) before requesting data to the Presenter");
	}
}