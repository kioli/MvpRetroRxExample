package com.kioli.mvpretrorxexample.core.data;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
abstract class MyAdapterFactory implements TypeAdapterFactory {

	// Static factory method to access the package private generated implementation
	static TypeAdapterFactory create() {
		return new AutoValueGson_MyAdapterFactory();
	}
}