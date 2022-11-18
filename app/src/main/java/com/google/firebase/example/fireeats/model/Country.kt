package com.google.firebase.example.fireeats.model

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Restaurant POJO.
 */
@IgnoreExtraProperties
data class Country(
    var name: String? = null,
    var continent: String? = null,
    var typeState: String? = null,
    var photo: String? = null,
    var size: Int = 0,
    var numRatings: Int = 0,
    var avgRating: Double = 0.toDouble()
) {

    companion object {

        const val FIELD_CITY = "continent"
        const val FIELD_CATEGORY = "typeState"
        const val FIELD_PRICE = "size"
        const val FIELD_POPULARITY = "numRatings"
        const val FIELD_AVG_RATING = "avgRating"
    }
}
