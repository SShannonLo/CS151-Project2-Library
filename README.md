# CS151-Project2-Library

## Overview
This is a Java-based Library Management System developed for CS151 Spring 2026.
The system simulates a real-world library where members can borrow books, staff
can manage the collection, and the system tracks loans and fines automatically.

## Design
The system is built around 6 core classes:

- **Book** - TBD
- **Catalog** - TBD
- **Loan** - represents a borrowing transaction between a member and a book. Handles fines and due dates.
- **Member** - represents library members that can have library accounts and track their library activity.
- **LibraryStaff** - represents a library employee. Manages member accounts, books, and library operations.
- **Library** - represents the library itself. It has operating hours, location, and tracks members and books.

The system also includes:
- **LibraryUser** (Abstract Class) - shared base class for Member and LibraryStaff
- **Borrowable** (Interface) - implemented by Book to enforce checkout/return behavior

## Installation Instructions
On your machine in the terminal window do the following (we assume you installed git):
mkdir NewDirForProjectGrading
cd NewDirForProjectGrading
git clone https://github.com/angelinary/CS151-Project2-Library

Open the folder NewDirForProjectGrading in your IDE of choosing.

Execute the files from Main. There is only one package.
Use 018134168 for user ID, and 00 for PIN. 

## Usage
TBD

## Contributions
| Team Member            | Classes                            | Responsibilities                   |
|_________________________________________________________________________________________________

| Yanin Arevalos         | LibraryStaff, Loan                 | Staff management, member accounts, book management, loan tracking, fines |

|  Angelina Ryabechenkova | LibraryUser, Member, Library, Main| Create Library, create member menu, manage members, sorting and book display, login tracking |

| Keyi Tan | TBD | TBD |

| Cagla Sah | TBD | TBD |