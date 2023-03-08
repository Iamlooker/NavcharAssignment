package com.looker.navcharassignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.navcharassignment.ui.theme.NavcharAssignmentTheme

/**
 * Labels can be added to composable to add extra attention to certain elements
 */
@Composable
fun Label(
	text: String,
	modifier: Modifier = Modifier,
	containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
	contentColor: Color = contentColorFor(containerColor),
) {
	Text(
		text = text,
		modifier = modifier
			.background(containerColor, MaterialTheme.shapes.extraSmall)
			.padding(vertical = 4.dp, horizontal = 8.dp),
		maxLines = 1,
		color = contentColor,
		style = MaterialTheme.typography.labelMedium
	)
}

@Preview
@Composable
fun LabelPreview() {
	NavcharAssignmentTheme {
		Label("Android")
	}
}
