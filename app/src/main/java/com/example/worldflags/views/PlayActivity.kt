package com.example.worldflags.views

import FlagButtonComponent
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldflags.R
import com.example.worldflags.designsystem.IconComponent
import com.example.worldflags.models.FlagProperty
import com.example.worldflags.utils.MockData
import com.example.worldflags.utils.Utils
import org.koin.android.ext.android.get

/** UI for play screen. */

private const val EN_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/"

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

    val fourFlagNamesToDisplay = viewModel.fourRandomUniqueFlagProps.observeAsState().value
    val correctFlag = viewModel.correctFlagProperty.observeAsState().value
    val isGameComplete = viewModel.isGameComplete.observeAsState().value
    val nbrOfGuessedFlags = viewModel.nbrOfGuessedFlags.observeAsState().value ?: 0
    val nbrOfFlags = viewModel.getNumberOfFlags()
    val nbrOfIncorrectGuessedFlags = viewModel.nbrOfIncorrectGuessedFlags.observeAsState().value ?: 0

    val nbrOfClicksPerFlag = remember { mutableIntStateOf(0) }
    val nbrOfCorrectGuessesOnFirstTry = viewModel.nbrOfCorrectGuessesOnFirstTry.observeAsState().value ?: 0

    val resetButtonColors  = remember { mutableStateOf(false) }

    // Trigger side-effect when isGameComplete changes to true
    LaunchedEffect(isGameComplete) {
        if (isGameComplete == true) {
            finishGame(context, nbrOfFlags, nbrOfCorrectGuessesOnFirstTry)
        }
    }

    if (fourFlagNamesToDisplay != null) {
        PlayScreen(
            fourFlagNamesToDisplay,
            correctFlag,
            nbrOfGuessedFlags,
            nbrOfFlags,
            nbrOfIncorrectGuessedFlags,
            resetButtonColors,
        ) { isCorrectClicked ->
            if (!isCorrectClicked) {
                viewModel.onIncorrectAnswerSelected()
                nbrOfClicksPerFlag.intValue += 1
            }
            if (isCorrectClicked && correctFlag != null) {
                onCorrectAnswerClicked(resetButtonColors, viewModel, correctFlag, nbrOfClicksPerFlag)
            } else {
                resetButtonColors.value = false
            }
        }
    }
}

private fun onCorrectAnswerClicked(
    resetButtonColors: MutableState<Boolean>,
    viewModel: PlayActivityViewModel,
    correctFlag: FlagProperty,
    nbrOfClicksPerFlag: MutableIntState
) {
    resetButtonColors.value = true
    viewModel.onCorrectAnswerSelected(correctFlag)
    if (nbrOfClicksPerFlag.intValue == 0) {
        viewModel.onCorrectAnswerClickedOnFirstTry()
    } else {
        nbrOfClicksPerFlag.intValue = 0
    }
}

private fun finishGame(context: Context, nbrOfFlags: Int, nbrOfCorrectGuessesOnFirstTry: Int) {
    val intent = Intent(context, ResultActivity::class.java)
    intent.putExtra("nbrOfCorrectGuessesOnFirstTry", nbrOfCorrectGuessesOnFirstTry)
    intent.putExtra("nbrOfFlags", nbrOfFlags)
    context.startActivity(intent)
}

@Composable
private fun PlayScreen(
    fourCountriesToDisplay: List<FlagProperty?>,
    correctFlagProperty: FlagProperty?,
    nbrOfGuessedCountries: Int,
    nbrOfFlags: Int,
    nbrOfIncorrectGuessedFlags: Int,
    resetButtonColors: MutableState<Boolean>,
    isCorrectClicked: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TopAppBarHeader(nbrOfGuessedCountries, nbrOfFlags, nbrOfIncorrectGuessedFlags)
        FlagPlaceholder(correctFlagProperty)
        OptionButtons(fourCountriesToDisplay, correctFlagProperty, resetButtonColors, isCorrectClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarHeader(nbrOfGuessedCountries: Int, nbrOfFlags: Int, nbrOfIncorrectGuessedFlags: Int) {
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
        actions = {
            IncorrectCounterText(nbrOfIncorrectGuessedFlags)
            CounterText(nbrOfGuessedCountries, nbrOfFlags)
                  },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.light_blue))

    )
}

@Composable
private fun IncorrectCounterText(nbrOfIncorrectGuessedFlags: Int) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .wrapContentWidth(Alignment.End)
            .background(
                color = if (nbrOfIncorrectGuessedFlags == 0) Color.Transparent else colorResource(R.color.dark_red),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "$nbrOfIncorrectGuessedFlags",
            color = if (nbrOfIncorrectGuessedFlags == 0) Color.Transparent else colorResource(R.color.red),
        )
    }
}

@Composable
private fun CounterText(nbrOfGuessedCountries: Int, nbrOfFlags: Int) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .wrapContentWidth(Alignment.End)
            .background(
                color = colorResource(R.color.dark_green),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "${nbrOfGuessedCountries}/${nbrOfFlags}",
            color = colorResource(R.color.green_stroke),
        )
    }
}

@Composable
private fun FlagPlaceholder(correctFlagProperty: FlagProperty?) {
    val context = LocalContext.current
    val utils = Utils()

    Column(
        modifier = Modifier
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val correctFlagISO = correctFlagProperty?.ISOAlpha2?.lowercase() ?: "eu_se"
        val correctFlagName = correctFlagProperty?.name ?: ""

        Image(
            painter = painterResource(utils.getResourceId(correctFlagISO)),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .clickable {
                    openWikipediaPage(context, correctFlagName)
                }
        )
    }
}

// TODO: move to VM?
private fun openWikipediaPage(context: Context, correctFlagName: String) {
    val utils = Utils()

    val formattedFlagName = utils.convertFlagName(correctFlagName)
    val wikiAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse(EN_WIKIPEDIA_URL + formattedFlagName)).apply {
        `package` = "org.wikipedia"
    }
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(EN_WIKIPEDIA_URL + formattedFlagName))

    try {
        context.startActivity(wikiAppIntent)
    } catch (e: ActivityNotFoundException) {
        context.startActivity(webIntent)
    }
}

@Composable
private fun OptionButtons(fourFlagsToDisplay: List<FlagProperty?>, correctFlagProperty: FlagProperty?, resetButtonColors: MutableState<Boolean>, isCorrectClicked: (Boolean) -> Unit) {
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
                flagName = fourFlagsToDisplay[0]?.name,
                correctFlagName = correctFlagProperty?.name,
                onClick = isCorrectClicked,
                resetButtonColors = resetButtonColors,
                paddingEnd = 8.dp,
            )
            FlagButtonComponent(
                flagName = fourFlagsToDisplay[1]?.name,
                correctFlagName = correctFlagProperty?.name,
                onClick = isCorrectClicked,
                resetButtonColors = resetButtonColors,
                paddingStart = 8.dp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            FlagButtonComponent(
                flagName = fourFlagsToDisplay[2]?.name,
                correctFlagName = correctFlagProperty?.name,
                onClick = isCorrectClicked,
                resetButtonColors = resetButtonColors,
                paddingEnd = 8.dp,
            )
            FlagButtonComponent(
                flagName = fourFlagsToDisplay[3]?.name,
                correctFlagName = correctFlagProperty?.name,
                onClick = isCorrectClicked,
                resetButtonColors = resetButtonColors,
                paddingStart = 8.dp,
            )
        }
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
    val mockResetButtonColors = remember { mutableStateOf(false) }
    val mockNbrOfIncorrectGuessedFlags = 1

    PlayScreen(
        mockFourFlagProps,
        mockCorrectFlag,
        mockNbrOfGuessedFlags,
        mockNbrOfFlags,
        mockNbrOfIncorrectGuessedFlags,
        mockResetButtonColors,
        mockIsCorrectClicked,
    )
}