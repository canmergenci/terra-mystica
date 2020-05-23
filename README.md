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

## Delivarables

Here is a list of my own contributions to the delivarable work products of this software:

### Analysis Report

- Use Case Diagrams & Use Case Descriptions
- Activity Diagrams and its Highlighted Versions
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
  - Package Diagram
  - Deployment Diagram
  - Component Diagram
- Subsystem Services

### Implementation

- Implementing a GameLogic model and a command-line GameController that lets users play the game from beginning to end. (~900 SLOC)
  Challenging features include:
  - Connected components graph algorithm to calculate Area Scoring.
  - Polymorphic terrain adjacency algorithm for given shipping value.
- Testing the GameLogic classes with automated JUnit tests with approximately 80% code coverage. (~400 SLOC)