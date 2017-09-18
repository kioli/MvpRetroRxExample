package com.kioli.mvpretrorxexample.my.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.core.mvp.MVPModel;
import com.kioli.mvpretrorxexample.core.mvp.MVPPresenter;
import com.kioli.mvpretrorxexample.core.mvp.MVPView;

import java.util.List;

public interface MyContract {

	interface ShowModel extends MVPModel {

		/**
		 * Method called to request the upcoming shows
		 */
		void getPeople(MyPresenter callback);
	}

	/**
	 * Interface for the View
	 */
	interface MVPMyView extends MVPView {

		/**
		 * Shows the loading page
		 */
		void showLoading();

		/**
		 * Hides the loading page
		 */
		void hideLoading();

		/**
		 * Triggers the request of data
		 */
		void requestData(boolean forceRefresh);

		/**
		 * Gives the list of data back to the view
		 *
		 * @param data list of data
		 */
		void sendDataToView(List<Person> data);
	}

	/**
	 * Interface for the MyPresenter
	 */
	interface MVPMyPresenter extends MVPPresenter<MVPMyView> {

		/**
		 * Method called to request the list of data
		 */
		void getListPeople(boolean isGenderMale);

		/**
		 * Callback between the Presenter and the Repository
		 */
		void callbackForData(@NonNull List<Person> people);

	}
}
