package com.looker.navcharassignment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun InteractionSources(
	modifier: Modifier = Modifier,
	items: @Composable RowScope.() -> Unit
) {
	CompositionLocalProvider(
		LocalTextStyle provides MaterialTheme.typography.labelSmall,
		LocalContentColor provides MaterialTheme.colorScheme.outline
	) {
		Row(
			modifier = modifier,
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceAround,
			content = items
		)
	}
}

@Composable
fun InteractionItem(
	icon: ImageVector,
	text: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	Row(
		modifier = modifier
			.defaultMinSize(minHeight = 40.dp)
			.clip(CircleShape)
			.clickable(onClick = onClick)
			.padding(horizontal = 12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(imageVector = icon, contentDescription = null)
		Spacer(modifier = Modifier.width(4.dp))
		Text(text = text)
	}
}