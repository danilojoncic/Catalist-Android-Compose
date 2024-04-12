package com.example.catalist_android_compose.breeds.networking

import com.example.catalist_android_compose.breeds.networking.serialization.AppJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor {
        val updatedRequest = it.request().newBuilder()
            .addHeader("x-api-key", "live_uzQcETh5VxOhcyvfjMX3AVOUhTDnOfSTzDH6L0jC6cevyIFJWAeN85sYnJW8oSNe")
            .build()
        it.proceed(updatedRequest)
    }
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    )
    .build()


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .client(okHttpClient)
    .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
    .build()
