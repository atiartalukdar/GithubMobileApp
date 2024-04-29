Index

Project Summary
Tech Stack
What has been covered
About the architecture
About Unit test
Scope of work


Project Summary
It's a simple mini GitHub mobile application, where users have the following features.
See user list
Search user (API based)
Able to see user profile
User’s basic profile (Name, Image, Followers, Following)
User’s Public Repository List (Name, Total stars, Description,  The main programming language)
Able to check the repository within Webview by clicking the row item.




Tech Stack
The project has been developed in Native Android with the following tech stacks. 
Kotlin, JetpackCompose
MVVM, Corotines, Dagger Hilt, Retrofit, Coil, State variable, NavHost
Unit test: Mockito, JUnit




What has been covered?

All the features are implemented as per the requirements. Besides that, I’ve covered two more things.
Created a Figma file for a better understanding of the UI and user flow. 
Figma: https://www.figma.com/file/H12CDiRhBtGEhlGe4HD9El/GithubMobileApp?type=design&node-id=15-789&mode=design&t=EBLsZkNUNDjpJc2H-0

With the following video, you can have an overview of the features of the developed app.
Video: https://youtu.be/dyNMygyB-CI

Unit testing has covered very basic levels like only for ViewModel, with some success and failure cases.
The codebase is simply ready for any type of member of the team. So if any junior also join the team they can understand the codebase very simply and start contributing to the project with very minimum learning curve.
The project’s data flow is maintained perfectly with MVVM architecture by keeping clean code. 
API data source: https://api.github.com/



About the architecture
I’ve used the MVVM architecture. 
Within MVVM architecture mostly 2 popular folder patterns. 
Feature-based folder architecture.
Component-based folder architecture.
Both of the folder patterns have some facilities and issues. In my code base, I’ve covered Feature-based folder architecture. 

The following drawing covers the overall architecture and data flow the system.


The following drawing covers the folder architecture of the overal project. 




About Unit test
I’ve covered only very basic Unit testing. Just covered the ViewModel.
Unit test coverage
Overall project ~12%
Domain layer ~100%
ViewModel ~93%


Scope of Work
We can add a few more features like
Share Users profile
Authentication to see private reop
Checking git repos inside app
And some more basic operations. 
More Unit testing coverage.
Bringing back some of the refactoring to have more readable code base.
Figma and UI match by pixel, font level. etc






