package com.looker.navcharassignment.ui.home_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NetworkImage(
	data: String,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.Crop,
	contentDescription: String? = null
) {
	AsyncImage(
		modifier = modifier,
		model = data,
		contentScale = contentScale,
		contentDescription = contentDescription
	)
}

@Composable
fun HorizontalImageList(
	data: List<String>,
	modifier: Modifier = Modifier,
	extraContentPadding: PaddingValues = PaddingValues(horizontal = 8.dp)
) {
	LazyRow(
		modifier = modifier,
		contentPadding = extraContentPadding,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		items(data) { imageUrl ->
			NetworkImage(
				data = imageUrl,
				modifier = Modifier
					.size(240.dp, 280.dp)
					.clip(MaterialTheme.shapes.small)

			)
		}
	}
}