package learn.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.layouts.ui.theme.LearnJetpackComposeTheme

class ModifiersActivity : ComponentActivity() {
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
		PhotographerCard()
	}
}

@Composable
private fun PhotographerCard(modifier: Modifier = Modifier) {
	Row(modifier
			.padding(8.dp)
			.clip(RoundedCornerShape(4.dp))
			.background(MaterialTheme.colors.surface)
			.clickable(onClick = { /* Ignoring onClick */ })
			.padding(16.dp)

	) {
		Surface(
			modifier = Modifier.size(50.dp),
			shape = CircleShape,
			color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
		) {
			// Image goes here
		}

		Column(
			modifier = Modifier
				.padding(start = 8.dp)
				.align(Alignment.CenterVertically)
		) {
			Text("Alfred Sisley", fontWeight = FontWeight.Bold)
			// LocalContentAlpha is defining opacity level of its children
			CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
				Text("3 minutes ago", style = MaterialTheme.typography.body2)
			}
		}
	}
}

@Preview
@Composable
private fun PhotographerCardPreview() {
	MainActivityScreenContent()
}
