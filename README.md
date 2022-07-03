#  Weather application
## Application demo to get data from openweathermap

### How to install
Run ```adb installDebug```
### Folder struct
- data: layer to get data from remote or database
- repository: layer to define logic load data
- ui: layer define ui with MVVM architecture
### UI:
![Alt text](/screenshots/empty_data.png?raw=true "Empty data")
![Alt text](/screenshots/success.png?raw=true "Success data")

### Checklist
1. Programming language: Kotlin
2. Design app's architecture: MVVM and lightweight Clean architecture
3. Apply LiveData
4. UI should be looks like in attachment.
5. Exception handling
6. Caching handling: Using caching retrofit(Can use database instance of caching)
7. Secure Android app from:
   7.1. Decompile APK: Using proguard
   7.2. Rooted device: Rooted checked(Not accept root device)
8. Accessibility for Disability Supports:
   8.1. Talkback: Use a screen reader.
   8.2. Scaling Text: Display size and font size: To change the size of items on your screen,
   adjust the display size or font size.-> using sp config for text view and wrap content for item recycleview
9. Readme file includes:
   9.1. Brief explanation for the software development principles, patterns & practices being
   applied
   9.2. Brief explanation for the code folder structure and the key Java/Kotlin libraries and
   frameworks being used
   9.3. All the required steps in order to get the application run on local computer
   Checklist of items the candidate has done
