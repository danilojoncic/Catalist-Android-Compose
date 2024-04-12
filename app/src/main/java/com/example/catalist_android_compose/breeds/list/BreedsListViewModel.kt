package com.example.catalist_android_compose.breeds.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalist_android_compose.breeds.repository.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class BreedsListViewModel constructor(
    //ovdje bi isla dependency injection
    private val repository:  BreedRepository = BreedRepository
    //17. minut 5. vjezbe

    //naslijedjujemo view model
) :ViewModel(){
    //privatni state objekat
    private val _state = MutableStateFlow(BreedsListState())
    //sada ga otkrivamo kao public koji ostali mogu da preuzmu
    val state = _state.asStateFlow()
    //magija
    private fun setState(reducer: BreedsListState.() -> BreedsListState) = _state.getAndUpdate(reducer)


    init{
        observeBreedsVM()
        fetchAllBreeds()
        //metode koje zovemo za hvatanje stvari
    }

    private fun observeBreedsVM() {
        // We are launching a new coroutine
        viewModelScope.launch {
            // Which will observe all changes to our passwords
            repository.observeBreeds().collect {
                setState { copy(allBreedsFromState = it) }
            }
        }
    }




    private fun fetchAllBreeds(){
        //pokrecemo novu korutinu da ui thread slucajno ne bi
        //ukljucili u ucitavanje jer ce onda da stane
        viewModelScope.launch {
            setState { copy(fetching = true) }
            //"svaki put kada znate da nesto moze da podje ka zlu
            //stavite u try catch blok"
            try {
                println("STAMPAM IZ VIEWMODELA Velicina liste macaka prije fetcha" + state.value.allBreedsFromState.size)

                //stavi na IO thread da izvrsava
                val breeds = withContext(Dispatchers.IO){
                    repository.fetch()
                }
                println("STAMPAM IZ VIEWMODELA Velicina liste nakon fetcha macaka" + state.value.allBreedsFromState.size)
            }catch (error : IOException){
                setState { copy(error = BreedsListState.ListError.ListUpdateFailed(cause = error)) }
            }finally {
                setState { copy(fetching = false) }
            }
        }
    }
}