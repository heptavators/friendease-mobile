package com.heptavators.friendease.ui.screen.register2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.data.model.Tags
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun HobiScreen(
    tags: List<String>,
    onValueChangeTags: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTags by remember { mutableStateOf(tags) }
    val progress by remember { mutableStateOf(0.55f) }
    LaunchedEffect(selectedTags) {
        onValueChangeTags(selectedTags)
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LinearProgressIndicator(
                progress = progress,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(top = 25.dp),
            ) {
                Text(
                    text = "Hobi atau profesi kamu apa sih?",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 37.5.sp
                    ),
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Pilih 5 tag yang menggambarkan dirimu. Ini akan membantu kamu menemukan teman yang sefrekuensi.",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(
                        lineHeight = 17.5.sp
                    ),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )

                TagSelection(
                    availableTags = listOf(
                        "Dermawan",
                        "Cerdas",
                        "Disiplin",
                        "Percaya-diri",
                        "Suka-menolong",
                        "Keibuan",
                        "Independen",
                        "Humoris",
                        "Skena",
                        "Anak-Senja",
                        "Berjiwa-petualang",
                        "Penuh-semangat",
                        "Kreatif",
                        "Kutu-buku",
                        "Perfeksionis",
                        "Sabar",
                        "Berjiwa-sosial",
                        "Romantis",
                        "Kritis",
                        "Terorganisir",
                    ),
                    selectedTags = selectedTags,
                    onTagSelected = { tag ->
                        selectedTags = if (selectedTags.contains(tag)) {
                            selectedTags - tag
                        } else {
                            if (selectedTags.size < 5) {
                                selectedTags + tag
                            } else {
                                selectedTags
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                NextButton(
                    onClick = {
                    },
                    isEnabled = selectedTags.size == 5,
                    selectedTagsCount = selectedTags.size
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagSelection(
    availableTags: List<String>,
    selectedTags: List<String>,
    onTagSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        availableTags.forEach { tag ->
            TagButton(
                text = tag,
                isSelected = tag in selectedTags,
                onClick = { onTagSelected(tag) },
            )
        }
    }
}

@Composable
fun TagButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(15.dp)
    val defaultColor = Color.White
    val selectedColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier
            .heightIn(min = 38.dp)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(
                color = if (isSelected) selectedColor else defaultColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = if (isSelected) defaultColor else selectedColor,
                shape = shape
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = if (isSelected) defaultColor else selectedColor,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun NextButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
    selectedTagsCount: Int
) {
    val buttonAlpha = if (isEnabled) 1f else 0.5f

//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 16.dp)
//            .background(
//                color = MaterialTheme.colorScheme.primary,
//                shape = RoundedCornerShape(4.dp)
//            )
//            .alpha(buttonAlpha)
//    )
}

@Preview
@Composable
fun HobiScreenPreview() {
    FriendeaseTheme {
        HobiScreen(
            tags = listOf("Mancing", "Travelling", "Peternak", "Membaca Buku", "Cosplayer"),
            onValueChangeTags = {}
        )
    }
}


