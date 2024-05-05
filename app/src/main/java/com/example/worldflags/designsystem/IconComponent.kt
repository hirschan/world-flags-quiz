package com.example.worldflags.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldflags.R

@Composable
fun IconComponent() {
    Icon(
        painter = painterResource(id = R.drawable.ic_action_arrow_left),
        contentDescription = "Go back arrow icon",
        tint = Color.White,
        modifier = Modifier.size(30.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconComponent() {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconComponent()
    }
}