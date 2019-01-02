package com.example.module_weather

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author
 * @Date 2019/1/1
 * @description
 * @since 1.0.0
 */
val url = "https://www.apiopen.top/"

class ApiManager private constructor() {

    private var iWeather: WeatherService = build(url, WeatherService::class.java)

    fun getIWeather(): WeatherService {
        return iWeather
    }

    private fun <T> build(host: String, cls: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(host)
            .client(RetrofitManager.instance.defaultClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(cls)
    }

    companion object {
        var instance = ApiManager()
            private set

        fun reset() {
            instance = ApiManager()
        }
    }
}