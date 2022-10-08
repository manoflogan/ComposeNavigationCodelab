package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.compose.rally.ui.components.RallyNavHost
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun test_whetherOverviewIsDisplayed() {
        composeRule.onNodeWithContentDescription("Overview Screen").assertIsDisplayed()
    }

    @Test
    fun test_whetherAccountsIsClicked() {
        composeRule.onNodeWithContentDescription("All Accounts").performClick()
        composeRule.onNodeWithContentDescription("Accounts Screen").assertIsDisplayed()
    }

    @Test
    fun test_whetherBillsIsClicked() {
        composeRule.onNodeWithContentDescription("All Bills").performScrollTo().performClick()
        Assert.assertEquals("bills", navController.currentBackStackEntry?.destination?.route)
    }
}