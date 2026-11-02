package com.example.a30dayapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a30dayapp.data.OpenedDaysStore
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Runs the real activity, so the opened days go through the phone's storage
 * exactly as they do for a user.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Before
    fun forgetOpenedDays() {
        OpenedDaysStore(ApplicationProvider.getApplicationContext()).save(emptySet())
    }

    @Test
    fun openingDay_ticksItAndCountsIt() {
        ActivityScenario.launch(MainActivity::class.java).use {
            composeTestRule.onNodeWithText("0/30").assertIsDisplayed()
            composeTestRule.onNodeWithText("Jay Fai").performClick()
            composeTestRule.onNodeWithText("1/30").assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription("Opened").assertIsDisplayed()
        }
    }

    @Test
    fun openedDay_isStillTickedAfterReopeningApp() {
        ActivityScenario.launch(MainActivity::class.java).use {
            composeTestRule.onNodeWithText("Jay Fai").performClick()
        }
        ActivityScenario.launch(MainActivity::class.java).use {
            composeTestRule.onNodeWithText("1/30").assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription("Opened").assertIsDisplayed()
        }
    }
}
