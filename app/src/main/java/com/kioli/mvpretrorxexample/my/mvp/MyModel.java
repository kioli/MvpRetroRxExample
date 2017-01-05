package com.kioli.mvpretrorxexample.my.mvp;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class MyModel implements Parcelable {

	public abstract String name();
	public abstract String surname();
	public abstract String gender();
	public abstract String region();

	public static TypeAdapter<MyModel> typeAdapter(Gson gson) {
		return new $AutoValue_MyModel.GsonTypeAdapter(gson);
	}
}
