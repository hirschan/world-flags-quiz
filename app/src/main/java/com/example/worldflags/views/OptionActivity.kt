package com.example.worldflags.views

import android.content.Intent
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
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
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarHeader()
        RadioButtonOptions()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarHeader() {
    val context = LocalContext.current

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_arrow_left),
                    contentDescription = "Go back arrow icon",
                    tint = White
                )
            }
        },
        title = { Text(text = "Options", color = White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.light_blue))
    )
}

@Composable
private fun RadioButtonOptions() {
    var selectedOption by remember { mutableStateOf(CountryCategories.EU_COUNTRIES_NON_UN) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.TopStart),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RadioButton(
            text = "European flags (non-UN)",
            option = CountryCategories.EU_COUNTRIES_NON_UN,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )

        RadioButton(
            text = "European flags (UN)",
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
            },
            colors = RadioButtonColors(
                selectedColor = White,
                unselectedColor = White,
                disabledSelectedColor = White,
                disabledUnselectedColor = White,
            )
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