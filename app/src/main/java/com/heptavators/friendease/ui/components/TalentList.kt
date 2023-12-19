package com.heptavators.friendease.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.heptavators.friendease.data.model.TalentData

@Composable
fun TalentList(
    data: List<TalentData>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        items(data, key = { it.talentId }) { talent ->
            CardView(
                talent,
                navigateToDetail = {navigateToDetail(talent.talentId)}
            )
        }
    }
}