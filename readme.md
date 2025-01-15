#

## how to do a cpg analyzer

### First install SDKMAN!
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Then install Gradle
sdk install gradle


brew install gradle

### 3. Verify installation

`gradle --version`

## how to install

Found existing files in the project directory: '/Users/kimsia/Projects/django-cpg-analyzer'.
Directory will be modified and existing files may be overwritten.  Continue? (default: no) [yes, no] yes

Select type of build to generate:
  1: Application
  2: Library
  3: Gradle plugin
  4: Basic (build structure only)
Enter selection (default: Application) [1..4] 1

Select implementation language:
  1: Java
  2: Kotlin
  3: Groovy
  4: Scala
  5: C++
  6: Swift
Enter selection (default: Java) [1..6] 2

Enter target Java version (min: 7, default: 21):

Project name (default: django-cpg-analyzer):

Select application structure:
  1: Single application project
  2: Application and library project
Enter selection (default: Single application project) [1..2] ^C%
> gradle init

Found existing files in the project directory: '/Users/kimsia/Projects/django-cpg-analyzer'.
Directory will be modified and existing files may be overwritten.  Continue? (default: no) [yes, no] yes

Select type of build to generate:
  1: Application
  2: Library
  3: Gradle plugin
  4: Basic (build structure only)
Enter selection (default: Application) [1..4] 1

Select implementation language:
  1: Java
  2: Kotlin
  3: Groovy
  4: Scala
  5: C++
  6: Swift
Enter selection (default: Java) [1..6] 2

Enter target Java version (min: 7, default: 21): 11

Project name (default: django-cpg-analyzer):

Select application structure:
  1: Single application project
  2: Application and library project
Enter selection (default: Single application project) [1..2] 1

Select build script DSL:
  1: Kotlin
  2: Groovy
Enter selection (default: Kotlin) [1..2] 1

Select test framework:
  1: kotlin.test
  2: JUnit Jupiter
Enter selection (default: kotlin.test) [1..2] 2

Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no]


> Task :init
Learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.12/samples/sample_building_kotlin_applications.html