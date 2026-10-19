##QuickChat

#Overview

QuickChat is a Java-based console messaging application developed in Apache NetBeans. The application allows users to register and log in before accessing the QuickChat messaging system.

The project demonstrates basic Java programming concepts such as classes and objects, methods, conditional statements, loops, input validation, switch statements, and message processing.

#Features

User registration
Username validation
Password complexity validation
South African cellphone number validation
User login
QuickChat messaging menu
Sending messages
Disregarding messages
Storing messages
Message ID generation
Message hash generation
Message length validation
Recipient validation
Recently sent message information
Program Flow

#The application follows this general flow:

The user starts the application.
The user registers by entering their personal details.
The system validates the username, password, and cellphone number.
The user logs in using their registered username and password.
After successful login, the QuickChat menu is displayed.
The user can choose to:

Send a message
View recently sent messages
Quit the application
When sending a message, the system validates the recipient and message length.
A unique message ID and message hash are generated.
The user can choose to send, disregard, or store the message.
The application displays the result of the selected action.
Technologies Used

Java
Apache NetBeans
Java Scanner
Object-Oriented Programming (OOP)
GitHub
Project Structure

#QuickChat

│

├── src

│   └── chatapp

│       ├── Main.java

│       ├── Login.java

│       └── Message.java

│

└── README.md

Main Classes

Main.java

The Main class controls the main flow of the application. It handles user input, registration, login, and the QuickChat menu.

Login.java

The Login class is responsible for user registration and login validation. It checks the username, password, and cellphone number before allowing the user to continue.

Message.java

The Message class handles the creation and processing of messages. It validates message information, generates a message ID and hash, and allows the user to send, disregard, or store a message.

Validation

The application includes several validation rules.

Username

The username must:

Contain an underscore (_)
Be no more than five characters long
Password

The password must:

Contain at least eight characters
Contain a capital letter
Contain a number
Contain a special character
Cellphone Number

The cellphone number is checked to ensure that it follows the required South African international format.

Message

Messages are checked to ensure that they do not exceed the required character limit.

#How to Run the Project

Download or clone this repository.
Open Apache NetBeans.
Select File → Open Project.
Select the QuickChat project.
Build the project.
Run Main.java.
Follow the instructions displayed in the console.
Example

Welcome to QuickChat



1) Send Messages

2) Show recently sent messages

3) Quit



Enter your choice:

The user can select an option and follow the prompts displayed by the application.

Learning Outcomes

This project demonstrates my understanding of:

Java programming fundamentals
Object-oriented programming
Classes and objects
Methods
Conditional statements
Loops
Switch statements
Input validation
String manipulation
Message processing
Basic application flow and logic
#Part 2 Demonstration

A video demonstration of the QuickChat application is provided as part of the Part 2 submission. The demonstration shows the source code, explains the program logic and flow, and demonstrates the application running successfully.
