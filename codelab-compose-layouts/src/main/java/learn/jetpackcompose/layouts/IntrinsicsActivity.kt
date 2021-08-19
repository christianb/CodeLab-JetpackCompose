package learn.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.layouts.ui.theme.LearnJetpackComposeTheme

class IntrinsicsActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MainActivityScreenContent()
		}
	}
}

@Composable
private fun MyApp(content: @Composable () -> Unit) {
	LearnJetpackComposeTheme {
		Surface {
			content()
		}
	}
}

@Composable
private fun MainActivityScreenContent() {
	MyApp {
		Surface() {
			TwoTexts(text1 = "Hi", text2 = "there")
		}
	}
}

@Preview
@Composable
private fun DefaultPreview() {
	MainActivityScreenContent()
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
	Row(modifier = modifier.height(IntrinsicSize.Min)) {
		Text(
			modifier = Modifier
				.weight(1f)
				.padding(start = 4.dp)
				.wrapContentWidth(Alignment.Start),
			text = text1
		)

		Divider(color = Color.Black, modifier = Modifier
			.fillMaxHeight()
			.width(1.dp))
		Text(
			modifier = Modifier
				.weight(1f)
				.padding(end = 4.dp)
				.wrapContentWidth(Alignment.End),

			text = text2
		)
	}
}
