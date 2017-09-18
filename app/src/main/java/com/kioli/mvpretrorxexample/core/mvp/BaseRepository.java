package com.kioli.mvpretrorxexample.core.mvp;

import android.util.LruCache;

import io.reactivex.Flowable;

public abstract class BaseRepository {
	private LruCache<String, Flowable> cacheFlowables = new LruCache<>(50);

	protected Flowable cacheFlowable(String symbol, Flowable flowable) {
		Flowable cachedFlowable = cacheFlowables.get(symbol);
		if (cachedFlowable != null) {
			return cachedFlowable;
		}
		cachedFlowable = flowable;
		updateCache(symbol, cachedFlowable);
		return cachedFlowable;
	}

	private void updateCache(String stockSymbol, Flowable flowable) {
		cacheFlowables.put(stockSymbol, flowable);
	}
}
