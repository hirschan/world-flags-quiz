package com.example.worldflags.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.worldflags.R
import org.koin.android.ext.android.get

/** UI for main screen. */

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        viewModel = get<MainActivityViewModel>()

        setContent {
            MainTopLevel(viewModel)
        }
    }
}

/**
 * Top-level component, handling ViewModel logic. Allows us to preview MainScreen() composable. */

@Composable
private fun MainTopLevel(viewModel: MainActivityViewModel) {
    val context = LocalContext.current

    // Use remember and mutableStateOf to hold the value that will be updated
    val selectedOptionState = remember { mutableStateOf<String?>(null) }

    // Use LaunchedEffect to read initial selected option from DataStore
    LaunchedEffect(context) {
        selectedOptionState.value = viewModel.readFromDataStore(context)
        viewModel.loadJsonFile(selectedOptionState.value)
    }

    MainScreen()
}

@Composable
private fun MainScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue_background)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.im_globe_flags),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color(0xFF253745)),
            )
        }
        PlayButton()
        OptionButton()
    }
}

@Composable
private fun PlayButton() {
    val context = LocalContext.current
    Button(
        border = BorderStroke(2.dp, colorResource(id = R.color.green_stroke)),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green)),
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        onClick = {
            context.startActivity(Intent(context, PlayActivity::class.java))
        }) {
        Text(
            text = "Play",
            color = colorResource(id = R.color.green_stroke),
            fontSize = 25.sp,
        )
    }
}

@Composable
private fun OptionButton() {
    val context = LocalContext.current
    Button(
        border = BorderStroke(2.dp, colorResource(id = R.color.blue_btn_stroke)),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_btn)),
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        onClick = {
            context.startActivity(Intent(context, OptionActivity::class.java))
        }) {
        Text(
            text = "Options",
            color = colorResource(id = R.color.blue_btn_stroke),
            fontSize = 25.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}