/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package learn.jetpackcompose.state.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learn.jetpackcompose.state.util.generateRandomTodoItem
import kotlin.random.Random

/**
 * Stateless component that is responsible for the entire todo screen.
 *
 * @param items (state) list of [TodoItem] to display
 * @param onAddItem (event) request an item be added
 * @param onRemoveItem (event) request an item be removed
 */
@Composable
fun TodoScreen(
	items: List<TodoItem>,
	currentlyEditing: TodoItem?,
	onAddItem: (TodoItem) -> Unit,
	onRemoveItem: (TodoItem) -> Unit,
	onStartEdit: (TodoItem) -> Unit,
	onEditItemChange: (TodoItem) -> Unit,
	onEditDone: () -> Unit

) {
	Column {
		val enableTopSection = currentlyEditing == null
		TodoItemInputBackground(elevate = enableTopSection) {
			if (enableTopSection) {
				TodoItemEntryInput(onAddItem)
			} else {
				Text(
					"Editing item",
					style = MaterialTheme.typography.h6,
					textAlign = TextAlign.Center,
					modifier = Modifier
						.align(Alignment.CenterVertically)
						.padding(16.dp)
						.fillMaxWidth()
				)
			}

		}

		LazyColumn(
			modifier = Modifier.weight(1f),
			contentPadding = PaddingValues(top = 8.dp)
		) {
			items(items) { todo ->
				if (currentlyEditing?.id == todo.id) {
					TodoItemInlineEditor(
						item = currentlyEditing,
						onEditItemChange = onEditItemChange,
						onEditDone = onEditDone,
						onRemoveItem = { onRemoveItem(todo) }
					)
				} else {
					TodoRow(
						todo = todo,
						onItemClicked = { onStartEdit(it) },
						modifier = Modifier.fillParentMaxWidth()
					)
				}
			}

		}

		// For quick testing, a random item generator button
		Button(
			onClick = { onAddItem(generateRandomTodoItem()) },
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
		) {
			Text("Add random item")
		}
	}
}

/**
 * Stateless composable that displays a full-width [TodoItem].
 *
 * @param todo item to show
 * @param onItemClicked (event) notify caller that the row was clicked
 * @param modifier modifier for this element
 */
@Composable
fun TodoRow(
	todo: TodoItem,
	onItemClicked: (TodoItem) -> Unit,
	modifier: Modifier = Modifier,
	iconAlpha: Float = remember(todo.id) { randomTint() },
) {
	Row(
		modifier = modifier
			.clickable { onItemClicked(todo) }
			.padding(horizontal = 16.dp, vertical = 8.dp),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(todo.task)
		Icon(
			imageVector = todo.icon.imageVector,
			tint = LocalContentColor.current.copy(alpha = iconAlpha),
			contentDescription = stringResource(id = todo.icon.contentDescription)
		)
	}
}

@Composable
fun TodoItemEntryInput(onItemComplete: (TodoItem) -> Unit) {
	val (text, setText) = remember { mutableStateOf("") }
	val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default)}
	val iconsVisible = text.isNotBlank()
	val submit = {
		onItemComplete(TodoItem(text, icon))
		setIcon(TodoIcon.Default)
		setText("")
	}
	TodoItemInput(
		text = text,
		onTextChange = setText,
		icon = icon,
		onIconChange = setIcon,
		submit = submit,
		iconsVisible = iconsVisible
	) {
		TodoEditButton(onClick = submit, text = "Add", enabled = text.isNotBlank())
	}

}


@Composable
fun TodoItemInput(
	text: String,
	onTextChange: (String) -> Unit,
	icon: TodoIcon,
	onIconChange: (TodoIcon) -> Unit,
	submit: () -> Unit,
	iconsVisible: Boolean,
	buttonSlot: @Composable () -> Unit
) {
	Column {
		Row(
			Modifier
				.padding(horizontal = 16.dp)
				.padding(top = 16.dp)
		) {
			TodoInputText(
				text,
				onTextChange,
				Modifier
					.weight(1f)
					.padding(end = 8.dp),
				submit
			)

			Spacer(modifier = Modifier.width(8.dp))
			Box(Modifier.align(Alignment.CenterVertically)) { buttonSlot() }
		}
		if (iconsVisible) {
			AnimatedIconRow(icon, onIconChange, Modifier.padding(top = 8.dp))
		} else {
			Spacer(modifier = Modifier.height(16.dp))
		}
	}
}


@Preview
@Composable
fun PreviewTodoItemInput() = TodoItemEntryInput(onItemComplete = { })

private fun randomTint(): Float {
	return Random.nextFloat().coerceIn(0.3f, 0.9f)
}

@Preview
@Composable
fun PreviewTodoScreen() {
	val items = listOf(
		TodoItem("Learn compose", TodoIcon.Event),
		TodoItem("Take the codelab"),
		TodoItem("Apply state", TodoIcon.Done),
		TodoItem("Build dynamic UIs", TodoIcon.Square)
	)
	TodoScreen(items, null, {}, {}, {}, {}, {})
}

@Preview
@Composable
fun PreviewTodoRow() {
	val todo = remember { generateRandomTodoItem() }
	TodoRow(todo = todo, onItemClicked = {}, modifier = Modifier.fillMaxWidth())
}