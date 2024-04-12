package com.example.catalist_android_compose.breeds.repository

import CatApiModel
import android.media.Image
import com.example.catalist_android_compose.breeds.domain.Cat
import com.example.catalist_android_compose.breeds.networking.api.CatApi
import com.example.catalist_android_compose.breeds.networking.api.model.mapCatApiModelToCat
import com.example.catalist_android_compose.breeds.networking.retrofit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.seconds

object BreedRepository {
    private val breeds = MutableStateFlow(listOf<Cat>())
    fun allCats() : List<Cat> = breeds.value
    fun getById(id:String):Cat?{
        return breeds.value.find { it.id == id }
    }

    private val catApi: CatApi = retrofit.create(CatApi::class.java)

    //cim mora da ceka mora da bude suspend
    suspend fun fetch(){
        //prava logika za fetchovanje apija
        val catApiModels: List<CatApiModel> = catApi.getAllBreeds()
        val cats = catApiModels.map { mapCatApiModelToCat(it) }

        println("finished mapping back to cats")
        breeds.update { cats.toMutableList()  }
        println("finished fetching inside the repository")
    }

    fun observeBreeds(): Flow<List<Cat>> = breeds.asStateFlow()
    suspend fun fetchBreedDetails(breedId: String){
        delay(1.seconds)
    }

    fun observeBreedDetails(breedId: String): Flow<Cat?> {
        return observeBreeds()
            .map { breeds -> breeds.find { it.id == breedId } }
            .distinctUntilChanged()
    }
}