package learn.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import learn.jetpackcompose.layouts.ui.theme.LearnJetpackComposeTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MainActivityScreenContent()
		}
	}
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
	LearnJetpackComposeTheme {
		Surface(color = Color.Yellow) {
			content()
		}
	}
}

@Composable
fun MainActivityScreenContent(names: List<String> = List(500) { "Hello Android #$it" }) {
	MyApp {
		Text(text = "Hello")

	}
}

@Preview(name = "LearnJetpackComposeBasic", showBackground = true)
@Composable
fun DefaultPreview() {
	MainActivityScreenContent()
}