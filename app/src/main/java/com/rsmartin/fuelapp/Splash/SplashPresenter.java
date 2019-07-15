package com.rsmartin.fuelapp.Splash;

import android.util.Log;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.remote.ApiDataGob.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements ContractSplash.SplashPresenter {

    private final String TAG = "SplashPresenter";

    private SplashView view;
    private SplashModel model;

    public SplashPresenter(SplashView view){
        this.view = view;
        model = new SplashModel(this);
    }

    @Override
    public void getOilsGob() {
        view.showProgress();

        App.getInstance().getApiService().getStationsGob().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if(response.isSuccessful()){
                    Model data = response.body();
                    model.map(data);
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage().toString());
            }
        });


    }

    @Override
    public void showResultMap(String response) {
        view.hideProgress();
        view.showResult(response);
    }
}
