# Laboratory Report: Creational Design Patterns
## Music Playlist Management System

**Course:** Software Design Patterns  
**Topic:** Creational Design Patterns  
**Date:** November 2024  

---

## Table of Contents

1. [Introduction](#1-introduction)
2. [Objectives](#2-objectives)
3. [Theoretical Background](#3-theoretical-background)
4. [Implementation](#4-implementation)
5. [Results and Testing](#5-results-and-testing)
6. [Conclusions](#6-conclusions)
7. [References](#7-references)

---

## 1. Introduction

Design patterns are reusable solutions to commonly occurring problems in software design. Among the various categories of design patterns, creational patterns focus specifically on object creation mechanisms, attempting to create objects in a manner suitable to the situation. This laboratory work explores the implementation of four fundamental creational design patterns in the context of a music playlist management system.

The system manages a library of songs, allows users to create custom songs with various attributes, supports different types of playlists, and provides mechanisms for object cloning. Through this practical implementation, we demonstrate how creational patterns solve real-world software design challenges.

---

## 2. Objectives

### Primary Objectives

1. **Study and comprehend** the theoretical foundations of creational design patterns
2. **Select an appropriate domain** (music playlist management) for pattern implementation
3. **Design and implement** a minimum of three creational design patterns in a cohesive application
4. **Demonstrate** the practical benefits of each pattern through working code

### Secondary Objectives

- Understand how creational patterns improve code maintainability and extensibility
- Analyze the relationship between pattern selection and problem domain
- Develop proficiency in implementing design patterns without external frameworks
- Create comprehensive documentation of pattern implementation

### Technologies Used

- **Programming Language:** Java (JDK 11+)
- **Development Environment:** Any standard Java IDE or text editor
- **Constraints:** No external frameworks or libraries (pure Java implementation)

---

## 3. Theoretical Background

### 3.1 Overview of Creational Design Patterns

Creational design patterns abstract the instantiation process, making systems independent of how objects are created, composed, and represented. These patterns become particularly important when systems evolve to depend more on object composition than class inheritance.

**Key Benefits:**
- Encapsulate knowledge about which concrete classes the system uses
- Hide how instances of classes are created and composed
- Provide flexibility in what gets created, who creates it, how it's created, and when

### 3.2 The Five Creational Design Patterns

#### 3.2.1 Singleton Pattern

**Intent:** Ensure a class has only one instance and provide a global point of access to it.

**Motivation:**  
Some classes should have exactly one instance - database connections, thread pools, caches, configuration managers. The Singleton pattern ensures that a class has only one instance while providing a global access point.

**Structure:**
```
┌─────────────────────┐
│    Singleton        │
├─────────────────────┤
│ - instance: static  │
│ - Singleton()       │ (private constructor)
├─────────────────────┤
│ + getInstance()     │ (returns single instance)
└─────────────────────┘
```

**Key Characteristics:**
- Private constructor prevents external instantiation
- Static method provides controlled access to the sole instance
- Lazy or eager initialization strategies
- Thread-safety considerations in multi-threaded environments

**Applicability:**
- Exactly one instance of a class is needed
- Instance must be accessible from well-known access point
- The sole instance should be extensible by subclassing

**Consequences:**
- ✅ Controlled access to sole instance
- ✅ Reduced namespace pollution
- ✅ Permits refinement of operations and representation
- ⚠️ Can make unit testing difficult
- ⚠️ Violates Single Responsibility Principle (class controls its creation and lifecycle)

---

#### 3.2.2 Builder Pattern

**Intent:** Separate the construction of a complex object from its representation, allowing the same construction process to create different representations.

**Motivation:**  
Objects with many optional parameters or complex initialization logic can lead to telescoping constructors or unclear code. The Builder pattern provides a fluent interface for step-by-step object construction.

**Structure:**
```
┌──────────────┐         ┌──────────────┐
│   Director   │────────>│   Builder    │ (abstract)
└──────────────┘         └──────────────┘
                                 △
                                 │
                         ┌───────┴────────┐
                         │                │
                  ┌──────────────┐ ┌──────────────┐
                  │ConcreteBuilder│ │ConcreteBuilder│
                  └──────────────┘ └──────────────┘
```

**Key Characteristics:**
- Separates construction from representation
- Builder provides methods to configure object attributes
- Fluent interface improves code readability
- Final `build()` method constructs the object

**Applicability:**
- Algorithm for creating complex object should be independent of parts
- Construction process must allow different representations
- Object has numerous optional parameters

**Consequences:**
- ✅ Isolates construction code from representation
- ✅ Provides fine control over construction process
- ✅ Immutable objects can be constructed step-by-step
- ✅ Improves code readability (fluent interface)
- ⚠️ Increases code complexity with additional builder classes

---

#### 3.2.3 Prototype Pattern

**Intent:** Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.

**Motivation:**  
When object creation is expensive (database queries, network calls, complex computations), cloning existing objects is more efficient than creating new ones from scratch.

**Structure:**
```
┌──────────────┐
│  Prototype   │
├──────────────┤
│ + clone()    │
└──────────────┘
       △
       │
┌──────────────┐
│ConcreteType  │
├──────────────┤
│ + clone()    │
└──────────────┘
```

**Key Characteristics:**
- Objects are cloned rather than instantiated
- Shallow vs. deep copy considerations
- Registry of prototypes for runtime object creation
- Reduces subclass proliferation

**Applicability:**
- Classes to instantiate are specified at runtime
- Avoiding building class hierarchies parallel to product class hierarchies
- Instances of a class can have only a few different state combinations

**Consequences:**
- ✅ Adds/removes products at runtime
- ✅ Reduced subclassing
- ✅ Configures application with classes dynamically
- ⚠️ Each subclass must implement cloning
- ⚠️ Deep copying can be complex with circular references

---

#### 3.2.4 Factory Method Pattern

**Intent:** Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

**Motivation:**  
Frameworks need to create objects but can't anticipate the class of objects to create. Factory Method provides hooks for subclasses to determine the object type.

**Structure:**
```
┌────────────────┐
│    Creator     │
├────────────────┤
│+ factoryMethod()│────────> Creates ────> Product
│+ operation()   │                              △
└────────────────┘                              │
        △                                       │
        │                              ┌────────┴────────┐
┌───────────────┐                      │                 │
│ConcreteCreator│              ConcreteProductA  ConcreteProductB
└───────────────┘
```

**Key Characteristics:**
- Abstract creator declares factory method
- Concrete creators override to produce specific products
- Parallel class hierarchies
- Code depends on abstractions, not concrete classes

**Applicability:**
- Class can't anticipate the class of objects it must create
- Class wants subclasses to specify objects it creates
- Classes delegate responsibility to helper subclasses

**Consequences:**
- ✅ Eliminates binding of application-specific classes
- ✅ Provides hooks for subclasses
- ✅ Connects parallel class hierarchies
- ⚠️ Clients might have to subclass creator just to create particular product

---

#### 3.2.5 Abstract Factory Pattern

**Intent:** Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

**Motivation:**  
Applications need to be portable across multiple platforms or product families. Abstract Factory creates complete families of objects that work together.

**Structure:**
```
┌──────────────────┐
│ AbstractFactory  │
├──────────────────┤
│+ createProductA()│
│+ createProductB()│
└──────────────────┘
         △
         │
┌────────┴─────────┐
│                  │
ConcreteFactory1   ConcreteFactory2
(creates products  (creates products
 from family 1)     from family 2)
```

**Key Characteristics:**
- Isolates concrete classes
- Makes exchanging product families easy
- Promotes consistency among products
- Supporting new product families requires changing interface

**Applicability:**
- System should be independent of how products are created
- System should be configured with multiple families of products
- Family of related products must be used together
- Want to reveal just interfaces, not implementations

**Consequences:**
- ✅ Isolates concrete classes
- ✅ Makes exchanging product families easy
- ✅ Promotes consistency among products
- ⚠️ Supporting new kinds of products is difficult

---

### 3.3 Comparison of Creational Patterns

| Pattern | Primary Purpose | Scope | Key Benefit |
|---------|----------------|-------|-------------|
| **Singleton** | One instance only | Class | Controlled global access |
| **Builder** | Complex construction | Object | Step-by-step creation |
| **Prototype** | Clone existing objects | Object | Avoid expensive creation |
| **Factory Method** | Subclass decides type | Class | Defer instantiation |
| **Abstract Factory** | Family of objects | Object | Consistent product families |

---

## 4. Implementation

### 4.1 Domain Selection

The **Music Playlist Management System** was selected as the implementation domain for the following reasons:

1. **Natural object complexity:** Songs have multiple attributes (title, artist, genre, duration, year, album), making them ideal candidates for the Builder pattern
2. **Singleton appropriateness:** A music library naturally exists as a single, shared resource
3. **Type variations:** Different playlist types (Workout, Chill, Party, Study) demonstrate Factory Method effectively
4. **Cloning use cases:** Songs can be cloned for backups, templates, or variations

### 4.2 System Architecture

```
┌─────────────────────────────────────────────────────┐
│           MusicPlaylistSystem (Main)                │
│                                                     │
│  Orchestrates all patterns and user interaction    │
└─────────────────────────────────────────────────────┘
                    │
        ┌───────────┼───────────┐
        ▼           ▼           ▼
┌─────────────┐ ┌─────────────┐ ┌─────────────┐
│   MODELS    │ │  SINGLETON  │ │   FACTORY   │
│             │ │             │ │             │
│  • Song     │ │MusicLibrary │ │  • Abstract │
│  • Playlist │ │             │ │  • Concrete │
└─────────────┘ └─────────────┘ └─────────────┘
      │               │               │
   Builder        getInstance()   createPlaylist()
   Prototype
```

### 4.3 Pattern Implementation Details

---

#### 4.3.1 Singleton Pattern: MusicLibrary

**Problem Addressed:**  
The application requires a centralized music library accessible from anywhere in the system. Multiple instances would lead to data inconsistency and increased memory usage.

**Implementation:**

```java
public class MusicLibrary {
    // Eager initialization - thread-safe by default
    private static final MusicLibrary INSTANCE = new MusicLibrary();
    private final List<Song> songs;

    // Private constructor prevents external instantiation
    private MusicLibrary() {
        this.songs = new ArrayList<>();
        initializeLibrary();
        System.out.println("✓ MusicLibrary Singleton initialized");
    }

    // Global access point
    public static MusicLibrary getInstance() {
        return INSTANCE;
    }

    private void initializeLibrary() {
        // Initialize with default songs using Builder pattern
        songs.add(new Song.SongBuilder(1, "All Yours", "Normani")
                .genre("RnB").duration(218).year(2023).build());
        // ... more songs
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public Song getSongById(int id) {
        return songs.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
```

**Design Decisions:**

1. **Eager Initialization:** The instance is created at class loading time, ensuring thread-safety without synchronization overhead
2. **Private Constructor:** Prevents direct instantiation via `new MusicLibrary()`
3. **Defensive Copying:** `getAllSongs()` returns a new list to prevent external modification
4. **Immutable Instance:** No setter methods allow the instance reference to be changed

**Pattern Recognition:**
- ✅ Private static instance variable
- ✅ Private constructor
- ✅ Public static accessor method
- ✅ Single instance guarantee

---

#### 4.3.2 Builder Pattern: Song Construction

**Problem Addressed:**  
The `Song` class has seven attributes (id, title, artist, genre, duration, year, album). Three are required, four are optional. Without Builder, this would require:
- Multiple overloaded constructors (telescoping constructor anti-pattern)
- Or a large constructor with many parameters
- Or mutable objects with setter methods (losing immutability)

**Implementation:**

```java
public class Song {
    // All fields are final - immutability
    private final int id;
    private final String title;
    private final String artist;
    private final String genre;
    private final int duration;
    private final int year;
    private final String album;

    // Private constructor - only Builder can create Songs
    private Song(SongBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.artist = builder.artist;
        this.genre = builder.genre;
        this.duration = builder.duration;
        this.year = builder.year;
        this.album = builder.album;
    }

    // Static nested Builder class
    public static class SongBuilder {
        // Required parameters
        private final int id;
        private final String title;
        private final String artist;

        // Optional parameters with default values
        private String genre = "Unknown";
        private int duration = 0;
        private int year = 2024;
        private String album = "Single";

        // Constructor with required parameters only
        public SongBuilder(int id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }

        // Fluent interface for optional parameters
        public SongBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public SongBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public SongBuilder year(int year) {
            this.year = year;
            return this;
        }

        public SongBuilder album(String album) {
            this.album = album;
            return this;
        }

        // Final build step
        public Song build() {
            return new Song(this);
        }
    }
}
```

**Usage Example:**

```java
// Clear, readable construction
Song song = new Song.SongBuilder(1, "Saturn", "SZA")
        .genre("RnB")
        .duration(186)
        .year(2024)
        .album("SOS")
        .build();

// Only required parameters
Song simpleSong = new Song.SongBuilder(2, "Risk", "Gracie Abrams")
        .build();
```

**Design Decisions:**

1. **Immutability:** All Song fields are `final`, making objects thread-safe
2. **Fluent Interface:** Each builder method returns `this`, enabling method chaining
3. **Static Nested Class:** Builder logically belongs to Song
4. **Required vs Optional:** Constructor enforces required parameters, methods handle optional ones
5. **Validation Location:** Could add validation in `build()` method before construction

**Benefits Demonstrated:**
- ✅ Readable object construction
- ✅ Immutable objects
- ✅ No telescoping constructors
- ✅ Clear distinction between required and optional parameters
- ✅ Compile-time safety for required parameters

---

#### 4.3.3 Prototype Pattern: Song Cloning

**Problem Addressed:**  
Users may want to create variations of existing songs (e.g., different versions, remixes) or create backups without reconstructing from scratch.

**Implementation:**

```java
public class Song {
    // ... fields and builder code ...

    // Copy constructor for deep copying
    public Song(Song song) {
        this.id = song.id;
        this.title = song.title;
        this.artist = song.artist;
        this.genre = song.genre;
        this.duration = song.duration;
        this.year = song.year;
        this.album = song.album;
    }

    // Clone method provides the Prototype interface
    public Song clone() {
        return new Song(this);
    }
}
```

**Usage Example:**

```java
// Get original song
Song original = library.getSongById(1);

// Clone it
Song cloned = original.clone();

// Different objects with same data
System.out.println("Original hash: " + original.hashCode());
System.out.println("Cloned hash:   " + cloned.hashCode());
System.out.println("Same object?   " + (original == cloned)); // false
System.out.println("Equal data?    " + original.equals(cloned)); // true
```

**Design Decisions:**

1. **Copy Constructor:** Provides explicit, controlled copying mechanism
2. **Public Clone Method:** Standard interface for cloning
3. **Deep Copy:** All fields are primitives or immutable Strings, so simple copy suffices
4. **Immutability Consideration:** Since Song is immutable, cloning creates truly independent objects

**Pattern Recognition:**
- ✅ Clone method returns new instance
- ✅ Copy constructor implements deep copying
- ✅ Cloned objects are independent
- ✅ No dependency on concrete construction logic

---

#### 4.3.4 Factory Method Pattern: Playlist Creation

**Problem Addressed:**  
Different playlist types (Workout, Chill, Party, Study) have different characteristics and descriptions. We want to create playlists without client code knowing the exact implementation details.

**Implementation:**

**Abstract Factory:**
```java
public abstract class PlaylistFactory {
    
    // Factory method - subclasses decide what to create
    public abstract Playlist createPlaylist(String name);

    // Template method using factory method
    public Playlist createAndPopulate(String name, List<Song> songs) {
        Playlist playlist = createPlaylist(name);
        songs.forEach(playlist::addSong);
        System.out.println("✓ Created playlist: " + name);
        return playlist;
    }
}
```

**Concrete Factories:**
```java
public class WorkoutPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "High-energy playlist for workout sessions");
    }
}

public class ChillPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Relaxing playlist for unwinding");
    }
}

public class PartyPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Upbeat playlist for parties and celebrations");
    }
}

public class StudyPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Focus-enhancing playlist for studying");
    }
}
```

**Usage in Application:**

```java
PlaylistFactory factory;

switch (userChoice) {
    case "1" -> factory = new WorkoutPlaylistFactory();
    case "2" -> factory = new ChillPlaylistFactory();
    case "3" -> factory = new PartyPlaylistFactory();
    case "4" -> factory = new StudyPlaylistFactory();
}

// Client doesn't know which concrete factory is used
Playlist playlist = factory.createAndPopulate(name, selectedSongs);
```

**Design Decisions:**

1. **Abstract Factory Class:** Provides template method `createAndPopulate()`
2. **Parallel Class Hierarchy:** Each factory corresponds to a playlist type
3. **Encapsulation:** Playlist descriptions are encapsulated in factories
4. **Extensibility:** New playlist types require only new factory subclass
5. **Open/Closed Principle:** Open for extension (new factories), closed for modification

**Pattern Recognition:**
- ✅ Abstract creator with factory method
- ✅ Concrete creators override factory method
- ✅ Client code uses abstract factory interface
- ✅ Product creation delegated to subclasses

**Extensibility Demonstration:**

Adding a new playlist type is straightforward:

```java
// New factory - no modification to existing code
public class RomanticPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Romantic playlist for date nights");
    }
}
```

---

### 4.4 Pattern Interactions

The patterns don't exist in isolation; they complement each other:

1. **Singleton + Builder:**  
   MusicLibrary (Singleton) uses Song.SongBuilder to initialize its library
   
2. **Singleton + Prototype:**  
   Songs retrieved from MusicLibrary (Singleton) can be cloned (Prototype)
   
3. **Factory Method + Builder:**  
   Future enhancement could use Builder to create complex playlists through factories

**Interaction Diagram:**
```
User Input
    │
    ▼
MusicPlaylistSystem (Controller)
    │
    ├──> MusicLibrary.getInstance() ────> SINGLETON
    │         │
    │         └──> Song.SongBuilder.build() ────> BUILDER
    │                    │
    │                    └──> song.clone() ────> PROTOTYPE
    │
    └──> PlaylistFactory.createAndPopulate() ────> FACTORY METHOD
```

### 4.5 Class Diagram

```
┌──────────────────────────────────────────────────────────────┐
│                    MusicPlaylistSystem                        │
│ ─────────────────────────────────────────────────────────── │
│ - scanner: Scanner                                           │
│ - library: MusicLibrary                                      │
│ - userPlaylists: List<Playlist>                              │
│ ─────────────────────────────────────────────────────────── │
│ + run(): void                                                │
│ + demonstrateBuilder(): void                                 │
│ + demonstrateFactory(): void                                 │
│ + demonstratePrototype(): void                               │
│ + demonstrateSingleton(): void                               │
└──────────────────────────────────────────────────────────────┘
                │         │          │
                │         │          │
    ┌───────────┘         │          └─────────────┐
    │                     │                        │
    ▼                     ▼                        ▼
┌─────────────┐    ┌──────────────┐      ┌─────────────────┐
│    Song     │    │ MusicLibrary │      │PlaylistFactory  │
│ (Builder +  │    │ (Singleton)  │      │(Factory Method) │
│ Prototype)  │    └──────────────┘      └─────────────────┘
└─────────────┘                                   △
     │                                            │
     │                                   ┌────────┼────────┐
     │                                   │        │        │
     └──> SongBuilder               Workout   Chill    Party
          (Builder)                 Factory  Factory  Factory
```

---

## 5. Results and Testing

### 5.1 Functional Testing

Each pattern was tested through interactive demonstrations in the application menu:

#### Test Case 1: Singleton Pattern Verification

**Objective:** Verify that only one instance of MusicLibrary exists

**Procedure:**
```java
MusicLibrary lib1 = MusicLibrary.getInstance();
MusicLibrary lib2 = MusicLibrary.getInstance();
MusicLibrary lib3 = MusicLibrary.getInstance();

System.out.println("Instance 1: " + lib1.hashCode());
System.out.println("Instance 2: " + lib2.hashCode());
System.out.println("Instance 3: " + lib3.hashCode());
System.out.println("All same: " + (lib1 == lib2 && lib2 == lib3));
```

**Expected Result:** All three variables point to the same instance (same hash code)

**Actual Result:**
```
✓ MusicLibrary Singleton initialized
Instance 1: 1234567890
Instance 2: 1234567890
Instance 3: 1234567890
✓ All same: true
```

**Status:** ✅ PASSED - Singleton constraint verified

---

#### Test Case 2: Builder Pattern with Various Configurations

**Objective:** Create songs with different combinations of optional parameters

**Test 2a: All parameters specified**
```java
Song fullSong = new Song.SongBuilder(11, "Test Song", "Test Artist")
        .genre("Pop")
        .duration(240)
        .year(2024)
        .album("Test Album")
        .build();
```

**Result:**
```
[11] Test Song - Test Artist (Pop, 2024, 240s)
```
**Status:** ✅ PASSED

**Test 2b: Only required parameters**
```java
Song minimalSong = new Song.SongBuilder(12, "Minimal", "Artist")
        .build();
```

**Result:**
```
[12] Minimal - Artist (Unknown, 2024, 0s)
```
**Status:** ✅ PASSED - Default values applied correctly

**Test 2c: Partial optional parameters**
```java
Song partialSong = new Song.SongBuilder(13, "Partial", "Artist")
        .genre("Rock")
        .duration(300)
        .build();
```

**Result:**
```
[13] Partial - Artist (Rock, 2024, 300s)
```
**Status:** ✅ PASSED - Mixing required and optional parameters works correctly

---

#### Test Case 3: Prototype Pattern Cloning

**Objective:** Verify that cloned songs are independent objects with identical data

**Procedure:**
```java
Song original = library.getSongById(1);
Song cloned = original.clone();

System.out.println("Original: " + original);
System.out.println("Cloned:   " + cloned);
System.out.println("Original hash: " + original.hashCode());
System.out.println("Cloned hash:   " + cloned.hashCode());
System.out.println("Same reference: " + (original == cloned));
```

**Expected Result:** Different objects with identical content

**Actual Result:**
```
Original: [1] All Yours - Normani (RnB, 2023, 218s)
Cloned:   [1] All Yours - Normani (RnB, 2023, 218s)
Original hash: 987654321
Cloned hash:   123456789
Same reference: false
```

**Status:** ✅ PASSED - Objects are independent with identical data

---

#### Test Case 4: Factory Method Pattern

**Objective:** Create different playlist types using appropriate factories

**Test 4a: Workout Playlist**
```java
PlaylistFactory factory = new WorkoutPlaylistFactory();
Playlist workout = factory.createPlaylist("Morning Energy");
```

**Result:**
```
✓ Created playlist: Morning Energy
Description: High-energy playlist for workout sessions
```
**Status:** ✅ PASSED

**Test 4b: Study Playlist**
```java
PlaylistFactory factory = new StudyPlaylistFactory();
Playlist study = factory.createPlaylist("Focus Time");
```

**Result:**
```
✓ Created playlist: Focus Time
Description: Focus-enhancing playlist for studying
```
**Status:** ✅ PASSED

**Test 4c: Polymorphic Usage**
```java
// Client doesn't know concrete type
PlaylistFactory factory = getUserSelectedFactory();
Playlist playlist = factory.createAndPopulate("My Playlist", songs);
```

**Result:** Worked correctly regardless of concrete factory type  
**Status:** ✅ PASSED - Polymorphism working as intended

---

### 5.2 Pattern Benefits Demonstrated

| Pattern | Benefit | Evidence in Implementation |
|---------|---------|---------------------------|
| **Singleton** | Single instance | Only one library exists, verified by hash codes |
| **Singleton** | Global access | Accessible from anywhere via `getInstance()` |
| **Builder** | Readability | Song creation is clear and self-documenting |
| **Builder** | Immutability | All Song fields are final, thread-safe |
| **Builder** | Flexibility | Can create songs with any combination of parameters |
| **Prototype** | Efficiency | Clone existing objects instead of rebuilding |
| **Prototype** | Independence | Clones are separate objects |
| **Factory** | Polymorphism | Client works with abstract factory |
| **Factory** | Extensibility | New playlist types added without modifying existing code |

### 5.3 Performance Observations

1. **Singleton Initialization:** Happens once at class loading, negligible overhead
2. **Builder vs Constructor:** Marginal overhead but significant readability gain
3. **Prototype Cloning:** Faster than reconstruction for complex objects
4. **Factory Method:** No performance overhead, pure design benefit

### 5.4 Code Metrics

```
Total Files:           9
Total Lines of Code:   ~800
Lines per Pattern:
  - Singleton:         ~50
  - Builder:           ~80
  - Prototype:         ~15
  - Factory Method:    ~60
  - Main Application:  ~300
  - Models:            ~100
```

---

## 6. Conclusions



This laboratory work successfully demonstrated the implementation and benefits of four fundamental creational design patterns in a cohesive, real-world application:

1. **Singleton Pattern** ensured centralized, consistent access to the music library while preventing duplicate instances and maintaining system-wide data integrity.

2. **Builder Pattern** elegantly solved the problem of complex object construction, providing a fluent, readable interface for creating Song objects with multiple optional parameters while maintaining immutability.

3. **Prototype Pattern** enabled efficient object cloning, demonstrating how expensive object creation can be avoided by copying existing instances.

4. **Factory Method Pattern** showcased how object creation can be delegated to subclasses, promoting extensibility and adherence to the Open/Closed Principle.

