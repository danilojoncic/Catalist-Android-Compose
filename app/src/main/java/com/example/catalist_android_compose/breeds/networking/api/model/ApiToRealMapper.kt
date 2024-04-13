package com.example.catalist_android_compose.breeds.networking.api.model

import CatApiModel
import com.example.catalist_android_compose.breeds.domain.Cat

fun mapCatApiModelToCat(catApiModel: CatApiModel): Cat {
    return Cat(
        id = catApiModel.id?:"",
        weight = "${catApiModel.weight.metric ?:20}",
        name = catApiModel.name?:"",
        alternateName = catApiModel.alt_names ?: "",
        temperament = catApiModel.temperament?:"",
        origin = catApiModel.origin?:"",
        description = catApiModel.description?:"",
        lifeSpan = catApiModel.life_span?:"",
        indoor = catApiModel.indoor?:0,
        lap = catApiModel.lap?:0,
        adaptability = catApiModel.adaptability?:0,
        affectionLevels = catApiModel.affection_level?:0,
        childFriendly = catApiModel.child_friendly?:0,
        dogFriendly = catApiModel.dog_friendly?:0,
        energyLevel = catApiModel.energy_level?:0,
        grooming = catApiModel.grooming?:0,
        healthIssues = catApiModel.health_issues?:0,
        inteligence = catApiModel.intelligence?:0,
        sheddingLevel = catApiModel.shedding_level?:0,
        socialNeeds = catApiModel.social_needs?:0,
        strangerFriendly = catApiModel.stranger_friendly?:0,
        vocalisation = catApiModel.vocalisation?:0,
        experimental = catApiModel.experimental?:0,
        hairless = catApiModel.hairless?:0,
        natural = catApiModel.natural?:0,
        rare = catApiModel.rare?:0,
        rex = catApiModel.rex?:0,
        shortLegs = catApiModel.short_legs?:0,
        wikipediaLink = catApiModel.wikipedia_url?:"https://www.wikipedia.org/",
        hypoallergenic = catApiModel.hypoallergenic?:0,
        referenceImageId = catApiModel.reference_image_id?:"",
        link = catApiModel.vetstreet_url ?: catApiModel.cfa_url ?: catApiModel.vcahospitals_url ?: "",
        numberOfLives = 9,
        url = catApiModel.image.url
    )
}
