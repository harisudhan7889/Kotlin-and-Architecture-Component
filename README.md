# *Kotlin*

## About project
This is just a tutorial project to learn about kotlin. Have used **The Movie Data Base(TMDB)** to get
to get list of movies available and diplay it in the UI. When user click a movie , its detail 
will be displayed in the next screen.

[Click to see TMDB Api documentation](https://www.themoviedb.org/documentation/api)

Simple Usage of kotlin in this project

### How a function/method defined in koltin?

### Function with scope and without return type
---
``` kotlin                                  
// Kotlin                                           // Java
private fun functionName(){                         private void functionName() {
}                                                   }    
```
### Function with return type
---
``` kotlin
// Kotlin                                           // Java    
fun functionName(): Int {                           int functionName() {
  return integer                                        return integer;
}                                                   }
```

### Function with parameter
---
``` kotlin
// Koltin                                           // Java
/**                                                 /**
* Here text is a parameter of type String.          * Here text is a parameter of type String.
*/                                                  */    
fun functionName(text: String): Int {               int functionName(String text) {
  return integer                                        return integer;
}                                                   }
```

### Overriden Function
---

```kotlin
// Kotlin                                          
override fun getItemCount(): Int {                 
      return movies.size                              
}                                                    
```           

```java
// Java
class MyClass extends BaseClass {
 @override
 int getItemCount() { 
     return movies.size();                                                       
}
}
```

[Click here for sample used](../KotlinTutorial/app/src/main/java/com/hari/kotlintutorial/adapter/MovieListAdapter.kt)

### Class file declaration

```kotlin
// Kotlin                                                                   
class MoviesListFragment : Fragment(), ListMovies.View {

}
```
```java
// Java
class MovieListFragment extends Fragment implements ListMovies.View {
    
}
```

Note: In above code snippet you can see the difference that no extends or implements keywords are used,
you can directly use comma separation if you want to extend or implement.

### Basic kotlin's concepts covered in this project are the following

* Data class
* lateinit
* lazy
* Extension function
* Destructuring declaration
* apply
* with
* Different for loop types
* How switch in kotlin handled?
* Smart type casting
* Safe type casting
* How to handle null pointer exception?
* View extension
* Companion object
* Elvis operator
* Filter operator in list
* Lambda expression

### Architecture Component in kotlin

* Room Database
* LiveData
* ViewModel