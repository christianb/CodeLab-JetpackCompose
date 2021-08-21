# Jetpack Compose Testing
* [CodeLab Testing in Jetpack Compose](https://developer.android.com/codelabs/jetpack-compose-testing)

You can get the code for the solution of this CodeLab from GitHub:
```
git clone https://github.com/googlecodelabs/android-compose-codelabs
```
Once downloaded, open the __TestingCodelab__ project.

## ComposeTestRule
Compose comes with a __ComposeTestRule__ that you can obtain by calling __createComposeRule()__. 
This rule lets you set the Compose content under test and interact with it. 

## Testing CheatSheet
A good way to understand what tools are available is using the [Compose Testing Cheat Sheet](https://developer. android.com/jetpack/compose/testing-cheatsheet)
or the test package [reference documentation](https://developer.android.com/reference/kotlin/androidx/compose/ui/test/package-summary).

## SemanticTree
Compose tests use a structure called the __semantics tree__ to look for elements on the screen and read their properties. 
This is the structure that accessibility services use as well, as they're meant to be read by a service such as __TalkBack__.

You can print the Semantics tree using the `printToLog` function on a node.
```
composeTestRule.onRoot().printToLog("currentLabelExists")
```


