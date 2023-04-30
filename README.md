# Pokemons

<img src="https://github.com/mmbehroozfar/Pokemons/blob/master/pictures/preview.gif?raw=true" alt="Logo" align="right" height="600">

This app allows users to see Pokemons. It provides the following features:

- Show a list of Pokemon
- Show Pokemon detail
- Allow users to access the app even without internet connectivity.

# Android development

I used the most latest technologies for developing this application.

Here is a list of technologies and tools that are used in this project.

- Kotlin: The app is written entirely in Kotlin, a modern and expressive programming language for Android development.
- Kotlin Coroutines: Coroutines are used throughout the app for efficient and asynchronous handling of tasks.
- Clean MVI Architecture: The app follows the Clean MVI architecture pattern, which separates concerns and improves maintainability.
- Architecture Components: The app uses various components from the Android Architecture Components library, such as Room, Lifecycle, Navigation, ViewModel, and Paging.
- Hilt for Dependency Injection: Hilt is used for dependency injection, making the app modular and easier to manage.
- Gradle Kotlin DSL: The project uses Gradle Kotlin DSL for build configuration, which provides a more concise and powerful way to configure the build.
- Unit Testing: Some classes in the app are unit tested using the Mockk library for mocking dependencies.
- Coil: Used Coil for loading images.
- Jetpack Compose: All screens are written using Jetpack Compose.

# Solution

1- Design:

The UI design of the app is kept simple and minimalistic, focusing on usability and functionality.

2- Clean MVI Architecture: 

The app follows the Clean MVI architecture pattern, separating the app's concerns into different layers, such as presentation, domain, and data. This architecture promotes maintainability, testability, and scalability.

3- Testing: 

The app's critical functionalities and modules are unit tested to ensure their functionality and reliability. The tests are written using the Mockk library, which provides a flexible and powerful mocking framework for Kotlin.

4- Git History:

Due to limited time, the best practices for git history, such as using git-flow for feature and bugfix branches, were not followed. Only the master branch was used, which is not recommended in a production environment.

5- Overall: 

The app uses the best architecture and tools to implement the project, with a clean and simple codebase following the required guidelines. It provides a seamless and delightful user experience, with its offline-first approach, efficient and asynchronous handling of tasks, and concise and maintainable codebase.

# Improvemnt

1- Design: I think the user interface of the app needs improvement to increase user satisfaction.

2- Test: More tests are needed to insure all different parts of the app are working as intended.

3- Modularization: More modules are needed to separate different layers like Cache and Remote. Also, I need to separate UI models from Domain models.


