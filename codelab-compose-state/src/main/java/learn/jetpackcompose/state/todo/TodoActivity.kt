package learn.jetpackcompose.state.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.state.ui.theme.LearnJetpackComposeTheme

class TodoActivity : ComponentActivity() {

	private val todoViewModel by viewModels<TodoViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TodoActivityScreenContent(todoViewModel)
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
private fun TodoActivityScreenContent(todoViewModel: TodoViewModel) {
	MyApp {
		TodoScreen(
			items = todoViewModel.todoItems,
			currentlyEditing = todoViewModel.currentEditItem(),
			onAddItem = todoViewModel::addItem,
			onRemoveItem = todoViewModel::removeItem,
			onStartEdit = todoViewModel::onEditItemSelected,
			onEditItemChange = todoViewModel::onEditItemChange,
			onEditDone = todoViewModel::onEditDone
		)
	}
}

@Composable
fun TodoItemInlineEditor(
	item: TodoItem,
	onEditItemChange: (TodoItem) -> Unit,
	onEditDone: () -> Unit,
	onRemoveItem: () -> Unit
) = TodoItemInput(
	text = item.task,
	onTextChange = { onEditItemChange(item.copy(task = it)) },
	icon = item.icon,
	onIconChange = { onEditItemChange(item.copy(icon = it)) },
	submit = onEditDone,
	iconsVisible = true,
	buttonSlot = {
		Row {
			val shrinkButtons = Modifier.widthIn(20.dp)
			TextButton(onClick = onEditDone, modifier = shrinkButtons) {
				Text(
					text = "\uD83D\uDCBE", // floppy disk
					textAlign = TextAlign.End,
					modifier = Modifier.width(30.dp)
				)
			}
			TextButton(onClick = onRemoveItem, modifier = shrinkButtons) {
				Text(
					text = "❌",
					textAlign = TextAlign.End,
					modifier = Modifier.width(30.dp)
				)
			}
		}
	}

)


@Preview
@Composable
private fun DefaultPreview() {
	MyApp {
		val items: List<TodoItem> = listOf(
			TodoItem("first"),
			TodoItem("second"),
		)
		TodoScreen(
			items = items,
			onAddItem = { },
			onRemoveItem = { },
			currentlyEditing = null,
			onStartEdit = {},
			onEditItemChange = {},
			onEditDone = {}
		)
	}
}