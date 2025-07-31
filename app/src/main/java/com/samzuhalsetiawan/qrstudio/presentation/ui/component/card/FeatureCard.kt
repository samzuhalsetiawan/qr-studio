package com.samzuhalsetiawan.qrstudio.presentation.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            content = content
        )
    }
}