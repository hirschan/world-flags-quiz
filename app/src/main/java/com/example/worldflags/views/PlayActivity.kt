package com.example.worldflags.views

import FlagButtonComponent
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.designsystem.IconComponent
import com.example.worldflags.models.FlagProperty
import com.example.worldflags.utils.MockData
import org.koin.android.ext.android.get

/** UI for play screen. */

class PlayActivity : ComponentActivity() {

    private lateinit var viewModel: PlayActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get<PlayActivityViewModel>()

        setContent {
            PlayTopLevel(viewModel)
        }
    }
}

@Composable
private fun PlayTopLevel(viewModel: PlayActivityViewModel) {
    val context = LocalContext.current

    val fourCountryNamesToDisplay = viewModel.fourRandomNoDuplicateFlagProps.observeAsState().value
    val correctCountry = viewModel.correctFlagProperty.observeAsState().value
    val isGameComplete = viewModel.isComplete.observeAsState().value
    val nbrOfGuessedCountries = viewModel.nbrOfGuessedFlags.observeAsState().value ?: 0
    val nbrOfCountries = viewModel.getNumberOfFlags()

    // Trigger side-effect when isComplete changes to true
    LaunchedEffect(isGameComplete) {
        if (isGameComplete == true) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    if (fourCountryNamesToDisplay != null) {
        PlayScreen(fourCountryNamesToDisplay, correctCountry, nbrOfGuessedCountries, nbrOfCountries) { isCorrectClicked ->
            if (isCorrectClicked) {
                if (correctCountry != null) {
                    viewModel.onCorrectAnswerSelected(correctCountry)
                }
            }
        }
    }
}

@Composable
private fun PlayScreen(fourCountriesToDisplay: List<FlagProperty?>, correctFlagProperty: FlagProperty?, nbrOfGuessedCountries: Int, nbrOfCountries: Int, isCorrectClicked: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TopAppBarHeader(nbrOfGuessedCountries, nbrOfCountries)
        FlagPlaceholder(correctFlagProperty)
        OptionButtons(fourCountriesToDisplay, correctFlagProperty, isCorrectClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarHeader(nbrOfGuessedCountries: Int, nbrOfCountries: Int) {
    val context = LocalContext.current

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
            ) {
                IconComponent()
            }
        },
        title = { Text(text = "", color = Color.White) },
        actions = { CounterText(nbrOfGuessedCountries, nbrOfCountries) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.light_blue))

    )
}

@Composable
private fun CounterText(nbrOfGuessedCountries: Int, nbrOfCountries: Int) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .wrapContentWidth(Alignment.End),
    ) {
        Text(
            text = "${nbrOfGuessedCountries}/${nbrOfCountries}",
            color = colorResource(R.color.custom_white)
        )
    }
}

@Composable
private fun FlagPlaceholder(correctFlagProperty: FlagProperty?) {
    Column(
        modifier = Modifier
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val correctFlagISO = correctFlagProperty?.ISOAlpha2?.lowercase() ?: "eu_se"

        Image(
            painter = painterResource(getResourceId(correctFlagISO)),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
        )
    }
}

private fun getResourceId(correctFlagISO: String): Int {
    val resourceId = try {
        val field = R.drawable::class.java.getField(correctFlagISO)
        field.getInt(null)
    } catch (e: Exception) {
        // Handle the case where the resource for the given country code doesn't exist
        R.drawable._missing_flag
    }
    return resourceId
}

@Composable
private fun OptionButtons(fourFlagsToDisplay: List<FlagProperty?>, correctFlagProperty: FlagProperty?, isCorrectClicked: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            FlagButtonComponent(
                text = fourFlagsToDisplay[0]?.name,
                onClick = { onFlagNameButtonClick(fourFlagsToDisplay[0]?.name, correctFlagProperty?.name, isCorrectClicked) },
                paddingEnd = 8.dp,
            )
            FlagButtonComponent(
                text = fourFlagsToDisplay[1]?.name,
                onClick = { onFlagNameButtonClick(fourFlagsToDisplay[1]?.name, correctFlagProperty?.name, isCorrectClicked) },
                paddingStart = 8.dp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            FlagButtonComponent(
                text = fourFlagsToDisplay[2]?.name,
                onClick = { onFlagNameButtonClick(fourFlagsToDisplay[2]?.name, correctFlagProperty?.name, isCorrectClicked) },
                paddingEnd = 8.dp,
            )
            FlagButtonComponent(
                text = fourFlagsToDisplay[3]?.name,
                onClick = { onFlagNameButtonClick(fourFlagsToDisplay[3]?.name, correctFlagProperty?.name, isCorrectClicked) },
                paddingStart = 8.dp,
            )
        }
    }
}

fun onFlagNameButtonClick(buttonFlagLabel: String?, correctFlag: String?, isCorrectClicked: (Boolean) -> Unit) {
    if (buttonFlagLabel.equals(correctFlag)) {
        isCorrectClicked(true)
    } else {
        isCorrectClicked(false)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayScreen() {

    val mockData = MockData()

    val mockFourFlagProps = listOf(mockData.mockFlagProperty, mockData.mockFlagProperty, mockData.mockFlagProperty, mockData.mockFlagProperty)
    val mockCorrectFlag = mockData.mockFlagProperty
    val mockIsCorrectClicked: (Boolean) -> Unit = {}
    val mockNbrOfGuessedFlags = 1
    val mockNbrOfFlags = 4

    PlayScreen(mockFourFlagProps, mockCorrectFlag, mockNbrOfGuessedFlags, mockNbrOfFlags, mockIsCorrectClicked)
}