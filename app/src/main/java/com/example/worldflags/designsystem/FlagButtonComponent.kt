import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldflags.R

@Composable
fun RowScope.FlagButtonComponent(
    flagName: String?,
    correctFlagName: String?,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    resetButtonColors: MutableState<Boolean>,
    paddingEnd: Dp = 0.dp,
    paddingStart: Dp = 0.dp,
) {

    val initialButtonColor: Color = colorResource(id = R.color.light_blue)
    val customRedColor: Color = colorResource(id = R.color.dark_red)

    val buttonColorState  = remember { mutableStateOf(initialButtonColor) }

    // Reset color when resetButtonColors changes to true
    if (resetButtonColors.value) {
        buttonColorState.value = initialButtonColor
    }

    fun onFlagNameButtonClick(buttonFlagLabel: String?, correctFlag: String?, isCorrectClicked: (Boolean) -> Unit) {
        if (buttonFlagLabel.equals(correctFlag)) {
            isCorrectClicked(true)
        } else {
            buttonColorState.value = customRedColor
            isCorrectClicked(false)
        }
    }

    Button(
        colors = ButtonDefaults.buttonColors(buttonColorState.value),
        onClick = {
            onFlagNameButtonClick(flagName, correctFlagName, onClick)
        },
        modifier = modifier
            .weight(1f)
            .height(100.dp)
            .padding(end = paddingEnd, start = paddingStart),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = flagName ?: "Null", style = TextStyle(fontSize = 18.sp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlagButtonComponent() {

    val mockResetButtonColors = remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        FlagButtonComponent(
            flagName = "Flag Button",
            correctFlagName = "Flag Button",
            onClick = {},
            resetButtonColors = mockResetButtonColors,
        )
    }
}