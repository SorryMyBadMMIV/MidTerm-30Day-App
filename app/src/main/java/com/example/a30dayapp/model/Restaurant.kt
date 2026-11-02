package com.example.a30dayapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * One day's restaurant recommendation. Text and images are held as resource ids
 * rather than literal strings so everything stays translatable.
 */
data class Restaurant(
    val day: Int,
    @param:StringRes val nameResourceId: Int,
    @param:StringRes val cuisineAreaResourceId: Int,
    @param:StringRes val signatureDishResourceId: Int,
    @param:StringRes val descriptionResourceId: Int,
    @param:DrawableRes val imageResourceId: Int,
)
