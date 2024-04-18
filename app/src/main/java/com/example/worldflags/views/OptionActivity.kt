package com.example.worldflags.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.models.Country
import org.koin.android.ext.android.get

/** UI for options screen. */

class OptionActivity : ComponentActivity() {

    private lateinit var viewModel: OptionActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get<OptionActivityViewModel>()

        setContent {
            OptionScreen()
        }
    }
}

@Composable
private fun OptionScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioButtonOptions()
    }
}

@Composable
private fun RadioButtonOptions() {
    var selectedOption by remember { mutableStateOf(CountryCategories.EU_COUNTRIES_NON_UN) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RadioButton(
            text = "Non-UN countries in Europe",
            option = CountryCategories.EU_COUNTRIES_NON_UN,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        RadioButton(
            text = "UN countries in Europe",
            option = CountryCategories.EU_COUNTRIES_UN,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
    }
}

@Composable
private fun RadioButton(
    text: String,
    option: CountryCategories,
    selectedOption: CountryCategories,
    onOptionSelected: (CountryCategories) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onOptionSelected(option) }
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = {
                onOptionSelected(option)

            }
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            color = colorResource(id = R.color.custom_white),
            fontSize = 18.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewOptionScreen() {
    OptionScreen()
}