package com.assignment.countrylist.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val Base_Url = "https://dl.dropboxusercontent.com/"
    private val okkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    val instance: NetworkApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okkHttpClient)
            .build()
        retrofit.create(NetworkApi::class.java)
    }
}
