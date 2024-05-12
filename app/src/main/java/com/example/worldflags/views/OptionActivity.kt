package com.example.worldflags.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldflags.R
import com.example.worldflags.designsystem.IconComponent
import com.example.worldflags.designsystem.RadioButtonComponent
import com.example.worldflags.models.CountryCategories
import com.example.worldflags.models.RadioButtonData
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
    val selectedOption = viewModel.selectedOption.observeAsState().value ?: CountryCategories.EU_TERRITORIES_NON_UN

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
                IconComponent()
            }
        },
        title = { Text(text = "Options", color = White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.light_blue))
    )
}

@Composable
private fun RadioButtonOptions(selectedOption: CountryCategories, onOptionSelected: (CountryCategories) -> Unit) {
    val radioButtons = listOf(
        RadioButtonData("Africa flags (UN)", "54 sovereign states", CountryCategories.NA_STATES_UN),
        RadioButtonData("Africa flags (non-UN)", "6 territories", CountryCategories.NA_TERRITORIES_NON_UN),
        RadioButtonData("Antarctica flags", "7 territories", CountryCategories.AN_TERRITORIES),
        RadioButtonData("Asia flags (UN)", "X sovereign states", CountryCategories.NA_STATES_UN),
        RadioButtonData("Asia flags (non-UN)", "X territories", CountryCategories.NA_TERRITORIES_NON_UN),
        RadioButtonData("Europe flags (UN)", "44 sovereign states", CountryCategories.EU_TERRITORIES_UN),
        RadioButtonData("Europe flags (non-UN)", "10 territories", CountryCategories.EU_TERRITORIES_NON_UN),
        RadioButtonData("North America flags (UN)", "23 sovereign states", CountryCategories.NA_STATES_UN),
        RadioButtonData("North America flags (non-UN)", "22 territories", CountryCategories.NA_TERRITORIES_NON_UN),
        RadioButtonData("Oceania flags (UN)", "X sovereign states", CountryCategories.NA_STATES_UN),
        RadioButtonData("Oceania flags (non-UN)", "X territories", CountryCategories.NA_TERRITORIES_NON_UN),
        RadioButtonData("South America flags (UN)", "13 sovereign states", CountryCategories.SA_STATES_UN),
        RadioButtonData("South America flags (non-UN)", "6 territories", CountryCategories.SA_TERRITORIES_NON_UN),
        )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.TopStart),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(radioButtons) { radioButton ->
            RadioButtonComponent(
                mainText = radioButton.mainText,
                subText = radioButton.subText,
                option = radioButton.option,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOptionScreen() {
    val mockSelectedOption = CountryCategories.EU_TERRITORIES_UN

    OptionScreen(mockSelectedOption, onOptionSelected = {})
}