package com.example.catalist_android_compose.breeds.list

import com.example.catalist_android_compose.breeds.domain.Cat

data class BreedsListState(
    val fetching: Boolean = false,
    //puna lista koju fetchujem
    val allBreedsFromState: List<Cat> = emptyList(),
    //filtrirana lista cije ce vrijednosti da se mijenjaju nakon filtera u search
    var filteredBreeds: List<Cat> = emptyList(),
    val error: ListError? = null
) {
    //profesor kaze bolje je pokriti sve greske
    //onda cemo to i da uradimo koliko i mozemo
    sealed class ListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
    }

    fun filterForSearch(query: String) {
        filteredBreeds = allBreedsFromState.filter { it.name.contains(query, ignoreCase = true) }
    }
}
