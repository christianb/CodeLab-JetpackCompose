package learn.jetpackcompose.testing

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import learn.jetpackcompose.testing.ui.components.RallyTopAppBar
import learn.jetpackcompose.testing.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	fun rallyTopAppBarTest_currentTabSelected() {
		val allScreens = RallyScreen.values().toList()

		composeTestRule.setContent {
			RallyTheme {
				RallyTopAppBar(
					allScreens = allScreens,
					onTabSelected = {},
					currentScreen = RallyScreen.Accounts
				)
			}
		}

		composeTestRule.onNode(
			matcher = hasText(RallyScreen.Accounts.name.toUpperCase()) and
				hasParent(
					hasContentDescription(RallyScreen.Accounts.name)
				),
			useUnmergedTree = true).printToLog("currentLabelExists")

		composeTestRule
			.onNodeWithContentDescription(RallyScreen.Accounts.name)
			.assertExists() // Still fails
	}
}