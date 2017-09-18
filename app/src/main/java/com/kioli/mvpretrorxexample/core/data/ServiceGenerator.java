package com.kioli.mvpretrorxexample.core.data;

import com.google.gson.GsonBuilder;
import com.kioli.mvpretrorxexample.my.mvp.Person;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ServiceGenerator {

	private static String baseUrl = "http://uinames.com/";
	private NetworkAPI networkAPI;

	public ServiceGenerator() {
		this(baseUrl);
	}

	private ServiceGenerator(String baseUrl) {
		final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
				.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.addInterceptor(new Interceptor() {
					@Override
					public Response intercept(Chain chain) throws IOException {
						final Request original = chain.request();
						final HttpUrl url = original.url().newBuilder()
								.addQueryParameter("amount", "10")
								.build();
						final Request newRequest = original.newBuilder().url(url).build();
						return chain.proceed(newRequest);
					}
				});

		final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
				new GsonBuilder()
						.registerTypeAdapterFactory(MyAdapterFactory.create())
						.create());

		final Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(gsonConverterFactory)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(httpClient.build())
				.build();

		networkAPI = retrofit.create(NetworkAPI.class);
	}

	public NetworkAPI getAPI() {
		return networkAPI;
	}

	public interface NetworkAPI {
		@GET("api/")
		Single<List<Person>> getGenderedNames(@Query("gender") String gender);

		@GET("api/")
		Single<List<Person>> getLocalisedNames(@Query("region") String region);
	}
}
