package com.example.worldflags.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.worldflags.R
import org.koin.android.ext.android.get

/** UI for main screen. */

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = get<MainActivityViewModel>()

        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.dark_blue)),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayButton()
        OptionButton()
    }
}

@Composable
private fun PlayButton() {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        onClick = {
            context.startActivity(Intent(context, PlayActivity::class.java))
        }) {
        Text(text = "Play", color = colorResource(id = R.color.custom_white), fontSize = 25.sp)
    }
}

@Composable
private fun OptionButton() {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        onClick = {
            context.startActivity(Intent(context, OptionActivity::class.java))
        }) {
        Text(text = "Options", color = colorResource(id = R.color.custom_white), fontSize = 25.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}