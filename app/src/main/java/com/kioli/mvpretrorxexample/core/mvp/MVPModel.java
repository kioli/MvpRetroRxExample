package com.kioli.mvpretrorxexample.core.mvp;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Common interface shared by models in the MVP structure
 */
public interface MVPModel {

	Scheduler DEFAULT_SUBSCRIBE_ON_SCHEDULER = Schedulers.newThread();
	Scheduler DEFAULT_OBSERVE_ON_SCHEDULER = AndroidSchedulers.mainThread();
}
