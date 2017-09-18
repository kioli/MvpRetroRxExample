package com.kioli.mvpretrorxexample.my.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.core.data.ServiceGenerator;
import com.kioli.mvpretrorxexample.core.mvp.BaseRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

class MyRepository extends BaseRepository {

	private String CACHE_PREFIX_GET_PEOPLE = "people";
	private ServiceGenerator _service;

	MyRepository(@NonNull final ServiceGenerator service) {
		_service = service;
	}

	Flowable fetchPeople() {
		final Single<List<Person>> userNamesSingle = _service.getAPI().getGenderedNames("female");
		final Single<List<Person>> adminNamesSingle = _service.getAPI().getLocalisedNames("Germany");
		return cacheFlowable(CACHE_PREFIX_GET_PEOPLE, adminNamesSingle.mergeWith(userNamesSingle)).cache();
	}
}
