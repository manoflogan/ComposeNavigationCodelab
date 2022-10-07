/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.rally.ui.bills

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import com.example.compose.rally.R
import com.example.compose.rally.SingleBill
import com.example.compose.rally.data.Bill
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.components.AccountRow
import com.example.compose.rally.ui.components.BillRow
import com.example.compose.rally.ui.components.Statement
import com.example.compose.rally.ui.components.StatementBody

/**
 * The Bills screen.
 */
@Composable
fun BillsScreen(
    bills: List<Bill> = remember { UserData.bills },
    onBillClick: (String) -> Unit
) {
    StatementBody(
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills" },
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.fold(0F) { sum, bill ->
           sum + bill.amount
        },
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(
                modifier = Modifier.clickable {
                    onBillClick(bill.name)
                },
                bill.name,
                bill.due,
                bill.amount,
                bill.color
            )
        }
    )
}

@Composable
fun SingleBillScreen(
    billType: String? = UserData.bills.first().name
) {
    val bill = remember(billType) {
        UserData.getBill(billType)
    }
    Statement(account = bill, color = {bill.color}, balance = {bill.amount} ,
        amountTotal = {bill.amount}, accountName = {bill.name}
    ) {
        BillRow(Modifier, name = it.name, due = bill.due, amount = it.amount, color = it.color)
    }
}
