package com.example.catalist_android_compose.breeds.networking.api

import com.example.catalist_android_compose.breeds.networking.api.model.CatApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {



    @GET("photos")
    suspend fun getAllBreeds(): List<CatApiModel>

//    @GET("photos/{id}")
//    suspend fun getPhoto(
//        @Path("id") photoId: Int,
//    ):
}