package com.example.catalist_android_compose.breeds.networking.api.model

import com.example.catalist_android_compose.breeds.domain.Cat

fun mapCatApiModelToCat(catApiModel: CatApiModel): Cat {
    return Cat(
        id = catApiModel.id,
        weight = "${catApiModel.weight.imperial} - ${catApiModel.weight.metric}",
        name = catApiModel.name,
        alternateName = catApiModel.alt_names ?: "",
        temperament = catApiModel.temperament,
        origin = catApiModel.origin,
        description = catApiModel.description,
        lifeSpan = catApiModel.life_span,
        indoor = catApiModel.indoor,
        lap = catApiModel.lap,
        adaptability = catApiModel.adaptability,
        affectionLevels = catApiModel.affection_level,
        childFriendly = catApiModel.child_friendly,
        dogFriendly = catApiModel.dog_friendly,
        energyLevel = catApiModel.energy_level,
        grooming = catApiModel.grooming,
        healthIssues = catApiModel.health_issues,
        inteligence = catApiModel.intelligence,
        sheddingLevel = catApiModel.shedding_level,
        socialNeeds = catApiModel.social_needs,
        strangerFriendly = catApiModel.stranger_friendly,
        vocalisation = catApiModel.vocalisation,
        experimental = catApiModel.experimental,
        hairless = catApiModel.hairless,
        natural = catApiModel.natural,
        rare = catApiModel.rare,
        rex = catApiModel.rex,
        shortLegs = catApiModel.short_legs,
        wikipediaLink = catApiModel.wikipedia_url,
        hypoallergenic = catApiModel.hypoallergenic,
        referenceImageId = catApiModel.reference_image_id,
        link = catApiModel.vetstreet_url ?: catApiModel.cfa_url ?: catApiModel.vcahospitals_url ?: "",
        numberOfLives = 9 // You might want to set this value differently
    )
}
