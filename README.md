# Jet pack compose layouts
## Animating elements in Jetpack Compose

link:- https://developer.android.com/codelabs/jetpack-compose-animation?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-animation#0

## Animating a simple value change

```kotlin
//before
    val backgroundColor = if (tabPage == TabPage.Home) Seashell else GreenLight
//after

val backgroundColor by animateColorAsState(
    targetValue = if(tabPage == TabPage.Home) Seashell else GreenLight,
    label = "background Color"
)
```