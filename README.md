# Bilkent University
# CS 319 - Object-Oriented Software Engineering Course Project (Spring 2020)

## Terra Mystica

Software of this project has been developed by following the software engineering development activities:
- [x] Requirements Elicitation
- [x] Analysis
- [x] System Design
- [x] Object Design
- [x] Implementation
- [x] Testing

*I worked with a team of 6 and participated in all of these activities. In this repository, I present my own contribution for the implementation and testing of the software.*

## Deliverables

Here is a list of my own contributions to the deliverable work products of this software:

### Analysis Report

- UML Use Case Diagrams & Use Case Descriptions
- UML Activity Diagram and its Highlighted Versions
- Interactive Menu Structure Mock-ups

### Design Report

- Introduction
  - Purpose of the System
  - System Design Goals Criteria & Trade-offs
- High-Level Software Architecture
  - Subsystem Decomposition
  - Hardware/Software Mapping
  - Persistent Data Management
  - Access Control & Security
  - Global Control Flow
  - Boundary Conditions
    - Configuration
    - Start-up & Shut-down
    - Exception Handling
  - UML Package Diagram
  - UML Deployment Diagram
  - UML Component Diagram
- Subsystem Services

### Implementation

- Implementing a GameLogic model and a command-line GameController that lets users play the game from beginning to end. (~900 SLOC)
  Challenging features include:
  - Connected components graph algorithm to calculate area scoring.
  - Polymorphic terrain adjacency algorithm for given shipping value.
- Testing the GameLogic classes with automated JUnit tests with approximately 80% code coverage. (~400 SLOC)

## Build Instructions

### macOS

1. It requires Java JDK 11 and can be installed via brew:

```
brew install java11
```

2. Clone the repository:

```
git clone https://github.com/canmergenci/terra-mystica.git
```

3. Enter the directory of the repo:

```
cd terra-mystica
```

4. Remove the test files:

```
rm src/com/TerraMystica/GameLogic/Test*.java
```

5. Compile the source code:

```
javac -d ./build src/com/TerraMystica/GameLogic/*.java
```

6. Make a .jar file:

```
jar cvmf MANIFEST.MF TerraMystica.jar -C ./build /com
```

7. Execute .jar file and play the game:

```
java -jar TerraMystica.jar
```