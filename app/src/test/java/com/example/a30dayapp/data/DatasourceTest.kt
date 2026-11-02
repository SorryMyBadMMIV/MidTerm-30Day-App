package com.example.a30dayapp.data

import com.example.a30dayapp.model.Restaurant
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Guards the 30 entries against copy-paste slips. Thirty near-identical entries
 * referencing 150 resource ids make a mistyped prefix almost inevitable, and such
 * a bug stays invisible until you scroll to the affected day.
 */
class DatasourceTest {

    private val restaurants: List<Restaurant> = Datasource.loadRestaurants()

    @Test
    fun loadRestaurants_returnsThirtyEntries() {
        assertEquals(30, restaurants.size)
    }

    @Test
    fun loadRestaurants_coversDaysOneToThirtyInOrder() {
        assertEquals((1..30).toList(), restaurants.map { it.day })
    }

    @Test
    fun loadRestaurants_reusesNoImage() {
        val images = restaurants.map { it.imageResourceId }
        assertEquals(
            "a drawable is used by more than one restaurant",
            images.size,
            images.distinct().size,
        )
    }

    @Test
    fun loadRestaurants_reusesNoString() {
        val strings = restaurants.flatMap {
            listOf(
                it.nameResourceId,
                it.cuisineAreaResourceId,
                it.signatureDishResourceId,
                it.descriptionResourceId,
            )
        }
        assertEquals(
            "a string resource is used by more than one restaurant",
            strings.size,
            strings.distinct().size,
        )
    }
}
