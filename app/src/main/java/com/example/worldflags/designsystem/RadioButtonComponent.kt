package com.example.worldflags.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.models.FlagCategories

@Composable
fun RadioButtonComponent(
    mainText: String,
    subText: String,
    option: FlagCategories,
    selectedOption: FlagCategories,
    onOptionSelected: (FlagCategories) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onOptionSelected(option) }
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) },
            colors = RadioButtonDefaults.colors(selectedColor = colorResource(
                id = R.color.custom_white)
            )
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = mainText,
                color = colorResource(id = R.color.custom_white),
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subText,
                color = colorResource(id = R.color.custom_white),
                fontSize = 12.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRadioButtonComponent() {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.dark_blue_background))
            .padding(16.dp)
            .wrapContentSize(Alignment.TopStart),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RadioButtonComponent(
            "Europe flags",
            "43 UN members",
            FlagCategories.EUROPE_UN,
            FlagCategories.EUROPE_UN,
        ) {}
    }
}