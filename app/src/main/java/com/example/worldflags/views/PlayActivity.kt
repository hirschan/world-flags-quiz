package com.example.worldflags.views

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
import com.example.worldflags.models.Country
import com.example.worldflags.utils.MockData
import org.koin.android.ext.android.get

/** UI for play screen. */

class PlayActivity : ComponentActivity() {

    private lateinit var viewModel: PlayActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get<PlayActivityViewModel>()

        setContent {
            TopLevel(viewModel)
        }
    }
}

@Composable
fun TopLevel(viewModel: PlayActivityViewModel) {
    val context = LocalContext.current

    val fourCountryNamesToDisplay = viewModel.fourRandomNoDuplicateCountries.observeAsState().value
    val correctCountry = viewModel.correctCountry.observeAsState().value
    val isGameComplete = viewModel.isComplete.observeAsState().value
    val nbrOfGuessedCountries = viewModel.nbrOfGuessedCountries.observeAsState().value ?: 0
    val nbrOfCountries = viewModel.getNumberOfCountries()

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
fun PlayScreen(fourCountriesToDisplay: List<Country?>, correctCountry: Country?, nbrOfGuessedCountries: Int, nbrOfCountries: Int, isCorrectClicked: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CounterText(nbrOfGuessedCountries, nbrOfCountries)
        FlagPlaceholder(correctCountry)
        OptionButtons(fourCountriesToDisplay, correctCountry, isCorrectClicked)
    }
}

@Composable
private fun CounterText(nbrOfGuessedCountries: Int, nbrOfCountries: Int) {
    Box(
        modifier = Modifier
            .padding(10.dp) // Add padding to give some space from the edges
            .offset(x = 170.dp), // Offset to position the text in the top left corner
//        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = "${nbrOfGuessedCountries}/${nbrOfCountries}",
            color = colorResource(R.color.custom_white)
        )
    }
}

@Composable
private fun FlagPlaceholder(correctCountry: Country?) {
    Column(
        modifier = Modifier
            .padding(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text(text = correctCountry?.emojiFlag ?: "Null", color = colorResource(R.color.custom_white), fontSize = 100.sp)

        val correctCountryISO = correctCountry?.ISOAlpha2?.lowercase() ?: "null"

        // Get the resource ID dynamically
        val resourceId = try {
            val field = R.drawable::class.java.getField(correctCountryISO)
            field.getInt(null)
        } catch (e: Exception) {
            // Handle the case where the resource for the given country code doesn't exist
            R.drawable.se // TODO: add default flag in case we can't read the flag
        }

        Image(
            painter = painterResource(resourceId),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )
    }
}

@Composable
private fun OptionButtons(fourCountriesToDisplay: List<Country?>, correctCountry: Country?, isCorrectClicked: (Boolean) -> Unit) {
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
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[0]?.name, correctCountry?.name, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[0]?.name ?: "Null 1", style = TextStyle(fontSize = 18.sp))
            }
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[1]?.name, correctCountry?.name, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[1]?.name ?: "Null 2", style = TextStyle(fontSize = 18.sp))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[2]?.name, correctCountry?.name, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[2]?.name ?: "Null 3", style = TextStyle(fontSize = 18.sp))
            }
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[3]?.name, correctCountry?.name, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[3]?.name ?: "Null 4", style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}

fun onCountryButtonClick(buttonLabelCountry: String?, correctCountry: String?, isCorrectClicked: (Boolean) -> Unit) {
    if (buttonLabelCountry.equals(correctCountry)) {
        isCorrectClicked(true)
    } else {
        isCorrectClicked(false)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayScreen() {

    val mockData = MockData()

    val mockFourCountries = listOf(mockData.mockCountry, mockData.mockCountry, mockData.mockCountry, mockData.mockCountry)
    val mockCorrectCountry = mockData.mockCountry
    val mockIsCorrectClicked: (Boolean) -> Unit = {}
    val mockNbrOfGuessedCountries = 1
    val mockNbrOfCountries = 4

    PlayScreen(mockFourCountries, mockCorrectCountry, mockNbrOfGuessedCountries, mockNbrOfCountries, mockIsCorrectClicked)
}