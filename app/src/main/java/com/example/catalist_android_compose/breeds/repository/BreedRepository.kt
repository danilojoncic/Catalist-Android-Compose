package com.example.catalist_android_compose.breeds.repository

import com.example.catalist_android_compose.breeds.model.Cat

object BreedRepository {
    private var mutableCats = SampleData.toMutableList()
    fun allCats() : List<Cat> = mutableCats
    fun getById(id:String):Cat?{
        return mutableCats.find { it.id == id }
    }
}