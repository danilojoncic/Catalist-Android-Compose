package com.example.catalist_android_compose.breeds.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist_android_compose.breeds.repository.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class BreedsDetailsViewModel constructor(
    private val breedId: String,
    private val repository: BreedRepository = BreedRepository,

) : ViewModel(){
    private val _state = MutableStateFlow(BreedDetailsState(breedId = breedId))
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedDetailsState.() -> BreedDetailsState) =
        _state.getAndUpdate(reducer)


    init {
        observeEvents()
        observePasswordDetails()
        fetchBreedDetails()
    }
    private fun observePasswordDetails() {
        viewModelScope.launch {
            repository.observeBreedDetails(breedId = breedId)
                .filterNotNull()
                .collect {
                    setState { copy(data = it) }
                }
        }
    }
    private fun observeEvents() {

    }
    private fun fetchBreedDetails() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                withContext(Dispatchers.IO) {
                    repository.fetchBreedDetails(breedId = breedId)
                }
            } catch (error: IOException) {
                setState {
                    copy(error = BreedDetailsState.DetailsError.DataUpdateFailed(cause = error))
                }
            } finally {
                setState { copy(fetching = false) }
            }
        }
    }
}