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
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingEnd: Dp = 0.dp,
    paddingStart: Dp = 0.dp,
    buttonColor: Color = colorResource(id = R.color.light_blue)
) {
    Button(
        colors = ButtonDefaults.buttonColors(buttonColor),
        onClick = onClick,
        modifier = modifier
            .weight(1f)
            .height(100.dp)
            .padding(end = paddingEnd, start = paddingStart),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = text ?: "Null", style = TextStyle(fontSize = 18.sp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlagButtonComponent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        FlagButtonComponent(
            text = "Flag Button",
            onClick = {},
        )
    }
}