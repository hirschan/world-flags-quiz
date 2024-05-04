package com.example.worldflags.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.models.CountryCategories
import org.koin.android.ext.android.get

/** UI for options screen. */

class OptionActivity : ComponentActivity() {

    private lateinit var viewModel: OptionActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get<OptionActivityViewModel>()

        setContent {
            OptionsTopLevel(viewModel)
        }
    }
}

@Composable
private fun OptionsTopLevel(viewModel: OptionActivityViewModel) {
    val selectedOption = viewModel.selectedOption.observeAsState().value ?: CountryCategories.EU_COUNTRIES_NON_UN

    OptionScreen(selectedOption) {
        viewModel.changeSelectedOption(it)
    }
}

@Composable
private fun OptionScreen(selectedOption: CountryCategories, onOptionSelected: (CountryCategories) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarHeader()
        RadioButtonOptions(selectedOption, onOptionSelected)
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

// TODO: refactor function
@Composable
private fun RadioButtonOptions(selectedOption: CountryCategories, onOptionSelected: (CountryCategories) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.TopStart),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CustomRadioButton(
                mainText = "Europe flags (non-UN)",
                subText = "10 countries", // TODO: create function for counting json file items
                option = CountryCategories.EU_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }
        item {
            CustomRadioButton(
                mainText = "Europe flags (UN)",
                subText = "44 countries",
                option = CountryCategories.EU_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }
        item {
            CustomRadioButton(
                mainText = "North America flags (UN)",
                subText = "23 countries",
                option = CountryCategories.NA_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "North America (non-UN)",
                subText = "22 countries",
                option = CountryCategories.NA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "South America flags (UN)",
                subText = "13 countries",
                option = CountryCategories.SA_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "South America flags (non-UN)",
                subText = "6 countries",
                option = CountryCategories.SA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Africa flags (UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Africa flags (non-UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Asia flags (UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Asia flags (non-UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Oceania flags (UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Oceania flags (non-UN)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            CustomRadioButton(
                mainText = "Antarctica flags (misc)",
                subText = "X countries",
                option = CountryCategories.NA_COUNTRIES_NON_UN,
                selectedOption = selectedOption,
                onOptionSelected = { onOptionSelected(it) }
            )
        }

        item {
            BottomText()
        }
    }
}

@Composable
private fun CustomRadioButton(
    mainText: String,
    subText: String,
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
            onClick = { onOptionSelected(option) },
            colors = RadioButtonDefaults.colors(selectedColor = White)
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = mainText,
                color = White,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subText,
                color = White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun BottomText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp), // Add padding to push the text off the bottom
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = "Our definition of a country is from Flagpedia, meaning countries whose flag is represented by emojis.",
            color = White,
            fontSize = 10.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewOptionScreen() {
    val mockSelectedOption = CountryCategories.EU_COUNTRIES_UN

    OptionScreen(mockSelectedOption, onOptionSelected = {})
}