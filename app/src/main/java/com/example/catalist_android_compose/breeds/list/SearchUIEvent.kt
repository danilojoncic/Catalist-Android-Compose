package com.example.catalist_android_compose.breeds.list

sealed class SearchUIEvent {
    data class SeatchQueryChanged(val query : String): SearchUIEvent()
}