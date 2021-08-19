# Jetpack Compose Layouts
* [CodeLab Layouts in Jetpack Compose](https://developer.android.com/codelabs/jetpack-compose-layouts)

You can get the code for the solution of this CodeLab from GitHub:
```
git clone https://github.com/googlecodelabs/android-compose-codelabs
```

## Slot APIs
Slots leave an empty space in the UI for the developer to fill as they wish.

To enable this, we provide an API for Button that takes a children composable lambda: `content: @Composable () -> Unit`.

## Material Icons
You can use the full list of [material icons](https://fonts.google.com/icons?selected=Material+Icons) by adding a new dependency to the project.
```
dependencies {
  ...
  implementation "androidx.compose.material:material-icons-extended:$compose_version"
}
```

## LazyColumn
LazyColumn in Jetpack Compose is the equivalent of RecyclerView in Android Views.