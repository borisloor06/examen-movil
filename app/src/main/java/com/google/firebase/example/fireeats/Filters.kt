package com.google.firebase.example.fireeats

import android.content.Context
import android.text.TextUtils
import com.google.firebase.example.fireeats.model.Restaurant
import com.google.firebase.example.fireeats.util.RestaurantUtil
import com.google.firebase.firestore.Query

/**
 * Object for passing filters around.
 */
class Filters {

    var typeState: String? = null
    var continet: String? = null
    var size = -1
    var sortBy: String? = null
    var sortDirection: Query.Direction = Query.Direction.DESCENDING

    fun hasCategory(): Boolean {
        return !TextUtils.isEmpty(typeState)
    }

    fun hasCity(): Boolean {
        return !TextUtils.isEmpty(continet)
    }

    fun hasPrice(): Boolean {
        return size > 0
    }

    fun hasSortBy(): Boolean {
        return !TextUtils.isEmpty(sortBy)
    }

    fun getSearchDescription(context: Context): String {
        val desc = StringBuilder()

        if (typeState == null && continet == null) {
            desc.append("<b>")
            desc.append(context.getString(R.string.all_restaurants))
            desc.append("</b>")
        }

        if (typeState != null) {
            desc.append("<b>")
            desc.append(typeState)
            desc.append("</b>")
        }

        if (typeState != null && continet != null) {
            desc.append(" in ")
        }

        if (continet != null) {
            desc.append("<b>")
            desc.append(continet)
            desc.append("</b>")
        }

        if (size > 0) {
            desc.append(" for ")
            desc.append("<b>")
            desc.append(RestaurantUtil.getPriceString(size))
            desc.append("</b>")
        }

        return desc.toString()
    }

    fun getOrderDescription(context: Context): String {
        return when (sortBy) {
            Restaurant.FIELD_PRICE -> context.getString(R.string.sorted_by_price)
            Restaurant.FIELD_POPULARITY -> context.getString(R.string.sorted_by_popularity)
            else -> context.getString(R.string.sorted_by_rating)
        }
    }

    companion object {

        val default: Filters
            get() {
                val filters = Filters()
                filters.sortBy = Restaurant.FIELD_AVG_RATING
                filters.sortDirection = Query.Direction.DESCENDING

                return filters
            }
    }
}
