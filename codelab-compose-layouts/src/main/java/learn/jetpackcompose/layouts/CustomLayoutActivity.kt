package learn.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.layouts.ui.theme.LearnJetpackComposeTheme

class CustomLayoutActivity : ComponentActivity() {
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
	// 	Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
	}
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
	MyApp {
		Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
	}
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
	MyApp {
		Text("Hi there!", Modifier.padding(top = 32.dp))
	}
}


private fun Modifier.firstBaselineToTop(
	firstBaselineToTop: Dp
) = this.then(
	layout { measurable, constraints ->
		val placeable = measurable.measure(constraints)

		// Check the composable has a first baseline
		check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
		val firstBaseline = placeable[FirstBaseline]

		// Height of the composable with padding - first baseline
		val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
		val height = placeable.height + placeableY
		layout(placeable.width, height) {
			// Where the composable gets placed
			placeable.placeRelative(0, placeableY)
		}
	}
)

@Preview
@Composable
fun MyOwnColumnPreview() {
	BodyContent()
}

@Composable
fun MyOwnColumn(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit
) {
	Layout(
		modifier = modifier,
		content = content
	) { measurables, constraints ->
		// Don't constrain child views further, measure them with given constraints
		// List of measured children
		val placeables: List<Placeable> = measurables.map { measurable ->
			// Measure each child
			measurable.measure(constraints)
		}

		// Track the y co-ord we have placed children up to
		var yPosition = 0

		// Set the size of the layout as big as it can
		layout(constraints.maxWidth, constraints.maxHeight) {
			// Place children in the parent layout
			placeables.forEach { placeable ->
				// Position item on the screen
				placeable.placeRelative(x = 0, y = yPosition)

				// Record the y co-ord placed up to
				yPosition += placeable.height
			}
		}
	}
}

@Composable
private fun BodyContent(modifier: Modifier = Modifier) {
	MyOwnColumn(modifier.padding(8.dp)) {
		Text("MyOwnColumn")
		Text("places items")
		Text("vertically.")
		Text("We've done it by hand!")
	}
}
