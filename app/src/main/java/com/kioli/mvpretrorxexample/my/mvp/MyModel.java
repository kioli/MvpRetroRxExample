package com.kioli.mvpretrorxexample.my.mvp;

import com.kioli.mvpretrorxexample.App;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.DisposableSubscriber;

public class MyModel implements MyContract.ShowModel {
	private MyRepository repository = new MyRepository(App.getInstance().getServiceGenerator());

	@Override
	public void getPeople(final MyPresenter callback) {
		repository.fetchPeople()
				.subscribeOn(DEFAULT_SUBSCRIBE_ON_SCHEDULER)
				.observeOn(DEFAULT_OBSERVE_ON_SCHEDULER)
				.subscribeWith(new DisposableSubscriber<List<Person>>() {
					final ArrayList<Person> result = new ArrayList<>();

					@Override
					public void onNext(final List<Person> o) {
						result.addAll(o);
					}

					@Override
					public void onError(final Throwable t) {
						callback.callbackForData(new ArrayList<Person>());
					}

					@Override
					public void onComplete() {
						callback.callbackForData(result);
					}
				});
	}
}

