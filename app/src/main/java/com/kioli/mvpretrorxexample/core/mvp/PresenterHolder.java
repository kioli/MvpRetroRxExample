package com.kioli.mvpretrorxexample.core.mvp;

import java.util.HashMap;
import java.util.Map;

public class PresenterHolder {

	private static PresenterHolder instance;
	private Map presenterMap = new HashMap<Class, MVPPresenter>();

	public static PresenterHolder getInstance() {
		if (instance == null) {
			synchronized (PresenterHolder.class) {
				if (instance == null) {
					instance = new PresenterHolder();
				}
			}
		}
		return instance;
	}

	private PresenterHolder() {
	}

	public void putPresenter(Class clazz, MVPPresenter presenter) {
		presenterMap.put(clazz, presenter);
	}

	public MVPPresenter getPresenter(Class clazz) {
		return (MVPPresenter) presenterMap.get(clazz);
	}

	public void remove(Class clazz) {
		presenterMap.remove(clazz);
	}
}
