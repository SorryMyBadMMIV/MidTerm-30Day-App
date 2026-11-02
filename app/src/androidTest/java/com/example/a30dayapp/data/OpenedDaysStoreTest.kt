package com.example.a30dayapp.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenedDaysStoreTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun forgetOpenedDays() {
        OpenedDaysStore(context).save(emptySet())
    }

    @Test
    fun nothingSaved_loadsNoDays() {
        assertEquals(emptySet<Int>(), OpenedDaysStore(context).load())
    }

    @Test
    fun savedDays_areLoadedByANewStore() {
        OpenedDaysStore(context).save(setOf(1, 14, 30))
        assertEquals(setOf(1, 14, 30), OpenedDaysStore(context).load())
    }
}
