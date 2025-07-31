package com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.samzuhalsetiawan.qrstudio.presentation.ui.component.card.FeatureCard

@Composable
fun DashboardScreen() {
    val context = LocalContext.current

    Column {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Features",
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FeatureCard {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Text("Create QR")
            }
            FeatureCard {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
                Text("My QR")
            }
            FeatureCard {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
                Text("Share My QR")
            }
        }
    }
}