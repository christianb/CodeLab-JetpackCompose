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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    val todoItems: SnapshotStateList<TodoItem> = mutableStateListOf()

    private var currentEditPosition: Int by mutableStateOf(NONE)

    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
        onEditDone() // don't keep the editor open when removing items
    }

    fun currentEditItem(): TodoItem? = todoItems.getOrNull(currentEditPosition)

    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = NONE
    }

    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem())
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }

        todoItems[currentEditPosition] = item
    }

    companion object {
        private const val NONE = -1
    }
}
