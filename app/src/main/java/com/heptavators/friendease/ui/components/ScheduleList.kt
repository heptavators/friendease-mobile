package com.heptavators.friendease.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heptavators.friendease.data.model.ListOrderResponse
import com.heptavators.friendease.data.model.OrderItem
import com.heptavators.friendease.data.model.OrderResponse

@Composable
fun ScheduleList(
    data: List<OrderItem>,
    makePayment: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp, top = 3.dp)
    ) {
        this.items(data, key = {it.orderId}) { order ->
            CardSchedule(
                order,
                makePayment = {makePayment(order.token?:"")}
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
            )
        }
    }
}