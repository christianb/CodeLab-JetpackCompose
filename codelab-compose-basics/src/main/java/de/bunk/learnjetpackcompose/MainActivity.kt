package de.bunk.learnjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.bunk.learnjetpackcompose.ui.theme.LearnJetpackComposeTheme
import androidx.compose.runtime.setValue

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
		val countState: MutableState<Int> = remember { mutableStateOf(0) }

		Column(modifier = Modifier.fillMaxHeight()) {
			NameList(names = names, modifier = Modifier.weight(1f))
			Counter(count = countState.value, updateCount = { newCount -> countState.value = newCount })
		}

	}
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
	Button(onClick = { updateCount(count + 1) },
		   colors = ButtonDefaults.buttonColors(
			   backgroundColor = if (count > 5) Color.Green else Color.White
		   )
	) {
		Text("I've been clicked $count times")
	}
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
	LazyColumn(modifier = modifier) {
		items(items = names) { name ->
			Greeting(name = name)
			Divider(color = Color.Black)
		}
	}
}

@Composable
fun Greeting(name: String) {
	var isSelected: Boolean by remember { mutableStateOf(false) }
	val backgroundColor: Color by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

	Text(
		text = "Hello $name!",
		modifier = Modifier
			.padding(24.dp)
			.background(color = backgroundColor)
			.clickable(onClick = { isSelected = !isSelected }),
		style = MaterialTheme.typography.body1

	)
}

@Preview(name = "LearnJetpackComposeBasic", showBackground = true)
@Composable
fun DefaultPreview() {
	MainActivityScreenContent()
}