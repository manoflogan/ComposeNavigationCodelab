package com.example.compose.rally.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.Accounts
import com.example.compose.rally.Bills
import com.example.compose.rally.Overview
import com.example.compose.rally.SingleAccount
import com.example.compose.rally.navigateSingleTopTo
import com.example.compose.rally.navigateToSingleDestination
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Overview.route, modifier = modifier) {
        composable(Overview.route) {
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController.navigateToSingleDestination(accountType)
                }
            )
        }
        composable(Accounts.route) {
            AccountsScreen {
                navController.navigateSingleTopTo(Accounts.route)
            }
        }
        composable(Bills.route) {
            BillsScreen()
        }
        composable(
            SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry: NavBackStackEntry ->
            val accountType = navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}