package com.example.a30dayapp.data

import android.content.Context
import androidx.core.content.edit

/**
 * Remembers which days have been opened, in the app's own SharedPreferences, so
 * the ticks are still there after the app is closed and opened again.
 */
class OpenedDaysStore(context: Context) {

    private val prefs = context.getSharedPreferences("opened_days", Context.MODE_PRIVATE)

    fun load(): Set<Int> =
        prefs.getStringSet(KEY_DAYS, null).orEmpty().map { it.toInt() }.toSet()

    fun save(days: Set<Int>) {
        prefs.edit { putStringSet(KEY_DAYS, days.map { it.toString() }.toSet()) }
    }

    private companion object {
        const val KEY_DAYS = "days"
    }
}
