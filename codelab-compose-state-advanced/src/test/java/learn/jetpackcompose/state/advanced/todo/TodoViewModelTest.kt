package learn.jetpackcompose.state.advanced.todo

import learn.jetpackcompose.state.advanced.util.generateRandomTodoItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TodoViewModelTest {

	@Test
	fun todoItems_should_have_correct_size_after_add() {
		val viewModel = TodoViewModel()
		val item1 = generateRandomTodoItem()
		val item2 = generateRandomTodoItem()
		viewModel.addItem(item1)
		viewModel.addItem(item2)

		assertThat(viewModel.todoItems).hasSize(2)
	}

	@Test
	fun todoItems_should_have_correct_size_after_remove() {
		val viewModel = TodoViewModel()
		val item1 = generateRandomTodoItem()
		val item2 = generateRandomTodoItem()
		viewModel.addItem(item1)
		viewModel.addItem(item2)
		assertThat(viewModel.todoItems).hasSize(2)

		viewModel.removeItem(item1)
		assertThat(viewModel.todoItems).containsExactly(item2)
	}

}