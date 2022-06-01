package com.dissiapps.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.dissiapps.weatherapp.data.remote.RepositoryImpl
import com.dissiapps.weatherapp.data.Repository
import com.dissiapps.weatherapp.data.dummy.DummyRepository
import com.dissiapps.weatherapp.data.local.WeatherDatabase
import com.dissiapps.weatherapp.data.remote.WeatherApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun gsonConverterFactory(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder().baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherDatabase(app: Application) =
        Room.databaseBuilder(app.applicationContext, WeatherDatabase::class.java, "weather_db.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    @Named("dummy")
    fun provideDummyRepository(database: WeatherDatabase): Repository {
        return DummyRepository(database)
    }

    @Provides
    @Singleton
    @Named("mock")
    fun provideRepository(database: WeatherDatabase, api: WeatherApi): Repository {
        return RepositoryImpl(api, database)
    }

}