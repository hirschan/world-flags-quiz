package com.example.worldflags.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R

/** UI for result screen. */

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nbrOfCorrectGuessedFlags = intent.getIntExtra("nbrOfCorrectGuessesOnFirstTry", 0)
        val nbrOfFlags = intent.getIntExtra("nbrOfFlags", 0)

        setContent {
            PlayTopLevel(nbrOfCorrectGuessedFlags, nbrOfFlags)
        }
    }
}

/**
 * Top-level component, handling ViewModel logic. Allows us to preview ResultScreen() composable. */

@Composable
private fun PlayTopLevel(nbrOfCorrectGuessedFlags: Int, nbrOfFlags: Int) {
    ResultScreen(nbrOfCorrectGuessedFlags, nbrOfFlags)
}

@Composable
private fun ResultScreen(nbrOfCorrectGuessedFlags: Int, nbrOfFlags: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_blue_background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "You guessed correct",
                color = colorResource(id = R.color.custom_white),
                fontSize = 30.sp,
            )
            CircleWithText(nbrOfCorrectGuessedFlags, nbrOfFlags)
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            FixedBottomSheet()
        }
    }
}

@Composable
private fun CircleWithText(nbrOfCorrectGuessedFlags: Int, nbrOfFlags: Int) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(colorResource(id = R.color.light_blue)),
        contentAlignment = Alignment.Center
    ) {
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorResource(id = R.color.green_stroke))) {
                append("$nbrOfCorrectGuessedFlags")
            }
            withStyle(style = SpanStyle(color = colorResource(id = R.color.custom_white))) {
                append("/${nbrOfFlags}")
            }
        }
        Text(
            text = text,
            fontSize = 32.sp
        )
    }
}

@Composable
private fun FixedBottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(color = colorResource(id = R.color.light_blue))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MainMenuButton()
                PlayAgainButton()
            }
        }
    }
}

@Composable
private fun PlayAgainButton() {
    val context = LocalContext.current
    Button(
        border = BorderStroke(2.dp, colorResource(id = R.color.green_stroke)),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green)),
        modifier = Modifier
            .height(50.dp)
            .width(150.dp),
        onClick = {
            context.startActivity(Intent(context, PlayActivity::class.java))
        }) {
        Text(
            text = "Play again",
            color = colorResource(id = R.color.green_stroke),
            fontSize = 20.sp)
    }
}

@Composable
private fun MainMenuButton() {
    val context = LocalContext.current
    Button(
        border = BorderStroke(2.dp, colorResource(id = R.color.blue_btn_stroke)),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_btn)),
        modifier = Modifier
            .height(50.dp)
            .width(150.dp),
        onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
        Text(
            text = "Main menu",
            color = colorResource(id = R.color.blue_btn_stroke),
            fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayScreen() {
    ResultScreen(0, 0)
}