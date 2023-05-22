# MuzzChat
A simple Chat Application for Android, build with Kotlin and Jetpack Compose. 

### Description

- Developed Android App with Kotlin using MVVM Architecture Pattern
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjw36GjBhAkEiwAKwIWyQQtNm4su13rjMDYwi4SEGtnx_anN1nihaGmVbw2ncKvISjLZbqAixoC9kAQAvD_BwE&gclsrc=aw.ds)
- [Kotlin Coroutines-Flow](https://developer.android.com/kotlin/flow)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room](https://developer.android.com/training/data-storage/room)


Developed the exercise application following MVVM with Clean Architecture principles.  Since the project will be between two users to chat I only build Message Entity. For more users to use creating user entity holding user information and save user messages using user id in the message entity will be better approach. For further development Firebase can be added to project to make it like real chat application with login and storing and collecting data online. Message info tick can be added for the project become more like a real chat app. Before that there are some updates must be applied to code, for example: currently the section that shows date and time between messages only show day of the week and time of the message, after one week or month passed it would be confusing so there may be logic added to project that date shown in ‘DD MM EEEE HH mm’ format and after a year also year is added to clearly see message time. 
I am somewhat new to compose, I did not use compose in my previous experience. I only used on my side projects to learn compose. I want to use compose in the project since I believe Muzz using compose. I think it would better for use compose in the exercise project. Most challenging part of the project was compose for me but I like to try and learn new things. 
To track code style to be good all time static analysis tools (linters/ formatters) and pre-commit hooks can be added.  



| Chat Screen User 1 | Chat Screen User 2 | Chatting | Chatting Video | 
| ------------- | ------------- | ------------- | ------------- |
|![Aliyah](https://github.com/SerhatAal/MuzzChat/assets/98642848/5636a55b-8b12-4657-a5f9-c4e4099da05e) | ![Adnan](https://github.com/SerhatAal/MuzzChat/assets/98642848/4f38f35a-25e6-410a-b32f-e9e967794a23) | ![Aliyah messaging](https://github.com/SerhatAal/MuzzChat/assets/98642848/e9f79f87-f62a-431a-bb78-9d8bfd986719) | ![Chat App Video](https://github.com/SerhatAal/MuzzChat/assets/98642848/1253197b-3b27-4cfa-b347-3a09fedb6de0) |

 

