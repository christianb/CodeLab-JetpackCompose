package learn.jetpackcompose.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import learn.jetpackcompose.state.ui.theme.LearnJetpackComposeTheme

class MainActivity : ComponentActivity() {
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
		Text("hello world")
	}
}

@Preview
@Composable
private fun DefaultPreview() {
	MainActivityScreenContent()
}
