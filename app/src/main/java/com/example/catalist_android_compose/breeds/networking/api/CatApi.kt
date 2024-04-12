package com.example.catalist_android_compose.breeds.networking.api

import CatApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<CatApiModel>
//    @GET("photos/{id}")
//    suspend fun getPhoto(
//        @Path("id") photoId: Int,
//    ):
}