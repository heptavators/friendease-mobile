package com.dylan.friendease.ui.screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.friendease.R
import com.dylan.friendease.ui.components.TalentList
import com.dylan.friendease.ui.components.UiState
import com.dylan.friendease.ui.screen.getViewModelFactory
import com.dylan.friendease.ui.theme.FriendeaseTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun SearchScreen(
    navigateToLogin: () -> Unit,
    navigateToWelcome: () -> Unit,
    navigateToDetail: (String) -> Unit,
    viewModel: SearchViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {

    var searchText by remember { mutableStateOf("") }
    val searchTextStateFlow = remember { MutableStateFlow(searchText) }
    val talentData by viewModel.talentData

    LaunchedEffect(searchText){
        searchTextStateFlow.debounce(400).distinctUntilChanged().collect { debouncedSearchText ->
            viewModel.getTalent(debouncedSearchText)
        }
    }

    LaunchedEffect(talentData){
        if (talentData is UiState.Loading) {
            viewModel.getTalent(searchText)
        }
        if (talentData is UiState.NotLogged) navigateToWelcome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "gak perlu takut \nSendirian lagi!!",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "#bersamalebihasyik",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.friends),
                    contentDescription = null,
                    modifier = Modifier
                        .width(144.dp)
                        .height(105.dp)
                        .background(Color.Transparent)
                )
            }
            SearchView(
                initialText = searchText,
                onValueChange = { searchText = it }
            )
        }
        when(talentData){
            is UiState.Loading -> {
                Text(text = "Loading")
            }
            is UiState.Success -> {
                val data = (talentData as UiState.Success).data
                if (data != null) {
                    Column {
                        TalentList(
                            data.data,
                            navigateToDetail = navigateToDetail
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null,
                                modifier = Modifier.size(240.dp),
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(240.dp),
                        )
                    }
                }
            }
            is UiState.NotLogged -> {
                Text("Not Logged")
            }
            else -> {}
        }
    }
}

@Composable
private fun SearchView(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Mau cari teman seperti apa?",
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    initialText: String = "",
    onValueChange: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf(initialText) }
    BasicTextField(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background,
                MaterialTheme.shapes.small,
            )
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Preview
@Composable
fun SearchScreenPreview(){
    FriendeaseTheme {
        SearchScreen(
            navigateToLogin = {},
            navigateToWelcome = {},
            navigateToDetail = {}
            )
    }
}