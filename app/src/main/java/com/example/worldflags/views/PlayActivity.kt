package com.example.worldflags.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R
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
    val fourCountryNamesToDisplay = viewModel.fourRandomAndUniqueCountryNames.observeAsState().value
    val correctCountry = viewModel.correctCountry.observeAsState().value

    if (fourCountryNamesToDisplay != null) {
        PlayScreen(fourCountryNamesToDisplay,correctCountry) { isCorrectClicked ->
            if (isCorrectClicked) {
                viewModel.onCorrectAnswerSelected()
            }
        }
    }
}

@Composable
fun PlayScreen(fourCountriesToDisplay: List<String?>, correctCountry: String?, isCorrectClicked: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DisplayOptionButtons(fourCountriesToDisplay, correctCountry, isCorrectClicked)
    }
}

@Composable
private fun DisplayOptionButtons(fourCountriesToDisplay: List<String?>, correctCountry: String?, isCorrectClicked: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .padding(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = correctCountry ?: "Null", color = colorResource(R.color.custom_white), fontSize = 32.sp)
    }

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
                onClick = { onCountryButtonClick(fourCountriesToDisplay[0], correctCountry, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[0] ?: "Null 1")
            }
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[1], correctCountry, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[1] ?: "Null 2")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[2], correctCountry, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[2] ?: "Null 3")
            }
            Button(
                onClick = { onCountryButtonClick(fourCountriesToDisplay[3], correctCountry, isCorrectClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = fourCountriesToDisplay[3] ?: "Null 4")
            }
        }
    }
}

fun onCountryButtonClick(buttonLabelCountry: String?, correctCountry: String?, isCorrectClicked: (Boolean) -> Unit) {
    if (buttonLabelCountry.equals(correctCountry)) {
        println("ALFF correct!")
        isCorrectClicked(true)
    } else {
        println("ALFF wrong!")
        isCorrectClicked(false)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayScreen() {
    val fourCountries = listOf("Denmark", "Sweden", "Finland", "Norway")
    val correctCountry = "Sweden"
    val isCorrectClicked: (Boolean) -> Unit = { isCorrect ->
        println("Correct click: $isCorrect")
    }

    PlayScreen(fourCountries, correctCountry, isCorrectClicked)
}