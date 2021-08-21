# Jetpack Compose State
* [CodeLab Layouts in Jetpack Compose](https://developer.android.com/codelabs/jetpack-compose-state)

You can get the code for the solution of this CodeLab from GitHub:
```
git clone https://github.com/googlecodelabs/android-compose-codelabs
```

## State
State in an application is any value that can change over time.
For example it may be a value stored in a Room database, a variable on a class, or even the current value read from an accelerometer.

## Unidirectional Data Flow
Unidirectional data flow is a design where state flows down and events flow up.
For example, in a ViewModel events are passed _up_ with method calls from the UI while state flows _down_ using LiveData.

* __State__ – any value that can change over time
* __Event__ – notify a part of a program that something has happened
* __Unidirectional data flow__ – a design where events flow up and state flows down

## State hoisting
State hoisting is a pattern of moving state up to make a component stateless.

When applied to composables, this often means introducing two parameters to the composable.
* `value: T` – the current value to display 
* `onValueChange: (T) -> Unit` – an event that requests the value to change, where T is the proposed new value

### Hoisting Rules
When hoisting state, there are three rules to help you figure out where it should go

1. State should be hoisted to at least the __lowest common parent__ of all composables that use the state (or read)
2. State should be hoisted to at least the __highest level it may be changed__ (or modified)
3. If __two states change in response to the same events__ they should be __hoisted together__

You can hoist state higher than these rules require, but underhoisting state will make it difficult or impossible to follow unidirectional data flow.


## MutableState
`mutableStateOf` creates a `MutableState<T>` which is an observable state holder built into compose.

```
interface MutableState<T> : State<T> {
    override var value: T
}
```

Any changes to ```value wil`l automatically recompose any composable functions that read this state.

You declare a `MutableState` object in a composable three ways:
```
val default: T = ...
```

1. `val state: MutableState<T> = remember { mutableStateOf(default) }`
2. `var value: T by remember { mutableStateOf(default) } // read and write of value: T delegates to MutableState<T>`
3. `val (value, setValue) = remember { mutableStateOf(default) } // destruct MutableState (component1(), component2())`

When creating `State<T>` (or other stateful objects) in composition, it's important to `remember` it. Otherwise it will be re-initialized every composition.

`MutableState<T>` similar to `MutableLiveData<T>`, but integrated with the compose runtime. Since it's observable, it will tell compose whenever it's updated so compose can recompose any composables that read it.

## Visibility
There is no "visibility" property in compose.

Since compose can dynamically change the composition, you do not need to set visibility gone. Instead, remove composables from the composition.

## Scaffold
Scaffold is the composable for describing an entire screen in Material design, such as the __topBar__, __bottomBar__, and body of the screen.

## Slots
__Slots__ are parameters to a composable function that allow the caller to describe a section of the screen.

Declare a slot with a parameter of type `@Composable () -> Unit`.

