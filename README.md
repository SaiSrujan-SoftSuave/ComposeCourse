# Jet pack compose layouts
## Animation and Drawing in compose 

link:-  https://www.youtube.com/watch?v=1yiuxWK74vI&ab_channel=AndroidDevelopers


## Drawing:-

```kotlin
  Spacer(modifier = Modifier.fillMaxSize().drawBehind {
                drawCircle(color = Color.Green)
            })
```
drawing is done using coordinate system 
![img_1.png](img_1.png)

image refers to mobile screen

```kotlin
 Spacer(modifier = Modifier.fillMaxSize().drawBehind {
                drawCircle(color = Color.Green, center = Offset(
                    10.dp.toPx(),
                    100.dp.toPx()
                ),
                    radius = 60.dp.toPx()
                )
            })
```

## DrawScope Transformation

to get draw scope we can use Canvas compose which is similar to drawBehind modifier 
Canvas(modifier){
//we are in draw scope
}
### type of transformation
- insert
- scale
- rotate
- translate
- multiple transformation

```kotlin
Canvas(modifier = Modifier.fillMaxSize()){
                scale(scaleX = 10f, scaleY = 15f){
                    drawCircle(color = Color.Green, radius = 10.dp.toPx())
                }
            }
```