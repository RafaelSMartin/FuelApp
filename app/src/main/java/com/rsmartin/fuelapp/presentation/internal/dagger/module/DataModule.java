package com.rsmartin.fuelapp.presentation.internal.dagger.module;

import com.rsmartin.fuelapp.data.repository.DefaultOilsRepository;
import com.rsmartin.fuelapp.domain.repository.OilsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    OilsRepository provideAccountRepository(DefaultOilsRepository oilsRepository) {
        return oilsRepository;
    }

//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient(App application) {
//        return new ClientBuilder().build(application);
//    }

}
