package com.google.firebase.example.fireeats

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.firebase.example.fireeats.databinding.DialogFiltersBinding
import com.google.firebase.example.fireeats.model.Country
import com.google.firebase.firestore.Query

/**
 * Dialog Fragment containing filter form.
 */
class FilterDialogFragment : DialogFragment() {

    private var _binding: DialogFiltersBinding? = null
    private val binding get() = _binding!!
    private var filterListener: FilterListener? = null

    private val selectedCategory: String?
        get() {
            val selected = binding.spinnerCategory.selectedItem as String
            return if (getString(R.string.value_any_category) == selected) {
                null
            } else {
                selected
            }
        }

    private val selectedCity: String?
        get() {
            val selected = binding.spinnerCity.selectedItem as String
            return if (getString(R.string.value_any_city) == selected) {
                null
            } else {
                selected
            }
        }

    private val selectedPrice: Int
        get() {
            return when (binding.spinnerPrice.selectedItem as String) {
                getString(R.string.price_1) -> 1
                getString(R.string.price_2) -> 2
                getString(R.string.price_3) -> 3
                else -> -1
            }
        }

    private val selectedSortBy: String?
        get() {
            val selected = binding.spinnerSort.selectedItem as String
            if (getString(R.string.sort_by_rating) == selected) {
                return Country.FIELD_AVG_RATING
            }
            if (getString(R.string.sort_by_price) == selected) {
                return Country.FIELD_PRICE
            }
            return if (getString(R.string.sort_by_popularity) == selected) {
                Country.FIELD_POPULARITY
            } else {
                null
            }
        }

    private val sortDirection: Query.Direction
        get() {
            val selected = binding.spinnerSort.selectedItem as String
            if (getString(R.string.sort_by_rating) == selected) {
                return Query.Direction.DESCENDING
            }
            if (getString(R.string.sort_by_price) == selected) {
                return Query.Direction.ASCENDING
            }
            return if (getString(R.string.sort_by_popularity) == selected) {
                Query.Direction.DESCENDING
            } else {
                Query.Direction.DESCENDING
            }
        }

    private val filters: Filters
        get() {
            val filters = Filters()

            filters.typeState = selectedCategory
            filters.continet = selectedCity
            filters.size = selectedPrice
            filters.sortBy = selectedSortBy
            filters.sortDirection = sortDirection

            return filters
        }

    interface FilterListener {

        fun onFilter(filters: Filters)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFiltersBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener { onSearchClicked() }
        binding.buttonCancel.setOnClickListener { onCancelClicked() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (parentFragment is FilterListener) {
            filterListener = parentFragment as FilterListener
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun onSearchClicked() {
        filterListener?.onFilter(filters)
        dismiss()
    }

    private fun onCancelClicked() {
        dismiss()
    }

    fun resetFilters() {
        _binding?.let {
            it.spinnerCategory.setSelection(0)
            it.spinnerCity.setSelection(0)
            it.spinnerPrice.setSelection(0)
            it.spinnerSort.setSelection(0)
        }
    }

    companion object {

        const val TAG = "FilterDialog"
    }
}
