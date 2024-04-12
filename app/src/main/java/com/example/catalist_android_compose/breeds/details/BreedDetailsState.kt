package com.example.catalist_android_compose.breeds.details

import android.media.Image
import com.example.catalist_android_compose.breeds.domain.Cat

data class BreedDetailsState(
    val breedId : String,
    val fetching: Boolean = false,
    val data: Cat? = null,
    val error: DetailsError? = null,
    val image : Image? = null
){
    sealed class DetailsError{
        data class DataUpdateFailed(val cause: Throwable? = null) : DetailsError()

    }

}
