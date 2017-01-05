package com.kioli.mvpretrorxexample.my.mvp;

import android.support.annotation.NonNull;

import com.kioli.mvpretrorxexample.App;
import com.kioli.mvpretrorxexample.core.data.DataFragment;
import com.kioli.mvpretrorxexample.core.mvp.MVPPresenter;
import com.kioli.mvpretrorxexample.core.mvp.MVPView;

import java.util.List;

import rx.Observable;

import static com.kioli.mvpretrorxexample.core.mvp.BasePresenter.DEFAULT_OBSERVE_ON_SCHEDULER;
import static com.kioli.mvpretrorxexample.core.mvp.BasePresenter.DEFAULT_SUBSCRIBE_ON_SCHEDULER;

public interface MyContract {
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
		void requestData();

		/**
		 * Gives the list of data back to the view
		 *
		 * @param data list of data
		 */
		void sendDataToView(List<MyModel> data);
	}

	/**
	 * Interface for the MyPresenter
	 */
	interface MVPMyPresenter extends MVPPresenter<MVPMyView, MVPMyPresenter.MyModelFragment> {

		class MyModelFragment extends DataFragment {

			public static String TAG = MyModelFragment.class.getSimpleName();

			public Observable<List<MyModel>> observableData;

			public Observable<List<MyModel>> getObservableForData() {
				if (observableData == null) {

					final Observable<List<MyModel>> userNamesObservable = App.getInstance().getNetworkService().getAPI().getGenderedNames("female");
					final Observable<List<MyModel>> adminNamesObservable = App.getInstance().getNetworkService().getAPI().getLocalisedNames("Germany");

					observableData = adminNamesObservable
							.mergeWith(userNamesObservable)
							.subscribeOn(DEFAULT_SUBSCRIBE_ON_SCHEDULER)
							.observeOn(DEFAULT_OBSERVE_ON_SCHEDULER)
							.cache();
				}
				return observableData;
			}
		}

		/**
		 * Method called to request the list of data
		 *
		 * @param observable the observable to subscribe to
		 */
		void subscribeToData(@NonNull Observable<List<MyModel>> observable);
	}
}
