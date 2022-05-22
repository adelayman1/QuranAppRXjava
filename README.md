# QuranAppRXjava :book:
This repository contains a quran app that implements mvvm architecture , clean architecture , navigation component , hilt , etc.... using kotlin language


![](https://github.com/adelayman1/QuranApp./blob/master/images/allAppImage.jpg)

<a href="https://www.linkedin.com/in/adel-ayman-2497ab1b3/">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>

## App Overview

- this screen view all surahs in quran
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image1.jpg" width="300" />

- this screen view surah the user selected it
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image2.jpg" width="300" />
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image3.jpg" width="300" />



## Built With 🛠

*  [Kotlin](https://kotlinlang.org/) 
*  [RXjava](https://github.com/ReactiveX/RxJava)
*  [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
*  mvvm architecture
*  Clean architecture
*  [Navigation component](https://developer.android.com/guide/navigation)
*  [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack) 
*  [Retrofit2](https://square.github.io/retrofit/) 
*  Single activity concept 
*  Repository pattern
*  [ViewBinding](https://developer.android.com/topic/libraries/view-binding) 

## Project Structure


    quranapprxjavaversion      
    ├── data                   
    │   ├── api              
    |   │   └── SurahApiService.kt                           
    |   |   
    │   ├── di                  
    |   │   └── module        
    |   |       ├──NetworkModule.kt         
    |   |       └──SurahApiModule.kt
    |   |
    │   ├── repositories         
    |   |   └──SurahRepositoryImpl.kt
    |   |
    |   |
    |   ├── models             
    |   |   ├──ApiResponse.kt   
    |   |   ├──SurahDetailsModel.kt 
    |   |   ├──SurahModel.kt 
    |   |   └──VerseModel.kt 
    |   |
    |   |
    |   |── utils
    |   |   └──ErrorHandlerImpl.kt 
    |
    |       
    |── domain 
    |   ├──enities
    |   |   ├──ErrorEnity.kt
    |   |   └──Result.kt
    |   |
    |   |──repositories
    |   |  └──SurahRepository.kt
    |   |
    |   |──usecases
    |   |  ├──GetAllSurahUseCase.kt
    |   |  └──GetSurahDetailsUseCase.kt
    |   |
    |   |──utils
    |   |  └──ErrorHandler.kt
    |
    |
    ├── presentation                      
    │   ├── homeScreen
    |   |   ├──HomeFragment.kt
    |   |   ├──HomeViewModel.kt
    |   |   └──SurahAdapter.kt
    |   |
    │   ├── surahDetailsScreen
    |   |   ├──SurahDetailsFragment.kt
    |   |   ├──SurahDetailViewModel.kt
    |   |   └──VerseAdapter.kt
    |
    |
    ├──MainActivity.kt
    └── Application.kt 
