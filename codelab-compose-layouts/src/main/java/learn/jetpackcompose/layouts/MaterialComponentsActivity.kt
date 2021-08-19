package learn.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.layouts.ui.theme.LearnJetpackComposeTheme

class MaterialComponentsActivity : ComponentActivity() {
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
private fun MainActivityScreenContent(names: List<String> = List(500) { "Hello Android #$it" }) {
	MyApp {
		LayoutsCodelab()
	}
}

@Preview
@Composable
private fun PhotographerCardPreview() {
	MainActivityScreenContent()
}

@Composable
fun LayoutsCodelab() {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(text = "LayoutsCodelab")
				},
				actions = {
					IconButton(onClick = { /* doSomething() */ }) {
						Icon(Icons.Filled.Favorite, contentDescription = null)
					}
				}
			)
		},
		floatingActionButton = {
			FloatingActionButton(onClick = { /*TODO*/ }) {
				// just for demo
			}
		},
		bottomBar = {
			BottomAppBar {
				// just for demo
			}
		}
	) { innerPadding ->
		BodyContent(Modifier
						.padding(innerPadding)
						.padding(8.dp))
	}
}

@Composable
private fun BodyContent(modifier: Modifier = Modifier) {
	Column(modifier = modifier) {
		Text(text = "Hi there!")
		Text(text = "Thanks for going through the Layouts codelab")
	}
}
