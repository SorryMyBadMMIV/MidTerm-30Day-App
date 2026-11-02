package com.example.a30dayapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a30dayapp.ui.GourmetApp
import com.example.a30dayapp.ui.theme.GourmetTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GourmetAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launchApp(
        openedDays: Set<Int> = emptySet(),
        onDayOpened: (Int) -> Unit = {},
    ) {
        composeTestRule.setContent {
            GourmetTheme {
                GourmetApp(openedDays = openedDays, onDayOpened = onDayOpened)
            }
        }
    }

    @Test
    fun topBar_showsAppName() {
        launchApp()
        composeTestRule.onNodeWithText("30 Days of Gourmet").assertIsDisplayed()
    }

    @Test
    fun firstCard_showsRestaurantName() {
        launchApp()
        composeTestRule.onNodeWithText("Jay Fai").assertIsDisplayed()
    }

    @Test
    fun tappingCard_revealsDescription() {
        launchApp()
        composeTestRule.onNodeWithText("TRY: Crab omelette").assertDoesNotExist()
        composeTestRule.onNodeWithText("Jay Fai").performClick()
        composeTestRule.onNodeWithText("TRY: Crab omelette").assertIsDisplayed()
    }

    @Test
    fun openedDay_showsTick() {
        launchApp(openedDays = setOf(1))
        composeTestRule.onNodeWithContentDescription("Opened").assertIsDisplayed()
    }

    @Test
    fun unopenedDays_showNoTick() {
        launchApp()
        composeTestRule.onNodeWithContentDescription("Opened").assertDoesNotExist()
    }

    @Test
    fun topBar_countsOpenedDays() {
        launchApp(openedDays = setOf(1, 5))
        composeTestRule.onNodeWithText("2/30").assertIsDisplayed()
    }

    @Test
    fun tappingCard_reportsDayOpened() {
        val reported = mutableListOf<Int>()
        launchApp(onDayOpened = { reported += it })
        composeTestRule.onNodeWithText("Jay Fai").performClick()
        assertEquals(listOf(1), reported)
    }

    @Test
    fun closingCard_doesNotReportItAgain() {
        val reported = mutableListOf<Int>()
        launchApp(onDayOpened = { reported += it })
        composeTestRule.onNodeWithText("Jay Fai").performClick()
        composeTestRule.onNodeWithText("Jay Fai").performClick()
        assertEquals(listOf(1), reported)
    }
}
