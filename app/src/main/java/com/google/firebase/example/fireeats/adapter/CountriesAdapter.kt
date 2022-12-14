package com.google.firebase.example.fireeats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.example.fireeats.R
import com.google.firebase.example.fireeats.databinding.ItemRestaurantBinding
import com.google.firebase.example.fireeats.model.Country
import com.google.firebase.example.fireeats.util.CountriesUtil
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

/**
 * RecyclerView adapter for a list of Restaurants.
 */
open class CountriesAdapter(query: Query, private val listener: OnCountrySelectedListener) :
        FirestoreAdapter<CountriesAdapter.ViewHolder>(query) {

    interface OnCountrySelectedListener {

        fun onRestaurantSelected(restaurant: DocumentSnapshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnCountrySelectedListener?
        ) {

            val restaurant = snapshot.toObject<Country>()
            if (restaurant == null) {
                return
            }

            val resources = binding.root.resources

            // Load image
            Glide.with(binding.restaurantItemImage.context)
                    .load(restaurant.photo)
                    .into(binding.restaurantItemImage)

            val numRatings: Int = restaurant.numRatings

            binding.restaurantItemName.text = restaurant.name
            binding.restaurantItemRating.rating = restaurant.avgRating.toFloat()
            binding.restaurantItemCity.text = restaurant.continent
            binding.restaurantItemCategory.text = restaurant.typeState
            binding.restaurantItemNumRatings.text = resources.getString(
                    R.string.fmt_num_ratings,
                    numRatings)
            binding.restaurantItemPrice.text = CountriesUtil.getPriceString(restaurant)

            // Click listener
            binding.root.setOnClickListener {
                listener?.onRestaurantSelected(snapshot)
            }
        }
    }
}
