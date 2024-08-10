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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.designsystem.IconComponent
import com.example.worldflags.designsystem.RadioButtonComponent
import com.example.worldflags.models.FlagCategories
import com.example.worldflags.models.RadioButtonData
import kotlinx.coroutines.launch
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
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Use remember and mutableStateOf to hold the value that will be updated
    val selectedOptionState = remember { mutableStateOf<String?>(FlagCategories.AFRICA_UN.name) }

    // Use LaunchedEffect to read initial selected option from DataStore
    LaunchedEffect(context) {
        selectedOptionState.value = viewModel.readFromDataStore(context)
    }

    selectedOptionState.value?.let { selectedOption ->
        OptionScreen(selectedOption) { newOption ->
            selectedOptionState.value = newOption.name
            viewModel.changeSelectedOption(newOption)
            coroutineScope.launch {
                viewModel.saveToDataStore(context, newOption.name)
            }
        }
    }
}

@Composable
private fun OptionScreen(selectedOption: String, onOptionSelected: (FlagCategories) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarHeader()
        OptionText()
        RadioButtonOptions(selectedOption, onOptionSelected)
    }
}

@Composable
fun OptionText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "UN member states, as of 08-2024",
            color = White,
            fontSize = 20.sp,
        )
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
private fun RadioButtonOptions(selectedOption: String, onOptionSelected: (FlagCategories) -> Unit) {
    val radioButtons = listOf(
        RadioButtonData("Africa flags", "54 UN members", FlagCategories.AFRICA_UN),
        RadioButtonData("Asia flags", "47 UN members", FlagCategories.ASIA_UN),
        RadioButtonData("Europe flags", "43 UN members", FlagCategories.EUROPE_UN),
        RadioButtonData("North America flags", "23 UN members", FlagCategories.NORTH_AMERICA_UN),
        RadioButtonData("Oceania flags", "14 UN members", FlagCategories.OCEANIA_UN),
        RadioButtonData("South America flags", "12 UN members", FlagCategories.SOUTH_AMERICA_UN),
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
    val mockSelectedOption = FlagCategories.EUROPE_UN.name

    OptionScreen(mockSelectedOption, onOptionSelected = {})
}