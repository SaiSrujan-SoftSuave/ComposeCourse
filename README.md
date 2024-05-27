# Jet pack compose layouts
## CodeLad - 3 (State in compose)
https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#1

## state 
An app's "state" is any value that can change over time. This is a very broad definition and encompasses everything from a Room database to a variable in a class.

**Key idea:** State determines what is shown in the UI at any particular time.

**Any action that causes the modification of state is called an "event"**

## Event
Events are inputs generated from outside or inside an application, such as:

- The user interacting with the UI by, for example, pressing a button.
- Other factors, such as sensors sending a new value, or network responses.

**While the state of the app offers a description of what to display in the UI, 
events are the mechanism through which the state changes, resulting in changes to the UI.**

![img.png](img.png)